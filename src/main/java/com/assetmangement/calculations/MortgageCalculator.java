package com.assetmangement.calculations;

import static com.assetmangement.constants.AssetManagementConstants.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class MortgageCalculator {

	/**
	 * The following formula is used to calculate the fixed monthly payment
	 * fixedMonthlyPayment required to fully amortize a loan of loanAmount dollars
	 * over a term of amountOfMonths months at a monthly interest rate of
	 * interestRate
	 * 
	 * Formula P = L[c(1 + c)^n]/[(1 + c)^n - 1]
	 * 
	 * @param interestRate
	 * @param loanAmount
	 * @param amountOfMonths
	 * @return
	 */
	public static double monthlyPaymentAndLoanBalance(double interestRate, double loanAmount, double amountOfMonths) {

		double fixedMonthlyPayment = 0.0;
		AmountHelper amountHelper = new AmountHelper(interestRate, loanAmount, amountOfMonths);
		if (amountHelper.allGreaterThenZero()) {

			double realMonthlyInterestRate = calculateMonthlyInterestRate(interestRate);
			double compIR = onePlusIRCompounded(realMonthlyInterestRate, amountOfMonths);

			// Calculate the Fixed Monthly Payment
			fixedMonthlyPayment = (loanAmount * realMonthlyInterestRate * compIR) / (compIR - 1.0);
		}

		return fixedMonthlyPayment;
	}

	/**
	 * This formula is used to calculate the remaining loan balance (B) of a fixed
	 * payment loan after amountOfMonths.
	 * 
	 * Formula B = L[(1 + c)^n - (1 + c)^p]/[(1 + c)^n - 1]
	 * 
	 * @param interestRate
	 * @param loanAmount
	 * @param amountOfMonths
	 * @return
	 */
	public static double remainingLoanBalance(double interestRate, double loanAmount, double amountOfMonths,
			double afterAmountOfMonths) {

		double remainingLoanBalance = 0.0;
		double fixedMonthlyPayment = monthlyPaymentAndLoanBalance(interestRate, loanAmount, amountOfMonths);
		AmountHelper amountHelper = new AmountHelper(fixedMonthlyPayment, interestRate, loanAmount, amountOfMonths);
		if (amountHelper.allGreaterThenZero()) {

			double realMonthlyInterestRate = calculateMonthlyInterestRate(interestRate);
			double compIR_FullTerm = onePlusIRCompounded(realMonthlyInterestRate, amountOfMonths);
			double compIR_AfterAmountOfMonthsSpecified = onePlusIRCompounded(realMonthlyInterestRate, afterAmountOfMonths);

			// Calculate the Remaining Loan Balance
			remainingLoanBalance = loanAmount * (compIR_FullTerm - compIR_AfterAmountOfMonthsSpecified)/(compIR_FullTerm - 1);
		}

		return remainingLoanBalance;
	}
	
	public  double remainingLoanBalance2(double interestRate, double loanAmount, double amountOfMonths,
			double afterAmountOfMonths) {

		double remainingLoanBalance = 0.0;
		double fixedMonthlyPayment = monthlyPaymentAndLoanBalance(interestRate, loanAmount, amountOfMonths);
		AmountHelper amountHelper = new AmountHelper(fixedMonthlyPayment, interestRate, loanAmount, amountOfMonths);
		if (amountHelper.allGreaterThenZero()) {

			double realMonthlyInterestRate = calculateMonthlyInterestRate(interestRate);
			double compIR_FullTerm = onePlusIRCompounded(realMonthlyInterestRate, amountOfMonths);
			double compIR_AfterAmountOfMonthsSpecified = onePlusIRCompounded(realMonthlyInterestRate, afterAmountOfMonths);

			// Calculate the Remaining Loan Balance
			remainingLoanBalance = loanAmount * (compIR_FullTerm - compIR_AfterAmountOfMonthsSpecified)/(compIR_FullTerm - 1);
		}

		return remainingLoanBalance;
	}

	private static double onePlusIRCompounded(double realMonthlyInterestRate, double amountOfMonths) {
		double compoundedInterestRate = Math.pow((1.0 + realMonthlyInterestRate), amountOfMonths);
		return compoundedInterestRate;
	}

	/**
	 * Calculate the monthly interest rate from the given interest rate
	 * 
	 * example: quoted rate is 6%, for example, c is .06/12 or .005].
	 * 
	 * @param monthlyInterestRate
	 * @return
	 */
	private static double calculateMonthlyInterestRate(double interestRate) {
		if (interestRate < 100.0 && interestRate > 0.0) {
			double adjustedInterestRate = adjustInterestRate(interestRate);
			double adjustedMonthlyInterestRate = (adjustedInterestRate / MONTH);
			return adjustedMonthlyInterestRate;
		} else {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Interest rate cannot be above 100 percent or below 0 percent.");
			System.out.println("Enter new interest rate: ");
			try {
				String s = br.readLine();
				double d = Double.parseDouble(s);
				return calculateMonthlyInterestRate(d);
			} catch (NumberFormatException e) {
				System.out.println("Try again!");
				return calculateMonthlyInterestRate(100.0);
			} catch (IOException e) {
				System.out.println("Malformed Input, Try again!");
				return calculateMonthlyInterestRate(100.0);
			}
		}

	}

	/**
	 * Adjusting the interest rate to a value that can be used for calculations.
	 * 
	 * 10.95% shifted to the right two decimal places --> 0.1095
	 * 
	 * @param interestRate
	 * @return
	 */
	private static double adjustInterestRate(double interestRate) {
		double adjustedInterestRate = interestRate * PERCENTAGE_FACTOR;
		return adjustedInterestRate;
	}

}
