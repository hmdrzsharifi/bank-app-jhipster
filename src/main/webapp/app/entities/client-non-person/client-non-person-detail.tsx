import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './client-non-person.reducer';
import { IClientNonPerson } from 'app/shared/model/client-non-person.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IClientNonPersonDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ClientNonPersonDetail = (props: IClientNonPersonDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { clientNonPersonEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="bankApp.clientNonPerson.detail.title">ClientNonPerson</Translate> [<b>{clientNonPersonEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="fieldName">
              <Translate contentKey="bankApp.clientNonPerson.fieldName">Field Name</Translate>
            </span>
            <UncontrolledTooltip target="fieldName">
              <Translate contentKey="bankApp.clientNonPerson.help.fieldName" />
            </UncontrolledTooltip>
          </dt>
          <dd>{clientNonPersonEntity.fieldName}</dd>
        </dl>
        <Button tag={Link} to="/client-non-person" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/client-non-person/${clientNonPersonEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ clientNonPerson }: IRootState) => ({
  clientNonPersonEntity: clientNonPerson.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ClientNonPersonDetail);
