package de.voolk.marbles.web.pages.base;

import org.apache.wicket.protocol.http.ClientProperties;

public enum ClinetType {
	MOBILE, WEB;
	
	public static ClinetType getInstace(ClientProperties clientProperties) {
		ClinetType variant;
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
	
}
