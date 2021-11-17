import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Set_Insert_Test {

    ///////////////////////////////////////////////////
    /////////// General functionality tests ///////////
    ///////////////////////////////////////////////////

    // make sure there are no duplicate items in set
    @Test
    void checkNoDuplicates() {
        Set set = new Set();
        set.insert(5);
        set.insert(5);
        set.insert(10);
        set.insert(10);
        assertEquals(2, set.toArray().length);
        set.insert(3);
        assertEquals(3, set.toArray().length);
    }

    // make sure that the Set is ordered
    @Test
    void checkArrayIsOrdered() {
        Set set = new Set();
        set.insert(3);
        set.insert(2);
        set.insert(1);
        set.insert(10);
        set.insert(0);
        set.insert(-5);
        int[] orderedArray = {-5, 0, 1, 2, 3, 10};
        assertArrayEquals(set.toArray(), orderedArray);
    }

    ///////////////////////////////////////////////////
    /////// Statement and branch Coverage tests ///////
    ///////////////////////////////////////////////////

    /**
     * Statement coverage tests (and consequently branch coverage)
     *
     * Statement 1: the for loop
     * Statement 2: if (a.get(i) > x)
     * Statement 3: a.add(i, x);
     * Statement 4: if (a.get(i) == x)
     * Statement 5: a.add(x);
     *
     * There are four interesting branches
     * One through the for loop, when a.get(i) > x
     * One through the for loop, when a.get(i) == x
     * One through the for loop, when a.get(i) < x
     * One that goes past the for loop ie. when size == 0
     *
     * The statement coverage tests also covers the branches
     */

    // Single call to check all statementCoverage-tests
    @Test
    void statementCoverage(){
        statementOneTwoThreeCoverage();
        statementFourCoverage();
        statementFiveCoverage();
    }

    ///////// Purpose: Make sure we DO enter the for loop and execute statement 2,
    // consequently executing statement 1
    //  * Statement 1: the for loop
    //  * Statement 2: if (a.get(i) > x)
    @Test
    private void statementOneTwoThreeCoverage(){

        Set set = new Set();
        set.insert(3);

        // Make sure 3 is the previous element
        assertTrue(set.member(3));

        // Insert an element that is < 3
        // this confirms that we have executed statement 2 and 3
        set.insert(1);

        // In turn, this confirms that we have executed statement 1
        assertEquals(2, set.toArray().length);
    }

    ///////// Purpose: test that we do not add an element that already has
    // the same value
    //  * Statement 3: a.add(i, x);
    //  * Statement 4: if (a.get(i) == x)
    @Test
    private void statementFourCoverage(){
        Set set = new Set();
        set.insert(1);
        set.insert(1);
        assertEquals(1, set.toArray().length);
    }

    ///////// Purpose: Make sure we do NOT enter the for loop
    // We know the length is zero so we know the loop wont run
    // instead it will just add the element
    //  * Statement 5: a.add(x);
    @Test
    private void statementFiveCoverage(){
        Set set = new Set();
        set.insert(3);

        // Confirms that we have executed statement 5
        assertEquals(1, set.toArray().length);
    }



}