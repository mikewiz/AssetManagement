package com.assetmanagement;

import static com.goddownloader.constants.UrlConstants.IRSGlossary;
import static com.goddownloader.constants.UrlConstants.javaCompleteReference;
import static com.goddownloader.constants.UrlConstants.listofavailablefreefillableforms;
import static com.goddownloader.constants.UrlConstants.llcfilingasacorporationorpartnership;

import java.util.ArrayList;

public class test {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<>();
		list.add(llcfilingasacorporationorpartnership);
		list.add(listofavailablefreefillableforms);
		list.add(javaCompleteReference);
		list.add(IRSGlossary);
		
		String searchString = "https://www.irs.gov/businesses/small-businesses-self-employed/llc-filing-as-a-corporation-or-partnership";
		
		boolean match = list.stream().anyMatch(s -> searchString.contains(s));
		
		System.out.println(match);
	}

}
