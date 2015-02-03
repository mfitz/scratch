package com.michaelfitzmaurice.dnn;

import java.io.File;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

public class DnnMenuParser {

	public DnnMenuParser() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		File menuFile = new File(args[0]);
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("Child 1");
		root.add(child1);
		root.add( new DefaultMutableTreeNode("Child 2") );
		child1.add( new DefaultMutableTreeNode("Grandchild1") );
		child1.add( new DefaultMutableTreeNode("Grandchild2") );
		
		printTree(root);
	}
	
	public static void printTree(DefaultMutableTreeNode node) {
		
		for (int i = 0; i < node.getChildCount(); i++) {
			DefaultMutableTreeNode child = 
				(DefaultMutableTreeNode)node.getChildAt(i);
	        for (int j = 0; j < child.getLevel(); j++) {
	            System.out.print("  ");
            }
			System.out.println( "|---- " + child.toString() );
			printTree(child);
        }
	}

}
