package grafic;
import temaPOO.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

class ManagerPage extends JFrame {
    DefaultListModel<Request> ListModelRequests = new DefaultListModel();
    DefaultListModel ListModelEmployees = new DefaultListModel();
    private JTabbedPane tabs;
    private JPanel mainpanel1;
    private JPanel mainpanel2;

    private JScrollPane scrollRequests, scrollEmployees;
    private JList<Request> myRequests;
    private  JList<Employee> myEmployees;
    private JButton accept, decline;
    ManagerPage(Manager manager) {
        preparePage(manager);
    }
    private void preparePage(Manager manager) {
        mainpanel1 = new JPanel();
        mainpanel2 = new JPanel();
        this.tabs = new JTabbedPane();
        Application app = Application.getInstance();

        Border paddingBorder = BorderFactory.createEmptyBorder(10,0,0,0);
        this.tabs.setBorder(BorderFactory.createCompoundBorder(null, paddingBorder));
        this.tabs.setBackground(new Color(0, 136, 145));
        this.tabs.setForeground(new Color(	15,	48,	87));
        this.setTitle("MANAGER PAGE");
        this.setMinimumSize(new Dimension(1100, 850));
        this.getContentPane().setLayout(new BorderLayout(20, 20));
        this.setBackground(new Color(248,241,241));
        this.setFont(new Font( "Georgia", Font.PLAIN, 15));


        this.mainpanel1.setBackground(new Color(25,69,107));
        this.mainpanel1.setLayout(new FlowLayout());
        JPanel admin = new JPanel();
        admin.setBackground(new Color(25,69,107));

        JLabel adminName = new JLabel("WELCOME Manager");
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
        //creez panelul de requests

        JPanel requests = new JPanel();
        requests.setBackground((new Color(25,69,107)));
        requests.setLayout(null);
        scrollRequests = new JScrollPane();
        myRequests = new JList<Request>();
        myRequests.setFont(new Font("Calibri Light", Font.PLAIN, 15));
        myRequests.setForeground(new Color(25,69,107));
        myRequests.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        scrollRequests.setViewportView(myRequests);

        JLabel title = new JLabel("LIST OF REQUESTS");
        title.setFont(new Font("Calibri Light", Font.PLAIN, 20));
        title.setForeground(new Color(248,241,241));
        accept = new JButton("ACCEPT");
        decline = new JButton("DECLINE");
        title.setBounds(430, 40, 200, 30);
        scrollRequests.setBounds(150, 100 ,800, 170);
        accept.setBounds(400, 300, 120, 40);
        accept.setBackground(new Color(0, 136, 145));
        decline.setBackground(new Color(0, 136, 145));
        accept.setForeground(new Color(248,241,241));
        decline.setForeground(new Color(248,241,241));
        decline.setBounds(560, 300, 120, 40);

        //daca apas click pe butonul de accept se va sterge requestul selectat
        //se va converti userul in employee si se va adauga in lista de employees
        accept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                ListSelectionModel selected = myRequests.getSelectionModel();
                int index = selected.getMinSelectionIndex();
                Request request = ListModelRequests.get(index);
                Job job = (Job) request.getKey();
                int noPos = job.getNoPositions();
                int angajati = 0;
                if (index >= 0) {
                    String user = String.valueOf(request.getValue1());
                    int best = 0;
                    for (int j = 0; j < app.getUsers().size(); j++) {
                        String userName = app.getUsers().get(j).getResume().getInfo().getNume();
                        if (user.equalsIgnoreCase(String.valueOf(userName)))
                            best = j;
                    }
                    String companyName = manager.getCompany_name();
                    Company com = app.getCompany(companyName);
                    if (angajati == noPos) {
                        JOptionPane.showMessageDialog(tabs, "There are no positions open for this job");
                    }
                    else {
                        Employee newEmployee = app.getUsers().get(best).convert();
                        com.getDepartaments().get(0).getEmployees().add(newEmployee);
                        angajati++;
                        ListModelEmployees.addElement(newEmployee);
                        manager.getAllRequests().remove(index);
                        ListModelRequests.remove(index);
                    }
                }
            }
        });

        //daca apas click pe decline, va sterge cererea de angajare cu totul
        decline.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                ListSelectionModel selected = myRequests.getSelectionModel();
                int index = selected.getMinSelectionIndex();
                if (index >= 0) {
                    ListModelRequests.remove(index);
                }
            }
        });

//=====================================================================================================================
        //creez panelul de employees
        JPanel employees = new JPanel();
        employees.setBackground((new Color(25,69,107)));
        employees.setLayout(null);
        scrollEmployees = new JScrollPane();
        myEmployees = new JList<>();
        myEmployees.setFont(new Font("Calibri Light", Font.PLAIN, 15));
        myEmployees.setForeground(new Color(25,69,107));
        myEmployees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollEmployees.setViewportView(myEmployees);

        JLabel title2 = new JLabel("LIST OF EMPLOYEES");
        title2.setFont(new Font("Calibri Light", Font.PLAIN, 20));
        title2.setForeground(new Color(248,241,241));
        title2.setBounds(430, 40, 300, 30);
        scrollEmployees.setBounds(50, 100 ,300, 150);


        employees.add(title2);
        employees.add(scrollEmployees);
        bindEmployees(manager);


        requests.add(title);
        requests.add(scrollRequests);
        requests.add(accept);
        requests.add(decline);
        bindRequests(manager);
//=====================================================================================================================
        tabs.add("  Home  ", mainpanel1);
        tabs.add("  Requests  ", requests);
        tabs.add("  Employees ", employees);

        mainpanel2.setLayout(new BoxLayout(this.mainpanel2, BoxLayout.X_AXIS));
        this.mainpanel2.add(tabs);
        this.add(mainpanel2);
        this.setResizable(false);
        this.setVisible(true);
        this.pack();
    }
    //crearea listei default model pentru requests
    private void bindRequests(Manager manager){
        manager.getAllRequests().stream().forEach((star) -> {
            ListModelRequests.addElement(star);
        });
        myRequests.setModel(ListModelRequests);
        myRequests.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    //crearea listei default model pentru employees
    private void bindEmployees(Manager manager){
        Application app = Application.getInstance();
        String company_name = manager.getCompany_name();
        Company company = app.getCompany(company_name);
        for (int i = 0; i < company.getDepartaments().size(); i++) {
            Departament dep = company.getDepartaments().get(i);
            ListModelEmployees.addAll(dep.getEmployees());
        }
        myEmployees.setModel(ListModelEmployees);
        myEmployees.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }


    private void backBtnClicked(MouseEvent evt){
        if (evt.getClickCount() == 1) {
            LoginFrame window = new LoginFrame();
            window.getContentPane();
        }
    }


}
