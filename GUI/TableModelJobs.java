package grafic;


import temaPOO.*;

import javax.swing.table.AbstractTableModel;
import java.util.Vector;

public class TableModelJobs extends AbstractTableModel {
    Vector data;
    Vector columns;
    //creier tabelul default pentru a afisa job-urile companiei
    public TableModelJobs(Company company) {
        data = new Vector();
        columns = new Vector();
        Application app = Application.getInstance();
        columns.addElement("Position");
        columns.addElement("No positions");
        columns.addElement("Department");
        columns.addElement("Average");
        columns.addElement("Grad Year Min");
        columns.addElement("Grad Year Max");
        columns.addElement("Exp Min");
        columns.addElement("Exp Max");
        columns.addElement("Salary");

        for (int i = 0; i < company.getDepartaments().size(); i++) {
            Departament dep = company.getDepartaments().get(i);
            for (int j = 0; j < dep.getAvailableJobs().size(); j++) {
                Job job = dep.getAvailableJobs().get(j);
                String position = job.getName();
                String noPos = "" + job.getNoPositions();
                String departament = "IT";
                String gpa = ""+ job.getAverage().getMinLimit();
                String gmin = job.getGraduationYear().getMinLimit();
                String gmax = job.getGraduationYear().getMaxLimit();
                String expmin = job.getExperienceYears().getMinLimit();
                String expmax = job.getExperienceYears().getMaxLimit();
                String salaryyy = "" + job.getSalary();
                data.addElement(position);
                data.addElement(noPos);
                data.addElement(departament);
                data.addElement(gpa);
                data.addElement(gmin);
                data.addElement(gmax);
                data.addElement(expmin);
                data.addElement(expmax);
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

