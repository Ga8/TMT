package com.take.my.time.model;

public enum OpportunityStateEnum {

  RED("red"), ORANGE("yellow"), YELLOW("indigo"), GREEN("green");
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
