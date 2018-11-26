package com.assetmangement.calculations;

public class AmountHelper {

	private double[] ds;
	
	public AmountHelper(double...ds) {
		this.ds = ds;
	}

	public boolean allGreaterThenOrEqualZero() {
		for (int i = 0; i < ds.length; i++) {
			if(!(ds[i] >= 0.0)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean allGreaterThenZero() {
		for (int i = 0; i < ds.length; i++) {
			if(!(ds[i] > 0.0)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean allLessThenOrEqualZero() {
		for (int i = 0; i < ds.length; i++) {
			if(!(ds[i] <= 0.0)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean allLessThenZero() {
		for (int i = 0; i < ds.length; i++) {
			if(!(ds[i] < 0.0)) {
				return false;
			}
		}
		return true;
	}

}
