/**
 * Copyright (C) 2010  Thomas Volk
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.voolk.marbles.web.pages.content;

import java.io.Serializable;
import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.PageParameters;
import org.apache.wicket.authorization.strategies.role.annotations.AuthorizeInstantiation;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.web.pages.content.sidebar.EditSidebar;

@AuthorizeInstantiation("user")
public class CreatePage extends EditPage {
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

    public CreatePage(PageParameters parameters) {
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
        return new EditSidebar(id, getMarblesPage(),
        		getDisplayPageClass());
    }


}
