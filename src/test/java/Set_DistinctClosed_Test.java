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
        set.insert(99);
        set.insert(1);
        set.insert(11);


    }

    @Test
    void distinctClosedAddition() {
        ibo = Integer::sum;
        assertTrue(set.distinctClosed(ibo));
    }

    @Test
    void distinctClosedMultiplication() {
        ibo = (left, right) -> left * right;
        assertFalse(set.distinctClosed(ibo));
    }
}