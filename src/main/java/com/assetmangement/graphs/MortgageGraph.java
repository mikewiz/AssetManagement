package com.assetmangement.graphs;

import java.io.IOException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import static com.assetmangement.calculations.MortgageCalculator.*;

public class MortgageGraph extends org.jfree.chart.ui.ApplicationFrame {
    private static final long serialVersionUID = 1L;
    XYSeriesCollection dataset;
    JFreeChart chart;
    XYSeries series;
    final ChartPanel chartPanel;
    final int chartWidth = 560;
    final int chartHeight = 367;
    private String applicationTitle;
    private double interestRate, loanAmount, amountOfMonths, afterAmountOfMonths;
    private int intervals;

    public MortgageGraph(String applicationTitle, double interestRate, double loanAmount, double amountOfMonths, double afterAmountOfMonths, int intervals) throws IOException {
        super(applicationTitle);
        this.setApplicationTitle(applicationTitle);
        this.interestRate = interestRate;
        this.loanAmount = loanAmount;
        this.amountOfMonths = amountOfMonths;
        this.afterAmountOfMonths = afterAmountOfMonths;
        this.intervals = intervals;

        //Start Graphing
        dataset = createDataset();
        chart = createChart(dataset);
        chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(chartHeight,
                chartWidth));
        this.add(chartPanel);
    }

	public XYSeriesCollection createDataset() throws NumberFormatException,
            IOException {
        dataset = new XYSeriesCollection();
        // Set up series
        final XYSeries seriesX = new XYSeries("X");
        final XYSeries seriesY = new XYSeries("Y");
        
        int i = 1;
        while ((i*intervals) <= amountOfMonths) {
            // add values to dataset
            seriesX.add((i*intervals), remainingLoanBalance(interestRate, loanAmount, amountOfMonths, (i*intervals)));
            i++;
        }
        
        double total = 0.0;
        for (int j = 1; j <= amountOfMonths; j++) {
        	// add values to dataset
        	double monthlyPaymentAndLoanBalance = monthlyPaymentAndLoanBalance(interestRate, loanAmount, amountOfMonths);
        	total += monthlyPaymentAndLoanBalance;
            seriesY.add(j, total);
		}
        
        
        
        System.out.println(seriesX.getMaxX() + "; " + seriesX.getMaxY());
        System.out.println(seriesY.getMaxX() + "; " + seriesY.getMaxY());
        dataset.addSeries(seriesX);
        dataset.addSeries(seriesY);
        
        double rate = 400.00;
        int monthsToRent = 0;
        if (monthsToRent != 0) {
        	amountOfMonthsYouPlanToRent(rate, monthsToRent, dataset);
		}
        
        return dataset;
    }

	private void amountOfMonthsYouPlanToRent(double rate, int monthsToRent, XYSeriesCollection dataset2) {
		final XYSeries seriesZ = new XYSeries("Z");
		double totalRent = 0.0;
        for (int k = 0; k < amountOfMonths; k++) {
        	totalRent += 400;
        	seriesZ.add(k, totalRent);
		}
		dataset.addSeries(seriesZ);
		
	}

	public JFreeChart createChart(XYDataset dataset)
            throws NumberFormatException, IOException {
        chart = ChartFactory.createXYLineChart("Vanessa's House Mortgage", // chart title
                "Time", // domain axis label
                "Loan Amount", // range axis label
                dataset, // data
                PlotOrientation.VERTICAL, // the plot orientation
                true, // legend
				true, // tooltips
                false); // urls

        return chart;
    }

	public String getApplicationTitle() {
		return applicationTitle;
	}

	public void setApplicationTitle(String applicationTitle) {
		this.applicationTitle = applicationTitle;
	}
}