package com.assetmangement.calculations;

/**
 * APR Annual Percentage Rate Calculator
 * 
 * @author mikep
 */
public abstract class AnnualPercentageRateCalculator {

	/**
	 * The APR is what economists call an "internal rate of return" (IRR), or the
	 * discount rate that equates a future stream of dollars with the present value
	 * of that stream. In the case of a home mortgage, the formula is
	 * 
	 * The APR is a special case of the IRR, because it assumes that the loan runs
	 * to term. In the equation, this means that n is equal to the term, and Bn is
	 * zero.
	 * 
	 * L - F = P_1/(1 + i) + P_2/(1 + i)^2 +… (P_n + B_n)/(1 + i)^n
	 * 
	 * @return the calculated APR
	 */
	public static double calculateAPR(int amountMonths, boolean fixedPayment, boolean variablePayment) {
		return recursiveSummation(amountMonths, fixedPayment, variablePayment);
	}

	/**
	 * This equation can be solved for i only through a series of successive
	 * approximations, which must be done by computer. This calculator will also
	 * calculate it provided that all the values of P (Monthly Payments) are the
	 * same (fixed).
	 * 
	 * @param amountMonths
	 *            is the amount of months
	 * @return
	 */
	private static double recursiveSummation(int amountMonths, boolean fixedPayment, boolean variablePayment) {
		// Check variables passed in
		if (fixedPayment && variablePayment) {
			System.out.println(
					"The calling method must specify either the a fixed payment or a vairable payment, but not both");
			return 0.0;
		} else if (!fixedPayment && !variablePayment) {
			System.out.println("The calling method must specify either the a fixed payment or a vairable payment.");
			return 0.0;
		}

		// Calculate based on payment type
		if (fixedPayment) {
			return fixedMonthlyPayment(amountMonths);
		}

		if (variablePayment) {
			return variableMonthlyPayment(amountMonths);
		}

		return 0.0;
	}

	/**
	 * Recursion for variable monthly payments
	 * 
	 * @param amountMonths
	 * @return
	 */
	private static int variableMonthlyPayment(int amountMonths) {
		if (amountMonths == 1) {
			return 1;
		}
		return variableMonthlyPayment(amountMonths - 1) + amountMonths;

	}

	/**
	 * Recursion for fixed monthly payments
	 * 
	 * @param amountMonths
	 * @return
	 */
	private static int fixedMonthlyPayment(int amountMonths) {
		if (amountMonths == 1) {
			return 1;
		}
		return fixedMonthlyPayment(amountMonths - 1) + amountMonths;

	}
}
