import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <MenuItem icon="asterisk" to="/client">
      <Translate contentKey="global.menu.entities.client" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/office">
      <Translate contentKey="global.menu.entities.office" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/code-value">
      <Translate contentKey="global.menu.entities.codeValue" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/app-user">
      <Translate contentKey="global.menu.entities.appUser" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/client-non-person">
      <Translate contentKey="global.menu.entities.clientNonPerson" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/image">
      <Translate contentKey="global.menu.entities.image" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/savings-product">
      <Translate contentKey="global.menu.entities.savingsProduct" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/savings-account">
      <Translate contentKey="global.menu.entities.savingsAccount" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/staff">
      <Translate contentKey="global.menu.entities.staff" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/savings-account-transaction">
      <Translate contentKey="global.menu.entities.savingsAccountTransaction" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/payment-details">
      <Translate contentKey="global.menu.entities.paymentDetails" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
