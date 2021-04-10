import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './client.reducer';
import { IClient } from 'app/shared/model/client.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IClientDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ClientDetail = (props: IClientDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { clientEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="bankApp.client.detail.title">Client</Translate> [<b>{clientEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="accountNumber">
              <Translate contentKey="bankApp.client.accountNumber">Account Number</Translate>
            </span>
          </dt>
          <dd>{clientEntity.accountNumber}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="bankApp.client.status">Status</Translate>
            </span>
          </dt>
          <dd>{clientEntity.status}</dd>
          <dt>
            <span id="activationDate">
              <Translate contentKey="bankApp.client.activationDate">Activation Date</Translate>
            </span>
          </dt>
          <dd>
            {clientEntity.activationDate ? <TextFormat value={clientEntity.activationDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="firstname">
              <Translate contentKey="bankApp.client.firstname">Firstname</Translate>
            </span>
          </dt>
          <dd>{clientEntity.firstname}</dd>
          <dt>
            <span id="lastname">
              <Translate contentKey="bankApp.client.lastname">Lastname</Translate>
            </span>
          </dt>
          <dd>{clientEntity.lastname}</dd>
          <dt>
            <span id="middlename">
              <Translate contentKey="bankApp.client.middlename">Middlename</Translate>
            </span>
          </dt>
          <dd>{clientEntity.middlename}</dd>
          <dt>
            <span id="fullname">
              <Translate contentKey="bankApp.client.fullname">Fullname</Translate>
            </span>
          </dt>
          <dd>{clientEntity.fullname}</dd>
          <dt>
            <span id="displayName">
              <Translate contentKey="bankApp.client.displayName">Display Name</Translate>
            </span>
          </dt>
          <dd>{clientEntity.displayName}</dd>
          <dt>
            <span id="mobileNo">
              <Translate contentKey="bankApp.client.mobileNo">Mobile No</Translate>
            </span>
          </dt>
          <dd>{clientEntity.mobileNo}</dd>
          <dt>
            <span id="emailAddress">
              <Translate contentKey="bankApp.client.emailAddress">Email Address</Translate>
            </span>
          </dt>
          <dd>{clientEntity.emailAddress}</dd>
          <dt>
            <span id="isStaff">
              <Translate contentKey="bankApp.client.isStaff">Is Staff</Translate>
            </span>
          </dt>
          <dd>{clientEntity.isStaff ? 'true' : 'false'}</dd>
          <dt>
            <span id="externalId">
              <Translate contentKey="bankApp.client.externalId">External Id</Translate>
            </span>
          </dt>
          <dd>{clientEntity.externalId}</dd>
          <dt>
            <span id="dateOfBirth">
              <Translate contentKey="bankApp.client.dateOfBirth">Date Of Birth</Translate>
            </span>
          </dt>
          <dd>{clientEntity.dateOfBirth ? <TextFormat value={clientEntity.dateOfBirth} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="closureDate">
              <Translate contentKey="bankApp.client.closureDate">Closure Date</Translate>
            </span>
          </dt>
          <dd>{clientEntity.closureDate ? <TextFormat value={clientEntity.closureDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="rejectionDate">
              <Translate contentKey="bankApp.client.rejectionDate">Rejection Date</Translate>
            </span>
          </dt>
          <dd>
            {clientEntity.rejectionDate ? <TextFormat value={clientEntity.rejectionDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="reactivateDate">
              <Translate contentKey="bankApp.client.reactivateDate">Reactivate Date</Translate>
            </span>
          </dt>
          <dd>
            {clientEntity.reactivateDate ? <TextFormat value={clientEntity.reactivateDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="submittedOnDate">
              <Translate contentKey="bankApp.client.submittedOnDate">Submitted On Date</Translate>
            </span>
          </dt>
          <dd>
            {clientEntity.submittedOnDate ? <TextFormat value={clientEntity.submittedOnDate} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <Translate contentKey="bankApp.client.image">Image</Translate>
          </dt>
          <dd>{clientEntity.imageId ? clientEntity.imageId : ''}</dd>
          <dt>
            <Translate contentKey="bankApp.client.staff">Staff</Translate>
          </dt>
          <dd>{clientEntity.staffId ? clientEntity.staffId : ''}</dd>
          <dt>
            <Translate contentKey="bankApp.client.office">Office</Translate>
          </dt>
          <dd>{clientEntity.officeId ? clientEntity.officeId : ''}</dd>
        </dl>
        <Button tag={Link} to="/client" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/client/${clientEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ client }: IRootState) => ({
  clientEntity: client.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ClientDetail);
