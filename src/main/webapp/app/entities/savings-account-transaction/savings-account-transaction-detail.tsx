import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './savings-account-transaction.reducer';
import { ISavingsAccountTransaction } from 'app/shared/model/savings-account-transaction.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISavingsAccountTransactionDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SavingsAccountTransactionDetail = (props: ISavingsAccountTransactionDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { savingsAccountTransactionEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="bankApp.savingsAccountTransaction.detail.title">SavingsAccountTransaction</Translate> [
          <b>{savingsAccountTransactionEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="transactionType">
              <Translate contentKey="bankApp.savingsAccountTransaction.transactionType">Transaction Type</Translate>
            </span>
          </dt>
          <dd>{savingsAccountTransactionEntity.transactionType}</dd>
          <dt>
            <span id="reversed">
              <Translate contentKey="bankApp.savingsAccountTransaction.reversed">Reversed</Translate>
            </span>
          </dt>
          <dd>{savingsAccountTransactionEntity.reversed ? 'true' : 'false'}</dd>
          <dt>
            <span id="dateOf">
              <Translate contentKey="bankApp.savingsAccountTransaction.dateOf">Date Of</Translate>
            </span>
          </dt>
          <dd>
            {savingsAccountTransactionEntity.dateOf ? (
              <TextFormat value={savingsAccountTransactionEntity.dateOf} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="amount">
              <Translate contentKey="bankApp.savingsAccountTransaction.amount">Amount</Translate>
            </span>
          </dt>
          <dd>{savingsAccountTransactionEntity.amount}</dd>
          <dt>
            <span id="overdraftAmount">
              <Translate contentKey="bankApp.savingsAccountTransaction.overdraftAmount">Overdraft Amount</Translate>
            </span>
          </dt>
          <dd>{savingsAccountTransactionEntity.overdraftAmount}</dd>
          <dt>
            <span id="balanceEndDate">
              <Translate contentKey="bankApp.savingsAccountTransaction.balanceEndDate">Balance End Date</Translate>
            </span>
          </dt>
          <dd>
            {savingsAccountTransactionEntity.balanceEndDate ? (
              <TextFormat value={savingsAccountTransactionEntity.balanceEndDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="balanceNumberOfDays">
              <Translate contentKey="bankApp.savingsAccountTransaction.balanceNumberOfDays">Balance Number Of Days</Translate>
            </span>
          </dt>
          <dd>{savingsAccountTransactionEntity.balanceNumberOfDays}</dd>
          <dt>
            <span id="runningBalance">
              <Translate contentKey="bankApp.savingsAccountTransaction.runningBalance">Running Balance</Translate>
            </span>
          </dt>
          <dd>{savingsAccountTransactionEntity.runningBalance}</dd>
          <dt>
            <span id="cumulativeBalance">
              <Translate contentKey="bankApp.savingsAccountTransaction.cumulativeBalance">Cumulative Balance</Translate>
            </span>
          </dt>
          <dd>{savingsAccountTransactionEntity.cumulativeBalance}</dd>
          <dt>
            <span id="createdDate">
              <Translate contentKey="bankApp.savingsAccountTransaction.createdDate">Created Date</Translate>
            </span>
          </dt>
          <dd>
            {savingsAccountTransactionEntity.createdDate ? (
              <TextFormat value={savingsAccountTransactionEntity.createdDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="isManualTransaction">
              <Translate contentKey="bankApp.savingsAccountTransaction.isManualTransaction">Is Manual Transaction</Translate>
            </span>
          </dt>
          <dd>{savingsAccountTransactionEntity.isManualTransaction ? 'true' : 'false'}</dd>
          <dt>
            <span id="releaseIdOfHoldAmountTransaction">
              <Translate contentKey="bankApp.savingsAccountTransaction.releaseIdOfHoldAmountTransaction">
                Release Id Of Hold Amount Transaction
              </Translate>
            </span>
          </dt>
          <dd>{savingsAccountTransactionEntity.releaseIdOfHoldAmountTransaction}</dd>
          <dt>
            <Translate contentKey="bankApp.savingsAccountTransaction.savingsAccount">Savings Account</Translate>
          </dt>
          <dd>{savingsAccountTransactionEntity.savingsAccountId ? savingsAccountTransactionEntity.savingsAccountId : ''}</dd>
        </dl>
        <Button tag={Link} to="/savings-account-transaction" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/savings-account-transaction/${savingsAccountTransactionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ savingsAccountTransaction }: IRootState) => ({
  savingsAccountTransactionEntity: savingsAccountTransaction.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SavingsAccountTransactionDetail);
