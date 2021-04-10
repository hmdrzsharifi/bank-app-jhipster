import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, UncontrolledTooltip, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './staff.reducer';
import { IStaff } from 'app/shared/model/staff.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStaffDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const StaffDetail = (props: IStaffDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { staffEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="bankApp.staff.detail.title">Staff</Translate> [<b>{staffEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="fieldName">
              <Translate contentKey="bankApp.staff.fieldName">Field Name</Translate>
            </span>
            <UncontrolledTooltip target="fieldName">
              <Translate contentKey="bankApp.staff.help.fieldName" />
            </UncontrolledTooltip>
          </dt>
          <dd>{staffEntity.fieldName}</dd>
        </dl>
        <Button tag={Link} to="/staff" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/staff/${staffEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ staff }: IRootState) => ({
  staffEntity: staff.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(StaffDetail);
