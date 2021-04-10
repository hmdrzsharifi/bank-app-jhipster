import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './payment-details.reducer';
import { IPaymentDetails } from 'app/shared/model/payment-details.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPaymentDetailsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PaymentDetailsDetail = (props: IPaymentDetailsDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { paymentDetailsEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="bankApp.paymentDetails.detail.title">PaymentDetails</Translate> [<b>{paymentDetailsEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="fieldName">
              <Translate contentKey="bankApp.paymentDetails.fieldName">Field Name</Translate>
            </span>
            <UncontrolledTooltip target="fieldName">
              <Translate contentKey="bankApp.paymentDetails.help.fieldName" />
            </UncontrolledTooltip>
          </dt>
          <dd>{paymentDetailsEntity.fieldName}</dd>
        </dl>
        <Button tag={Link} to="/payment-details" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/payment-details/${paymentDetailsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ paymentDetails }: IRootState) => ({
  paymentDetailsEntity: paymentDetails.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PaymentDetailsDetail);
