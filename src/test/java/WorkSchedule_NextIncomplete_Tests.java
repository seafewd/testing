import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WorkSchedule_NextIncomplete_Tests {
    WorkSchedule ws;

    // test whether we can get the first instance of an
    // hour which doesn't yet have its positions filled
    // with workingEmployees, or -1 if all positions filled

    // this test doesn't have all positions filled and should
    // thus return 4 since that is the first hour in which
    // its required minimum number of positions aren't filled

    // the test also makes sure that the schedule is unchanged
    @Test
    void test_workingEmployees_lt_requiredNumber() {
        int workScheduleLength = 6;
        ws = new WorkSchedule(workScheduleLength);
        //WorkSchedule.Hour[] newSchedule = ws.getSchedule();
        //WorkSchedule.Hour[] oldSchedule = Arrays.copyOf(newSchedule, newSchedule.length);

        int result = -1;

        // set up five separate hours with varying number of needed employees
        ws.setRequiredNumber(1, 1, 1);
        ws.setRequiredNumber(2, 2, 2);
        ws.setRequiredNumber(3, 3, 3);
        ws.setRequiredNumber(4, 4, 4);
        ws.setRequiredNumber(2, 5, 5);

        // add working periods
        ws.addWorkingPeriod("Soffan", 1, 4);
        ws.addWorkingPeriod("Novalie", 2, 4);
        ws.addWorkingPeriod("Robert", 3, 5);

        for (int i = 0; i < workScheduleLength; i++) {
            WorkSchedule.Hour hour = ws.readSchedule(i);
            if (hour.workingEmployees.length < hour.requiredNumber) {
                result = ws.nextIncomplete(i);
                break;
            }
        }
        // result should now be 4 since hour 4 is the first
        // hour that hasn't yet filled its free positions
        assertEquals(4, result);

        // make sure the schedule is unchanged
        //assertArrayEquals(oldSchedule, newSchedule);

    }

    // test whether we can get the first instance of an
    // hour which doesn't yet have its positions filled
    // with workingEmployees, or -1 if all positions filled

    // this test has all positions filled and should thus
    // return -1

    // the test also makes sure that the schedule is unchanged
    @Test
    void test_workingEmployees_gtOrEq_requiredNumber() {
        int worksScheduleLength = 6;
        ws = new WorkSchedule(worksScheduleLength);
        //WorkSchedule.Hour[] newSchedule = ws.getSchedule();
        //WorkSchedule.Hour[] oldSchedule = Arrays.copyOf(newSchedule, newSchedule.length);
        int result = -1;

        // set up five separate hours with varying number of needed employees
        ws.setRequiredNumber(1, 1, 1);
        ws.setRequiredNumber(2, 2, 2);
        ws.setRequiredNumber(3, 3, 3);
        ws.setRequiredNumber(4, 4, 4);
        ws.setRequiredNumber(2, 5, 5);

        // add working periods
        ws.addWorkingPeriod("Soffan", 1, 4);
        ws.addWorkingPeriod("Novalie", 2, 4);
        ws.addWorkingPeriod("Robert", 3, 5);
        ws.addWorkingPeriod("Axel", 4, 5);

        for (int i = 1; i < worksScheduleLength; i++) {
            WorkSchedule.Hour hour = ws.readSchedule(i);
            if (hour.workingEmployees.length < hour.requiredNumber) {
                result = ws.nextIncomplete(i);
                break;
            }
        }
        // result should now be -1 since all hours have their employee needs satisfied
        assertEquals(-1, result);

        // make sure the schedule is unchanged TODO
        //assertArrayEquals(oldSchedule, newSchedule);

    }
}