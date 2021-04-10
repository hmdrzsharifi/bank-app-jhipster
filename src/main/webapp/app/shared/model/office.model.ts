import { Moment } from 'moment';

export interface IOffice {
  id?: number;
  name?: string;
  openingDate?: string;
  externalId?: string;
  children?: IOffice[];
  parentId?: number;
}

export const defaultValue: Readonly<IOffice> = {};
