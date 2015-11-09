package assignment5;
import java.util.Scanner;
import java.io.*;
import java.util.*;

/**
 * 
 * @author Jacob Jones
 * @param <E>
 */
public class BinarySearchTree<E extends Comparable<E>> extends AbstractTree<E> {

    /**
     * 
     */
    protected TreeNode<E> root;
    /**
     * 
     */
    protected int size = 0;

    /**
     * Create a default binary tree
     */
    public BinarySearchTree() {
    }

    /**
     * Create a binary tree from an array of objects
     * @param objects 
     */
    public BinarySearchTree(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            insert(objects[i]);
        }
    }

    /**
     * Returns true if the element is in the tree
     * @param e 
     * @return 
     */
    @Override
    public boolean search(E e) {
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
            {
                return true; // Element is found
            }
        }
        return false;
    }
    /**
     * 
     * @param e
     * @param count
     * @return
     */
    public boolean search(E e, int count[]) {
        TreeNode<E> current = root; // Start from the root
        while (current != null) {
            count[0]++;
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else // element matches current.element
            {
                return true; // Element is found
            }
        }
        return false;
    }

    /**
     * Insert element o into the binary tree Return true if the element is
     * inserted successfully. Uses an iterative algorithm
     * @param e 
     * @return 
     */
    public boolean insert(E e) {
        if (root == null) {
            root = createNewNode(e); // Create a new root
        } else {
            // Locate the parent node
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false; // Duplicate node not inserted
                }
            }
            // Create the new node and attach it to the parent node
            if (e.compareTo(parent.element) < 0) {
                parent.left = createNewNode(e);
            } else {
                parent.right = createNewNode(e);
            }
        }
        size++;
        return true; // Element inserted
    }

    /**
     * 
     * @param e
     * @return
     */
    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<E>(e);
    }

    /**
     * Inorder traversal from the root
     */
    public void inorder() {
        inorder(root);
    }

    /**
     * Inorder traversal from a subtree
     * @param root 
     */
    protected void inorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    /**
     * 
     */
    public void inorderFileWriter() {
        File outputfile = new File("output.txt");
        try {
            PrintWriter output = new PrintWriter(outputfile);
            inorderFileWriter(root, output);
            output.close();
        } catch (Exception e) {
        }
    }

    /**
     * Inorder traversal from a subtree, writes data to output file
     * @param root
     * @param output  
     */
    protected void inorderFileWriter(TreeNode<E> root, java.io.PrintWriter output) {
        if (root == null) {
            return;
        }
        inorderFileWriter(root.left, output);
        output.println(root.element);
        inorderFileWriter(root.right, output);
    }

    /**
     * Postorder traversal from the root
     */
    public void postorder() {
        postorder(root);
    }

    /**
     * Postorder traversal from a subtree
     * @param root 
     */
    protected void postorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    /**
     * Preorder traversal from the root
     */
    public void preorder() {
        preorder(root);
    }

    /**
     * Preorder traversal from a subtree
     * @param root 
     */
    protected void preorder(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    /**
     * Inner class tree node
     * @param <E> 
     */
    public static class TreeNode<E extends Comparable<E>> {

        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        /**
         * 
         * @param e
         */
        public TreeNode(E e) {
            element = e;
        }
    }

    /**
     * Get the number of nodes in the tree
     * @return 
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns the root of the tree
     * @return 
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Returns a path from the root leading to the specified element
     * @param e
     * @return  
     */
    public java.util.ArrayList<TreeNode<E>> path(E e) {
        java.util.ArrayList<TreeNode<E>> list = new java.util.ArrayList<TreeNode<E>>();
        TreeNode<E> current = root; // Start from the root

        while (current != null) {
            list.add(current); // Add the node to the list
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                break;
            }
        }
        return list; // Return an array of nodes
    }

    /**
     * Delete an element from the binary tree. Return true if the element is
     * deleted successfully Return false if the element is not in the tree
     * @param e 
     * @return 
     */
    public boolean delete(E e) {
        // Locate the node to be deleted and also locate its parent node
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break; // Element is in the tree pointed by current
            }
        }
        if (current == null) {
            return false; // Element is not in the tree
        }    // Case 1: current has no left children
        if (current.left == null) {
            // Connect the parent with the right child of the current node
            if (parent == null) {
                root = current.right;
            } else {
                if (e.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        } else {
            // Case 2 & 3: The current node has a left child
            // Locate the rightmost node in the left subtree of
            // the current node and also its parent
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right; // Keep going to the right
            }
            // Replace the element in current by the element in rightMost
            current.element = rightMost.element;

            // Eliminate rightmost node
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else // Special case: parentOfRightMost == current
            {
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true; // Element inserted
    }

    /**
     * Obtain an iterator. Use inorder.
     * @return 
     */
    public java.util.Iterator iterator() {
        return inorderIterator();
    }

    /**
     * Obtain an inorder iterator
     * @return 
     */
    public java.util.Iterator inorderIterator() {
        return new InorderIterator();
    }

    // Inner class InorderIterator
    class InorderIterator implements java.util.Iterator {
        // Store the elements in a list

        private java.util.ArrayList<E> list = new java.util.ArrayList<E>();
        private int current = 0; // Point to the current element in list

        public InorderIterator() {
            inorder(); // Traverse binary tree and store elements in list
        }

        /**
         * Inorder traversal from the root
         */
        private void inorder() {
            inorder(root);
        }

        /**
         * Inorder traversal from a subtree
         */
        private void inorder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inorder(root.left);
            list.add(root.element);
            inorder(root.right);
        }

        /**
         * Next element for traversing?
         */
        public boolean hasNext() {
            if (current < list.size()) {
                return true;
            }
            return false;
        }

        /**
         * Get the current element and move cursor to the next
         */
        public Object next() {
            return list.get(current++);
        }

        /**
         * Remove the current element and refresh the list
         */
        public void remove() {
            delete(list.get(current)); // Delete the current element
            list.clear(); // Clear the list
            inorder(); // Rebuild the list
        }
    }

    /**
     * Remove all elements from the tree
     */
    public void clear() {
        root = null;
        size = 0;
    }
}