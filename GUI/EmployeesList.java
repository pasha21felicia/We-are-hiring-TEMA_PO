package grafic;

import temaPOO.Application;
import temaPOO.Company;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class EmployeesList extends JFrame {
    JTable table, tableJobs, tableBudget;;
    TableModelEmployees model;
    TableModelJobs modelJobs;
    TableModelBudget modelBudget;
    JPanel numbers;
    public EmployeesList(Company company) {
        Application app = Application.getInstance();
        Font f = new Font("SanSerif", Font.PLAIN, 24);
        setFont(f);
        setLayout(new BorderLayout());
        setTitle("Employees/ Budget/ Jobs");
        model = new TableModelEmployees(company);
        table = new JTable();
        table.setModel(model);
        table.createDefaultColumnsFromModel();
        table.setPreferredScrollableViewportSize(new Dimension(500, 150));
        table.setFillsViewportHeight(true);
        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setSize(700, 200);


        modelJobs = new TableModelJobs(company);
        tableJobs = new JTable();
        tableJobs.setModel(modelJobs);
        tableJobs.createDefaultColumnsFromModel();
        tableJobs.getColumnModel().getColumn(0).setPreferredWidth(350);
        tableJobs.setPreferredScrollableViewportSize(new Dimension(500, 50));
        tableJobs.setFillsViewportHeight(true);
        JScrollPane scrollpane2 = new JScrollPane(tableJobs);
        scrollpane2.setSize(700, 50);

        modelBudget = new TableModelBudget(company);
        tableBudget = new JTable();
        tableBudget.setModel(modelBudget);
        tableBudget.createDefaultColumnsFromModel();
        tableBudget.setPreferredScrollableViewportSize(new Dimension(500, 50));
        tableBudget.setFillsViewportHeight(true);
        JScrollPane scrollpane3 = new JScrollPane(tableBudget);
        scrollpane3.setSize(700, 150);


        add(scrollpane, BorderLayout.PAGE_START);
        add(scrollpane2, BorderLayout.SOUTH);
        add(scrollpane3, BorderLayout.CENTER);

        setResizable(false);
        setSize(700, 400);
        setVisible(true);
    }

}