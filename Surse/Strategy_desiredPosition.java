package temaPOO;

public class Strategy_desiredPosition implements Strategy{
    private Job job;
    private String position;

    public void setJob(Job job) {
        this.job = job;
    }
    public Job getJob() {
        return job;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getPosition() {
        return position;
    }

    //strategia de aplicare doar la joburile care au positia dorita de user
    @Override
    public void execute(Job job, User user) {
        System.out.println(job.getName());
        if (job.getName().contains(position)) {
            job.apply(user);
        }
        else return;
    }
}
