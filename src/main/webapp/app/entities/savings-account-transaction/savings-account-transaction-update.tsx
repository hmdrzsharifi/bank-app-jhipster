import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ISavingsAccount } from 'app/shared/model/savings-account.model';
import { getEntities as getSavingsAccounts } from 'app/entities/savings-account/savings-account.reducer';
import { getEntity, updateEntity, createEntity, reset } from './savings-account-transaction.reducer';
import { ISavingsAccountTransaction } from 'app/shared/model/savings-account-transaction.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISavingsAccountTransactionUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SavingsAccountTransactionUpdate = (props: ISavingsAccountTransactionUpdateProps) => {
  const [savingsAccountId, setSavingsAccountId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { savingsAccountTransactionEntity, savingsAccounts, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/savings-account-transaction');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getSavingsAccounts();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.dateOf = convertDateTimeToServer(values.dateOf);
    values.balanceEndDate = convertDateTimeToServer(values.balanceEndDate);
    values.createdDate = convertDateTimeToServer(values.createdDate);

    if (errors.length === 0) {
      const entity = {
        ...savingsAccountTransactionEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="bankApp.savingsAccountTransaction.home.createOrEditLabel">
            <Translate contentKey="bankApp.savingsAccountTransaction.home.createOrEditLabel">
              Create or edit a SavingsAccountTransaction
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : savingsAccountTransactionEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="savings-account-transaction-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="savings-account-transaction-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="transactionTypeLabel" for="savings-account-transaction-transactionType">
                  <Translate contentKey="bankApp.savingsAccountTransaction.transactionType">Transaction Type</Translate>
                </Label>
                <AvField id="savings-account-transaction-transactionType" type="string" className="form-control" name="transactionType" />
              </AvGroup>
              <AvGroup check>
                <Label id="reversedLabel">
                  <AvInput id="savings-account-transaction-reversed" type="checkbox" className="form-check-input" name="reversed" />
                  <Translate contentKey="bankApp.savingsAccountTransaction.reversed">Reversed</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="dateOfLabel" for="savings-account-transaction-dateOf">
                  <Translate contentKey="bankApp.savingsAccountTransaction.dateOf">Date Of</Translate>
                </Label>
                <AvInput
                  id="savings-account-transaction-dateOf"
                  type="datetime-local"
                  className="form-control"
                  name="dateOf"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.savingsAccountTransactionEntity.dateOf)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="amountLabel" for="savings-account-transaction-amount">
                  <Translate contentKey="bankApp.savingsAccountTransaction.amount">Amount</Translate>
                </Label>
                <AvField id="savings-account-transaction-amount" type="text" name="amount" />
              </AvGroup>
              <AvGroup>
                <Label id="overdraftAmountLabel" for="savings-account-transaction-overdraftAmount">
                  <Translate contentKey="bankApp.savingsAccountTransaction.overdraftAmount">Overdraft Amount</Translate>
                </Label>
                <AvField id="savings-account-transaction-overdraftAmount" type="text" name="overdraftAmount" />
              </AvGroup>
              <AvGroup>
                <Label id="balanceEndDateLabel" for="savings-account-transaction-balanceEndDate">
                  <Translate contentKey="bankApp.savingsAccountTransaction.balanceEndDate">Balance End Date</Translate>
                </Label>
                <AvInput
                  id="savings-account-transaction-balanceEndDate"
                  type="datetime-local"
                  className="form-control"
                  name="balanceEndDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.savingsAccountTransactionEntity.balanceEndDate)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="balanceNumberOfDaysLabel" for="savings-account-transaction-balanceNumberOfDays">
                  <Translate contentKey="bankApp.savingsAccountTransaction.balanceNumberOfDays">Balance Number Of Days</Translate>
                </Label>
                <AvField
                  id="savings-account-transaction-balanceNumberOfDays"
                  type="string"
                  className="form-control"
                  name="balanceNumberOfDays"
                />
              </AvGroup>
              <AvGroup>
                <Label id="runningBalanceLabel" for="savings-account-transaction-runningBalance">
                  <Translate contentKey="bankApp.savingsAccountTransaction.runningBalance">Running Balance</Translate>
                </Label>
                <AvField id="savings-account-transaction-runningBalance" type="text" name="runningBalance" />
              </AvGroup>
              <AvGroup>
                <Label id="cumulativeBalanceLabel" for="savings-account-transaction-cumulativeBalance">
                  <Translate contentKey="bankApp.savingsAccountTransaction.cumulativeBalance">Cumulative Balance</Translate>
                </Label>
                <AvField id="savings-account-transaction-cumulativeBalance" type="text" name="cumulativeBalance" />
              </AvGroup>
              <AvGroup>
                <Label id="createdDateLabel" for="savings-account-transaction-createdDate">
                  <Translate contentKey="bankApp.savingsAccountTransaction.createdDate">Created Date</Translate>
                </Label>
                <AvInput
                  id="savings-account-transaction-createdDate"
                  type="datetime-local"
                  className="form-control"
                  name="createdDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.savingsAccountTransactionEntity.createdDate)}
                />
              </AvGroup>
              <AvGroup check>
                <Label id="isManualTransactionLabel">
                  <AvInput
                    id="savings-account-transaction-isManualTransaction"
                    type="checkbox"
                    className="form-check-input"
                    name="isManualTransaction"
                  />
                  <Translate contentKey="bankApp.savingsAccountTransaction.isManualTransaction">Is Manual Transaction</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="releaseIdOfHoldAmountTransactionLabel" for="savings-account-transaction-releaseIdOfHoldAmountTransaction">
                  <Translate contentKey="bankApp.savingsAccountTransaction.releaseIdOfHoldAmountTransaction">
                    Release Id Of Hold Amount Transaction
                  </Translate>
                </Label>
                <AvField
                  id="savings-account-transaction-releaseIdOfHoldAmountTransaction"
                  type="string"
                  className="form-control"
                  name="releaseIdOfHoldAmountTransaction"
                />
              </AvGroup>
              <AvGroup>
                <Label for="savings-account-transaction-savingsAccount">
                  <Translate contentKey="bankApp.savingsAccountTransaction.savingsAccount">Savings Account</Translate>
                </Label>
                <AvInput id="savings-account-transaction-savingsAccount" type="select" className="form-control" name="savingsAccountId">
                  <option value="" key="0" />
                  {savingsAccounts
                    ? savingsAccounts.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/savings-account-transaction" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  savingsAccounts: storeState.savingsAccount.entities,
  savingsAccountTransactionEntity: storeState.savingsAccountTransaction.entity,
  loading: storeState.savingsAccountTransaction.loading,
  updating: storeState.savingsAccountTransaction.updating,
  updateSuccess: storeState.savingsAccountTransaction.updateSuccess,
});

const mapDispatchToProps = {
  getSavingsAccounts,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SavingsAccountTransactionUpdate);
