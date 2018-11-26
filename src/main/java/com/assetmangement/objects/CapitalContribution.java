package com.assetmangement.objects;

public class CapitalContribution {
	public static final String info_descripton = "This is money or property that has been invested by the individual and into the LLC. This can consist of property, cash, or any tanglible or intangible asset.";
	public static final String example1 = "e.g. \"$25,000 in cash and a 2010 Ford F-150\"";
	public static final String name = "Capital Contributions";

	private boolean singleMemberContribution;

	public CapitalContribution() {
		singleMemberContribution = false;
	}

	public boolean getSingleMemberContribution() {
		return singleMemberContribution;
	}

	public void setSingleMemberContribution(boolean setValue) {
		this.singleMemberContribution = setValue;
	}

	@Override
	public String toString() {
		return name + " - " + info_descripton + "\n" + example1;
	}
}
