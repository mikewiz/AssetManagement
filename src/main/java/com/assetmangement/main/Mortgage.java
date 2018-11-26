package com.assetmangement.main;

import java.io.IOException;

import com.assetmangement.calculations.MortgageCalculator;
import com.assetmangement.graphs.MortgageGraph;
import com.assetmangement.graphs.RefineryUtilities;

public class Mortgage extends MortgageCalculator {

	private static double domPesoConv = 49.76;
	private static double interestRate;
	private static double loanAmount;
	private static double amountOfMonths;
	private static double afterAmountOfMonths;
	private static int intervals;
	static {
		interestRate = 10.95;
		loanAmount = 860000.00 / domPesoConv;
		amountOfMonths = 240;
		afterAmountOfMonths = 12;
		intervals = 12;
	}

	public Mortgage() {
		try {
			MortgageGraph mg = new MortgageGraph("Vanessa's House", interestRate, loanAmount, amountOfMonths, afterAmountOfMonths, intervals);
			mg.pack();
			RefineryUtilities.centerFrameOnScreen(mg);
			mg.setVisible(true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	public static void main(String[] args) {
		
		double monthlyPaymentAndLoanBalance = monthlyPaymentAndLoanBalance(interestRate, loanAmount, amountOfMonths);
		System.out.printf("Your Montly Payment: $%.2f \t Pesos: $%.2f \n", monthlyPaymentAndLoanBalance,
				(monthlyPaymentAndLoanBalance * domPesoConv));
		double remainingLoanBalance = remainingLoanBalance(interestRate, loanAmount, amountOfMonths,
				afterAmountOfMonths);
		System.out.printf("Initial Loan Amount: $%.2f \t Pesos: $%.2f \n", loanAmount, (loanAmount * domPesoConv));
		System.out.println("");
		System.out.printf("After %.0f/240 months your remaining loan balance: $%.2f \t Pesos: $%.2f \n", afterAmountOfMonths,
				remainingLoanBalance, (remainingLoanBalance * domPesoConv));
		new Mortgage();
	}
}
