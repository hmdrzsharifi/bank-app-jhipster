import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SavingsProduct from './savings-product';
import SavingsProductDetail from './savings-product-detail';
import SavingsProductUpdate from './savings-product-update';
import SavingsProductDeleteDialog from './savings-product-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SavingsProductUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SavingsProductUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SavingsProductDetail} />
      <ErrorBoundaryRoute path={match.url} component={SavingsProduct} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={SavingsProductDeleteDialog} />
  </>
);

export default Routes;
