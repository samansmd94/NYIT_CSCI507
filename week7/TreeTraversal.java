import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class BTreeNode<T>{
    
    public T value;
    public BTreeNode<T> leftChild=null, rightChild=null;

    public BTreeNode(T pValue){
        value = pValue;
    }

    public BTreeNode<T> insertLeftNode(T pValue){
        leftChild = new BTreeNode<T>(pValue);
        return leftChild;
    }

    public BTreeNode<T> insertRightNode(T pValue){
        rightChild = new BTreeNode<T>(pValue);
        return rightChild;
    }
}

class BTree{

    BTreeNode<Integer> rootNode= null;

    public BTree(){
        
    }

    public void initTree(){
        rootNode = new BTreeNode<Integer>(1);
        BTreeNode<Integer> two = rootNode.insertLeftNode(2);
        two.insertLeftNode(4);
        two.insertRightNode(5);
        rootNode.insertRightNode(3);
    }

    public void draw(){
        BTreePrinter.printNode(rootNode);
    }

    public void printInorder(){
        __printInorder(rootNode);
        System.out.println();
    }

    private void __printInorder(BTreeNode<Integer> node){
        if(node == null){
            return;
        }
        __printInorder(node.leftChild);
        System.out.print(node.value+" ");
        __printInorder(node.rightChild);
    }

    public void printPreorder(){
        __printPreorder(rootNode);
        System.out.println();
    }

    private void __printPreorder(BTreeNode<Integer> node){
        if(node == null){
            return;
        }
        System.out.print(node.value+" ");
        __printPreorder(node.leftChild);
        __printPreorder(node.rightChild);
    }

    public void printPostorder(){
        __printPostorder(rootNode);
        System.out.println();
    }

    private void __printPostorder(BTreeNode<Integer> node){
        if(node == null){
            return;
        }
        __printPostorder(node.leftChild);
        __printPostorder(node.rightChild);
        System.out.print(node.value+" ");
    }
    

}

public class TreeTraversal{

    public static void main(String[] args) {
        BTree bTree = new BTree();
        bTree.initTree();
        bTree.draw();
        
        while(true){

            Scanner myObj = new Scanner(System.in);
            System.out.println("\n1.Inorder\n2.Preorder\n3.Postorder\nPlease select a travesal method:(\'q\' for quit ):");
    
            String type = myObj.nextLine();
            if("q".equalsIgnoreCase(type)){
                System.out.println("Quit! Bye!");
                myObj.close();
                break;
            } else if("1".equals(type)){
                System.out.print("Inorder print:");
                bTree.printInorder();
            } else if("2".equals(type)){
                System.out.print("Preorder print:");
                bTree.printPreorder();
            } else if("3".equals(type)){
                System.out.print("Postorder print:");
                bTree.printPostorder();
            }
    
        }

    }

}

/**
 * Draw the binary tree
 * 
 * https://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram-in-java
 */
class BTreePrinter {

    public static <T extends Comparable<?>> void printNode(BTreeNode<T> root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<BTreeNode<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<BTreeNode<T>> newNodes = new ArrayList<BTreeNode<T>>();
        for (BTreeNode<T> node : nodes) {
            if (node != null) {
                System.out.print(node.value);
                newNodes.add(node.leftChild);
                newNodes.add(node.rightChild);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).leftChild != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).rightChild != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(BTreeNode<T> node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.leftChild), BTreePrinter.maxLevel(node.rightChild)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }
}