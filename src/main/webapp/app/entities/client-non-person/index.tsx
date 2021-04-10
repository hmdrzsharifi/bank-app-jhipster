import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ClientNonPerson from './client-non-person';
import ClientNonPersonDetail from './client-non-person-detail';
import ClientNonPersonUpdate from './client-non-person-update';
import ClientNonPersonDeleteDialog from './client-non-person-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ClientNonPersonUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ClientNonPersonUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ClientNonPersonDetail} />
      <ErrorBoundaryRoute path={match.url} component={ClientNonPerson} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ClientNonPersonDeleteDialog} />
  </>
);

export default Routes;
