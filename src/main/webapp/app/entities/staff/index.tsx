import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Staff from './staff';
import StaffDetail from './staff-detail';
import StaffUpdate from './staff-update';
import StaffDeleteDialog from './staff-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={StaffUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={StaffUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={StaffDetail} />
      <ErrorBoundaryRoute path={match.url} component={Staff} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={StaffDeleteDialog} />
  </>
);

export default Routes;
