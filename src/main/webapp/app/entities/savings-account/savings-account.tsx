import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './savings-account.reducer';
import { ISavingsAccount } from 'app/shared/model/savings-account.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISavingsAccountProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const SavingsAccount = (props: ISavingsAccountProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { savingsAccountList, match, loading } = props;
  return (
    <div>
      <h2 id="savings-account-heading">
        <Translate contentKey="bankApp.savingsAccount.home.title">Savings Accounts</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="bankApp.savingsAccount.home.createLabel">Create new Savings Account</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {savingsAccountList && savingsAccountList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.accountNumber">Account Number</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.externalId">External Id</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.subStatus">Sub Status</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.accountType">Account Type</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.submittedOnDate">Submitted On Date</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.rejectedOnDate">Rejected On Date</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.approvedOnDate">Approved On Date</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.nominalAnnualInterestRate">Nominal Annual Interest Rate</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.interestCompoundingPeriodType">Interest Compounding Period Type</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.interestPostingPeriodType">Interest Posting Period Type</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.interestCalculationType">Interest Calculation Type</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.interestCalculationDaysInYearType">
                    Interest Calculation Days In Year Type
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.minRequiredOpeningBalance">Min Required Opening Balance</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.lockinPeriodFrequency">Lockin Period Frequency</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.lockinPeriodFrequencyType">Lockin Period Frequency Type</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.nominalAnnualInterestRateOverdraft">
                    Nominal Annual Interest Rate Overdraft
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.minOverdraftForInterestCalculation">
                    Min Overdraft For Interest Calculation
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.minBalanceForInterestCalculation">
                    Min Balance For Interest Calculation
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.enforceMinRequiredBalance">Enforce Min Required Balance</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.minRequiredBalance">Min Required Balance</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.withdrawalFeeApplicableForTransfer">
                    Withdrawal Fee Applicable For Transfer
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.allowOverdraft">Allow Overdraft</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccount.overdraftLimit">Overdraft Limit</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {savingsAccountList.map((savingsAccount, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${savingsAccount.id}`} color="link" size="sm">
                      {savingsAccount.id}
                    </Button>
                  </td>
                  <td>{savingsAccount.accountNumber}</td>
                  <td>{savingsAccount.externalId}</td>
                  <td>{savingsAccount.status}</td>
                  <td>{savingsAccount.subStatus}</td>
                  <td>{savingsAccount.accountType}</td>
                  <td>
                    {savingsAccount.submittedOnDate ? (
                      <TextFormat type="date" value={savingsAccount.submittedOnDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {savingsAccount.rejectedOnDate ? (
                      <TextFormat type="date" value={savingsAccount.rejectedOnDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {savingsAccount.approvedOnDate ? (
                      <TextFormat type="date" value={savingsAccount.approvedOnDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{savingsAccount.nominalAnnualInterestRate}</td>
                  <td>{savingsAccount.interestCompoundingPeriodType}</td>
                  <td>{savingsAccount.interestPostingPeriodType}</td>
                  <td>{savingsAccount.interestCalculationType}</td>
                  <td>{savingsAccount.interestCalculationDaysInYearType}</td>
                  <td>{savingsAccount.minRequiredOpeningBalance}</td>
                  <td>{savingsAccount.lockinPeriodFrequency}</td>
                  <td>{savingsAccount.lockinPeriodFrequencyType}</td>
                  <td>{savingsAccount.nominalAnnualInterestRateOverdraft}</td>
                  <td>{savingsAccount.minOverdraftForInterestCalculation}</td>
                  <td>{savingsAccount.minBalanceForInterestCalculation}</td>
                  <td>{savingsAccount.enforceMinRequiredBalance ? 'true' : 'false'}</td>
                  <td>{savingsAccount.minRequiredBalance}</td>
                  <td>{savingsAccount.withdrawalFeeApplicableForTransfer ? 'true' : 'false'}</td>
                  <td>{savingsAccount.allowOverdraft ? 'true' : 'false'}</td>
                  <td>{savingsAccount.overdraftLimit}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${savingsAccount.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${savingsAccount.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${savingsAccount.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="bankApp.savingsAccount.home.notFound">No Savings Accounts found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ savingsAccount }: IRootState) => ({
  savingsAccountList: savingsAccount.entities,
  loading: savingsAccount.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SavingsAccount);
