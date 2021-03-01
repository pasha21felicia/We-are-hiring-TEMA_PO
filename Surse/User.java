package temaPOO;
import java.util.*;

public class User extends Consumer implements Observer{
    private List<String> companies = new ArrayList<String>();
    private ArrayList<Company> appliedComp = new ArrayList<Company>();  //mentin un array cu job-rile la care a aplicat userul
    // pentru a il putea sterge din lista de observeri a acestor companii in cazul in care este angajat
    private ArrayList notifications = new ArrayList();
    private String strategy;
    private int minSalary;
    private String desiredPosition;
    private String username;
    private String password;


    public void add(Company company) {
        this.appliedComp.add(company);
    }
    public User(ArrayList<Consumer> listAcquintances, Consumer.Resume resume, List<String> companies ) {
        super(listAcquintances, resume);
        this.companies = companies;
    }
    public Double getTotalScore() {
        double score = 0;
        double media_academica = meanGPA();
        int nrYearExp = getExperienceYears();
        score = nrYearExp * 1.5 + media_academica;
        return score;
    }
    public ArrayList<Company> getAppliedComp() {
        return appliedComp;
    }
    public List<String> getCompanies() {
        return companies;
    }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }
    public String getStrategy() {
        return strategy;
    }
    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }
    public int getMinSalary() {
        return minSalary;
    }
    public void setDesiredPosition(String desiredPosition) {
        this.desiredPosition = desiredPosition;
    }
    public String getDesiredPosition() {
        return desiredPosition;
    }

    @Override
    public String toString() {
        return resume.getInfo().getNume();
    }
    public Employee convert() {
        Employee newEmployee = new Employee(this.listAcquintances, this.resume);
        return newEmployee;
    }

    @Override
    public void update(Notification notification) {
        notifications.add(notification);
        switch (notification.getNotificationType()) {
            case NEW_JOB:
                notification.setMessage("A aparut un job nou! Check it out!");
                System.out.println(this.getResume().getInfo().getNume() + "\n" + notification.getMessage());
                break;
            case DENIAL:
                notification.setMessage("Ne pare rau, dar nu indepliniti calificarile job-ului");
                System.out.println(this.getResume().getInfo().getNume() + "\n" + notification.getCompany().getName() +"\n" + notification.getJob().getName() + "\n"+ notification.getMessage());
                break;
            case CLOSED_JOB:
                notification.setMessage("Ne pare rau, dar acest job este deja inschis!");
                System.out.println(this.getResume().getInfo().getNume() + "\n" + notification.getCompany().getName() +"\n" + notification.getJob().getName() + "\n" + notification.getMessage());
                break;
        }
    }
}
