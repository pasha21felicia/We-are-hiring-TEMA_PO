package temaPOO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Application {
    private static Application app = new Application();
    private ArrayList<Company> companies = new ArrayList<Company>();
    private ArrayList<User> users = new ArrayList<User>();
    private Application() {
    }
    public static Application getInstance() {
        if (app == null) {
            app = new Application();
        }
        return app;
    }
    public ArrayList<Company> getCompanies() {
        return companies;
    }
    public ArrayList<User> getUsers() { return users; }
    //cauta obiectul Company dupa nume
    public Company getCompany(String name) {
        for (Iterator it = companies.listIterator(); it.hasNext();) {
            Company nextCom = (Company) it.next();
            if (nextCom.name.equals(name))
                return nextCom;
        }
        return null;
    }
    //cauta obiectul User dupa nume
    public User getUser(String name) {
        for (Iterator it = users.listIterator(); it.hasNext();) {
            User nextUser = (User) it.next();
            if (nextUser.getResume().getInfo().getNume().equals(name))
                return nextUser;
        }
        return null;
    }
    public void add(Company company) {
        this.companies.add(company);
    }
    public void add(User user) {
        this.users.add(user);
    }
    public boolean remove(Company company) {
        if (companies.contains(company))
            return companies.remove(company);
        else
            return false;
    }
    public boolean remove(User user) {
        if (users.contains(user))
            return users.remove(user);
        else
            return false;
    }
    //parcurg lista de departamente din fiecare companie si aflu toate joburile deschise
    public ArrayList<Job> getJobs(List<String> companies) {
        ArrayList<Job> allAvailableJobs = new ArrayList<Job>();
        for (Iterator i = companies.listIterator(); i.hasNext();) {
            String comName = (String) i.next();
            Company nextCom = getCompany(comName);
            for (Iterator j = nextCom.getDepartaments().listIterator(); j.hasNext();) {
                Departament dep = (Departament) j.next();
                allAvailableJobs.addAll(dep.getJobs());
            }
        }
        return allAvailableJobs;
    }
}
