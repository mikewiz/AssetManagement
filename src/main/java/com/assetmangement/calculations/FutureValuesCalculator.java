package com.assetmangement.calculations;

/**
 * Calculator measure financial results in terms of "future values" -- the
 * borrower's net wealth at the end of a specified period.
 *
 * @author mikep
 *
 */
public abstract class FutureValuesCalculator {

	public static double FVn_singleSum; // is the value of the single sum after n periods
	public static double FVn_seriesOfPayments; // is the value of the single sum after n periods
	public static double S; // is the amount of the single sum now
	public static double interestRate; // is the applicable interest rate
	public static double n; // is the length of the period

	public FutureValuesCalculator(double S, double c, double n) {
		FutureValuesCalculator.S = S;
		FutureValuesCalculator.interestRate = c;
		FutureValuesCalculator.n = n;
	}

	/**
	 * The future value of a single sum today is
	 * 
	 * formula: FV_n = S(1+c)^n
	 * 
	 * @return
	 */
	public static double singleSumFutureValue() {
		FVn_singleSum = S * Math.pow((1 + interestRate), n);
		return FVn_singleSum;
	}

	/**
	 * The future value of a series of payments of equal size, beginning after one period, is
	 * 
	 * formula:
	 * FV_n = P[(1+c)^n - 1]/c
	 * 
	 * @return
	 */
	public static double futureValueOfSeriesOfPayments(double payment) {
		FVn_seriesOfPayments = payment * (Math.pow((1 + interestRate), n) - 1) / interestRate;
		return FVn_seriesOfPayments;
	}
}
