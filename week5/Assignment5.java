package week5.assignment;

import java.util.LinkedList;

class Node{
    public int data;
    public Node nextLink = null;

    public Node(int d, Node preNode ){
        data = d;
        if(preNode!=null)
            preNode.nextLink = this;
    }

    public void setPrivousNode(Node preNode){
        if(preNode!=null)
            preNode.nextLink = this;
    }
}

public class Assignment5 {

    public static void main(String[] args) {
        Node intersection = null;

        Node commNode1 = new Node(7,null);
        Node commNode2 = new Node(6,commNode1);

        LinkedList<Node> a = new LinkedList<Node>();
        a.add(new Node(10,null));
        a.add(new Node(8, a.getFirst()));
        commNode1.setPrivousNode(a.get(1));
        a.add(commNode1);
        a.add(commNode2);

        LinkedList<Node> b = new LinkedList<Node>();
        b.add(new Node(12,null));
        commNode1.setPrivousNode(b.getFirst());
        b.add(commNode1);
        b.add(commNode2);

        int number1 = a.size();
        int number2 = b.size();

        for (int i = 0; i < number1; i++) {
            Node temp = a.get(i);
            System.out.println("a_node" + i + " is: " + temp.data);
        }
        System.out.println();

        for (int i = 0; i < number2; i++) {
            Node temp = b.get(i);
            System.out.println("b_node" + i + " is: " + temp.data);
        }
        intersection = sortedIntersection(a.getFirst(), b.getFirst());
        System.out.println("The intersection of sortedlinked list a and b is: " + intersection.data);

    }

   
    static public Node sortedIntersection(Node head_a, Node head_b){
        if(head_a == null || head_b == null){
            return null;
        }
        Node node_a = head_a;
        Node node_b = head_b;

        outer:
        while(node_a != null){
            node_b = head_b;
            while(node_b != null){
                // System.out.println("a:"+ node_a.data + " b:" + node_b.data);
                if(node_a == node_b){
                    // System.out.println("common node found");
                    break outer;
                }
                node_b = node_b.nextLink;
            }
            node_a = node_a.nextLink;
        }
        return node_a;
    }
}