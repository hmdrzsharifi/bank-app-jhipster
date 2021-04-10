import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './office.reducer';
import { IOffice } from 'app/shared/model/office.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOfficeProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Office = (props: IOfficeProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { officeList, match, loading } = props;
  return (
    <div>
      <h2 id="office-heading">
        <Translate contentKey="bankApp.office.home.title">Offices</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="bankApp.office.home.createLabel">Create new Office</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {officeList && officeList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.office.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.office.openingDate">Opening Date</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.office.externalId">External Id</Translate>
                </th>
                <th>
                  <Translate contentKey="bankApp.office.parent">Parent</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {officeList.map((office, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${office.id}`} color="link" size="sm">
                      {office.id}
                    </Button>
                  </td>
                  <td>{office.name}</td>
                  <td>{office.openingDate ? <TextFormat type="date" value={office.openingDate} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{office.externalId}</td>
                  <td>{office.parentId ? <Link to={`office/${office.parentId}`}>{office.parentId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${office.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${office.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${office.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="bankApp.office.home.notFound">No Offices found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ office }: IRootState) => ({
  officeList: office.entities,
  loading: office.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Office);
