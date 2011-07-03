package de.voolk.marbles.persistence.utils;

import com.thoughtworks.xstream.XStream;
import de.voolk.marbles.persistence.beans.Role;
import de.voolk.marbles.persistence.beans.User;

import java.io.InputStream;
import java.util.ArrayList;

public class AuthDataImporter {
	public static class AuthData {
		private ArrayList<User> users;

		public ArrayList<User> getUsers() {
			return users;
		}

		public void setUsers(ArrayList<User> users) {
			this.users = users;
		}
		
	}
	public AuthData parse(InputStream xmlData) {
		XStream xstream = new XStream();
		xstream.alias("auth", AuthData.class);
		xstream.alias("user", User.class);
		xstream.alias("role", Role.class);
		return (AuthData) xstream.fromXML(xmlData);
	}
	
	
}
