package temaPOO;

import java.util.ArrayList;
import java.util.Iterator;

public class IT extends Departament{
    public IT(ArrayList<Employee> employeesList, ArrayList<Job> availableJobs) {
        super(employeesList, availableJobs);
    }
    public IT(){

    }
    public double getTotalSalaryBudget() {
        double totalBudget = 0;
        for (Iterator it = this.getEmployees().listIterator(); it.hasNext();) {
            Employee nextEmp = (Employee) it.next();
            totalBudget += nextEmp.getSalary();
        }
        return totalBudget;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
