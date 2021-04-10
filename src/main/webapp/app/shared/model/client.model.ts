import { Moment } from 'moment';

export interface IClient {
  id?: number;
  accountNumber?: string;
  status?: number;
  activationDate?: string;
  firstname?: string;
  lastname?: string;
  middlename?: string;
  fullname?: string;
  displayName?: string;
  mobileNo?: string;
  emailAddress?: string;
  isStaff?: boolean;
  externalId?: string;
  dateOfBirth?: string;
  closureDate?: string;
  rejectionDate?: string;
  reactivateDate?: string;
  submittedOnDate?: string;
  imageId?: number;
  staffId?: number;
  officeId?: number;
}

export const defaultValue: Readonly<IClient> = {
  isStaff: false,
};
