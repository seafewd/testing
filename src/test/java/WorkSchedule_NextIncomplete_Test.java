import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WorkSchedule_NextIncomplete_Test {
    WorkSchedule ws;

    // test whether we can get the first instance of an
    // hour which doesn't yet have its positions filled
    // with workingEmployees
    @Test
    void test_workingEmployees_lt_requiredNumber() {
        ws = new WorkSchedule(6);
        int scheduleStartTime = 1;
        int result = -1;

        // set up five separate hours
        WorkSchedule.Hour hour1 = ws.readSchedule(scheduleStartTime);
        WorkSchedule.Hour hour2 = ws.readSchedule(2);
        WorkSchedule.Hour hour3 = ws.readSchedule(3);
        WorkSchedule.Hour hour4 = ws.readSchedule(4);
        WorkSchedule.Hour hour5 = ws.readSchedule(2);

        // set required number of employees for each hour
        hour1.requiredNumber = 1;
        hour2.requiredNumber = 2;
        hour3.requiredNumber = 3;
        hour4.requiredNumber = 4;
        hour5.requiredNumber = 2;

        // add employees to each respective working hour
        hour1.addEmployee("Soffan");

        hour2.addEmployee("Soffan");
        hour2.addEmployee("Novalie");

        hour3.addEmployee("Soffan");
        hour3.addEmployee("Novalie");
        hour3.addEmployee("Robert");

        hour4.addEmployee("Soffan");
        hour4.addEmployee("Novalie");
        hour4.addEmployee("Robert");

        hour5.addEmployee("Robert");

        for (int i = scheduleStartTime; i < ws.getSchedule().length; i++) {
            WorkSchedule.Hour hour = ws.readSchedule(i);
            if (hour.workingEmployees.length < hour.requiredNumber) {
                result = ws.nextIncomplete(i);
                break;
            }
        }
        // result should now be 4 since hour 4 is the first
        // hour that hasn't yet filled its free positions
        assertEquals(4, result);
    }

    @Test
    void test_workingEmployees_gtOrEq_requiredNumber() {
        ws = new WorkSchedule(6);
        int scheduleStartTime = 1;
        int result = -1;

        // set up five separate hours
        WorkSchedule.Hour hour1 = ws.readSchedule(scheduleStartTime);
        WorkSchedule.Hour hour2 = ws.readSchedule(2);
        WorkSchedule.Hour hour3 = ws.readSchedule(3);
        WorkSchedule.Hour hour4 = ws.readSchedule(4);
        WorkSchedule.Hour hour5 = ws.readSchedule(2);

        // set required number of employees for each hour
        hour1.requiredNumber = 1;
        hour2.requiredNumber = 2;
        hour3.requiredNumber = 3;
        hour4.requiredNumber = 4;
        hour5.requiredNumber = 2;

        // add employees to each respective working hour
        hour1.addEmployee("Soffan");

        hour2.addEmployee("Soffan");
        hour2.addEmployee("Novalie");

        hour3.addEmployee("Soffan");
        hour3.addEmployee("Novalie");
        hour3.addEmployee("Robert");

        hour4.addEmployee("Soffan");
        hour4.addEmployee("Novalie");
        hour4.addEmployee("Robert");
        hour4.addEmployee("Axel");

        hour5.addEmployee("Robert");
        hour5.addEmployee("Axel");

        for (int i = scheduleStartTime; i < ws.getSchedule().length; i++) {
            WorkSchedule.Hour hour = ws.readSchedule(i);
            if (hour.workingEmployees.length < hour.requiredNumber) {
                result = ws.nextIncomplete(i);
                break;
            }
        }
        // result should now be 4 since hour 4 is the first
        // hour that hasn't yet filled its free positions
        assertEquals(-1, result);
    }
}