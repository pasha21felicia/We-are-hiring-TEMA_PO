package temaPOO;

import java.util.ArrayList;
import java.util.Iterator;

public class Finance extends Departament{
    public Finance(ArrayList<Employee> employeesList, ArrayList<Job> availableJobs) {
        super(employeesList, availableJobs);
    }
    public Finance(){

    }
    public double getTotalSalaryBudget() {
        double totalBudget = 0;
        for (Iterator it = this.getEmployees().listIterator(); it.hasNext();) {
            Employee nextEmp = (Employee) it.next();
            Experience last = nextEmp.getResume().getExp().get(this.getEmployees().size() - 1);
            if (last.startDate.contains("2020"))
                totalBudget += nextEmp.getSalary() + nextEmp.getSalary()*0.10;
            else
                totalBudget += nextEmp.getSalary() + nextEmp.getSalary()*0.16;
        }
        return totalBudget;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}