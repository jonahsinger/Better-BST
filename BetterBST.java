/*
    Jonah Singer
    Class for BetterBST
*/

import java.util.*;

public class BetterBST<T extends Comparable<? super T>> extends BinarySearchTree<T> {


    // public height method
    public int height(){
        return height(root);
    }


    // private recursive height method
    private int height(BinaryNode<T> node){
        // if empty return -1
        if (node == null){
            return -1;
        }
        // adds one for each call and calculates max
        return Math.max(height(node.left), height(node.right)) + 1;
    }


    // public imbalance method calls private imbalance method
    public T imbalance(){
        return imbalance(root);
    }


    // private recursive imbalance method
    private T imbalance(BinaryNode<T> node){
        if (node == null) {
            return null;
        }
        // recurses down the tree
        T leftNode = imbalance(node.left);
        if (leftNode != null){
            return leftNode;
        }
        T rightNode = imbalance(node.right);
        if (rightNode != null){
            return rightNode;
        }
        // checks if difference in height is > 1
        if (Math.abs(height(node.left) - height(node.right)) > 1){
            return node.data;
        }
        return null;
    }


    // public smallestGreaterThan method
    public T smallestGreaterThan(T t){
        return smallestGreaterThan(t, root);
    }


    // private recursive smallestGreaterThan method
    private T smallestGreaterThan(T t, BinaryNode<T> node){
        // checks if node is null
        if (node == null){
            return null;
        }

        // if you are too big
        if (node.data.compareTo(t) > 0){
            // if there is no smallest greater than to the left
            if (smallestGreaterThan(t, node.left) == null){
                return node.data;
            }
            else {
                return smallestGreaterThan(t, node.left);
            }
        }

        // if you are too small or equal recurse right
        if (node.data.compareTo(t) <= 0){
            return smallestGreaterThan(t, node.right);
        }
        return null;
    }

    // public mirror method
    public BinarySearchTree<T> mirror(){
        // creates new BetterBST
        BinarySearchTree<T> BST = new BetterBST<>();
        // calls private mirror on the root
        BST.root = mirror(root);
        return BST;
    }


    // private recursive mirror
    private BinaryNode<T> mirror(BinaryNode<T> node){
        if (node == null){
            return null;
        }
        // this swaps the BST condition
        return new BinaryNode<>(node.data, mirror(node.right), mirror(node.left));
    }


    // method to give a level Order Traversal
    public LinkedList<BinaryNode<T>> levelOrderTraversal(){
        // so it does not change root
        BinaryNode<T> node = root;
        // LinkedList to use for a queue
        LinkedList<BinaryNode<T>> queue = new LinkedList<>();
        // LinkedList to use for output
        LinkedList<BinaryNode<T>> output = new LinkedList<>();

        // if the node is null return null
        if (node == null){
            return null;
        }
        // add the node to the queue
        queue.addLast(node);
        // run while there are items in the queue
        while (queue.size() > 0){
            // calculate size of queue
            int size = queue.size();
            // for each element in the queue
            for (int i = 0; i < size; i++){
                // add its children if it has them to the back
                if (node.left != null){
                    queue.addLast(node.left);
                }
                if (node.right != null){
                    queue.addLast(node.right);
                }
                // remove the node and add to output LinkedList
                BinaryNode<T> temp = queue.removeFirst();
                output.addLast(temp);
                // set the node to the next item in the queue
                node = queue.peekFirst();
            }
        }
        // return the LinkedList
        return output;
    }



    public static void main (String [] args){
        BetterBST<Integer> bst = new BetterBST<>();

        bst.insert(3);
        bst.insert(1);
        bst.insert(2);
        bst.insert(6);
        bst.insert(7);
        bst.insert(9);
        bst.insert(8);
        bst.insert(5);
        bst.insert(4);


        System.out.println(bst.smallestGreaterThan(0));
        System.out.println(bst.smallestGreaterThan(1));
        System.out.println(bst.smallestGreaterThan(2));
        System.out.println(bst.smallestGreaterThan(3));
        System.out.println(bst.smallestGreaterThan(4));
        System.out.println(bst.smallestGreaterThan(5));
        System.out.println(bst.smallestGreaterThan(6));
        System.out.println(bst.smallestGreaterThan(7));
        System.out.println(bst.smallestGreaterThan(8));
        System.out.println(bst.smallestGreaterThan(9));


        System.out.println("");


        System.out.println(bst.height());

        LinkedList<BinaryNode<Integer>> ll = bst.levelOrderTraversal();
        System.out.println(ll);
        for (BinaryNode<Integer> node : ll){
            System.out.println(node.data);
        }
        System.out.println("");
        System.out.println(bst.imbalance());
        System.out.println("");

        BinarySearchTree<Integer> jonah = bst.mirror();
        ll = jonah.levelOrderTraversal();
        for (BinaryNode<Integer> node : ll){
            System.out.println(node.data);
        }
    }
}
