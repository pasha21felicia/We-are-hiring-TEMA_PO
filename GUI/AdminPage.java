package grafic;
import temaPOO.*;

import temaPOO.Consumer.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.InternationalFormatter;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

class AdminPage extends JFrame {
    DefaultListModel ListModelUsers = new DefaultListModel();
    DefaultListModel ListModelCompanies = new DefaultListModel();
    private JTabbedPane tabs;
    private JPanel mainpanel1;
    private JPanel mainpanel2;

    private JScrollPane scrollUsers, scrollCompanies;
    private JList<String> myCompanies, myUsers;
    private JLabel searchLabel, searchLabel1;
    private JTextField searchTxt, searchTxt1;
    AdminPage() {
        preparePage();
    }
    private void preparePage() {
        mainpanel1 = new JPanel();
        mainpanel2 = new JPanel();
        this.tabs = new JTabbedPane();
        Application app = Application.getInstance();

        Border paddingBorder = BorderFactory.createEmptyBorder(10,0,0,0);
        this.tabs.setBorder(BorderFactory.createCompoundBorder(null, paddingBorder));

        this.setTitle("ADMIN PAGE");
        this.setMinimumSize(new Dimension(1100, 850));
        this.getContentPane().setLayout(new BorderLayout(20, 20));
        this.setBackground(new Color(248,241,241));
        this.setFont(new Font( "Georgia", Font.PLAIN, 15));


        this.mainpanel1.setBackground(new Color(25,69,107));
        this.mainpanel1.setLayout(new FlowLayout());
        JPanel admin = new JPanel();
        admin.setBackground(new Color(25,69,107));

        JLabel adminName = new JLabel("WELCOME ADMIN");
        adminName.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        adminName.setForeground(new Color(248,241,241));
        adminName.setFont(new Font( "Georgia", Font.PLAIN, 20));

        JButton backBtn = new JButton("Back");
        backBtn.setBackground(new Color(248,241,241));
        backBtn.setForeground(new Color(25,69,107));
        backBtn.setFont(new Font( "Calibri Light", Font.PLAIN, 20));
        backBtn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                backBtnClicked(e);
            }
        });

        admin.add(adminName);
        admin.add(backBtn);

        this.mainpanel1.add(admin);
//=====================================================================================================================

        JPanel companies = new JPanel();
        companies.setBackground((new Color(25,69,107)));

        scrollCompanies = new JScrollPane();
        myCompanies = new JList<>();
        searchTxt1 = new JTextField();
        searchLabel1 = new JLabel();

        myCompanies.setFont(new Font("Calibri Light", Font.PLAIN, 15));
        myCompanies.setForeground(new Color(25,69,107));
        myCompanies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        myCompanies.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                myCompanyMouseClicked(evt);
            }
        });

        scrollCompanies.setViewportView(myCompanies);
        searchTxt1.setForeground(new Color(25,69,107));
        searchTxt1.setFont(new Font("Calibri Light", Font.PLAIN, 15));
        searchTxt1.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                searchTxtKeyCompanies(evt);
            }
        });

        searchLabel1.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        searchLabel1.setForeground(Color.WHITE);
        searchLabel1.setText("Search/Filter");

        GroupLayout companiesLayout = new GroupLayout(companies);
        companies.setLayout(companiesLayout);
        companiesLayout.setHorizontalGroup(
                companiesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(companiesLayout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(companiesLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(companiesLayout.createSequentialGroup()
                                                .addComponent(searchLabel1)
                                                .addGap(18, 18, 18)
                                                .addComponent(searchTxt1, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(scrollCompanies, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(90, Short.MAX_VALUE))
        );
        companiesLayout.setVerticalGroup(
                companiesLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(companiesLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(companiesLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(searchTxt1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(scrollCompanies, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                                .addContainerGap())
        );
        bindCompanies();
//=====================================================================================================================
        JPanel users = new JPanel();
        users.setBackground((new Color(25,69,107)));
        scrollUsers = new JScrollPane();
        myUsers = new JList<>();
        searchTxt = new JTextField();
        searchLabel = new JLabel();

        myUsers.setFont(new Font("Calibri Light", Font.PLAIN, 15));
        myUsers.setForeground(new Color(25,69,107));
        myUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        myUsers.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                myUsersMouseClicked(evt);
            }
        });
        scrollUsers.setViewportView(myUsers);
        searchTxt.setForeground(new Color(25,69,107));
        searchTxt.setFont(new Font("Calibri Light", Font.PLAIN, 15));
        searchTxt.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent evt) {
                searchTxtKeyReleased(evt);
            }
        });

        searchLabel.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setText("Search/Filter");

        GroupLayout jPanel1Layout = new GroupLayout(users);
        users.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(searchLabel)
                                                .addGap(18, 18, 18)
                                                .addComponent(searchTxt, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(scrollUsers, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(searchTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(scrollUsers, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                                .addContainerGap())
        );
        bindData();

//=====================================================================================================================
        tabs.add("  Home  ", mainpanel1);
        tabs.add("  Companies  ", companies);
        tabs.add("  Users  ", users);

        mainpanel2.setLayout(new BoxLayout(this.mainpanel2, BoxLayout.X_AXIS));
        this.mainpanel2.add(tabs);
        this.add(mainpanel2);
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
    }

    private ArrayList getUsers() {
        Application app = Application.getInstance();
        ArrayList usersNames = new ArrayList();
        for (int i = 0; i < app.getUsers().size(); i++) {
            User user = app.getUsers().get(i);
            usersNames.add(user.getResume().getInfo().getNume());
        }
        return usersNames;
    }
    private void bindData(){
        getUsers().stream().forEach((star) -> {
            ListModelUsers.addElement(star);
        });
        myUsers.setModel(ListModelUsers);
        myUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    private ArrayList getCompanies() {
        Application app = Application.getInstance();
        ArrayList companiesNames = new ArrayList();
        for (int i = 0; i < app.getCompanies().size(); i++) {
            Company company = app.getCompanies().get(i);
            companiesNames.add(company.getName());
        }
        return companiesNames;
    }
    private void bindCompanies(){
        Application app = Application.getInstance();
        getCompanies().stream().forEach((star) -> {
            ListModelCompanies.addElement(star);
        });
        myCompanies.setModel(ListModelCompanies);
        myCompanies.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    private void searchFilter(String searchTerm) {
        DefaultListModel filteredItems = new DefaultListModel();
        ArrayList stars = getUsers();

        stars.stream().forEach((star) -> {
            String starName = star.toString().toLowerCase();
            if (starName.contains(searchTerm.toLowerCase())) {
                filteredItems.addElement(star);
            }
        });
        ListModelUsers = filteredItems;
        myUsers.setModel(ListModelUsers);

    }

    private void myUsersMouseClicked(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            UserPage userPage = new UserPage(myUsers.getSelectedValue());
            getContentPane();
        }
    }
    private void myCompanyMouseClicked(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            Application app = Application.getInstance();
            int index = myCompanies.getSelectedIndex();
            Company company = app.getCompanies().get(index);
            EmployeesList employeePage = new EmployeesList(company);
            employeePage.getContentPane();

        }
    }
    private void backBtnClicked(MouseEvent evt){
        if (evt.getClickCount() == 1) {
            LoginFrame window = new LoginFrame();
//            this.dispose();
            window.getContentPane();
        }
    }
    private void searchTxtKeyReleased(KeyEvent evt) {
        searchFilter(searchTxt.getText());
    }
    private void searchTxtKeyCompanies(KeyEvent evt) {
        searchFilter(searchTxt1.getText());
    }

}
