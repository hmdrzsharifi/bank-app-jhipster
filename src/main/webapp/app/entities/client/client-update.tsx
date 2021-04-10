import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IImage } from 'app/shared/model/image.model';
import { getEntities as getImages } from 'app/entities/image/image.reducer';
import { IStaff } from 'app/shared/model/staff.model';
import { getEntities as getStaff } from 'app/entities/staff/staff.reducer';
import { IOffice } from 'app/shared/model/office.model';
import { getEntities as getOffices } from 'app/entities/office/office.reducer';
import { getEntity, updateEntity, createEntity, reset } from './client.reducer';
import { IClient } from 'app/shared/model/client.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IClientUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ClientUpdate = (props: IClientUpdateProps) => {
  const [imageId, setImageId] = useState('0');
  const [staffId, setStaffId] = useState('0');
  const [officeId, setOfficeId] = useState('0');
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { clientEntity, images, staff, offices, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/client');
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }

    props.getImages();
    props.getStaff();
    props.getOffices();
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    values.activationDate = convertDateTimeToServer(values.activationDate);
    values.dateOfBirth = convertDateTimeToServer(values.dateOfBirth);
    values.closureDate = convertDateTimeToServer(values.closureDate);
    values.rejectionDate = convertDateTimeToServer(values.rejectionDate);
    values.reactivateDate = convertDateTimeToServer(values.reactivateDate);
    values.submittedOnDate = convertDateTimeToServer(values.submittedOnDate);

    if (errors.length === 0) {
      const entity = {
        ...clientEntity,
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
          <h2 id="bankApp.client.home.createOrEditLabel">
            <Translate contentKey="bankApp.client.home.createOrEditLabel">Create or edit a Client</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : clientEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="client-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="client-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="accountNumberLabel" for="client-accountNumber">
                  <Translate contentKey="bankApp.client.accountNumber">Account Number</Translate>
                </Label>
                <AvField
                  id="client-accountNumber"
                  type="text"
                  name="accountNumber"
                  validate={{
                    required: { value: true, errorMessage: translate('entity.validation.required') },
                    maxLength: { value: 20, errorMessage: translate('entity.validation.maxlength', { max: 20 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="statusLabel" for="client-status">
                  <Translate contentKey="bankApp.client.status">Status</Translate>
                </Label>
                <AvField id="client-status" type="string" className="form-control" name="status" />
              </AvGroup>
              <AvGroup>
                <Label id="activationDateLabel" for="client-activationDate">
                  <Translate contentKey="bankApp.client.activationDate">Activation Date</Translate>
                </Label>
                <AvInput
                  id="client-activationDate"
                  type="datetime-local"
                  className="form-control"
                  name="activationDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.clientEntity.activationDate)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="firstnameLabel" for="client-firstname">
                  <Translate contentKey="bankApp.client.firstname">Firstname</Translate>
                </Label>
                <AvField
                  id="client-firstname"
                  type="text"
                  name="firstname"
                  validate={{
                    maxLength: { value: 50, errorMessage: translate('entity.validation.maxlength', { max: 50 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="lastnameLabel" for="client-lastname">
                  <Translate contentKey="bankApp.client.lastname">Lastname</Translate>
                </Label>
                <AvField
                  id="client-lastname"
                  type="text"
                  name="lastname"
                  validate={{
                    maxLength: { value: 50, errorMessage: translate('entity.validation.maxlength', { max: 50 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="middlenameLabel" for="client-middlename">
                  <Translate contentKey="bankApp.client.middlename">Middlename</Translate>
                </Label>
                <AvField
                  id="client-middlename"
                  type="text"
                  name="middlename"
                  validate={{
                    maxLength: { value: 50, errorMessage: translate('entity.validation.maxlength', { max: 50 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="fullnameLabel" for="client-fullname">
                  <Translate contentKey="bankApp.client.fullname">Fullname</Translate>
                </Label>
                <AvField
                  id="client-fullname"
                  type="text"
                  name="fullname"
                  validate={{
                    maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="displayNameLabel" for="client-displayName">
                  <Translate contentKey="bankApp.client.displayName">Display Name</Translate>
                </Label>
                <AvField
                  id="client-displayName"
                  type="text"
                  name="displayName"
                  validate={{
                    maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="mobileNoLabel" for="client-mobileNo">
                  <Translate contentKey="bankApp.client.mobileNo">Mobile No</Translate>
                </Label>
                <AvField
                  id="client-mobileNo"
                  type="text"
                  name="mobileNo"
                  validate={{
                    maxLength: { value: 50, errorMessage: translate('entity.validation.maxlength', { max: 50 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="emailAddressLabel" for="client-emailAddress">
                  <Translate contentKey="bankApp.client.emailAddress">Email Address</Translate>
                </Label>
                <AvField
                  id="client-emailAddress"
                  type="text"
                  name="emailAddress"
                  validate={{
                    maxLength: { value: 50, errorMessage: translate('entity.validation.maxlength', { max: 50 }) },
                  }}
                />
              </AvGroup>
              <AvGroup check>
                <Label id="isStaffLabel">
                  <AvInput id="client-isStaff" type="checkbox" className="form-check-input" name="isStaff" />
                  <Translate contentKey="bankApp.client.isStaff">Is Staff</Translate>
                </Label>
              </AvGroup>
              <AvGroup>
                <Label id="externalIdLabel" for="client-externalId">
                  <Translate contentKey="bankApp.client.externalId">External Id</Translate>
                </Label>
                <AvField
                  id="client-externalId"
                  type="text"
                  name="externalId"
                  validate={{
                    maxLength: { value: 100, errorMessage: translate('entity.validation.maxlength', { max: 100 }) },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="dateOfBirthLabel" for="client-dateOfBirth">
                  <Translate contentKey="bankApp.client.dateOfBirth">Date Of Birth</Translate>
                </Label>
                <AvInput
                  id="client-dateOfBirth"
                  type="datetime-local"
                  className="form-control"
                  name="dateOfBirth"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.clientEntity.dateOfBirth)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="closureDateLabel" for="client-closureDate">
                  <Translate contentKey="bankApp.client.closureDate">Closure Date</Translate>
                </Label>
                <AvInput
                  id="client-closureDate"
                  type="datetime-local"
                  className="form-control"
                  name="closureDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.clientEntity.closureDate)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="rejectionDateLabel" for="client-rejectionDate">
                  <Translate contentKey="bankApp.client.rejectionDate">Rejection Date</Translate>
                </Label>
                <AvInput
                  id="client-rejectionDate"
                  type="datetime-local"
                  className="form-control"
                  name="rejectionDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.clientEntity.rejectionDate)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="reactivateDateLabel" for="client-reactivateDate">
                  <Translate contentKey="bankApp.client.reactivateDate">Reactivate Date</Translate>
                </Label>
                <AvInput
                  id="client-reactivateDate"
                  type="datetime-local"
                  className="form-control"
                  name="reactivateDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.clientEntity.reactivateDate)}
                />
              </AvGroup>
              <AvGroup>
                <Label id="submittedOnDateLabel" for="client-submittedOnDate">
                  <Translate contentKey="bankApp.client.submittedOnDate">Submitted On Date</Translate>
                </Label>
                <AvInput
                  id="client-submittedOnDate"
                  type="datetime-local"
                  className="form-control"
                  name="submittedOnDate"
                  placeholder={'YYYY-MM-DD HH:mm'}
                  value={isNew ? displayDefaultDateTime() : convertDateTimeFromServer(props.clientEntity.submittedOnDate)}
                />
              </AvGroup>
              <AvGroup>
                <Label for="client-image">
                  <Translate contentKey="bankApp.client.image">Image</Translate>
                </Label>
                <AvInput id="client-image" type="select" className="form-control" name="imageId">
                  <option value="" key="0" />
                  {images
                    ? images.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="client-staff">
                  <Translate contentKey="bankApp.client.staff">Staff</Translate>
                </Label>
                <AvInput id="client-staff" type="select" className="form-control" name="staffId">
                  <option value="" key="0" />
                  {staff
                    ? staff.map(otherEntity => (
                        <option value={otherEntity.id} key={otherEntity.id}>
                          {otherEntity.id}
                        </option>
                      ))
                    : null}
                </AvInput>
              </AvGroup>
              <AvGroup>
                <Label for="client-office">
                  <Translate contentKey="bankApp.client.office">Office</Translate>
                </Label>
                <AvInput id="client-office" type="select" className="form-control" name="officeId">
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
              <Button tag={Link} id="cancel-save" to="/client" replace color="info">
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
  images: storeState.image.entities,
  staff: storeState.staff.entities,
  offices: storeState.office.entities,
  clientEntity: storeState.client.entity,
  loading: storeState.client.loading,
  updating: storeState.client.updating,
  updateSuccess: storeState.client.updateSuccess,
});

const mapDispatchToProps = {
  getImages,
  getStaff,
  getOffices,
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ClientUpdate);
