import java.util.Arrays;

public class WorkSchedule {
    private final Hour[] schedule;

    public WorkSchedule(int time) {
        this.schedule = new Hour[time];

        for(int i = 0; i < time; ++i) {
            this.schedule[i] = new Hour(0);
        }

    }

    public Hour[] getSchedule() {
        return this.schedule;
    }

    public Hour readSchedule(int time) {
        return this.schedule[time];
    }

    public void setRequiredNumber(int nemployee, int starttime, int endtime) {
        for(int i = starttime; i <= endtime; ++i) {
            this.schedule[i].requiredNumber = nemployee;
            if (nemployee < this.schedule[i].workingEmployees.length) {
                this.schedule[i].workingEmployees = (String[])Arrays.copyOf(this.schedule[i].workingEmployees, Math.max(nemployee - 1, 0));
            }
        }

    }

    public boolean addWorkingPeriod(String employee, int starttime, int endtime) {
        boolean check = starttime >= 0 && endtime < this.schedule.length && starttime <= endtime;

        int i;
        for(i = starttime; i <= endtime && check; ++i) {
            if (!this.schedule[i].checkEmployee(employee)) {
                check = false;
                break;
            }
        }

        if (check) {
            for(i = starttime; i <= endtime; ++i) {
                this.schedule[i].addEmployee(employee);
            }
        }

        return check;
    }

    public String[] workingEmployees(int starttime, int endtime) {
        if (endtime < starttime) {
            return new String[0];
        } else {
            String[][] acc = new String[this.schedule.length][];
            int size = 0;

            for(int i = starttime; i <= endtime; ++i) {
                acc[i] = this.schedule[i].workingEmployees;
                size += this.schedule[i].workingEmployees.length;
            }

            String[] acc0 = new String[size];
            int n = 0;

            for(int i = starttime; i <= endtime; ++i) {
                for(int j = 0; j < this.schedule[i].workingEmployees.length; ++j) {
                    acc0[n++] = this.schedule[i].workingEmployees[j];
                }
            }

            return (String[])Arrays.stream(acc0).distinct().toArray((var0) -> {
                return new String[var0];
            });
        }
    }

    public int nextIncomplete(int currenttime) {
        int req = -1;

        for(int i = currenttime; i < this.schedule.length; ++i) {
            if (this.schedule[i].workingEmployees.length < this.schedule[i].requiredNumber) {
                req = i;
            }
        }

        return req;
    }

    public class Hour {
        public int requiredNumber;
        public String[] workingEmployees;

        private Hour(int requiredNumber) {
            this.requiredNumber = requiredNumber;
            this.workingEmployees = new String[0];
        }

        private boolean checkEmployee(String name) {
            boolean res = true;
            if (this.workingEmployees.length < this.requiredNumber) {
                for(int i = 0; i < this.workingEmployees.length; ++i) {
                    if (this.workingEmployees[i].equals(name)) {
                        res = false;
                        break;
                    }
                }
            } else {
                res = false;
            }

            return res;
        }

        private void addEmployee(String name) {
            if (this.workingEmployees.length < this.requiredNumber) {
                this.workingEmployees = (String[])Arrays.copyOf(this.workingEmployees, this.workingEmployees.length + 1);
                this.workingEmployees[this.workingEmployees.length - 1] = name;
            }

        }
    }
}
