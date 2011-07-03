package de.voolk.marbles.persistence.services;

import de.voolk.marbles.persistence.beans.User;

import java.util.Collection;

public interface IApplicationInitialisationService {
	void importAuthentificationData(Collection<User> users);
}