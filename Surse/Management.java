package temaPOO;

import java.util.ArrayList;
import java.util.Iterator;

public class Management extends Departament{
    public Management(ArrayList<Employee> employeesList, ArrayList<Job> availableJobs) {
        super(employeesList, availableJobs);
    }
    public Management(){

    }
    public double getTotalSalaryBudget() {
        double totalBudget = 0;
        for (Iterator it = this.getEmployees().listIterator(); it.hasNext();) {
            Employee nextEmp = (Employee) it.next();
            totalBudget += nextEmp.getSalary() + nextEmp.getSalary()*0.16;
        }
        return totalBudget;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
