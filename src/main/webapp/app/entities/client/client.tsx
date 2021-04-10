import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './client.reducer';
import { IClient } from 'app/shared/model/client.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IClientProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Client = (props: IClientProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { clientList, match, loading } = props;
  return (
    <div>
      <h2 id="client-heading">
        <Translate contentKey="bankApp.client.home.title">Clients</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="bankApp.client.home.createLabel">Create new Client</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {clientList && clientList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.client.accountNumber">Account Number</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.client.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.client.activationDate">Activation Date</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.client.firstname">Firstname</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.client.lastname">Lastname</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.client.middlename">Middlename</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.client.fullname">Fullname</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.client.displayName">Display Name</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.client.mobileNo">Mobile No</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.client.emailAddress">Email Address</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.client.isStaff">Is Staff</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.client.externalId">External Id</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.client.dateOfBirth">Date Of Birth</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.client.closureDate">Closure Date</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.client.rejectionDate">Rejection Date</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.client.reactivateDate">Reactivate Date</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.client.submittedOnDate">Submitted On Date</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.client.image">Image</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.client.staff">Staff</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.client.office">Office</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {clientList.map((client, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${client.id}`} color="link" size="sm">
                      {client.id}
                    </Button>
                  </td>
                  <td>{client.accountNumber}</td>
                  <td>{client.status}</td>
                  <td>
                    {client.activationDate ? <TextFormat type="date" value={client.activationDate} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{client.firstname}</td>
                  <td>{client.lastname}</td>
                  <td>{client.middlename}</td>
                  <td>{client.fullname}</td>
                  <td>{client.displayName}</td>
                  <td>{client.mobileNo}</td>
                  <td>{client.emailAddress}</td>
                  <td>{client.isStaff ? 'true' : 'false'}</td>
                  <td>{client.externalId}</td>
                  <td>{client.dateOfBirth ? <TextFormat type="date" value={client.dateOfBirth} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{client.closureDate ? <TextFormat type="date" value={client.closureDate} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{client.rejectionDate ? <TextFormat type="date" value={client.rejectionDate} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>
                    {client.reactivateDate ? <TextFormat type="date" value={client.reactivateDate} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {client.submittedOnDate ? <TextFormat type="date" value={client.submittedOnDate} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{client.imageId ? <Link to={`image/${client.imageId}`}>{client.imageId}</Link> : ''}</td>
                  <td>{client.staffId ? <Link to={`staff/${client.staffId}`}>{client.staffId}</Link> : ''}</td>
                  <td>{client.officeId ? <Link to={`office/${client.officeId}`}>{client.officeId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${client.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${client.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${client.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="bankApp.client.home.notFound">No Clients found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ client }: IRootState) => ({
  clientList: client.entities,
  loading: client.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Client);
