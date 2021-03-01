package temaPOO;

import java.util.*;
import java.util.Comparator;

public class Manager extends Employee {
    private String username;
    private String password;
    private ArrayList<Request<Job, Consumer>> allRequests = new ArrayList<Request<Job, Consumer>>();
    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public ArrayList<Request<Job, Consumer>> getAllRequests() { return allRequests; }

    public Manager(ArrayList<Consumer> listAcquintances, Resume resume, String company_name, int salary) {
        super(listAcquintances, resume, company_name, salary);
    }
    public void add(Request request) {
        this.allRequests.add(request);
    }

    public void process(Job job) {
        Application app = Application.getInstance();
        for (Iterator it = allRequests.listIterator(); it.hasNext();) {
            Request<Job, Consumer> nextRequest = (Request<Job, Consumer>) it.next();
            if (nextRequest.getKey().equals(job)) {
                job.candidates.sort(Comparator.comparing(User::getTotalScore).reversed());//sortarea candidatilor dupa scor
            }
        }
        Company com = app.getCompany(this.getCompany_name());

        for (int j = 0; j < job.candidates.size(); j++) {
           User goodCandidate = job.candidates.get(j);
           if (app.getUsers().contains(goodCandidate)) {
               for (int i = 0; i < job.noPositions && (job.state == false); i++) {
                   Employee newEmployee = goodCandidate.convert();
                   newEmployee.setCompany_name(job.getCompanyName());
                   newEmployee.setSalary(job.salary);
                   com.add(newEmployee, com.getDepartaments().get(0));

                   //stergerea observerului angajat din toate companiile la care mai era observer
                   for (int k = 0; k < goodCandidate.getAppliedComp().size(); k++) {
                       Company appliedCom = goodCandidate.getAppliedComp().get(k);
                       appliedCom.removeObserver(goodCandidate);
                   }
                   app.remove(goodCandidate); //stergerea din lista de useri a aplicatiei
                   job.candidates.remove(goodCandidate);
               }
               job.state = true;    //inchiderea job-ului
           }
       }
        //trimiterea notificarilor catre toti observerii companiei ca jobul s-a inschis
        Notification note = new Notification();
        note.settNotificationType(Notification.NotificationType.CLOSED_JOB);
        note.setJob(job);
        note.setCompany(com);
        com.notification = note;
        com.notifyAllObservers();
    }

}
