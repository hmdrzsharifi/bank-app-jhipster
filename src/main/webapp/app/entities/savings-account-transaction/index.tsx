import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SavingsAccountTransaction from './savings-account-transaction';
import SavingsAccountTransactionDetail from './savings-account-transaction-detail';
import SavingsAccountTransactionUpdate from './savings-account-transaction-update';
import SavingsAccountTransactionDeleteDialog from './savings-account-transaction-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SavingsAccountTransactionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SavingsAccountTransactionUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SavingsAccountTransactionDetail} />
      <ErrorBoundaryRoute path={match.url} component={SavingsAccountTransaction} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={SavingsAccountTransactionDeleteDialog} />
  </>
);

export default Routes;
