package temaPOO;

import java.util.ArrayList;

public class Recruiter extends Employee {
    private int Rating;
    public Recruiter(ArrayList<Consumer> listAcquintances, Resume resume, String company_name, int salary, int Rating) {
        super(listAcquintances, resume, company_name, salary);
        this.Rating = Rating;
    }
    public int getRating() {
        return Rating;
    }
    public double evaluate(Job job, User user) {
        Application app = Application.getInstance();
        if (job.candidates.size() == 0) //cazul daca nimeni nu a aplicat la acel job
            return 0;
        double finalScore = this.Rating * user.getTotalScore();
        this.Rating += this.Rating + 0.1;
        Request<Job, Consumer> newRequest = new Request<Job, Consumer>(job, user, this, finalScore);
        String comName = this.getCompany_name();
        Company company = app.getCompany(comName);
        Manager theBoss = company.getManager();
        theBoss.add(newRequest);    //adaugarea request-ului in lista de request-uri a managerului
        return finalScore;
    }


}
