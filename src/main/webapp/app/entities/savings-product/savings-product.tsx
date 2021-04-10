import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './savings-product.reducer';
import { ISavingsProduct } from 'app/shared/model/savings-product.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISavingsProductProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const SavingsProduct = (props: ISavingsProductProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { savingsProductList, match, loading } = props;
  return (
    <div>
      <h2 id="savings-product-heading">
        <Translate contentKey="bankApp.savingsProduct.home.title">Savings Products</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="bankApp.savingsProduct.home.createLabel">Create new Savings Product</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {savingsProductList && savingsProductList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.shortName">Short Name</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.nominalAnnualInterestRate">Nominal Annual Interest Rate</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.interestCompoundingPeriodType">Interest Compounding Period Type</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.interestPostingPeriodType">Interest Posting Period Type</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.interestCalculationType">Interest Calculation Type</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.interestCalculationDaysInYearType">
                    Interest Calculation Days In Year Type
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.minRequiredOpeningBalance">Min Required Opening Balance</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.lockinPeriodFrequency">Lockin Period Frequency</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.lockinPeriodFrequencyType">Lockin Period Frequency Type</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.accountingRule">Accounting Rule</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.withdrawalFeeApplicableForTransfer">
                    Withdrawal Fee Applicable For Transfer
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.allowOverdraft">Allow Overdraft</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.overdraftLimit">Overdraft Limit</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.nominalAnnualInterestRateOverdraft">
                    Nominal Annual Interest Rate Overdraft
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.minOverdraftForInterestCalculation">
                    Min Overdraft For Interest Calculation
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.enforceMinRequiredBalance">Enforce Min Required Balance</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.minRequiredBalance">Min Required Balance</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.minBalanceForInterestCalculation">
                    Min Balance For Interest Calculation
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.withHoldTax">With Hold Tax</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.isDormancyTrackingActive">Is Dormancy Tracking Active</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.daysToInactive">Days To Inactive</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.daysToDormancy">Days To Dormancy</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsProduct.daysToEscheat">Days To Escheat</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {savingsProductList.map((savingsProduct, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${savingsProduct.id}`} color="link" size="sm">
                      {savingsProduct.id}
                    </Button>
                  </td>
                  <td>{savingsProduct.name}</td>
                  <td>{savingsProduct.shortName}</td>
                  <td>{savingsProduct.description}</td>
                  <td>{savingsProduct.nominalAnnualInterestRate}</td>
                  <td>{savingsProduct.interestCompoundingPeriodType}</td>
                  <td>{savingsProduct.interestPostingPeriodType}</td>
                  <td>{savingsProduct.interestCalculationType}</td>
                  <td>{savingsProduct.interestCalculationDaysInYearType}</td>
                  <td>{savingsProduct.minRequiredOpeningBalance}</td>
                  <td>{savingsProduct.lockinPeriodFrequency}</td>
                  <td>{savingsProduct.lockinPeriodFrequencyType}</td>
                  <td>{savingsProduct.accountingRule}</td>
                  <td>{savingsProduct.withdrawalFeeApplicableForTransfer ? 'true' : 'false'}</td>
                  <td>{savingsProduct.allowOverdraft ? 'true' : 'false'}</td>
                  <td>{savingsProduct.overdraftLimit}</td>
                  <td>{savingsProduct.nominalAnnualInterestRateOverdraft}</td>
                  <td>{savingsProduct.minOverdraftForInterestCalculation}</td>
                  <td>{savingsProduct.enforceMinRequiredBalance ? 'true' : 'false'}</td>
                  <td>{savingsProduct.minRequiredBalance}</td>
                  <td>{savingsProduct.minBalanceForInterestCalculation}</td>
                  <td>{savingsProduct.withHoldTax ? 'true' : 'false'}</td>
                  <td>{savingsProduct.isDormancyTrackingActive ? 'true' : 'false'}</td>
                  <td>{savingsProduct.daysToInactive}</td>
                  <td>{savingsProduct.daysToDormancy}</td>
                  <td>{savingsProduct.daysToEscheat}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${savingsProduct.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${savingsProduct.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${savingsProduct.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="bankApp.savingsProduct.home.notFound">No Savings Products found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ savingsProduct }: IRootState) => ({
  savingsProductList: savingsProduct.entities,
  loading: savingsProduct.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SavingsProduct);
