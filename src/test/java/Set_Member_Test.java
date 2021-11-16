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
    // check whether an element is in the list
    @Test
    void checkIsMember() {
        assertTrue(set.member(5));
    }

    // check whether a (negative) element is in the list
    @Test
    void checkIsMember2() {
        assertTrue(set.member(-1));
    }

    @Test
    void checkIsNotMember() {
        assertFalse(set.member(4));
    }
}