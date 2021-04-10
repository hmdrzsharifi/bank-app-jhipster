import { Moment } from 'moment';
import { ISavingsAccountTransaction } from 'app/shared/model/savings-account-transaction.model';

export interface ISavingsAccount {
  id?: number;
  accountNumber?: string;
  externalId?: string;
  status?: number;
  subStatus?: number;
  accountType?: number;
  submittedOnDate?: string;
  rejectedOnDate?: string;
  approvedOnDate?: string;
  nominalAnnualInterestRate?: number;
  interestCompoundingPeriodType?: number;
  interestPostingPeriodType?: number;
  interestCalculationType?: number;
  interestCalculationDaysInYearType?: number;
  minRequiredOpeningBalance?: number;
  lockinPeriodFrequency?: number;
  lockinPeriodFrequencyType?: number;
  nominalAnnualInterestRateOverdraft?: number;
  minOverdraftForInterestCalculation?: number;
  minBalanceForInterestCalculation?: number;
  enforceMinRequiredBalance?: boolean;
  minRequiredBalance?: number;
  withdrawalFeeApplicableForTransfer?: boolean;
  allowOverdraft?: boolean;
  overdraftLimit?: number;
  savingsAccountTransactions?: ISavingsAccountTransaction[];
}

export const defaultValue: Readonly<ISavingsAccount> = {
  enforceMinRequiredBalance: false,
  withdrawalFeeApplicableForTransfer: false,
  allowOverdraft: false,
};
