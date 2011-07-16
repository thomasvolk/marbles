package de.voolk.marbles.persistence;

import de.voolk.marbles.api.pages.IPageTraversationHandler;
import de.voolk.marbles.persistence.beans.Page;

public class PageTraverser {
	private final IPageTraversationHandler traversationHandler;

	public PageTraverser(IPageTraversationHandler pageHandler) {
		this.traversationHandler = pageHandler;
	}

	private IPageTraversationHandler getTraversationHandler() {
		return traversationHandler;
	}

	public void traverse(Page page) {
		traverse(page, null);
	}

	public void traverse(Page page, Page parent) {
		getTraversationHandler().handle(page, parent);
		for(Page child: page.getChildren()) {
			traverse(child, page);
		}
	}
}
