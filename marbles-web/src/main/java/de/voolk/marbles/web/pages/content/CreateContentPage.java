package de.voolk.marbles.web.pages.content;


import java.io.Serializable;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.web.pages.content.sidebar.EditPageSidebarPanel;

@AuthorizeInstantiation("user")
public class CreateContentPage extends EditContentPage {
    @SuppressWarnings("serial")
	static class NewPageWrapper implements IPage, Serializable {
		private final String name;
        private final boolean isRoot;
        private String content = "edit here ...";

        NewPageWrapper(String name, boolean root) {
            this.name = name;
            isRoot = root;
        }

        @Override
        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public boolean isRoot() {
            return isRoot;
        }

        @Override
        public Integer getId() {
            return null;
        }

		@Override
		public IPage getParent() {
			return null;
		}

    }

    public CreateContentPage(PageParameters parameters) {
        super(parameters);
    }

    @Override
    protected List<IPage> getMarblesPagePath() {
        List<IPage> marblesPagePath = getPageSession().getPagePath(getMarblesParentPage());
        marblesPagePath.add(getMarblesPage());
        return marblesPagePath;
    }

    protected String getMarblesPageName() {
        return getPageParameters().getString("name");
    }

    protected IPage getMarblesParentPage() {
        return getPageSession().findPageById(
        		getPageParameters().getAsInteger("parent"));
    }

    @Override
    protected IPage getMarblesPage() {
        return new NewPageWrapper(getMarblesPageName(),
                getMarblesParentPage() == null);
    }

    @Override
    protected int savePageContent(String content) {
        return getPageSession().createPage(getMarblesParentPage().getId(),
                getMarblesPageName(), content).getId();
    }

    @Override
    protected Component createSidebarPanel(String id) {
        return new EditPageSidebarPanel(this, id, getMarblesParentPage(),
        		getDisplayPageClass(), getPageSession());
    }


}
