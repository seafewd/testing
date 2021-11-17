import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class Set_Intersect_Test {

    ///////////////////////////////////////////////////
    /////////// General functionality tests ///////////
    ///////////////////////////////////////////////////

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


    ///////////////////////////////////////////////////
    /////// Statement and branch Coverage tests ///////
    ///////////////////////////////////////////////////

    /**
     * Statement coverage tests (and consequently branch coverage)
     *
     * Statements tested:
     * Statement 1: the for loop
     * Statement 2: 'if (a.get(i).equals(s.a.get(j)))'
     * Statement 3: 'i++'
     * Statement 4: 'j++'
     * Statement 5: 'if (a.get(i) < s.a.get(j))
     * Statement 6: 'a.remove(i)'
     * Statement 7: 'i++' (line 47)
     * Statement 8: 'j++' (line 49)
     *
     * There are three interesting branches
     * One via St1 -> St2 -> St3 -> St4 -> out/St1
     * One via (St1 -> St2) -> St5 -> St6 -> St7 -> out/St1
     * One via (St1 -> St2 -> St5) -> St8 -> out/St1
     *
     * The statement coverage tests also covers the branches since every branch is covered
     */



    @Test
    void statementCoverage(){
        ///////// Purpose: By making sure we have equal first elements in each set we
        // can be sure we execute St1, St2, St3 and St4
        //  * Statement 1: the for loop
        //  * Statement 2: if (a.get(i) > x)
        //  * Statement 3: i++
        //  * Statement 4: j++

        Set s1 = new Set();
        Set s2 = new Set();
        s1.insert(1);
        s2.insert(1);
        assertEquals(s1.toArray()[0], (s2.toArray()[0]));


        ///////// Purpose: By making sure s1[0] < s2[0] we can be sure
        //  * Statement 5: 'if (a.get(i) < s.a.get(j))
        //  * Statement 6: 'a.remove(i)'
        //  * Statement 7: 'i++'
        s1 = new Set();
        s2 = new Set();
        s1.insert(1);
        s1.insert(2);
        s2.insert(2);
        s2.insert(3);

        // current s1 = [1,2]
        // current s2 = [2,3]
        Set s3 = new Set();
        s1.intersect(s2);

        // s1.intersect(s2) should leave us with the set {2} thus
        assertTrue(!s1.member(1) && s1.member(2) && s1.toArray().length == 1);


        ///////// Purpose: Last branch is via St8, which we'll achieve with a.get(i) > a.s.get(j)
        //  * Statement 8: 'j++' (line 49)
        s1 = new Set();
        s2 = new Set();
        s1.insert(2);
        s2.insert(1);
        s1.intersect(s2);

        // s1 should be empty
        assertEquals(0, s1.toArray().length);
    }


}