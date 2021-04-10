import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SavingsAccount from './savings-account';
import SavingsAccountDetail from './savings-account-detail';
import SavingsAccountUpdate from './savings-account-update';
import SavingsAccountDeleteDialog from './savings-account-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SavingsAccountUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SavingsAccountUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SavingsAccountDetail} />
      <ErrorBoundaryRoute path={match.url} component={SavingsAccount} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={SavingsAccountDeleteDialog} />
  </>
);

export default Routes;
