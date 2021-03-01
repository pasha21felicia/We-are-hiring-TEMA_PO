package temaPOO;

import java.util.ArrayList;

public abstract class Departament {
    private ArrayList<Employee> employeesList = new ArrayList<Employee>();
    private ArrayList<Job> availableJobs = new ArrayList<Job>();

    public Departament(ArrayList<Employee> employeesList, ArrayList<Job> availableJobs) {
        this.availableJobs = availableJobs;
        this.employeesList = employeesList;
    }
    public Departament() {

    }
    public abstract double getTotalSalaryBudget();
    //returnez joburile deschise din departament
    public ArrayList<Job> getJobs() {
        for (int i = 0; i < this.availableJobs.size(); i++) {
            Job nextJob = this.availableJobs.get(i);
            if (nextJob.state == true)
                this.availableJobs.remove(nextJob);
        }
        return this.availableJobs;
    }

    public ArrayList<Job> getAvailableJobs() { return availableJobs; }
    public ArrayList<Employee> getEmployees() { return this.employeesList; }
    public void add(Employee employee) {
        this.employeesList.add(employee);
    }
    public void remove(Employee employee) {
        this.employeesList.remove(employee);
    }
    public void add(Job job) {
        this.availableJobs.add(job);
    }

    @Override
    public String toString() {
        return "\nDepartament{" +
                "employeesList=" + employeesList + "\n" +
                "availableJobs=" + availableJobs +
                '}';
    }


}
