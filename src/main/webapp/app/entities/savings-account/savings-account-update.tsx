import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './savings-account.reducer';
import { ISavingsAccount } from 'app/shared/model/savings-account.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISavingsAccountUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SavingsAccountUpdate = (props: ISavingsAccountUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { savingsAccountEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/savings-account');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.submittedOnDate = convertDateTimeToServer(values.submittedOnDate);
    values.rejectedOnDate = convertDateTimeToServer(values.rejectedOnDate);
    values.approvedOnDate = convertDateTimeToServer(values.approvedOnDate);

    if (errors.length === 0) {
      const entity = {
        ...savingsAccountEntity,
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
          <h2 id="bankApp.savingsAccount.home.createOrEditLabel">
            <Translate contentKey="bankApp.savingsAccount.home.createOrEditLabel">Create or edit a SavingsAccount</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : savingsAccountEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="savings-account-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="savings-account-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="accountNumberLabel" for="savings-account-accountNumber">
                  <Translate contentKey="bankApp.savingsAccount.accountNumber">Account Number</Translate>
                </Label>
                <AvField
                  id="savings-account-accountNumber"
                  type="text"
                  name="accountNumber"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 20, errorMessage: translate('entity.validation.maxlength', { max: 20 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="externalIdLabel" for="savings-account-externalId">
                  <Translate contentKey="bankApp.savingsAccount.externalId">External Id</Translate>
                </Label>
                <AvField id="savings-account-externalId" type="text" name="externalId" />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="savings-account-status">
                  <Translate contentKey="bankApp.savingsAccount.status">Status</Translate>
                </Label>
                <AvField id="savings-account-status" type="string" className="form-control" name="status" />
              </AvGroup>
              <AvGroup>
                <Label id="subStatusLabel" for="savings-account-subStatus">
                  <Translate contentKey="bankApp.savingsAccount.subStatus">Sub Status</Translate>
                </Label>
                <AvField id="savings-account-subStatus" type="string" className="form-control" name="subStatus" />
              </AvGroup>
              <AvGroup>
                <Label id="accountTypeLabel" for="savings-account-accountType">
                  <Translate contentKey="bankApp.savingsAccount.accountType">Account Type</Translate>
                </Label>
                <AvField id="savings-account-accountType" type="string" className="form-control" name="accountType" />
              </AvGroup>
              <AvGroup>
                <Label id="submittedOnDateLabel" for="savings-account-submittedOnDate">
                  <Translate contentKey="bankApp.savingsAccount.submittedOnDate">Submitted On Date</Translate>
                </Label>
                <AvInput
                  id="savings-account-submittedOnDate"
                  type="datetime-local"
                  className="form-control"
                  name="submittedOnDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.savingsAccountEntity.submittedOnDate)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="rejectedOnDateLabel" for="savings-account-rejectedOnDate">
                  <Translate contentKey="bankApp.savingsAccount.rejectedOnDate">Rejected On Date</Translate>
                </Label>
                <AvInput
                  id="savings-account-rejectedOnDate"
                  type="datetime-local"
                  className="form-control"
                  name="rejectedOnDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.savingsAccountEntity.rejectedOnDate)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="approvedOnDateLabel" for="savings-account-approvedOnDate">
                  <Translate contentKey="bankApp.savingsAccount.approvedOnDate">Approved On Date</Translate>
                </Label>
                <AvInput
                  id="savings-account-approvedOnDate"
                  type="datetime-local"
                  className="form-control"
                  name="approvedOnDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.savingsAccountEntity.approvedOnDate)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="nominalAnnualInterestRateLabel" for="savings-account-nominalAnnualInterestRate">
                  <Translate contentKey="bankApp.savingsAccount.nominalAnnualInterestRate">Nominal Annual Interest Rate</Translate>
                </Label>
                <AvField id="savings-account-nominalAnnualInterestRate" type="text" name="nominalAnnualInterestRate" />
              </AvGroup>
              <AvGroup>
                <Label id="interestCompoundingPeriodTypeLabel" for="savings-account-interestCompoundingPeriodType">
                  <Translate contentKey="bankApp.savingsAccount.interestCompoundingPeriodType">Interest Compounding Period Type</Translate>
                </Label>
                <AvField
                  id="savings-account-interestCompoundingPeriodType"
                  type="string"
                  className="form-control"
                  name="interestCompoundingPeriodType"
                />
              </AvGroup>
              <AvGroup>
                <Label id="interestPostingPeriodTypeLabel" for="savings-account-interestPostingPeriodType">
                  <Translate contentKey="bankApp.savingsAccount.interestPostingPeriodType">Interest Posting Period Type</Translate>
                </Label>
                <AvField
                  id="savings-account-interestPostingPeriodType"
                  type="string"
                  className="form-control"
                  name="interestPostingPeriodType"
                />
              </AvGroup>
              <AvGroup>
                <Label id="interestCalculationTypeLabel" for="savings-account-interestCalculationType">
                  <Translate contentKey="bankApp.savingsAccount.interestCalculationType">Interest Calculation Type</Translate>
                </Label>
                <AvField
                  id="savings-account-interestCalculationType"
                  type="string"
                  className="form-control"
                  name="interestCalculationType"
                />
              </AvGroup>
              <AvGroup>
                <Label id="interestCalculationDaysInYearTypeLabel" for="savings-account-interestCalculationDaysInYearType">
                  <Translate contentKey="bankApp.savingsAccount.interestCalculationDaysInYearType">
                    Interest Calculation Days In Year Type
                  </Translate>
                </Label>
                <AvField
                  id="savings-account-interestCalculationDaysInYearType"
                  type="string"
                  className="form-control"
                  name="interestCalculationDaysInYearType"
                />
              </AvGroup>
              <AvGroup>
                <Label id="minRequiredOpeningBalanceLabel" for="savings-account-minRequiredOpeningBalance">
                  <Translate contentKey="bankApp.savingsAccount.minRequiredOpeningBalance">Min Required Opening Balance</Translate>
                </Label>
                <AvField id="savings-account-minRequiredOpeningBalance" type="text" name="minRequiredOpeningBalance" />
              </AvGroup>
              <AvGroup>
                <Label id="lockinPeriodFrequencyLabel" for="savings-account-lockinPeriodFrequency">
                  <Translate contentKey="bankApp.savingsAccount.lockinPeriodFrequency">Lockin Period Frequency</Translate>
                </Label>
                <AvField id="savings-account-lockinPeriodFrequency" type="string" className="form-control" name="lockinPeriodFrequency" />
              </AvGroup>
              <AvGroup>
                <Label id="lockinPeriodFrequencyTypeLabel" for="savings-account-lockinPeriodFrequencyType">
                  <Translate contentKey="bankApp.savingsAccount.lockinPeriodFrequencyType">Lockin Period Frequency Type</Translate>
                </Label>
                <AvField
                  id="savings-account-lockinPeriodFrequencyType"
                  type="string"
                  className="form-control"
                  name="lockinPeriodFrequencyType"
                />
              </AvGroup>
              <AvGroup>
                <Label id="nominalAnnualInterestRateOverdraftLabel" for="savings-account-nominalAnnualInterestRateOverdraft">
                  <Translate contentKey="bankApp.savingsAccount.nominalAnnualInterestRateOverdraft">
                    Nominal Annual Interest Rate Overdraft
                  </Translate>
                </Label>
                <AvField id="savings-account-nominalAnnualInterestRateOverdraft" type="text" name="nominalAnnualInterestRateOverdraft" />
              </AvGroup>
              <AvGroup>
                <Label id="minOverdraftForInterestCalculationLabel" for="savings-account-minOverdraftForInterestCalculation">
                  <Translate contentKey="bankApp.savingsAccount.minOverdraftForInterestCalculation">
                    Min Overdraft For Interest Calculation
                  </Translate>
                </Label>
                <AvField id="savings-account-minOverdraftForInterestCalculation" type="text" name="minOverdraftForInterestCalculation" />
              </AvGroup>
              <AvGroup>
                <Label id="minBalanceForInterestCalculationLabel" for="savings-account-minBalanceForInterestCalculation">
                  <Translate contentKey="bankApp.savingsAccount.minBalanceForInterestCalculation">
                    Min Balance For Interest Calculation
                  </Translate>
                </Label>
                <AvField id="savings-account-minBalanceForInterestCalculation" type="text" name="minBalanceForInterestCalculation" />
              </AvGroup>
              <AvGroup check>
                <Label id="enforceMinRequiredBalanceLabel">
                  <AvInput
                    id="savings-account-enforceMinRequiredBalance"
                    type="checkbox"
                    className="form-check-input"
                    name="enforceMinRequiredBalance"
                  />
                  <Translate contentKey="bankApp.savingsAccount.enforceMinRequiredBalance">Enforce Min Required Balance</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="minRequiredBalanceLabel" for="savings-account-minRequiredBalance">
                  <Translate contentKey="bankApp.savingsAccount.minRequiredBalance">Min Required Balance</Translate>
                </Label>
                <AvField id="savings-account-minRequiredBalance" type="text" name="minRequiredBalance" />
              </AvGroup>
              <AvGroup check>
                <Label id="withdrawalFeeApplicableForTransferLabel">
                  <AvInput
                    id="savings-account-withdrawalFeeApplicableForTransfer"
                    type="checkbox"
                    className="form-check-input"
                    name="withdrawalFeeApplicableForTransfer"
                  />
                  <Translate contentKey="bankApp.savingsAccount.withdrawalFeeApplicableForTransfer">
                    Withdrawal Fee Applicable For Transfer
                  </Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="allowOverdraftLabel">
                  <AvInput id="savings-account-allowOverdraft" type="checkbox" className="form-check-input" name="allowOverdraft" />
                  <Translate contentKey="bankApp.savingsAccount.allowOverdraft">Allow Overdraft</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="overdraftLimitLabel" for="savings-account-overdraftLimit">
                  <Translate contentKey="bankApp.savingsAccount.overdraftLimit">Overdraft Limit</Translate>
                </Label>
                <AvField id="savings-account-overdraftLimit" type="text" name="overdraftLimit" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/savings-account" replace color="info">
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
  savingsAccountEntity: storeState.savingsAccount.entity,
  loading: storeState.savingsAccount.loading,
  updating: storeState.savingsAccount.updating,
  updateSuccess: storeState.savingsAccount.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SavingsAccountUpdate);
