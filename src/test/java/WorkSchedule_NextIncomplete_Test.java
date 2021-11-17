import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class WorkSchedule_NextIncomplete_Test {
    WorkSchedule ws;

    @Test
    void test_workingEmployees_lt_requiredNumber() {
        ws = new WorkSchedule(3);
        int result = -1;
        for (int i = 2; i < ws.getSchedule().length; i++) {
            WorkSchedule.Hour hour = ws.readSchedule(i);
            hour.addEmployee("Soffan");
            ws.addWorkingPeriod("Soffan", 2, 3);
            if (hour.workingEmployees.length < hour.requiredNumber) {
                result = ws.nextIncomplete(i);
                break;
            }
        }
        assertEquals(2, result); // todo change
    }
}