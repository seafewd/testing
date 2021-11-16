import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Set_Intersect_Test {

    Set set = new Set();
    Set set2 = new Set();

    @BeforeEach
    void setUp() {
        set.insert(-5);
        set.insert(0);
        set.insert(1);
        set.insert(11);

        set2.insert(-5);
        set2.insert(0);
        set2.insert(1);
        set2.insert(10);
        set2.insert(99);
    }

    // check that intersect works, ie deletes elements that
    // are not in both sets
    @Test
    void testRemove() {
        int[] smallerArray = {1, 0, -5};
        set.intersect(set2);
        boolean contains = true;
        for (int i = 0; i < set.toArray().length; i++)
            for (int n : smallerArray)
                if (!set.member(n) || set.toArray().length != smallerArray.length)
                    contains = false;
        assertTrue(contains);
    }

    // check that intersect works, ie deletes elements that
    // are not in both sets
    // (different ordering)
    @Test
    void testRemove2() {
        int[] smallerArray = {1, -5, 0};
        set.intersect(set2);
        boolean contains = true;
        for (int i = 0; i < set.toArray().length; i++)
            for (int n : smallerArray)
                if (!set.member(n) || set.toArray().length != smallerArray.length)
                    contains = false;
        assertTrue(contains);
    }
}