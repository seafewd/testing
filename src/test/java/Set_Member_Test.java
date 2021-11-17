import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class Set_Member_Test {

    Set set = new Set();

    @BeforeEach
    void setUp() {
        set.insert(7);
        set.insert(-1);
        set.insert(5);
        set.insert(0);
        set.insert(999);
    }


    /**
     * Statement coverage tests (and consequently branch coverage)
     *
     * Statement 1: the for loop
     * Statement 2: 'if (n == x)'
     * Statement 3: 'isMember = true'
     * Statement 4: 'break'
     * Statement 5: 'return isMember'
     */



    @Test
    void statementCoverage(){

        // Purpose: If the element is a member, we will enter the for loop
        // and execute statement 3 and 4
        // * Statement 1: the for loop
        // * Statement 2: 'if (n == x)'
        // * Statement 3: 'isMember = true'

        checkIsMember();
        checkIsMember2();

        // Purpose: If the element is not a member, we will enter the for loop
        // and execute statement 5
        // * Statement 1: the for loop
        // * Statement 5: 'if (n == x)'
        checkIsNotMember();




    }



    // check whether an element which we know is in the list,
    // is in the list
    @Test
    void checkIsMember() {
        assertTrue(set.member(5));
    }

    // check whether a (negative) element is in the list
    @Test
    void checkIsMember2() {
        assertTrue(set.member(-1));
    }

    // check whether an element which we know is not
    // in the list, is in the list
    @Test
    void checkIsNotMember() {
        assertFalse(set.member(4));
    }
}