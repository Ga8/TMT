package com.take.my.time.model;

public enum OpportunityStateEnum {

  RED("#EE1010"), ORANGE("#F7C940"), YELLOW("#C8EE37"), GREEN("#008E58");
	// red DB1702
	
	private String couleurCss;

	
	
	private OpportunityStateEnum(String couleurCss) {
		this.couleurCss = couleurCss;
	}

	public String getCouleurCss() {
		return couleurCss;
	}

	public void setCouleurCss(String couleurCss) {
		this.couleurCss = couleurCss;
	}
	
	
	
	
	
	
}
