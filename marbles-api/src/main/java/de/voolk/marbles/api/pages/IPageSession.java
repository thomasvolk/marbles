package de.voolk.marbles.api.pages;

import de.voolk.marbles.api.beans.IPage;

import java.util.List;

public interface IPageSession {
    IPage createPage(int parentPageId, String name, String content);

    IPage findPageById(int id);

    void updatePage(int id, String content);

    IPage getRootPage();

    void removePage(int id);

    IPage findPageByParentAndName(IPage parent, String name);

    List<IPage> getPagePath(IPage page);
}
