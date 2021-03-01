package temaPOO;
import java.util.*;

public class Company implements Subject{
    String name;
    Manager manager;
    private ArrayList<Departament> departaments = new ArrayList<Departament>();
    private ArrayList<Recruiter> recruiters = new ArrayList<Recruiter>();
    private ArrayList<User> observers = new ArrayList<User>();
    Notification notification;

    public Company(String name, Manager manager) {
        this.name = name;
        this.manager = manager;
    }
    public ArrayList<Departament> getDepartaments() { return departaments; }
    public ArrayList<Recruiter> getRecruiters() { return recruiters; }
    public Manager getManager() {
        return manager;
    }
    public String getName() {
        return name;
    }
    public void add(Departament d) {
        this.departaments.add(d);
    }
    public void add(Recruiter r) {
        this.recruiters.add(r);
    }
    public void add(Employee employee, Departament department) {
        department.getEmployees().add(employee);
    }
    public void remove(Employee employee) {
        departaments.remove(employee);
    }
    public void remove(Departament department) {
        department = null;
    }
    public void remove(Recruiter r) {
        recruiters.remove(r);
    }
    public void move(Departament source, Departament destination) {

    }

    public void move(Employee employee, Departament newDepartment) {
        newDepartment.getEmployees().add(employee);
    }
    public boolean contains(Departament department) {
        if (departaments.contains(department))
            return true;
        else return false;
    }
    public boolean contains(Employee employee) {
        if (departaments.contains(employee))
            return true;
        else return false;
    }
    public boolean contains(Recruiter recruiter) {
        if (recruiters.contains(recruiter))
            return true;
        else return false;
    }
    public boolean contains(User obs) {
        if (observers.contains(obs))
            return true;
        else return false;
    }
    public Recruiter getRecruiter(User user) {
        ArrayList<Recruiter> gradOfRec = new ArrayList<Recruiter>();
        int maxi = 0;
        for (Iterator it = this.recruiters.listIterator(); it.hasNext(); ) {
            Recruiter nextRec = (Recruiter) it.next();
            int key = user.getDegreeInFriendship(nextRec);
            if (key >= maxi) {
                maxi = key;
            }
        }

        for (Iterator it = this.recruiters.listIterator(); it.hasNext(); ) {
            Recruiter nextRec = (Recruiter) it.next();
            if (user.getDegreeInFriendship(nextRec) == maxi) {
                gradOfRec.add(nextRec);
            }
        }
        if (gradOfRec.size() == 1)
           return gradOfRec.get(0);
        else
            return Collections.max(gradOfRec, Comparator.comparing(s->s.getRating()));
    }
    //arata toate joburile deschise din companie
    public ArrayList<Job> getJobs() {
        ArrayList<Job> allJobs= new ArrayList<Job>();
        for (Iterator it = this.departaments.listIterator(); it.hasNext();) {
            Departament nextDep = (Departament) it.next();
            allJobs.addAll(nextDep.getJobs());
        }
        return allJobs;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", manager=" + manager +
                ", departaments=" + departaments +
                ", recruiters=" + recruiters +
                '}';
    }
    @Override
    public void addObserver(User c){
        this.observers.add(c);
    }

    @Override
    public void removeObserver(User c){
        this.observers.remove(c);
    }
    ArrayList<User> getObservers(){
        return this.observers;
    }
    @Override
    public void notifyAllObservers(){
        for (Observer o : observers){
            o.update(notification);
        }
    }

}
