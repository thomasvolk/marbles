package de.voolk.marbles.web.pages.content.panel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import org.apache.wicket.extensions.markup.html.tree.DefaultAbstractTree.LinkType;
import org.apache.wicket.extensions.markup.html.tree.Tree;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.html.tree.ITreeStateListener;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.api.pages.IPageTraversationHandler;
import de.voolk.marbles.pages.IPageRepository;
import de.voolk.marbles.web.app.IdentSession;

public class SiteMapPanel extends Panel implements IPageTraversationHandler {
	public interface ISiteMapListener {
		void pageSelected(IPage page);
	}
	private class TreeStateListener implements ITreeStateListener, Serializable {
		private static final long serialVersionUID = 1L;
		private final ISiteMapListener listener;

		public TreeStateListener(ISiteMapListener listener) {
			this.listener = listener;
		}

		private ISiteMapListener getListener() {
			return listener;
		}

		@Override
		public void nodeUnselected(Object node) {
		}

		@Override
		public void nodeSelected(Object node) {
			DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) node;
			PageEntity userObject = (PageEntity) treeNode.getUserObject();
			if(userObject != null) {
				getListener().pageSelected(userObject.getPage());
			}
		}

		@Override
		public void nodeExpanded(Object arg0) {
		}

		@Override
		public void nodeCollapsed(Object arg0) {
		}

		@Override
		public void allNodesExpanded() {
		}

		@Override
		public void allNodesCollapsed() {
		}
	}
	private class PageEntity {
		private final IPage page;

		public PageEntity(IPage page) {
			super();
			this.page = page;
		}

		public IPage getPage() {
			return page;
		}

		public String toString() {
			return getPage().getName();
		}
	}
	private static final long serialVersionUID = 1L;
	private DefaultMutableTreeNode rootNode;
	private Map<Integer, DefaultMutableTreeNode> nodes;
	@SpringBean
    private IPageRepository pageRepository;

	public SiteMapPanel(String id, int rootPageId, ISiteMapListener listener) {
		super(id);
		IPageSession pageSession = pageRepository.createSession(getIdentSession().getUser());
		pageSession.traverse(rootPageId, this);
        TreeModel treeModel = new DefaultTreeModel(rootNode);

		@SuppressWarnings("serial")
		Tree tree = new Tree("tree", treeModel)
        {
            @Override
            protected String renderNode(TreeNode node)
            {
                DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)node;
                return  String.valueOf(treeNode.getUserObject());
            }
        };
        tree.setLinkType(LinkType.REGULAR);
        tree.getTreeState().addTreeStateListener(new TreeStateListener(listener));
        add(tree);
	}

	public Map<Integer, DefaultMutableTreeNode> getNodes() {
		if(nodes == null) {
			nodes = new HashMap<Integer, DefaultMutableTreeNode>();
		}
		return nodes;
	}

	@Override
	public void handle(IPage currentPage, IPage parent) {
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(new PageEntity(currentPage));
		getNodes().put(currentPage.getId(), node);
		if(parent == null) {
			rootNode = node;
		}
		else {
			getNodes().get(parent.getId()).add(node);
		}
	}

	protected IdentSession getIdentSession() {
        return ((IdentSession) getSession());
    }
}
