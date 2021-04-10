import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Office from './office';
import OfficeDetail from './office-detail';
import OfficeUpdate from './office-update';
import OfficeDeleteDialog from './office-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={OfficeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={OfficeUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={OfficeDetail} />
      <ErrorBoundaryRoute path={match.url} component={Office} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={OfficeDeleteDialog} />
  </>
);

export default Routes;
