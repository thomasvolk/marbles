package de.voolk.marbles.api.pages.render;

import de.voolk.marbles.api.beans.IPage;

public interface ILinkResolver {
    String renderPageLink(IPage parentPage, String name);
}
