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
