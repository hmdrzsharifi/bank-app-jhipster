export interface ISavingsProduct {
  id?: number;
  name?: string;
  shortName?: string;
  description?: string;
  nominalAnnualInterestRate?: number;
  interestCompoundingPeriodType?: number;
  interestPostingPeriodType?: number;
  interestCalculationType?: number;
  interestCalculationDaysInYearType?: number;
  minRequiredOpeningBalance?: number;
  lockinPeriodFrequency?: number;
  lockinPeriodFrequencyType?: number;
  accountingRule?: number;
  withdrawalFeeApplicableForTransfer?: boolean;
  allowOverdraft?: boolean;
  overdraftLimit?: number;
  nominalAnnualInterestRateOverdraft?: number;
  minOverdraftForInterestCalculation?: number;
  enforceMinRequiredBalance?: boolean;
  minRequiredBalance?: number;
  minBalanceForInterestCalculation?: number;
  withHoldTax?: boolean;
  isDormancyTrackingActive?: boolean;
  daysToInactive?: number;
  daysToDormancy?: number;
  daysToEscheat?: number;
}

export const defaultValue: Readonly<ISavingsProduct> = {
  withdrawalFeeApplicableForTransfer: false,
  allowOverdraft: false,
  enforceMinRequiredBalance: false,
  withHoldTax: false,
  isDormancyTrackingActive: false,
};
