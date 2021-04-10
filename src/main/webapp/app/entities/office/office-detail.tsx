import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './office.reducer';
import { IOffice } from 'app/shared/model/office.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOfficeDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OfficeDetail = (props: IOfficeDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { officeEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="bankApp.office.detail.title">Office</Translate> [<b>{officeEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="name">
              <Translate contentKey="bankApp.office.name">Name</Translate>
            </span>
          </dt>
          <dd>{officeEntity.name}</dd>
          <dt>
            <span id="openingDate">
              <Translate contentKey="bankApp.office.openingDate">Opening Date</Translate>
            </span>
          </dt>
          <dd>{officeEntity.openingDate ? <TextFormat value={officeEntity.openingDate} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="externalId">
              <Translate contentKey="bankApp.office.externalId">External Id</Translate>
            </span>
          </dt>
          <dd>{officeEntity.externalId}</dd>
          <dt>
            <Translate contentKey="bankApp.office.parent">Parent</Translate>
          </dt>
          <dd>{officeEntity.parentId ? officeEntity.parentId : ''}</dd>
        </dl>
        <Button tag={Link} to="/office" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/office/${officeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ office }: IRootState) => ({
  officeEntity: office.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OfficeDetail);
