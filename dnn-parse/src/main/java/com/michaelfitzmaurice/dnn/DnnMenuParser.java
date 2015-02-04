package com.michaelfitzmaurice.dnn;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Quick and dirty class to parse the sitemap from a DotNetNuke generated
 * JavaScript menu and dump it to the console as a tree represented in 
 * plain text. The DNN generated JavaScript needs to be cleaned up 
 * slightly before being fed to this application, specifically (in this order),
 * take the web page that contains the site map definition and:
 * <ol>
 *   <li>Remove everything that is not between the <SCRIPT language="javascript"> tags
 *   <li>All instances of the string "&nbsp;" should be removed (replaced with nothing)
 *   <li>All instances of the string ";" should have a line break appended 
 *       immediately afterwards (i.e. be replaced with ";\n")
 * </ol>
 * 
 * @author Michael Fitzmaurice, February 2015
 */
public class DnnMenuParser {
	
	private static final String NODE_MARKER_TEXT = "=new SPJSXMLNode";
	
	private static final String ROOT_NODE_KEY = "m_oSPNode['-1']";

	public static void main(String[] args) throws Exception {
		
		File menuFile = new File(args[0]);
		System.out.println("Parsing DNN menu from " + menuFile 
							+ "\n-----------------------\n");
		DefaultMutableTreeNode menuTree = parseDnnMenu(menuFile);
		printTree(menuTree, System.out);
	}
	
	public static DefaultMutableTreeNode parseDnnMenu(File file) 
	throws IOException {
		
		Map<String, DefaultMutableTreeNode> nodeMap = 
			new HashMap<String, DefaultMutableTreeNode>();
		
		BufferedReader reader = new BufferedReader( new FileReader(file) );
		String line = reader.readLine();
		while (line != null) {
			String[] tokens = line.split(NODE_MARKER_TEXT);
			if (tokens != null && tokens.length > 1) {
				String nodeProperties = tokens[1];
				nodeProperties = nodeProperties.substring(1, nodeProperties.length() );
				tokens = nodeProperties.split(",");
				if (tokens != null && tokens.length > 3) {
					String nodeType = tokens[0].trim();
					String nodeId = tokens[1].trim();
					String parentId = tokens[2].trim();
					String nodeValue = tokens[3].trim();
					DefaultMutableTreeNode treeNode = 
							new DefaultMutableTreeNode(nodeValue);
					if ( nodeType.equals("'root'") ) {
						nodeMap.put( ROOT_NODE_KEY, treeNode );
					} else {
						nodeId = "m_oSPNode[" + nodeId + "]";
						DefaultMutableTreeNode parent = nodeMap.get(parentId);
						parent.add(treeNode);
						nodeMap.put(nodeId, treeNode);
					}
				}
			}
			
			line = reader.readLine();
		}
		
		reader.close();
		
		return nodeMap.get(ROOT_NODE_KEY);
	}
	
	public static void printTree(DefaultMutableTreeNode node, PrintStream stream) {
		
		for (int i = 0; i < node.getChildCount(); i++) {
			DefaultMutableTreeNode child = 
				(DefaultMutableTreeNode)node.getChildAt(i);
			int level = child.getLevel();
			if (level > 1) {
				stream.print(" ");
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
