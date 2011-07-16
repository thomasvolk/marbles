package de.voolk.marbles.api.pages;

import de.voolk.marbles.api.beans.IPage;

public interface IPageTraversationHandler {
	void handle(IPage currentPage, IPage parent);
}