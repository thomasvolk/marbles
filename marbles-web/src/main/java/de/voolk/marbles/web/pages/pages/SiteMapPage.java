package de.voolk.marbles.web.pages.pages;

import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;

import org.apache.wicket.PageParameters;
import org.apache.wicket.extensions.markup.html.tree.DefaultAbstractTree.LinkType;
import org.apache.wicket.extensions.markup.html.tree.Tree;

import de.voolk.marbles.web.pages.base.AbstractPage;

public class SiteMapPage extends AbstractPage {

	public SiteMapPage(PageParameters parameters) {
		super(parameters);
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("<root>");
        DefaultMutableTreeNode newChild = new DefaultMutableTreeNode("child1");
		rootNode.add(newChild);
		newChild.add(new DefaultMutableTreeNode("subchild2"));
        TreeModel treeModel = new DefaultTreeModel(rootNode);

		Tree tree = new Tree("tree", treeModel)
        {
            @Override
            protected String renderNode(TreeNode node)
            {
                DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode)node;
                Object userObject = treeNode.getUserObject();
                return (userObject instanceof List) ? "<subtree>"
                    : String.valueOf(treeNode.getUserObject());
            }
        };
        tree.setLinkType(LinkType.REGULAR);

        add(tree);
	}

}
