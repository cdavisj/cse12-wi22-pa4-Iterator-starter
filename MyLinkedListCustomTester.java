
/**
 * Name: Caleb Davis
 * ID: A17232832
 * Email: cjd001@ucsd.edu
 * Sources used: None
 * 
 * This is the edge case custom JUnit tester for MyLinkedList.MyListIterator. 
 * It will test all edge cases for the methods not covered by the
 * MyLinkedListPublicTester.java file.
 */

import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.*;

/**
 * Custom tester class for all JUnit tests, as well as any instance variables
 * for testing.
 */
public class MyLinkedListCustomTester {

    // Create lists/iterators for testing
    MyLinkedList<Object> emptyList;
    MyLinkedList<Object>.MyListIterator emptyListIterator;

    /**
     * This sets up the test fixture. JUnit invokes this method before every
     * testXXX method. The @Before tag tells JUnit to run this method before
     * each test.
     */
    @Before
    public void setUp() throws Exception {
        // Set up emptyList and emptyListIterator
        emptyList = new MyLinkedList<>();
        emptyListIterator = emptyList.new MyListIterator();
    }

    /**
     * Test the hasNext method when the list is empty.
     */
    @Test
    public void testHasNext() {
        // Make sure emptyList comes up with no next
        assertFalse(emptyListIterator.hasNext());
    }

    /**
     * Test the next method when MyLinkedList is empty
     */
    @Test
    public void testNext() {
        // Make sure we get NoSuchElementException when list is empty
        try {
            // Call next() on empty list
            emptyListIterator.next();

            // Fail test if no exception is thrown
            fail();
        }
        catch (NoSuchElementException e) {
            // Test passed
        }
    }

    /**
     * Test the hasPrevious method when list is empty
     */
    @Test
    public void testHasPrevious() {
        // Make sure we get empty list does not have previous
        assertFalse(emptyListIterator.hasPrevious());
    }

    /**
     * Test the previous method when list is empty
     */
    @Test
    public void testPrevious() {
        // Make sure we get NoSuchElementException when list is empty
        try {
            // Call previous() on empty list
            emptyListIterator.previous();

            // Fail if no exception was thrown
            fail();
        }
        catch (NoSuchElementException e) {
            // Test passed
        }
    }

    /**
     * Test the nextIndex method when list is empty
     */
    @Test
    public void testNextIndex() {
        // Make sure we get the list size (0) when used on empty list
        assertEquals(0, emptyListIterator.nextIndex());
    }

    /**
     * Test the previousIndex method when list is empty
     */
    @Test
    public void testPreviousIndex() {
        // Make sure we get -1 when used on empty list
        assertEquals(-1, emptyListIterator.previousIndex());
    }

    /**
     * Test the set method with empty list first with valid element, second with
     * null element.
     */
    @Test
    public void testSet() {
        // Make sure we get IllegalStateException() when calling with valid
        // element
        try {
            // Try setting a new valid element
            emptyListIterator.set("New String Object");

            // Fail if no exception was thrown
            fail();
        }
        catch (IllegalStateException e) {
            // Test passed
        }

        // Make sure we get NullPointerException when invalid element is used
        try {
            // Try setting invalid element
            emptyListIterator.set(null);

            // Fail if no exception is thrown
            fail();
        }
        catch (NullPointerException e) {
            // Test passed
        }
    }

    /**
     * Test the remove method when list is empty
     */
    @Test
    public void testRemoveTestOne() {
        // Make sure we get IllegalStateException when calling on empty list
        try {
            // Call remove() on empty list
            emptyListIterator.remove();

            // Fail if no exception is thrown
            fail();
        }
        catch (IllegalStateException e) {
            // Test passed
        }
    }

    /**
     * Test the remove method when a single element is previously added to an
     * empty list
     */
    @Test
    public void testRemoveTestTwo() {
        // Add a single element to the empty list
        emptyListIterator.add("New Valid Object");

        // Go previous to prepare removing node
        emptyListIterator.previous();

        // Remove newly added element from list
        emptyListIterator.remove();

        // Make sure list is empty
        assertTrue(emptyList.isEmpty());

        // Check idx, emptyList.size, left reference, right reference, and
        // canRemoveOrSet
        assertEquals(0, emptyListIterator.idx);
        assertEquals(0, emptyList.size);
        assertEquals(emptyList.head, emptyListIterator.left);
        assertEquals(emptyList.tail, emptyListIterator.right);
        assertFalse(emptyListIterator.canRemoveOrSet);
    }

    /**
     * Test the add method when list is empty
     */
    @Test
    public void testAdd() {
        // Add new element to the empty list
        emptyListIterator.add("New Valid Object");

        // Check left reference, right reference, list index, idx, and
        // canRemoveOrSet
        assertEquals("New Valid Object", emptyListIterator.left.getElement());
        assertEquals(emptyList.tail, emptyListIterator.right);
        assertEquals(1, emptyList.size);
        assertEquals(1, emptyListIterator.idx);
        assertFalse(emptyListIterator.canRemoveOrSet);
    }

}