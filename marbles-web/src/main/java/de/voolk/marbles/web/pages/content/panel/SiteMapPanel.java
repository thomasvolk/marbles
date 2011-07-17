package de.voolk.marbles.web.pages.content.panel;

import java.util.HashMap;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import org.apache.wicket.extensions.markup.html.tree.Tree;
import org.apache.wicket.extensions.markup.html.tree.DefaultAbstractTree.LinkType;
import org.apache.wicket.markup.html.panel.Panel;

import de.voolk.marbles.api.beans.IPage;
import de.voolk.marbles.api.pages.IPageSession;
import de.voolk.marbles.api.pages.IPageTraversationHandler;

public class SiteMapPanel extends Panel implements IPageTraversationHandler {
	private static final long serialVersionUID = 1L;
	private DefaultMutableTreeNode rootNode;
	private Map<Integer, DefaultMutableTreeNode> nodes;

	public SiteMapPanel(String id, IPageSession pageSession, int rootPageId) {
		super(id);
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
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(currentPage.getName());
		getNodes().put(currentPage.getId(), node);
		if(parent == null) {
			rootNode = node;
		}
		else {
			getNodes().get(parent.getId()).add(node);
		}
	}
}
