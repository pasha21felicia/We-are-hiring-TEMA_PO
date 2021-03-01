package grafic;


import temaPOO.*;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class TableModelBudget extends AbstractTableModel {
    Vector data;
    Vector columns;
    //creez tabelul default pentru a afisa bugetul fiecarui departament
    public TableModelBudget(Company company) {
        String line;
        data = new Vector();
        columns = new Vector();
        Application app = Application.getInstance();
        columns.addElement("Department");
        columns.addElement("Annual Budget");

        for (int i = 0; i < company.getDepartaments().size(); i++) {
            Departament dep = company.getDepartaments().get(i);
            String departament = null;
            if (i == 0) {
                departament = "IT";
                Double salarybudgetIT = company.getDepartaments().get(0).getTotalSalaryBudget();
                String budgetIt = String.valueOf(salarybudgetIT);
                data.addElement(departament);
                data.addElement(budgetIt);
            }
            else if (i == 1) {
                departament = "Management";
                Double salarybudgetMan = company.getDepartaments().get(1).getTotalSalaryBudget();
                String budgetMan = String.valueOf(salarybudgetMan);
                data.addElement(departament);
                data.addElement(budgetMan);
            }
            else if (i == 2) {
                departament = "Marketing";
                Double salarybudgetMark = company.getDepartaments().get(2).getTotalSalaryBudget();
                String budgetMark = String.valueOf(salarybudgetMark);
                data.addElement(departament);
                data.addElement(budgetMark);
            }
            else {
                departament = "Finance";
                Double salarybudgetMFin = company.getDepartaments().get(3).getTotalSalaryBudget();
                String budgetFin = String.valueOf(salarybudgetMFin);
                data.addElement(departament);
                data.addElement(budgetFin);
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

