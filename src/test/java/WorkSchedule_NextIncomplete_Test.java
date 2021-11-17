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
        ws = new WorkSchedule(10); // ????
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
        ws.addWorkingPeriod("Axel", 8, 10);
        ws.addWorkingPeriod("Robert", 8, 10);
        ws.addWorkingPeriod("Pandi", 8, 10);
        ws.setRequiredNumber(1, 10, 8);
        // assert that these values changed
        for (WorkSchedule.Hour hour : ws.getSchedule()) {
            assertEquals(1, hour.requiredNumber);
            assertEquals(3, hour.workingEmployees.length);
        }
    }

    // test whether we can assign one employee between hour 8 and hour 10
    @Test
    void test_starttime_lt_endtime_and_workingEmployees_lt_nemployee() {
        ws = new WorkSchedule(12);
        ws.addWorkingPeriod("Snorkle", 8, 10);
        ws.setRequiredNumber(3, 8, 10);
        // assert that these values changed
        for (WorkSchedule.Hour hour: ws.getSchedule()) {
            assertEquals(3, hour.requiredNumber);
            assertEquals(1, hour.workingEmployees.length);
        }
    }
}