package com.michaelfitzmaurice.dnn;

import java.io.File;
import java.io.PrintStream;

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
		DefaultMutableTreeNode grandChild1 = new DefaultMutableTreeNode("Grandchild 1");
		grandChild1.add( new DefaultMutableTreeNode("GreatGrandchild2") );
		child1.add(grandChild1);
		child1.add( new DefaultMutableTreeNode("Grandchild2") );
		
		printTree(root, System.out);
	}
	
	public static void printTree(DefaultMutableTreeNode node, PrintStream stream) {
		
		for (int i = 0; i < node.getChildCount(); i++) {
			DefaultMutableTreeNode child = 
				(DefaultMutableTreeNode)node.getChildAt(i);
			stream.print(" ");
			int level = child.getLevel();
			if (level > 1) {
		        for (int j = 0; j < level - 2; j++) {
		            stream.print("     ");
	            }
				stream.print("|---- ");
			}
			stream.println( child.toString() );
			printTree(child, stream);
        }
	}

}
