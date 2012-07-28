package de.voolk.marbles.web.pages.base;

import org.apache.wicket.protocol.http.ClientProperties;

public enum ClientType {
	MOBILE, WEB;
	
	private static final String CSS_BASE_NAME = "default"; 
	
	public static ClientType getInstace(ClientProperties clientProperties) {
		ClientType variant;
		String navigatorUserAgent = clientProperties.getNavigatorUserAgent();
		if(navigatorUserAgent.contains("Android") || 
				clientProperties.getBrowserWidth() < 760) {
			variant = MOBILE;
		}
		else {
			variant = WEB;
		}
		return variant;
	}
	
	public String getVariant() {
		if(this == WEB) {
			return null;
		}
		else {
			return name().toLowerCase();
		}
	}
	
	public String getStylesheet() {
		if(this == WEB) {
			return String.format("%s.css", CSS_BASE_NAME);
		}
		else {
			return String.format("%s_%s.css", CSS_BASE_NAME, getVariant());
		}
	}
	
}
