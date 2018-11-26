package com.assetmangement.llc;

import java.util.ArrayList;
import com.assetmangement.objects.Asset;

public class LLC_Asset extends Asset {

	public static final String ASSET_NAME = "LLC";
	private String nameOfLLC;

	public LLC_Asset(String nameOfLLC) {
		this.nameOfLLC = nameOfLLC;
	}

	public String getName() {
		return ASSET_NAME;
	}

	public ArrayList<Document> getIncorporationDocumentList() {
		// TODO Auto-generated method stub
		return null;
	}

	public String toString() {
		return this.nameOfLLC + " llc";
	}

	public void setName(String name) {
		nameOfLLC = name;
	}
	
	class Document{
		
	}

}
