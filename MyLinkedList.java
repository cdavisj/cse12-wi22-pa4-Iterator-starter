
/**
 * Name: Caleb Davis
 * Email: cjd001@ucsd.edu
 * Sources used: None
 * 
 * This file contains the MyLinkedList class: a generic doubly linked
 * list class, self-containing all method implementations. MyLinkedList also 
 * contains the Node class for implementing the doubly linked list. It also 
 * contains the inner MyListIterator class for traversing MyLinkedList.
 */

import java.util.AbstractList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * {@code MyLinkedList} is a doubly linked list implementation using two
 * sentinel nodes; one for the {@code head} and one for the {@code tail}. This
 * class includes an inner {@code Node} class for implementation. Instance
 * variables include the list {@code size}, the {@code head} of the list, and
 * the {@code tail} of the list.
 * 
 * @author Caleb Davis
 */
public class MyLinkedList<E> extends AbstractList<E> {
    /**
     * The {@code size} of the list. That is, the number of elements in which
     * the list contains.
     */
    int size;

    /**
     * A {@link Node} reference to the front-most element in the list.
     */
    Node head;

    /**
     * A {@link Node} reference to the back-most element in the list.
     */
    Node tail;

    /**
     * A {@code Node} class for holding linked list data. Includes getters and
     * setters for internal use. Instance variables include the generic data to
     * be held in each node, a reference to the {@code next} node, a reference
     * to the {@code prev} node.
     */
    protected class Node {
        /**
         * The data in which the current {@link Node} holds.
         */
        E data;

        /**
         * A {@link Node} reference to the {@code next} {@code Node} in the
         * linked list.
         */
        Node next;

        /**
         * A {@link Node} reference to the {@code prev} {@code Node} in the
         * linked list.
         */
        Node prev;

        /**
         * Constructor to create singleton {@link Node}.
         * 
         * @param element element to add, can be {@code null}
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /**
         * Set the parameter {@code prev} as the {@link #prev} node.
         * 
         * @param prev new {@code prev} node
         */
        public void setPrev(Node prev) {
            this.prev = prev;
        }

        /**
         * Set the parameter {@code next} as the {@link #next} node.
         * 
         * @param next new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /**
         * Set the parameter element as the node's {@link #data}.
         * 
         * @param element element with which to replace {@code data}
         */
        public void setElement(E element) {
            this.data = element;
        }

        /**
         * Accessor to get the {@link #next} node in the list.
         * 
         * @return the {@code next} node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Accessor to get the {@link #prev} node in the list.
         * 
         * @return the {@code prev} node
         */
        public Node getPrev() {
            return this.prev;
        }

        /**
         * Accessor to get the current node's {@link #data}.
         * 
         * @return this node's {@code data}
         */
        public E getElement() {
            return this.data;
        }
    }

    /**
     * Default constructor for new lists. List begins as empty with
     * {@link #head} and {@link #tail} sentinel nodes pointing to each other.
     */
    public MyLinkedList() {
        // Set up head and tail sentinel nodes
        head = new Node(null);
        tail = new Node(null);

        head.setNext(tail);
        head.setPrev(null);

        tail.setNext(null);
        tail.setPrev(head);

        // Start size off at 0
        this.size = 0;
    }

    /**
     * Returns the {@link #size} of the list.
     * 
     * @return the {@code size} of the list
     */
    @Override
    public int size() {
        // Simply return the size member
        return this.size;
    }

    /**
     * Finds {@link Node} at specified {@code index} and returns its
     * {@link Node#data}.
     * 
     * @param index {@code node} corresponding to {@code Node.data} to be
     *              returned
     * @return node data at {@code index}
     */
    @Override
    public E get(int index) {
        return this.getNth(index).getElement();
    }

    /**
     * Adds a new {@link Node} to the list with specified {@code data} at
     * specified {@code index}.
     * 
     * @param index index for new node
     * @param data  data for new node to hold
     */
    @Override
    public void add(int index, E data) {
        // Check for invalid index
        if (index < 0 || index > this.size) {
            throw new IndexOutOfBoundsException();
        }

        // Check for invalid data
        if (data == null) {
            throw new NullPointerException();
        }

        // Set up Node's for insertion
        Node newNode = new Node(data);
        Node curr, prev;

        // If index is at list size, insert at end; else, do normal insertion
        if (index == this.size) {
            curr = this.tail;
            prev = curr.getPrev();
        }
        else {
            curr = this.getNth(index);
            prev = curr.getPrev();
        }

        // Relink nodes and update size
        prev.setNext(newNode);

        newNode.setPrev(prev);
        newNode.setNext(curr);

        curr.setPrev(newNode);

        this.size++;
    }

    /**
     * Appends {@code data} to list and returns {@code true} once this is done.
     * 
     * @param data {@code data} for new {@link Node} to be added
     * @return {@code true} once new {@code Node} is appended
     */
    public boolean add(E data) {
        // Check for invalid data
        if (data == null) {
            throw new NullPointerException();
        }

        // Add new Node to the end of the list
        this.add(this.size, data);

        return true;
    }

    /**
     * Sets the {@link Node#data} in a {@link Node} to a new value.
     * 
     * @param index {@code index} for {@code Node.data} to be changed
     * @param data  {@code data} for {@code Node.data} to be changed to
     * @return {@code Node.data} removed from node
     */
    public E set(int index, E data) {
        // Check for invalid index
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        // Check for null data
        if (data == null) {
            throw new NullPointerException();
        }

        // Set new data at index, while preserving old data for return
        Node curr = this.getNth(index);
        E removedData = curr.getElement();
        curr.setElement(data);

        return removedData;
    }

    /**
     * Removes a {@link Node} from the list.
     * 
     * @param index {@code index} of {@code Node} to be removed
     * @return {@link Node#data} from removed node
     */
    public E remove(int index) {
        // Check for invalid index
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        // Store Node and data to be removed
        Node remNode = this.getNth(index);
        E removedData = remNode.getElement();

        // Perform remove operation and update size
        Node prev = remNode.getPrev();
        Node next = remNode.getNext();

        prev.setNext(next);
        next.setPrev(prev);

        this.size--;

        return removedData;
    }

    /**
     * Removed every {@link Node} from the list.
     */
    public void clear() {
        // Iterate and removes Node's from the list until it is empty
        while (!this.isEmpty()) {
            this.remove(0);
        }
    }

    /**
     * Returns {@code true} if the list is empty, and {@code false} if the list
     * is not empty.
     * 
     * @return {@code true} if the list is empty, {@code false} if the list is
     *         not empty
     */
    public boolean isEmpty() {
        return head.getNext() == tail;
    }

    /**
     * Returns the {@link Node} at specified {@code index} in the list.
     * 
     * @param index {@code index} for {@code Node} to be returned
     * @return {@code Node} at specified {@code index} argument
     */
    protected Node getNth(int index) {
        // Check for index out of bounds
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException();
        }

        // Create new node and iterate it until desired index, before returning
        // said Node
        Node curr = this.head.getNext();

        for (int i = 0; i < index; i++) {
            curr = curr.getNext();
        }

        return curr;
    }

    /**
     * An iterator for {@code MyListIterator} class to traverse the list in
     * either direction, modify the list, and get iterators current position in
     * the list.
     * 
     * @author Caleb Davis
     */
    protected class MyListIterator implements ListIterator<E> {
        /**
         * {@link Node} references to the left and right {@code Node}'s relative
         * to the cursor position.
         */
        Node left, right;

        /**
         * Index of the {@link Node} returned by a call to {@link #next()}.
         */
        int idx;

        // The current moving direction of the iterator. Becomes true when
        // next() is called and false when previous() is called. Will begin as
        // true, assuming the iterator will start with forward movement.
        /**
         * The current moving direction of the iterator. This is set
         * {@code true} when there is a call to {@link #next()}, and set
         * {@code false} when there is a call to {@link #previous()}. The
         * default value is {@code true}.
         */
        boolean forward = true;

        /**
         * Helper to keep track of whether the current state of the iterator
         * allows for calls to {@link #remove()} or {@link #set(E)}. This is set
         * to {@code true} when there is a call to {@link #next()} or
         * {@link #previous()}, and set to {@code false} when there is a call to
         * {@link #add(E)} or {@link #remove()}. The default value is
         * {@code false}.
         */
        boolean canRemoveOrSet;

        // Constants
        static final int INVALID_PREVIOUS_INDEX = -1;
        static final int PREVIOUS_INDEX_OFFSET = 1;

        /**
         * Constructor to initialize the iterator. The initial position of the
         * iterator cursor will be between the {@code head} sentinel node and
         * the first {@code Node} in the list. When the list is empty,
         * {@link #left} will be the head and {@link #right} will be the tail.
         */
        public MyListIterator() {
            // Set up left and right Node references
            this.left = head;
            this.right = head.getNext();
        }

        /**
         * Returns {@code true} if the list iterator has more elements when
         * traversing the list in the forward direction.
         * 
         * @return {@code true} if the list iterator has more elements to
         *         traverse in the forward direction
         */
        public boolean hasNext() {
            return this.right != tail;
        }

        /**
         * Returns the next element in the list and advances the iterator
         * position forwards.
         * 
         * @return the next element in the list
         * @throws NoSuchElementException if the iterator has already reached
         *                                the end of the list
         */
        public E next() {
            // Make sure the iterator has not reached the end of the list
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }

            // Progress cursor forward
            this.left = this.right;
            this.right = this.right.getNext();

            // Update canRemoveOrSet, forward, and idx
            canRemoveOrSet = true;
            forward = true;
            idx++;

            return this.left.data;
        }

        /**
         * Returns {@code true} if this list iterator has more elements when
         * traversing the list in the backwards direction.
         * 
         * @return {@code true} if the list iterator has more elements to
         *         traverse in the backward direction
         */
        public boolean hasPrevious() {
            return this.left != head;
        }

        /**
         * Returns the previous element in the list and advances the iterator
         * position backwards.
         * 
         * @return the previous element in the list
         */
        public E previous() {
            // Make sure the iterator has not reached the beginning of the list
            if (!this.hasPrevious()) {
                throw new NoSuchElementException();
            }

            // Progress cursor backwards
            this.right = this.left;
            this.left = this.left.getPrev();

            // Update canRemoveOrSet, forward, and idx
            canRemoveOrSet = true;
            forward = false;
            idx--;

            return this.right.data;
        }

        /**
         * Returns the index of the element that would be returned by a call to
         * {@link #next()}. Note that this would return the list size if the
         * iterator reached the end of the list.
         * 
         * @return the index of the element that would be returned by a call to
         *         {@code next} or the list size if the iterator is at the end
         *         of the list
         */
        public int nextIndex() {
            if (!this.hasNext()) {
                return size;
            }
            else {
                return this.idx;
            }
        }

        /**
         * Returns the index of the element that would be returned by a call to
         * {@link #previous}. Note this will return -1 if the list iterator is
         * at the beginning of the list
         * 
         * @return the index of the element that would be returned by a call to
         *         {@code previous} or -1 if the list iterator is at the
         *         beginning of the list
         */
        public int previousIndex() {
            if (!this.hasPrevious()) {
                return INVALID_PREVIOUS_INDEX;
            }
            else {
                return this.idx - PREVIOUS_INDEX_OFFSET;
            }
        }

        /**
         * Removes the last element that was returned by {@link #next()} or
         * {@link #previous()}. This call can only be made once per call to
         * {@code next} or {@code previous}. It can also only be done if
         * {@link #add(E)} has not been called after the last call to
         * {@code next} or {@code previous}.
         */
        public void remove() {
            // Check that we can set an element
            if (!canRemoveOrSet) {
                throw new IllegalStateException();
            }

            // If moving forward, remove left element; if moving backwards,
            // remove right element
            if (forward) {
                // Cut out left node and relink left reference
                this.left.getPrev().setNext(this.right);
                this.right.setPrev(this.left.getPrev());

                this.left = this.right.getPrev();

                // Update idx
                this.idx--;
            }
            else {
                // Cut out right node and relink right reference
                this.right.getNext().setPrev(this.left);
                this.left.setNext(this.right.getNext());

                this.right = this.left.getNext();
            }

            // Update MyLinkedList.size and canRemoveOrSet
            size--;
            canRemoveOrSet = false;
        }

        /**
         * Replaces the last element returned by {@link #next()} or
         * {@link #previous()} with the specified element.
         * 
         * @param element the element with which to replace the last element
         *                returned by {@code next} or {@code previous}.
         */
        public void set(E element) {
            // Check validity of element
            if (element == null) {
                throw new NullPointerException();
            }

            // Check that we can set an element
            if (!canRemoveOrSet) {
                throw new IllegalStateException();
            }

            // If moving forward, set left element; if moving backwards, set
            // right element
            if (forward) {
                this.left.setElement(element);
            }
            else {
                this.right.setElement(element);
            }
        }

        /**
         * Inserts the specified element into the list. The element is inserted
         * immediately before the element that would be returned by
         * {@link #next()}, if any, and after the element that would be returned
         * by {@link #previous()}, if any.
         * 
         * @param element the element to insert
         */
        public void add(E element) {
            // Check validity of element
            if (element == null) {
                throw new NullPointerException();
            }

            // Create new node with specified element
            Node newNode = new Node(element);

            // Add new node to list
            this.left.setNext(newNode);
            newNode.setPrev(this.left);

            newNode.setNext(this.right);
            this.right.setPrev(newNode);

            // Progress left Node reference forward to new node
            this.left = newNode;

            // Update MyLinkedList.size, idx, and canRemoveOrSet
            size++;
            this.idx++;
            canRemoveOrSet = false;
        }
    }
}