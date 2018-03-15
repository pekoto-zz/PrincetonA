package com.pekoto.test.datastructures;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.Test;
import com.pekoto.datastructures.Bag;

/**
 * Unit tests for the Bag class
 */
public class BagTests {

    @Test
    public void testAdd() {
        Bag<String> bag = new Bag<String>();
        
        bag.add("One");
        bag.add("Two");
        bag.add("Three");
        
        ArrayList<String> list = new ArrayList<String>();
        
        for(String s: bag) {
            list.add(s);
        }
        
        assertEquals("Three", list.get(0));
        assertEquals("Two", list.get(1));
        assertEquals("One", list.get(2));
        assertEquals(3, bag.size());
    }
}
