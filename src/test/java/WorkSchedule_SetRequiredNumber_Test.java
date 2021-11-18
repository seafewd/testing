import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WorkSchedule_SetRequiredNumber_Test {

    WorkSchedule ws, ws2;

    // test whether the schedule remains unchanged when
    // starttime > endtime
    @Test
    void test_starttime_gt_endtime() {
        ws = new WorkSchedule(10);
        ws2 = new WorkSchedule(10);
        int nemployee = 1;
        int starttime = 4;
        int endtime = 2;
        //WorkSchedule.Hour[] oldSchedule = ws.getSchedule();
        //WorkSchedule.Hour[] newSchedule = (WorkSchedule.Hour[]) Arrays.copyOf(oldSchedule, oldSchedule.length);
        ws.setRequiredNumber(nemployee, starttime, endtime);
        // should be unchanged
        System.out.println();
        // First element should be 0, same for remaining elements since starttime > endtime
        assertEquals(0, ws.getSchedule()[0].requiredNumber);
        for(int i = 0; i < ws.getSchedule().length; i++)
            assertEquals(0, ws.getSchedule()[i].requiredNumber);
    }


    // test whether we can set the required number of employees
    // for each hour between 8 and 10 to 1
    // as well as discarding the rest of the working employees
    // which are not needed
    @Test
    void test_starttime_lt_endtime_and_workingEmployees_gt_nemployee() {
        ws = new WorkSchedule(12);
        int nemployee = 1;
        int starttime = 8;
        int endtime = 10;
        // workingEmployees = 3
        ws.setRequiredNumber(nemployee, starttime, endtime);
        // add three employees as well as three workingPeriods
        for (int i = starttime; i <= endtime; i++) {
            WorkSchedule.Hour hour = ws.readSchedule(i);
            hour.addEmployee("Axel");
            hour.addEmployee("Robert");
            hour.addEmployee("Pandi");
            ws.addWorkingPeriod("Axel", starttime, endtime);
            ws.addWorkingPeriod("Robert", starttime, endtime);
            ws.addWorkingPeriod("Pandi", starttime, endtime);
        }
        // assert that only one employee is needed for these hours
        // as well as that the schedule only assigns as many as needed (one)
        for (int i = starttime; i < endtime; i++) {
            WorkSchedule.Hour hour = ws.readSchedule(i);
            assertEquals(nemployee, hour.requiredNumber);
            assertEquals(1, hour.workingEmployees.length);
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
        // workingEmployees = 1
        ws.setRequiredNumber(nemployee, starttime, endtime);
        // add one employee as well as one workingPeriod
        for (int i = starttime; i <= endtime; i++) {
            WorkSchedule.Hour hour = ws.readSchedule(i);
            hour.addEmployee("Axel");
            ws.addWorkingPeriod("Axel", starttime, endtime);
        }
        // assert that three employee are needed for these hours
        // as well as that workingEmployees is unchanged
        for (int i = starttime; i <= endtime; i++) {
            WorkSchedule.Hour hour = ws.readSchedule(starttime);
            assertEquals(nemployee, hour.requiredNumber);
            assertEquals(1, hour.workingEmployees.length);
        }
    }
}