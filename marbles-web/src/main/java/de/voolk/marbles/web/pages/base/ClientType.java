/**
 * Copyright (C) 2010  Thomas Volk
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
