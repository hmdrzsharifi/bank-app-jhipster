import { Moment } from 'moment';

export interface ISavingsAccountTransaction {
  id?: number;
  transactionType?: number;
  reversed?: boolean;
  dateOf?: string;
  amount?: number;
  overdraftAmount?: number;
  balanceEndDate?: string;
  balanceNumberOfDays?: number;
  runningBalance?: number;
  cumulativeBalance?: number;
  createdDate?: string;
  isManualTransaction?: boolean;
  releaseIdOfHoldAmountTransaction?: number;
  savingsAccountId?: number;
}

export const defaultValue: Readonly<ISavingsAccountTransaction> = {
  reversed: false,
  isManualTransaction: false,
};
