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
package de.voolk.marbles.app;

import de.voolk.marbles.persistence.beans.User;
import de.voolk.marbles.persistence.utils.AuthDataImporter;

import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

public class AppConfig {
	private static final String DEFAULT_PROFILE = "default";
	private Properties properties;

	private String getProfileName() {
		return System.getProperty("marbles.env", DEFAULT_PROFILE);
	}

	private InputStream getFileInputStream(String name) {
		return getClass().getClassLoader().getResourceAsStream(
				 String.format("properties/%s/%s", getProfileName(), name));
	}

	private Properties loadProperties(String name) {
		Properties props = new Properties();
		try {
			props.load(getFileInputStream(String.format("%s.properties", name)));
		} catch (Exception e) {
			throw new RuntimeException("error loading properties", e);
		}
		return props;
	}
	
	public Properties getProperties() {
		if(properties == null) {
			properties = loadProperties("app");
		}
		return properties;
	}
	
	public Collection<User> getDefaultUsers() {
		return new AuthDataImporter().parse(
				getFileInputStream("users.xml")).getUsers();
	}
	
	public String get(String key) {
		return getProperties().getProperty(key);
	}

	public Boolean getBoolean(String key) {
		return Boolean.valueOf(get(key));
	}

}
