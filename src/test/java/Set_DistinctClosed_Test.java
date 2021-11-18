import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.IntBinaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class Set_DistinctClosed_Test {

    ///////////////////////////////////////////////////
    /////////// General functionality tests ///////////
    ///////////////////////////////////////////////////

    Set set;
    IntBinaryOperator ibo;

    @BeforeEach
    void setUp() {
        set = new Set();
        set.insert(-5);
        set.insert(98);
        set.insert(2);
        set.insert(11);
        set.insert(100);
    }

    // test if at least one element f(a,b) is in the set
    // must return true since 0 and 1 is in the set
    // and 0 + 1 = 1
    @Test
    void distinctClosedAddition() {
        ibo = Integer::sum;
        assertTrue(set.distinctClosed(ibo));
    }

    // test if at least one element f(a,b) is in the set
    // must return false since no two elements in the set
    // can be multiplied to get a value already in the set
    @Test
    void distinctClosedMultiplication() {
        ibo = (left, right) -> left * right;
        assertFalse(set.distinctClosed(ibo));
    }


    ///////////////////////////////////////////////////
    /////// Statement and branch Coverage tests ///////
    ///////////////////////////////////////////////////

    /**
     * Statements tested:
     * Statement 1: 'int vi, vj'
     * Statement 2: 'for (Integer i : a)'
     * Statement 3: 'for (Integer j : a)'
     * Statement 4: 'vi = i;'
     * Statement 5: 'vj = j'
     * Statement 6: 'if (this.member(f.applyAsInt(vi, vj)) && vi != vj)'
     * Statement 7: 'return true'
     * Statement 8: 'return false'
     *
     * There are four interesting branches
     * Branch 1: St1 -> St2 -> St3 -> St4 -> St5 -> st6 -> St7
     * Branch 2: (St1 -> St2) -> St8
     * Branch 3: (St1 -> St2 -> St3) -> St2 -> arbitrary (unreachable?)
     * Branch 4: (St1 -> St2 -> St3 -> St4 -> St5 -> St6) -> St3 -> arbitrary
     *
     */

    @Test
    void statementAndBranchCoverage(){

        ///////// Purpose: We will cover all statements except one and
        // cover branch 1 by stepping through both for-loops without
        // looping. This can be achieved by inserting only two elements
        // in the set, thus returning true
        //  * Statement 1: 'int vi, vj'
        //  * Statement 2: 'for (Integer i : a)'
        //  * Statement 3: 'for (Integer j : a)'
        //  * Statement 4: 'vi = i;'
        //  * Statement 5: 'vj = j'
        //  * Statement 6: 'if (this.member(f.applyAsInt(vi, vj)) && vi != vj)'
        //  * Statement 7: 'return true'
        //
        //  * Branch 1: St1 -> St2 -> St3 -> St4 -> St5 -> st6 -> St7

        Set s1 = new Set();
        s1.insert(1);
        s1.insert(2);
        ibo = Integer::sum;
        assertTrue(s1.distinctClosed(ibo));

        ///////// Purpose: We will cover statement 1 and branch 2 testing on an empty set,
        // since that will make sure we do not enter the for-loops, thus returning false
        //  * Statement 8: 'return false'
        //  * Branch 2: (St1 -> St2) -> St8

        s1 = new Set();
        assertEquals(0, s1.toArray().length);
        ibo = Integer::sum;
        assertFalse(s1.distinctClosed(ibo));


        ///////// Purpose: We will cover branch 3 by making sure statement 6 is false
        // on the first step through, and by keeping at least one element in the set
        // we are guaranteed to loop back to statement 3
        //  Branch 4: (St1 -> St2 -> St3 -> St4 -> St5 -> St6) -> St3 -> arbitrary

        s1 = new Set();
        s1.insert(1);
        ibo = Integer::sum;
        assertFalse(s1.distinctClosed(ibo));

    }




}