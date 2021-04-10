import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './savings-product.reducer';
import { ISavingsProduct } from 'app/shared/model/savings-product.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISavingsProductUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SavingsProductUpdate = (props: ISavingsProductUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { savingsProductEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/savings-product');
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
    if (errors.length === 0) {
      const entity = {
        ...savingsProductEntity,
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
          <h2 id="bankApp.savingsProduct.home.createOrEditLabel">
            <Translate contentKey="bankApp.savingsProduct.home.createOrEditLabel">Create or edit a SavingsProduct</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : savingsProductEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="savings-product-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="savings-product-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="savings-product-name">
                  <Translate contentKey="bankApp.savingsProduct.name">Name</Translate>
                </Label>
                <AvField id="savings-product-name" type="text" name="name" validate={{}} />
              </AvGroup>
              <AvGroup>
                <Label id="shortNameLabel" for="savings-product-shortName">
                  <Translate contentKey="bankApp.savingsProduct.shortName">Short Name</Translate>
                </Label>
                <AvField id="savings-product-shortName" type="text" name="shortName" validate={{}} />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="savings-product-description">
                  <Translate contentKey="bankApp.savingsProduct.description">Description</Translate>
                </Label>
                <AvField
                  id="savings-product-description"
                  type="text"
                  name="description"
                  validate={{
                    maxLength: { value: 500, errorMessage: translate('entity.validation.maxlength', { max: 500 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="nominalAnnualInterestRateLabel" for="savings-product-nominalAnnualInterestRate">
                  <Translate contentKey="bankApp.savingsProduct.nominalAnnualInterestRate">Nominal Annual Interest Rate</Translate>
                </Label>
                <AvField id="savings-product-nominalAnnualInterestRate" type="text" name="nominalAnnualInterestRate" />
              </AvGroup>
              <AvGroup>
                <Label id="interestCompoundingPeriodTypeLabel" for="savings-product-interestCompoundingPeriodType">
                  <Translate contentKey="bankApp.savingsProduct.interestCompoundingPeriodType">Interest Compounding Period Type</Translate>
                </Label>
                <AvField
                  id="savings-product-interestCompoundingPeriodType"
                  type="string"
                  className="form-control"
                  name="interestCompoundingPeriodType"
                />
              </AvGroup>
              <AvGroup>
                <Label id="interestPostingPeriodTypeLabel" for="savings-product-interestPostingPeriodType">
                  <Translate contentKey="bankApp.savingsProduct.interestPostingPeriodType">Interest Posting Period Type</Translate>
                </Label>
                <AvField
                  id="savings-product-interestPostingPeriodType"
                  type="string"
                  className="form-control"
                  name="interestPostingPeriodType"
                />
              </AvGroup>
              <AvGroup>
                <Label id="interestCalculationTypeLabel" for="savings-product-interestCalculationType">
                  <Translate contentKey="bankApp.savingsProduct.interestCalculationType">Interest Calculation Type</Translate>
                </Label>
                <AvField
                  id="savings-product-interestCalculationType"
                  type="string"
                  className="form-control"
                  name="interestCalculationType"
                />
              </AvGroup>
              <AvGroup>
                <Label id="interestCalculationDaysInYearTypeLabel" for="savings-product-interestCalculationDaysInYearType">
                  <Translate contentKey="bankApp.savingsProduct.interestCalculationDaysInYearType">
                    Interest Calculation Days In Year Type
                  </Translate>
                </Label>
                <AvField
                  id="savings-product-interestCalculationDaysInYearType"
                  type="string"
                  className="form-control"
                  name="interestCalculationDaysInYearType"
                />
              </AvGroup>
              <AvGroup>
                <Label id="minRequiredOpeningBalanceLabel" for="savings-product-minRequiredOpeningBalance">
                  <Translate contentKey="bankApp.savingsProduct.minRequiredOpeningBalance">Min Required Opening Balance</Translate>
                </Label>
                <AvField id="savings-product-minRequiredOpeningBalance" type="text" name="minRequiredOpeningBalance" />
              </AvGroup>
              <AvGroup>
                <Label id="lockinPeriodFrequencyLabel" for="savings-product-lockinPeriodFrequency">
                  <Translate contentKey="bankApp.savingsProduct.lockinPeriodFrequency">Lockin Period Frequency</Translate>
                </Label>
                <AvField id="savings-product-lockinPeriodFrequency" type="string" className="form-control" name="lockinPeriodFrequency" />
              </AvGroup>
              <AvGroup>
                <Label id="lockinPeriodFrequencyTypeLabel" for="savings-product-lockinPeriodFrequencyType">
                  <Translate contentKey="bankApp.savingsProduct.lockinPeriodFrequencyType">Lockin Period Frequency Type</Translate>
                </Label>
                <AvField
                  id="savings-product-lockinPeriodFrequencyType"
                  type="string"
                  className="form-control"
                  name="lockinPeriodFrequencyType"
                />
              </AvGroup>
              <AvGroup>
                <Label id="accountingRuleLabel" for="savings-product-accountingRule">
                  <Translate contentKey="bankApp.savingsProduct.accountingRule">Accounting Rule</Translate>
                </Label>
                <AvField id="savings-product-accountingRule" type="string" className="form-control" name="accountingRule" />
              </AvGroup>
              <AvGroup check>
                <Label id="withdrawalFeeApplicableForTransferLabel">
                  <AvInput
                    id="savings-product-withdrawalFeeApplicableForTransfer"
                    type="checkbox"
                    className="form-check-input"
                    name="withdrawalFeeApplicableForTransfer"
                  />
                  <Translate contentKey="bankApp.savingsProduct.withdrawalFeeApplicableForTransfer">
                    Withdrawal Fee Applicable For Transfer
                  </Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="allowOverdraftLabel">
                  <AvInput id="savings-product-allowOverdraft" type="checkbox" className="form-check-input" name="allowOverdraft" />
                  <Translate contentKey="bankApp.savingsProduct.allowOverdraft">Allow Overdraft</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="overdraftLimitLabel" for="savings-product-overdraftLimit">
                  <Translate contentKey="bankApp.savingsProduct.overdraftLimit">Overdraft Limit</Translate>
                </Label>
                <AvField id="savings-product-overdraftLimit" type="text" name="overdraftLimit" />
              </AvGroup>
              <AvGroup>
                <Label id="nominalAnnualInterestRateOverdraftLabel" for="savings-product-nominalAnnualInterestRateOverdraft">
                  <Translate contentKey="bankApp.savingsProduct.nominalAnnualInterestRateOverdraft">
                    Nominal Annual Interest Rate Overdraft
                  </Translate>
                </Label>
                <AvField id="savings-product-nominalAnnualInterestRateOverdraft" type="text" name="nominalAnnualInterestRateOverdraft" />
              </AvGroup>
              <AvGroup>
                <Label id="minOverdraftForInterestCalculationLabel" for="savings-product-minOverdraftForInterestCalculation">
                  <Translate contentKey="bankApp.savingsProduct.minOverdraftForInterestCalculation">
                    Min Overdraft For Interest Calculation
                  </Translate>
                </Label>
                <AvField id="savings-product-minOverdraftForInterestCalculation" type="text" name="minOverdraftForInterestCalculation" />
              </AvGroup>
              <AvGroup check>
                <Label id="enforceMinRequiredBalanceLabel">
                  <AvInput
                    id="savings-product-enforceMinRequiredBalance"
                    type="checkbox"
                    className="form-check-input"
                    name="enforceMinRequiredBalance"
                  />
                  <Translate contentKey="bankApp.savingsProduct.enforceMinRequiredBalance">Enforce Min Required Balance</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="minRequiredBalanceLabel" for="savings-product-minRequiredBalance">
                  <Translate contentKey="bankApp.savingsProduct.minRequiredBalance">Min Required Balance</Translate>
                </Label>
                <AvField id="savings-product-minRequiredBalance" type="text" name="minRequiredBalance" />
              </AvGroup>
              <AvGroup>
                <Label id="minBalanceForInterestCalculationLabel" for="savings-product-minBalanceForInterestCalculation">
                  <Translate contentKey="bankApp.savingsProduct.minBalanceForInterestCalculation">
                    Min Balance For Interest Calculation
                  </Translate>
                </Label>
                <AvField id="savings-product-minBalanceForInterestCalculation" type="text" name="minBalanceForInterestCalculation" />
              </AvGroup>
              <AvGroup check>
                <Label id="withHoldTaxLabel">
                  <AvInput id="savings-product-withHoldTax" type="checkbox" className="form-check-input" name="withHoldTax" />
                  <Translate contentKey="bankApp.savingsProduct.withHoldTax">With Hold Tax</Translate>
                </Label>
              </AvGroup>
              <AvGroup check>
                <Label id="isDormancyTrackingActiveLabel">
                  <AvInput
                    id="savings-product-isDormancyTrackingActive"
                    type="checkbox"
                    className="form-check-input"
                    name="isDormancyTrackingActive"
                  />
                  <Translate contentKey="bankApp.savingsProduct.isDormancyTrackingActive">Is Dormancy Tracking Active</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="daysToInactiveLabel" for="savings-product-daysToInactive">
                  <Translate contentKey="bankApp.savingsProduct.daysToInactive">Days To Inactive</Translate>
                </Label>
                <AvField id="savings-product-daysToInactive" type="string" className="form-control" name="daysToInactive" />
              </AvGroup>
              <AvGroup>
                <Label id="daysToDormancyLabel" for="savings-product-daysToDormancy">
                  <Translate contentKey="bankApp.savingsProduct.daysToDormancy">Days To Dormancy</Translate>
                </Label>
                <AvField id="savings-product-daysToDormancy" type="string" className="form-control" name="daysToDormancy" />
              </AvGroup>
              <AvGroup>
                <Label id="daysToEscheatLabel" for="savings-product-daysToEscheat">
                  <Translate contentKey="bankApp.savingsProduct.daysToEscheat">Days To Escheat</Translate>
                </Label>
                <AvField id="savings-product-daysToEscheat" type="string" className="form-control" name="daysToEscheat" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/savings-product" replace color="info">
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
  savingsProductEntity: storeState.savingsProduct.entity,
  loading: storeState.savingsProduct.loading,
  updating: storeState.savingsProduct.updating,
  updateSuccess: storeState.savingsProduct.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SavingsProductUpdate);
