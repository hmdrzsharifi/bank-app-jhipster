import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './savings-account-transaction.reducer';
import { ISavingsAccountTransaction } from 'app/shared/model/savings-account-transaction.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISavingsAccountTransactionProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const SavingsAccountTransaction = (props: ISavingsAccountTransactionProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { savingsAccountTransactionList, match, loading } = props;
  return (
    <div>
      <h2 id="savings-account-transaction-heading">
        <Translate contentKey="bankApp.savingsAccountTransaction.home.title">Savings Account Transactions</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="bankApp.savingsAccountTransaction.home.createLabel">Create new Savings Account Transaction</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {savingsAccountTransactionList && savingsAccountTransactionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccountTransaction.transactionType">Transaction Type</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccountTransaction.reversed">Reversed</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccountTransaction.dateOf">Date Of</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccountTransaction.amount">Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccountTransaction.overdraftAmount">Overdraft Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccountTransaction.balanceEndDate">Balance End Date</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccountTransaction.balanceNumberOfDays">Balance Number Of Days</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccountTransaction.runningBalance">Running Balance</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccountTransaction.cumulativeBalance">Cumulative Balance</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccountTransaction.createdDate">Created Date</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccountTransaction.isManualTransaction">Is Manual Transaction</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccountTransaction.releaseIdOfHoldAmountTransaction">
                    Release Id Of Hold Amount Transaction
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.savingsAccountTransaction.savingsAccount">Savings Account</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {savingsAccountTransactionList.map((savingsAccountTransaction, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${savingsAccountTransaction.id}`} color="link" size="sm">
                      {savingsAccountTransaction.id}
                    </Button>
                  </td>
                  <td>{savingsAccountTransaction.transactionType}</td>
                  <td>{savingsAccountTransaction.reversed ? 'true' : 'false'}</td>
                  <td>
                    {savingsAccountTransaction.dateOf ? (
                      <TextFormat type="date" value={savingsAccountTransaction.dateOf} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{savingsAccountTransaction.amount}</td>
                  <td>{savingsAccountTransaction.overdraftAmount}</td>
                  <td>
                    {savingsAccountTransaction.balanceEndDate ? (
                      <TextFormat type="date" value={savingsAccountTransaction.balanceEndDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{savingsAccountTransaction.balanceNumberOfDays}</td>
                  <td>{savingsAccountTransaction.runningBalance}</td>
                  <td>{savingsAccountTransaction.cumulativeBalance}</td>
                  <td>
                    {savingsAccountTransaction.createdDate ? (
                      <TextFormat type="date" value={savingsAccountTransaction.createdDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{savingsAccountTransaction.isManualTransaction ? 'true' : 'false'}</td>
                  <td>{savingsAccountTransaction.releaseIdOfHoldAmountTransaction}</td>
                  <td>
                    {savingsAccountTransaction.savingsAccountId ? (
                      <Link to={`savings-account/${savingsAccountTransaction.savingsAccountId}`}>
                        {savingsAccountTransaction.savingsAccountId}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${savingsAccountTransaction.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${savingsAccountTransaction.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${savingsAccountTransaction.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="bankApp.savingsAccountTransaction.home.notFound">No Savings Account Transactions found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ savingsAccountTransaction }: IRootState) => ({
  savingsAccountTransactionList: savingsAccountTransaction.entities,
  loading: savingsAccountTransaction.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SavingsAccountTransaction);
