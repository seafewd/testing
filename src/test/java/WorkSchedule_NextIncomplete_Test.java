import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WorkSchedule_NextIncomplete_Test {
    WorkSchedule ws;

    @BeforeEach
    void setUp() {
    }

    // test whether we can assign one employee between hour 8 and hour 10
    @Test
    void test_starttime_gt_endtime() {
        ws = new WorkSchedule(100); // ????
        WorkSchedule.Hour[] oldSchedule = ws.getSchedule();
        WorkSchedule.Hour[] newSchedule = oldSchedule.clone();
        ws.setRequiredNumber(1, 8, 10);
        // should be unchanged
        assertArrayEquals(oldSchedule, newSchedule);
    }

    // test whether we can assign one employee between hour 8 and hour 10
    @Test
    void test_starttime_lt_endtime_and_workingEmployees_gt_nemployee() {
        ws = new WorkSchedule(2);
        ws.addWorkingPeriod("Axel", 10, 8);
        ws.addWorkingPeriod("Robert", 10, 8);
        ws.addWorkingPeriod("Pandi", 10, 8);
        ws.setRequiredNumber(1, 10, 8);
        // assert that these values changed
        assertTrue(requiredNumber == 1);
        assertTrue(workingEmployees == 3);
    }

    // test whether we can assign one employee between hour 8 and hour 10
    @Test
    void test_starttime_lt_endtime_and_workingEmployees_lt_nemployee() {
        ws = new WorkSchedule(2);
        ws.addWorkingPeriod("Snorkle", 10, 8);
        ws.setRequiredNumber(3, 10, 8);
        // assert that these values changed
        assertTrue(requiredNumber == 3);
        assertTrue(workingEmployees == 1);
    }
}