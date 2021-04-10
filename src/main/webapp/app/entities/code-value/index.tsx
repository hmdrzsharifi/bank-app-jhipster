import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CodeValue from './code-value';
import CodeValueDetail from './code-value-detail';
import CodeValueUpdate from './code-value-update';
import CodeValueDeleteDialog from './code-value-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CodeValueUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CodeValueUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CodeValueDetail} />
      <ErrorBoundaryRoute path={match.url} component={CodeValue} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CodeValueDeleteDialog} />
  </>
);

export default Routes;
