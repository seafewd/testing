import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Set_Insert_Test {

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
}