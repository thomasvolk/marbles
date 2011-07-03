package de.voolk.marbles.pages;


import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.persistence.beans.User;

public interface IPageRepository {
    IPageSession createSession(User user);
}
