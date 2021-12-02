import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WorkSchedule_SetRequiredNumber_Tests {

    WorkSchedule ws, ws2;

    // test whether the schedule remains unchanged when
    // starttime > endtime
    @Test
    void test_starttime_gt_endtime() {
        int workScheduleLength = 10;
        ws = new WorkSchedule(workScheduleLength);
        int nemployee = 1;
        int starttime = 4;
        int endtime = 2;

        ws.setRequiredNumber(nemployee, starttime, endtime);

        for (int i = starttime; i < workScheduleLength; i++)
            assertEquals(0, ws.readSchedule(i).requiredNumber);
    }


    // test whether we can set the required number of employees
    // for each hour between 8 and 10 to 1
    // as well as discarding the rest of the working employees
    // which are not needed
    @Test
    void test_starttime_lt_endtime_and_workingEmployees_gt_nemployee() {
        int workScheduleLength = 12;
        ws = new WorkSchedule(workScheduleLength);
        int nemployee = 1;
        int starttime = 8;
        int endtime = 10;

        ws.setRequiredNumber(nemployee, starttime, endtime);
        // add three workingPeriods
        for (int i = starttime; i < endtime; i++) {
            ws.addWorkingPeriod("Axel", starttime, endtime);
            ws.addWorkingPeriod("Robert", starttime, endtime);
            ws.addWorkingPeriod("Pandi", starttime, endtime);
        }
        // assert that only one employee is needed for these hours
        // as well as that the schedule only assigns as many as needed (one)
        for (int i = starttime; i < endtime; i++) {
            WorkSchedule.Hour hour = ws.readSchedule(i);
            assertEquals(nemployee, hour.requiredNumber);
            assertEquals(nemployee, hour.workingEmployees.length);
        }
    }

    // test whether we can set the required number of employees
    // for each hour between 8 and 10 to 3
    // as well as making sure that the number of
    // workingEmployees is unchanged
    @Test
    void test_starttime_lt_endtime_and_workingEmployees_lt_nemployee() {
        ws = new WorkSchedule(12);
        int nemployee = 3;
        int starttime = 8;
        int endtime = 10;

        ws.setRequiredNumber(nemployee, starttime, endtime);
        // add a workingPeriod with one employee
        for (int i = starttime; i < endtime; i++)
            ws.addWorkingPeriod("Axel", starttime, endtime);

        // assert that three employee are needed for these hours
        // as well as that workingEmployees is unchanged
        for (int i = starttime; i < endtime; i++) {
            WorkSchedule.Hour hour = ws.readSchedule(starttime);
            assertEquals(nemployee, hour.requiredNumber);
            assertEquals(1, hour.workingEmployees.length);
        }
    }
}