import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.IntBinaryOperator;

import static org.junit.jupiter.api.Assertions.*;

class Set_DistinctClosed_Test {

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
}