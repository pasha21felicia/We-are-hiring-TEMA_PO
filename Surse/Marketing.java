package temaPOO;

import java.util.ArrayList;
import java.util.Iterator;

public class Marketing extends Departament{
    public Marketing(ArrayList<Employee> employeesList, ArrayList<Job> availableJobs) {
        super(employeesList, availableJobs);
    }
    public Marketing() {
    }
    public double getTotalSalaryBudget() {
        double totalBudget = 0;
        for (Iterator it = this.getEmployees().listIterator(); it.hasNext();) {
            Employee nextEmp = (Employee) it.next();
            if (nextEmp.getSalary() > 5000)
                totalBudget += nextEmp.getSalary() + nextEmp.getSalary()*0.10;
            else if (nextEmp.getSalary() < 3000)
                totalBudget += nextEmp.getSalary();
            else totalBudget += nextEmp.getSalary() + nextEmp.getSalary()*0.16;
        }
        return totalBudget;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
