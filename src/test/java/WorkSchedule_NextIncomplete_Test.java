import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WorkSchedule_NextIncomplete_Test {
    WorkSchedule ws;

    @BeforeEach
    void setUp() {
    }

    // test whether the schedule remains unchanged when
    // starttime > endtime
    @Test
    void test_starttime_gt_endtime() {
        ws = new WorkSchedule(10); // ????
        WorkSchedule.Hour[] oldSchedule = ws.getSchedule();
        WorkSchedule.Hour[] newSchedule = oldSchedule.clone();
        ws.setRequiredNumber(1, 10, 8);
        // should be unchanged
        assertArrayEquals(oldSchedule, newSchedule);
    }

    // test whether we can assign one employee between hour 8 and hour 10
    @Test
    void test_starttime_lt_endtime_and_workingEmployees_gt_nemployee() {
        ws = new WorkSchedule(12);
        ws.setRequiredNumber(1, 8, 10);
        // add three employees as well as three workingPeriods
        for (int i = 8; i < 10; i++) {
            WorkSchedule.Hour hour = ws.readSchedule(i);
            hour.addEmployee("Axel");
            hour.addEmployee("Robert");
            hour.addEmployee("Pandi");
            ws.addWorkingPeriod("Axel", 8, 10);
            ws.addWorkingPeriod("Robert", 8, 10);
            ws.addWorkingPeriod("Pandi", 8, 10);
        }
        // assert that only one employee is needed for these hours
        // as well as that the schedule only assigns as many as needed (one)
        for (int i = 8; i < 10; i++) {
            WorkSchedule.Hour hour = ws.readSchedule(i);
            assertEquals(1, hour.requiredNumber);
            assertEquals(1, hour.workingEmployees.length);
        }
    }

    // test whether we can assign one employee between hour 8 and hour 10
    @Test
    void test_starttime_lt_endtime_and_workingEmployees_lt_nemployee() {
        ws = new WorkSchedule(12);
        ws.setRequiredNumber(3, 8, 10);
        // add one employee as well as one workingPeriod
        for (int i = 8; i < 10; i++) {
            WorkSchedule.Hour hour = ws.readSchedule(i);
            hour.addEmployee("Axel");
            ws.addWorkingPeriod("Axel", 8, 10);
        }
        // assert that three employee are needed for these hours
        // as well as that workingEmployees is unchanged
        for (int i = 8; i < 10; i++) {
            WorkSchedule.Hour hour = ws.readSchedule(i);
            assertEquals(3, hour.requiredNumber);
            assertEquals(1, hour.workingEmployees.length);
        }
    }
}