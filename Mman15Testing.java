import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for Mman 15
 *
 * @author Daniel Wong & Daniyel Lopaz
 * @version 29.05.2022
 * @link https://wongi.io/
 */

/* Copy the method to Set class! - Just for testing, do not forget to delete before submitting the mman.

    public Set() {
        _head = null;
    }
    public IntNode getHead(){
        return _head;
    }

 */

public class Mman15Testing {
    private Set _set1, _set2, _set3, _set4, _set5;

    @BeforeEach
    public void setUp() {
        _set1 = new Set();
        _set2 = new Set();
        _set2.addToSet(1);
        _set2.addToSet(3);
        _set2.addToSet(5);
        _set2.addToSet(7);
        _set2.addToSet(9);
        _set2.addToSet(11);
        _set3 = new Set();
        _set3.addToSet(2);
        _set3.addToSet(0);
        _set3.addToSet(-1);
        _set4 = new Set();
        _set4.addToSet(3);
        _set4.addToSet(1);
        _set4.addToSet(7);
        _set4.addToSet(11);
        _set4.addToSet(9);
        _set5 = new Set();
    }

    @Test
    @DisplayName("Testing isEmpty")
    public void isEmpty() {
        assertTrue(_set1.isEmpty());
        assertFalse(_set2.isEmpty());
        assertTrue(_set3.isEmpty());
    }

    @Test
    @DisplayName("Testing isMember")
    public void isMember() {
        assertFalse(_set3.isMember(1));
        assertFalse(_set3.isMember(2));
        assertFalse(_set3.isMember(0));
        assertFalse(_set3.isMember(-1));
        assertTrue(_set2.isMember(1));
        assertFalse(_set2.isMember(101));
    }

    @Test
    @DisplayName("Testing numOfElements")
    public void numOfElements() {
        assertEquals(6, _set2.numOfElements());
        assertEquals(0, _set1.numOfElements());
        assertEquals(0, _set3.numOfElements());
    }

    @Test
    @DisplayName("Testing addToSet")
    public void addToSet() {
        _set1.addToSet(2);
        assertTrue(_set1.isEmpty());
        _set1.addToSet(-1);
        assertTrue(_set1.isEmpty());
        _set1.addToSet(0);
        assertTrue(_set1.isEmpty());
        _set1.addToSet(101);
        assertTrue(_set1.isMember(101));
        _set1.addToSet(101);
        assertEquals(1, _set1.numOfElements(), "101 already exist in the set.");
        _set1.addToSet(5);
        _set1.addToSet(7);
        _set1.addToSet(103);
        assertTrue(_set1.isMember(5));
        assertTrue(_set1.isMember(7));
        assertTrue(_set1.isMember(103));
        assertEquals(4, _set1.numOfElements());
    }

    @Test
    @DisplayName("Testing removeFromSet")
    public void removeFromSet() {
        _set1.removeFromSet(1);
        assertTrue(_set1.isEmpty());
        _set2.removeFromSet(1);
        assertEquals(5, _set2.numOfElements());
        _set2.removeFromSet(2);
        _set2.removeFromSet(0);
        _set2.removeFromSet(-1);
        assertEquals(5, _set2.numOfElements());
        _set2.removeFromSet(3);
        _set2.removeFromSet(5);
        _set2.removeFromSet(7);
        _set2.removeFromSet(9);
        _set2.removeFromSet(11);
        assertTrue(_set2.isEmpty());
    }

    @Test
    @DisplayName("Testing toString")
    public void toStringSet() {
        _set1.removeFromSet(1);
        assertEquals("{}", _set1.toString());
        _set2.removeFromSet(1);
        sort(_set2.getHead());
        assertEquals("{3,5,7,9,11}", _set2.toString());
        _set2.removeFromSet(2);
        _set2.removeFromSet(0);
        _set2.removeFromSet(-1);
        assertEquals("{3,5,7,9,11}", _set2.toString());
        _set2.removeFromSet(3);
        _set2.removeFromSet(5);
        _set2.removeFromSet(7);
        _set2.removeFromSet(9);
        _set2.removeFromSet(11);
        assertEquals("{}", _set2.toString());
    }

    @Test
    @DisplayName("Testing subSet")
    public void subSet() {
        assertTrue(_set3.subSet(_set1));
        _set1.addToSet(1);
        assertFalse(_set3.subSet(_set1));
        assertFalse(_set3.subSet(_set2));
        assertTrue(_set1.subSet(_set3));
        _set1.addToSet(3);
        _set1.addToSet(5);
        _set1.addToSet(11);
        _set1.addToSet(9);
        _set1.addToSet(7);
        assertTrue(_set1.subSet(_set2));
        assertTrue(_set2.subSet(_set1));
        assertFalse(_set4.subSet(_set2));
        assertTrue(_set2.subSet(_set4));
    }

    @Test
    @DisplayName("Testing equals")
    public void equals() {
        assertTrue(_set3.equals(_set1));
        _set1.addToSet(1);
        assertFalse(_set3.equals(_set1));
        assertFalse(_set3.equals(_set2));
        assertFalse(_set1.equals(_set3));
        _set1.addToSet(3);
        _set1.addToSet(5);
        _set1.addToSet(11);
        _set1.addToSet(9);
        _set1.addToSet(7);
        assertTrue(_set1.equals(_set2));
        assertTrue(_set2.equals(_set1));
        assertFalse(_set4.equals(_set2));
        assertFalse(_set2.equals(_set4));
    }

    @Test
    @DisplayName("Testing intersection")
    public void intersection() {
        Set s1 = _set1.intersection(_set3);
        assertEquals("{}", s1.toString());
        Set s2 = _set2.intersection(_set4);
        sort(s2.getHead());
        assertEquals("{1,3,7,9,11}", s2.toString());
        _set2.removeFromSet(1);
        Set s3 = _set2.intersection(_set4);
        sort(s3.getHead());
        assertEquals("{3,7,9,11}", s3.toString());
        sort(_set2.getHead());
        assertEquals("{3,5,7,9,11}", _set2.toString());
        sort(_set4.getHead());
        assertEquals("{1,3,7,9,11}", _set4.toString());
        Set s4 = _set5.intersection(s3);
        assertEquals("{}", s4.toString());
        assertNotSame(s4, _set5, "There is an aliasing.");
        assertNotSame(_set2, s3, "There is an aliasing.");
    }

    @Test
    @DisplayName("Testing union")
    public void union() {
        Set s1 = _set1.union(_set3);
        assertEquals("{}", s1.toString());
        Set s2 = _set2.union(_set4);
        assertTrue(_set2.equals(s2));
        _set2.removeFromSet(1);
        Set s3 = _set2.union(_set4);
        sort(s3.getHead());
        assertEquals("{1,3,5,7,9,11}", s3.toString());
        Set s4 = _set5.union(s3);
        assertTrue(s3.equals(s4));
        assertTrue(s4.equals(s3));
        Set s5 = s3.union(_set5);
        assertTrue(s3.equals(s5));
        assertTrue(s5.equals(s3));
    }

    @Test
    @DisplayName("Testing difference")
    public void difference() {
        Set s1 = _set1.difference(_set3);
        assertEquals("{}", s1.toString());
        Set s2 = _set2.difference(_set4);
        assertEquals("{5}", s2.toString());
        _set4.removeFromSet(1);
        Set s3 = _set2.difference(_set4);
        sort(s3.getHead());
        assertEquals("{1,5}", s3.toString());
        Set s4 = _set5.difference(_set2);
        assertEquals("{}", s4.toString());
        s4 = _set2.difference(_set5);
        assertNotSame(s4, _set2, "There is an aliasing.");
        sort(s4.getHead());
        assertEquals("{1,3,5,7,9,11}", s4.toString());
        assertNotSame(s4, _set5, "There is an aliasing.");
    }

    static void sort(IntNode head) {
        IntNode tmp = head;

        // Traverse the List
        while (tmp != null) {
            IntNode min = tmp;
            IntNode nxt = tmp.getNext();

            // Traverse the unsorted sublist
            while (nxt != null) {
                if (min.getValue() > nxt.getValue()) min = nxt;

                nxt = nxt.getNext();
            }
            int x = tmp.getValue();
            tmp.setValue(min.getValue());
            min.setValue(x);
            tmp = tmp.getNext();
        }
    }
}