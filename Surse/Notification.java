package temaPOO;

class Notification {
    private NotificationType n;
    private String message;
    private Job job;
    private Company company;
    //notificarile vor fi standardizate dupa tip
    public enum NotificationType  {
        NEW_JOB,
        CLOSED_JOB,
        DENIAL
    };
    public void setJob(Job job) {
        this.job = job;
    }
    public Job getJob() {
        return job;
    }
    public void setCompany(Company company) {
        this.company = company;
    }
    public Company getCompany() {
        return company;
    }
    public NotificationType getNotificationType() {
        return this.n;
    }
    public void settNotificationType(NotificationType n) {
        this.n = n;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
