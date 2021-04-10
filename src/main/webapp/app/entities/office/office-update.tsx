import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntities as getOffices } from 'app/entities/office/office.reducer';
import { getEntity, updateEntity, createEntity, reset } from './office.reducer';
import { IOffice } from 'app/shared/model/office.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IOfficeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OfficeUpdate = (props: IOfficeUpdateProps) => {
  const [childrenId, setChildrenId] = useState('0');
  const [parentId, setParentId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { officeEntity, offices, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/office');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getOffices();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.openingDate = convertDateTimeToServer(values.openingDate);

    if (errors.length === 0) {
      const entity = {
        ...officeEntity,
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
          <h2 id="bankApp.office.home.createOrEditLabel">
            <Translate contentKey="bankApp.office.home.createOrEditLabel">Create or edit a Office</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : officeEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="office-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="office-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="nameLabel" for="office-name">
                  <Translate contentKey="bankApp.office.name">Name</Translate>
                </Label>
                <AvField
                  id="office-name"
                  type="text"
                  name="name"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="openingDateLabel" for="office-openingDate">
                  <Translate contentKey="bankApp.office.openingDate">Opening Date</Translate>
                </Label>
                <AvInput
                  id="office-openingDate"
                  type="datetime-local"
                  className="form-control"
                  name="openingDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.officeEntity.openingDate)}
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="externalIdLabel" for="office-externalId">
                  <Translate contentKey="bankApp.office.externalId">External Id</Translate>
                </Label>
                <AvField
                  id="office-externalId"
                  type="text"
                  name="externalId"
                  validate={{
                    maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label for="office-parent">
                  <Translate contentKey="bankApp.office.parent">Parent</Translate>
                </Label>
                <AvInput id="office-parent" type="select" className="form-control" name="parentId">
                  <option value="" key="0" />
                  {offices
                    ? offices.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/office" replace color="info">
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
  offices: storeState.office.entities,
  officeEntity: storeState.office.entity,
  loading: storeState.office.loading,
  updating: storeState.office.updating,
  updateSuccess: storeState.office.updateSuccess,
});

const mapDispatchToProps = {
  getOffices,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OfficeUpdate);
