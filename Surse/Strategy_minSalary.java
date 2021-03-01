package temaPOO;

import java.util.ArrayList;

public class Strategy_minSalary implements Strategy {
    private Job job;
    private int minSalary;

    public void setJob(Job job) {
        this.job = job;
    }
    public Job getJob() {
        return job;
    }
    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }
    public int getMinSalary() {
        return minSalary;
    }

    //strategia de aplicare doar la job-urile cu un salariu minim dorit de user
    @Override
    public void execute(Job job, User user) {
        if (job.salary >= minSalary) {
            job.apply(user);
        }
    }
}
