package grafic;

import temaPOO.Application;
import temaPOO.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class UserPage extends JFrame {
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9;
    JTextField tf1, tf2, tf3, tf4;
    JList languages, companies;
    ArrayList<String> langLevel;

    //creez pagina de profil a unui user
    UserPage(String userName) {
        setTitle("Profile Page");
        Application app = Application.getInstance();
        User user = app.getUser(userName);  //determin obiectul user dupa nume
        langLevel = new ArrayList<String>();
        //conectez limbile cu nuvelul limbilor intr-un singur arraylist
        for (int i = 0 ; i < user.getResume().getInfo().getLanguages().size(); i++) {
            String lang = user.getResume().getInfo().getLanguages().get(i);
            String level = user.getResume().getInfo().getLevelOfLanguages().get(i);
            String langlevel = lang + " - " + level;
            langLevel.add(langlevel);
        }

        l1 = new JLabel(userName);
        l1.setForeground(new Color(17,105,142));
        l1.setFont(new Font("George", Font.BOLD, 20));
        l2 = new JLabel("Email:");
        l3 = new JLabel("Telephone");
        l4 = new JLabel("Date of Birth:");
        l5 = new JLabel("Genre:");
        l7 = new JLabel("Languages");
        l6 = new JLabel("Interested Companies");
        l8 = new JLabel("Education");
        l9 = new JLabel("Experience");
        tf1 = new JTextField(user.getResume().getInfo().getEmail());
        tf2 = new JTextField(user.getResume().getInfo().getTelefon());
        tf3 = new JTextField(user.getResume().getInfo().getBirthdate());
        tf4 = new JTextField(user.getResume().getInfo().getSex());
        languages = new JList(langLevel.toArray());
        companies = new JList(user.getCompanies().toArray());

        //frumuseti de afisare a tuturor componentelor
        l1.setBounds(270, 30, 400, 30);
        l2.setBounds(80, 80, 200, 30);
        l3.setBounds(80, 120, 200, 30);
        l4.setBounds(80, 160, 200, 30);
        l5.setBounds(80, 200, 200, 30);
        l6.setBounds(80, 240, 200, 30);
        l7.setBounds(80, 290, 200, 30);
        l8.setBounds(80, 385, 200, 30);
        l9.setBounds(80, 600, 200, 30);
        l2.setFont(new Font("Calibri Light", Font.BOLD, 13));
        l3.setFont(new Font("Calibri Light", Font.BOLD, 13));
        l4.setFont(new Font("Calibri Light", Font.BOLD, 13));
        l5.setFont(new Font("Calibri Light", Font.BOLD, 13));
        l6.setFont(new Font("Calibri Light", Font.BOLD, 13));
        l7.setFont(new Font("Calibri Light", Font.BOLD, 13));
        l8.setFont(new Font("Calibri Light", Font.BOLD, 13));
        l9.setFont(new Font("Calibri Light", Font.BOLD, 13));

        l2.setForeground(new Color(25,69,107));
        l3.setForeground(new Color(25,69,107));
        l4.setForeground(new Color(25,69,107));
        l5.setForeground(new Color(25,69,107));
        l6.setForeground(new Color(25,69,107));
        l7.setForeground(new Color(25,69,107));
        l8.setForeground(new Color(25,69,107));
        l9.setForeground(new Color(25,69,107));

        tf1.setBounds(300, 80, 200, 30);
        tf2.setBounds(300, 120, 200, 30);
        tf3.setBounds(300, 160, 200, 30);
        tf4.setBounds(300, 200, 200, 30);
        companies.setBounds(300, 240, 200, 35);
        languages.setBounds(300, 290, 200, 75);

        companies.setFont(new Font("Calibri Light", Font.PLAIN, 13));
        languages.setFont(new Font("Calibri Light", Font.PLAIN, 13));
        add(l1);
        add(l2);
        add(tf1);
        add(l3);
        add(tf2);
        add(l4);
        add(tf3);
        add(l5);
        add(tf4);
        add(l6);
        add(companies);
        add(l7);
        add(languages);
        add(l8);
        int y = 385;

        //afiseaza educatia user-ului
        for (int i = 0; i < user.getResume().getEd().size(); i++) {
            String level = user.getResume().getEd().get(i).levelOfEducation;
            String name = user.getResume().getEd().get(i).institutionName;
            String start_date = user.getResume().getEd().get(i).startDate;
            String end_date = user.getResume().getEd().get(i).endDate;
            String gpa =  String.valueOf(user.getResume().getEd().get(i).GPA);
            JLabel level1 = new JLabel(level + " - " + name);
            JLabel start_date1 = new JLabel(start_date + " - " + end_date);
            JLabel gpa1 = new JLabel("GPA: " + gpa);
            level1.setBounds(300, y, 400, 15);
            start_date1.setBounds(300, y+20, 400, 15);
            gpa1.setBounds(300, y+40, 400, 15);

            level1.setFont(new Font("Calibri Light", Font.PLAIN, 13));
            start_date1.setFont(new Font("Calibri Light", Font.PLAIN, 13));
            gpa1.setFont(new Font("Calibri Light", Font.PLAIN, 13));
            y += 70;
            add(level1);
            add(start_date1);
            add(gpa1);
        }
        add(l9);
        y = 600;
        //afiseaza experienta user-ului
        for (int i = 0; i < user.getResume().getExp().size(); i++) {
            String company = user.getResume().getExp().get(i).company;
            String position = user.getResume().getExp().get(i).position;
            String start_date = user.getResume().getExp().get(i).startDate;
            String end_date = user.getResume().getExp().get(i).endDate;
            JLabel company1 = new JLabel(company);
            JLabel position1 = new JLabel(position);
            JLabel date = new JLabel(start_date + " - " + end_date);
            company1.setBounds(300, y, 400, 15);
            position1.setBounds(300, y+20, 400, 15);
            date.setBounds(300, y+40, 400, 15);

            company1.setFont(new Font("Calibri Light", Font.PLAIN, 13));
            position1.setFont(new Font("Calibri Light", Font.PLAIN, 13));
            date.setFont(new Font("Calibri Light", Font.PLAIN, 13));
            y += 70;
            add(company1);
            add(position1);
            add(date);
        }
        setVisible(true);
        setSize(700, 900);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}