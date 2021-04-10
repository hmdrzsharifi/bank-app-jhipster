import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Client from './client';
import Office from './office';
import CodeValue from './code-value';
import AppUser from './app-user';
import ClientNonPerson from './client-non-person';
import Image from './image';
import SavingsProduct from './savings-product';
import SavingsAccount from './savings-account';
import Staff from './staff';
import SavingsAccountTransaction from './savings-account-transaction';
import PaymentDetails from './payment-details';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}client`} component={Client} />
      <ErrorBoundaryRoute path={`${match.url}office`} component={Office} />
      <ErrorBoundaryRoute path={`${match.url}code-value`} component={CodeValue} />
      <ErrorBoundaryRoute path={`${match.url}app-user`} component={AppUser} />
      <ErrorBoundaryRoute path={`${match.url}client-non-person`} component={ClientNonPerson} />
      <ErrorBoundaryRoute path={`${match.url}image`} component={Image} />
      <ErrorBoundaryRoute path={`${match.url}savings-product`} component={SavingsProduct} />
      <ErrorBoundaryRoute path={`${match.url}savings-account`} component={SavingsAccount} />
      <ErrorBoundaryRoute path={`${match.url}staff`} component={Staff} />
      <ErrorBoundaryRoute path={`${match.url}savings-account-transaction`} component={SavingsAccountTransaction} />
      <ErrorBoundaryRoute path={`${match.url}payment-details`} component={PaymentDetails} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
