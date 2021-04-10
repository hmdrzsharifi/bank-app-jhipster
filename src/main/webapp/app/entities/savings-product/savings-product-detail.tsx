import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './savings-product.reducer';
import { ISavingsProduct } from 'app/shared/model/savings-product.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISavingsProductDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SavingsProductDetail = (props: ISavingsProductDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { savingsProductEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="bankApp.savingsProduct.detail.title">SavingsProduct</Translate> [<b>{savingsProductEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="bankApp.savingsProduct.name">Name</Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.name}</dd>
          <dt>
            <span id="shortName">
              <Translate contentKey="bankApp.savingsProduct.shortName">Short Name</Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.shortName}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="bankApp.savingsProduct.description">Description</Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.description}</dd>
          <dt>
            <span id="nominalAnnualInterestRate">
              <Translate contentKey="bankApp.savingsProduct.nominalAnnualInterestRate">Nominal Annual Interest Rate</Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.nominalAnnualInterestRate}</dd>
          <dt>
            <span id="interestCompoundingPeriodType">
              <Translate contentKey="bankApp.savingsProduct.interestCompoundingPeriodType">Interest Compounding Period Type</Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.interestCompoundingPeriodType}</dd>
          <dt>
            <span id="interestPostingPeriodType">
              <Translate contentKey="bankApp.savingsProduct.interestPostingPeriodType">Interest Posting Period Type</Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.interestPostingPeriodType}</dd>
          <dt>
            <span id="interestCalculationType">
              <Translate contentKey="bankApp.savingsProduct.interestCalculationType">Interest Calculation Type</Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.interestCalculationType}</dd>
          <dt>
            <span id="interestCalculationDaysInYearType">
              <Translate contentKey="bankApp.savingsProduct.interestCalculationDaysInYearType">
                Interest Calculation Days In Year Type
              </Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.interestCalculationDaysInYearType}</dd>
          <dt>
            <span id="minRequiredOpeningBalance">
              <Translate contentKey="bankApp.savingsProduct.minRequiredOpeningBalance">Min Required Opening Balance</Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.minRequiredOpeningBalance}</dd>
          <dt>
            <span id="lockinPeriodFrequency">
              <Translate contentKey="bankApp.savingsProduct.lockinPeriodFrequency">Lockin Period Frequency</Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.lockinPeriodFrequency}</dd>
          <dt>
            <span id="lockinPeriodFrequencyType">
              <Translate contentKey="bankApp.savingsProduct.lockinPeriodFrequencyType">Lockin Period Frequency Type</Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.lockinPeriodFrequencyType}</dd>
          <dt>
            <span id="accountingRule">
              <Translate contentKey="bankApp.savingsProduct.accountingRule">Accounting Rule</Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.accountingRule}</dd>
          <dt>
            <span id="withdrawalFeeApplicableForTransfer">
              <Translate contentKey="bankApp.savingsProduct.withdrawalFeeApplicableForTransfer">
                Withdrawal Fee Applicable For Transfer
              </Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.withdrawalFeeApplicableForTransfer ? 'true' : 'false'}</dd>
          <dt>
            <span id="allowOverdraft">
              <Translate contentKey="bankApp.savingsProduct.allowOverdraft">Allow Overdraft</Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.allowOverdraft ? 'true' : 'false'}</dd>
          <dt>
            <span id="overdraftLimit">
              <Translate contentKey="bankApp.savingsProduct.overdraftLimit">Overdraft Limit</Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.overdraftLimit}</dd>
          <dt>
            <span id="nominalAnnualInterestRateOverdraft">
              <Translate contentKey="bankApp.savingsProduct.nominalAnnualInterestRateOverdraft">
                Nominal Annual Interest Rate Overdraft
              </Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.nominalAnnualInterestRateOverdraft}</dd>
          <dt>
            <span id="minOverdraftForInterestCalculation">
              <Translate contentKey="bankApp.savingsProduct.minOverdraftForInterestCalculation">
                Min Overdraft For Interest Calculation
              </Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.minOverdraftForInterestCalculation}</dd>
          <dt>
            <span id="enforceMinRequiredBalance">
              <Translate contentKey="bankApp.savingsProduct.enforceMinRequiredBalance">Enforce Min Required Balance</Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.enforceMinRequiredBalance ? 'true' : 'false'}</dd>
          <dt>
            <span id="minRequiredBalance">
              <Translate contentKey="bankApp.savingsProduct.minRequiredBalance">Min Required Balance</Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.minRequiredBalance}</dd>
          <dt>
            <span id="minBalanceForInterestCalculation">
              <Translate contentKey="bankApp.savingsProduct.minBalanceForInterestCalculation">
                Min Balance For Interest Calculation
              </Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.minBalanceForInterestCalculation}</dd>
          <dt>
            <span id="withHoldTax">
              <Translate contentKey="bankApp.savingsProduct.withHoldTax">With Hold Tax</Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.withHoldTax ? 'true' : 'false'}</dd>
          <dt>
            <span id="isDormancyTrackingActive">
              <Translate contentKey="bankApp.savingsProduct.isDormancyTrackingActive">Is Dormancy Tracking Active</Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.isDormancyTrackingActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="daysToInactive">
              <Translate contentKey="bankApp.savingsProduct.daysToInactive">Days To Inactive</Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.daysToInactive}</dd>
          <dt>
            <span id="daysToDormancy">
              <Translate contentKey="bankApp.savingsProduct.daysToDormancy">Days To Dormancy</Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.daysToDormancy}</dd>
          <dt>
            <span id="daysToEscheat">
              <Translate contentKey="bankApp.savingsProduct.daysToEscheat">Days To Escheat</Translate>
            </span>
          </dt>
          <dd>{savingsProductEntity.daysToEscheat}</dd>
        </dl>
        <Button tag={Link} to="/savings-product" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/savings-product/${savingsProductEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ savingsProduct }: IRootState) => ({
  savingsProductEntity: savingsProduct.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SavingsProductDetail);
