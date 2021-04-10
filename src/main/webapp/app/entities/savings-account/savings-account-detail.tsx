import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './savings-account.reducer';
import { ISavingsAccount } from 'app/shared/model/savings-account.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISavingsAccountDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SavingsAccountDetail = (props: ISavingsAccountDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { savingsAccountEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="bankApp.savingsAccount.detail.title">SavingsAccount</Translate> [<b>{savingsAccountEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="accountNumber">
              <Translate contentKey="bankApp.savingsAccount.accountNumber">Account Number</Translate>
            </span>
          </dt>
          <dd>{savingsAccountEntity.accountNumber}</dd>
          <dt>
            <span id="externalId">
              <Translate contentKey="bankApp.savingsAccount.externalId">External Id</Translate>
            </span>
          </dt>
          <dd>{savingsAccountEntity.externalId}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="bankApp.savingsAccount.status">Status</Translate>
            </span>
          </dt>
          <dd>{savingsAccountEntity.status}</dd>
          <dt>
            <span id="subStatus">
              <Translate contentKey="bankApp.savingsAccount.subStatus">Sub Status</Translate>
            </span>
          </dt>
          <dd>{savingsAccountEntity.subStatus}</dd>
          <dt>
            <span id="accountType">
              <Translate contentKey="bankApp.savingsAccount.accountType">Account Type</Translate>
            </span>
          </dt>
          <dd>{savingsAccountEntity.accountType}</dd>
          <dt>
            <span id="submittedOnDate">
              <Translate contentKey="bankApp.savingsAccount.submittedOnDate">Submitted On Date</Translate>
            </span>
          </dt>
          <dd>
            {savingsAccountEntity.submittedOnDate ? (
              <TextFormat value={savingsAccountEntity.submittedOnDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="rejectedOnDate">
              <Translate contentKey="bankApp.savingsAccount.rejectedOnDate">Rejected On Date</Translate>
            </span>
          </dt>
          <dd>
            {savingsAccountEntity.rejectedOnDate ? (
              <TextFormat value={savingsAccountEntity.rejectedOnDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="approvedOnDate">
              <Translate contentKey="bankApp.savingsAccount.approvedOnDate">Approved On Date</Translate>
            </span>
          </dt>
          <dd>
            {savingsAccountEntity.approvedOnDate ? (
              <TextFormat value={savingsAccountEntity.approvedOnDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="nominalAnnualInterestRate">
              <Translate contentKey="bankApp.savingsAccount.nominalAnnualInterestRate">Nominal Annual Interest Rate</Translate>
            </span>
          </dt>
          <dd>{savingsAccountEntity.nominalAnnualInterestRate}</dd>
          <dt>
            <span id="interestCompoundingPeriodType">
              <Translate contentKey="bankApp.savingsAccount.interestCompoundingPeriodType">Interest Compounding Period Type</Translate>
            </span>
          </dt>
          <dd>{savingsAccountEntity.interestCompoundingPeriodType}</dd>
          <dt>
            <span id="interestPostingPeriodType">
              <Translate contentKey="bankApp.savingsAccount.interestPostingPeriodType">Interest Posting Period Type</Translate>
            </span>
          </dt>
          <dd>{savingsAccountEntity.interestPostingPeriodType}</dd>
          <dt>
            <span id="interestCalculationType">
              <Translate contentKey="bankApp.savingsAccount.interestCalculationType">Interest Calculation Type</Translate>
            </span>
          </dt>
          <dd>{savingsAccountEntity.interestCalculationType}</dd>
          <dt>
            <span id="interestCalculationDaysInYearType">
              <Translate contentKey="bankApp.savingsAccount.interestCalculationDaysInYearType">
                Interest Calculation Days In Year Type
              </Translate>
            </span>
          </dt>
          <dd>{savingsAccountEntity.interestCalculationDaysInYearType}</dd>
          <dt>
            <span id="minRequiredOpeningBalance">
              <Translate contentKey="bankApp.savingsAccount.minRequiredOpeningBalance">Min Required Opening Balance</Translate>
            </span>
          </dt>
          <dd>{savingsAccountEntity.minRequiredOpeningBalance}</dd>
          <dt>
            <span id="lockinPeriodFrequency">
              <Translate contentKey="bankApp.savingsAccount.lockinPeriodFrequency">Lockin Period Frequency</Translate>
            </span>
          </dt>
          <dd>{savingsAccountEntity.lockinPeriodFrequency}</dd>
          <dt>
            <span id="lockinPeriodFrequencyType">
              <Translate contentKey="bankApp.savingsAccount.lockinPeriodFrequencyType">Lockin Period Frequency Type</Translate>
            </span>
          </dt>
          <dd>{savingsAccountEntity.lockinPeriodFrequencyType}</dd>
          <dt>
            <span id="nominalAnnualInterestRateOverdraft">
              <Translate contentKey="bankApp.savingsAccount.nominalAnnualInterestRateOverdraft">
                Nominal Annual Interest Rate Overdraft
              </Translate>
            </span>
          </dt>
          <dd>{savingsAccountEntity.nominalAnnualInterestRateOverdraft}</dd>
          <dt>
            <span id="minOverdraftForInterestCalculation">
              <Translate contentKey="bankApp.savingsAccount.minOverdraftForInterestCalculation">
                Min Overdraft For Interest Calculation
              </Translate>
            </span>
          </dt>
          <dd>{savingsAccountEntity.minOverdraftForInterestCalculation}</dd>
          <dt>
            <span id="minBalanceForInterestCalculation">
              <Translate contentKey="bankApp.savingsAccount.minBalanceForInterestCalculation">
                Min Balance For Interest Calculation
              </Translate>
            </span>
          </dt>
          <dd>{savingsAccountEntity.minBalanceForInterestCalculation}</dd>
          <dt>
            <span id="enforceMinRequiredBalance">
              <Translate contentKey="bankApp.savingsAccount.enforceMinRequiredBalance">Enforce Min Required Balance</Translate>
            </span>
          </dt>
          <dd>{savingsAccountEntity.enforceMinRequiredBalance ? 'true' : 'false'}</dd>
          <dt>
            <span id="minRequiredBalance">
              <Translate contentKey="bankApp.savingsAccount.minRequiredBalance">Min Required Balance</Translate>
            </span>
          </dt>
          <dd>{savingsAccountEntity.minRequiredBalance}</dd>
          <dt>
            <span id="withdrawalFeeApplicableForTransfer">
              <Translate contentKey="bankApp.savingsAccount.withdrawalFeeApplicableForTransfer">
                Withdrawal Fee Applicable For Transfer
              </Translate>
            </span>
          </dt>
          <dd>{savingsAccountEntity.withdrawalFeeApplicableForTransfer ? 'true' : 'false'}</dd>
          <dt>
            <span id="allowOverdraft">
              <Translate contentKey="bankApp.savingsAccount.allowOverdraft">Allow Overdraft</Translate>
            </span>
          </dt>
          <dd>{savingsAccountEntity.allowOverdraft ? 'true' : 'false'}</dd>
          <dt>
            <span id="overdraftLimit">
              <Translate contentKey="bankApp.savingsAccount.overdraftLimit">Overdraft Limit</Translate>
            </span>
          </dt>
          <dd>{savingsAccountEntity.overdraftLimit}</dd>
        </dl>
        <Button tag={Link} to="/savings-account" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/savings-account/${savingsAccountEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ savingsAccount }: IRootState) => ({
  savingsAccountEntity: savingsAccount.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SavingsAccountDetail);
