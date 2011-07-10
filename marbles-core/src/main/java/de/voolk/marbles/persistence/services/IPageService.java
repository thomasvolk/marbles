package de.voolk.marbles.persistence.services;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.api.beans.IUser;
import de.voolk.marbles.persistence.beans.User;

import java.util.List;

public interface IPageService {

    void updatePage(IUser user, int id, String content);

	IPage findPageByUserAndId(IUser user, int id);

    IPage findPageByUserAndParentAndName(IUser user, IPage parent, String name);

	void removePage(IUser user, int id);

	IPage getRootPage(IUser user);

    IPage createPage(IUser user, int parentPageId, String name, String content);

    List<IPage> getPagePath(IUser user, IPage page);

	void removeAllPages(User user);

	boolean hasChildren(IPage page);
}