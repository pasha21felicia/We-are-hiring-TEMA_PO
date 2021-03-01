package temaPOO;
import java.util.ArrayList;
public class Employee extends Consumer {
    private String company_name;
    private int salary;
    public Employee (ArrayList<Consumer> listAcquintances, Consumer.Resume resume, String company_name, int salary) {
        super(listAcquintances, resume);
        this.salary = salary;
        this.company_name = company_name;
    }
    public Employee (ArrayList<Consumer> listAcquintances, Consumer.Resume resume) {
        super(listAcquintances, resume);
    }
    public Employee() {

    }
    public int getSalary() {
        return this.salary;
    }
    public String getCompany_name() {
        return this.company_name;
    }
    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }
    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.getResume().getInfo().getNume();
    }
}
