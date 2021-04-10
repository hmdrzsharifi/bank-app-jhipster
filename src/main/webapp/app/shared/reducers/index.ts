import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import locale, { LocaleState } from './locale';
import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
import sessions, { SessionsState } from 'app/modules/account/sessions/sessions.reducer';
// prettier-ignore
import client, {
  ClientState
} from 'app/entities/client/client.reducer';
// prettier-ignore
import office, {
  OfficeState
} from 'app/entities/office/office.reducer';
// prettier-ignore
import codeValue, {
  CodeValueState
} from 'app/entities/code-value/code-value.reducer';
// prettier-ignore
import appUser, {
  AppUserState
} from 'app/entities/app-user/app-user.reducer';
// prettier-ignore
import clientNonPerson, {
  ClientNonPersonState
} from 'app/entities/client-non-person/client-non-person.reducer';
// prettier-ignore
import image, {
  ImageState
} from 'app/entities/image/image.reducer';
// prettier-ignore
import savingsProduct, {
  SavingsProductState
} from 'app/entities/savings-product/savings-product.reducer';
// prettier-ignore
import savingsAccount, {
  SavingsAccountState
} from 'app/entities/savings-account/savings-account.reducer';
// prettier-ignore
import staff, {
  StaffState
} from 'app/entities/staff/staff.reducer';
// prettier-ignore
import savingsAccountTransaction, {
  SavingsAccountTransactionState
} from 'app/entities/savings-account-transaction/savings-account-transaction.reducer';
// prettier-ignore
import paymentDetails, {
  PaymentDetailsState
} from 'app/entities/payment-details/payment-details.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly locale: LocaleState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly sessions: SessionsState;
  readonly client: ClientState;
  readonly office: OfficeState;
  readonly codeValue: CodeValueState;
  readonly appUser: AppUserState;
  readonly clientNonPerson: ClientNonPersonState;
  readonly image: ImageState;
  readonly savingsProduct: SavingsProductState;
  readonly savingsAccount: SavingsAccountState;
  readonly staff: StaffState;
  readonly savingsAccountTransaction: SavingsAccountTransactionState;
  readonly paymentDetails: PaymentDetailsState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  locale,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  sessions,
  client,
  office,
  codeValue,
  appUser,
  clientNonPerson,
  image,
  savingsProduct,
  savingsAccount,
  staff,
  savingsAccountTransaction,
  paymentDetails,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
});

export default rootReducer;
