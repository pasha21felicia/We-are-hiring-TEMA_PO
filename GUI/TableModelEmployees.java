package grafic;


import temaPOO.Application;
import temaPOO.Company;
import temaPOO.Departament;
import temaPOO.Employee;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class TableModelEmployees extends AbstractTableModel {
    Vector data;
    Vector columns;
    //creiez tabelul default pentru a afisa toti angajatii companiei
    public TableModelEmployees(Company company) {
        String line;
        data = new Vector();
        columns = new Vector();
        Application app = Application.getInstance();
        columns.addElement("Nume");
        columns.addElement("Email");
        columns.addElement("Telephone");
        columns.addElement("Date of Birth");
        columns.addElement("Genre");
        columns.addElement("Department");
        columns.addElement("Position");
        columns.addElement("Salary");

        for (int i = 0; i < company.getDepartaments().size(); i++) {
            Departament dep = company.getDepartaments().get(i);
            for (int j = 0; j < dep.getEmployees().size(); j++) {
                Employee emp = dep.getEmployees().get(j);
                String name = emp.getResume().getInfo().getNume();
                String email = emp.getResume().getInfo().getEmail();
                String phone = emp.getResume().getInfo().getTelefon();
                String birthdate = emp.getResume().getInfo().getBirthdate();
                String genre = emp.getResume().getInfo().getSex();
                String departament = null;
                String position = null;
                int salary = emp.getSalary();
                String salaryyy = ""+ salary;
                if (i == 0) departament = "IT";
                else if (i == 1) departament = "Management";
                else if (i == 2) departament = "Marketing";
                else departament = "Finance";
                if (company.getRecruiters().contains(emp)) {
                    position = "Recruiter";
                } else position = "Engineer";

                data.addElement(name);
                data.addElement(email);
                data.addElement(phone);
                data.addElement(birthdate);
                data.addElement(genre);
                data.addElement(departament);
                data.addElement(position);
                data.addElement(salaryyy);
            }
        }
    }

    public int getRowCount() {
        return data.size() / getColumnCount();
    }

    public int getColumnCount(){
        return columns.size();
    }

    public String getColumnName(int columnIndex) {
        String colName = "";

        if (columnIndex <= getColumnCount())
            colName = (String)columns.elementAt(columnIndex);

        return colName;
    }

    public Class getColumnClass(int columnIndex){
        return String.class;
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return (String)data.elementAt
                ( (rowIndex * getColumnCount()) + columnIndex);
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        return;
    }
}
