package temaPOO;

import java.io.FileReader;
import java.util.*;
import java.lang.*;
import java.io.BufferedReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;

public class Test extends JFrame {

    public static void main(String[] args) throws Exception {
        JSONParser parser = new JSONParser();
        ArrayList<Consumer> allConsumers = new ArrayList<Consumer>();
        HashMap<String, Consumer> AllConsumers = new HashMap<String, Consumer>();
        Application app = Application.getInstance();
        DepartmentFactory depFactory = new DepartmentFactory();

        try {
            Object obj = parser.parse(new FileReader("consumers.json"));
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray usersList = (JSONArray) jsonObject.get("users");
            JSONArray employeesList = (JSONArray) jsonObject.get("employees");
            JSONArray recruitersList = (JSONArray) jsonObject.get("recruiters");
            JSONArray managersList = (JSONArray) jsonObject.get("managers");

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            //Cititrea listei de manageri  din fisier

            Iterator<JSONObject> manager = managersList.iterator();
            while (manager.hasNext()) {
                ArrayList<String> managerAllLang = new ArrayList<String>();
                ArrayList<String> managerAllLevelLang = new ArrayList<String>();
                Information managerInfo = new Information();
                ArrayList<Education> managerAllEd = new ArrayList<Education>();
                ArrayList<Experience> managerAllExp = new ArrayList<Experience>();


                //citim datele personale din Info
                JSONObject nextManager = manager.next();
                String name = (String) nextManager.get("name");
                String email = (String) nextManager.get("email");
                String phone = (String) nextManager.get("phone");
                String date_of_birth = (String) nextManager.get("date_of_birth");
                String genre = (String) nextManager.get("genre");
                long salary = (long) nextManager.get("salary");
                JSONArray languagesList = (JSONArray) nextManager.get("languages");
                JSONArray levelOfLanguagesList = (JSONArray) nextManager.get("languages_level");
                managerInfo.setNume(name);
                managerInfo.setEmail(email);
                managerInfo.setTelefon(phone);
                managerInfo.setBirthdate(date_of_birth);
                managerInfo.setSex(genre);

                Iterator<String> language = languagesList.iterator();
                while (language.hasNext()) {
                    String nextLang = language.next();
                    managerAllLang.add(nextLang);
                }
                Iterator<String> levelLanguage = levelOfLanguagesList.iterator();
                while (levelLanguage.hasNext()) {
                    String nextLevel = levelLanguage.next();
                    managerAllLevelLang.add(nextLevel);
                }
                managerInfo.setLanguages(managerAllLang);
                managerInfo.setLevelOfLanguages(managerAllLevelLang);

                //citirea educatiei din fisier si transformarea in arraylist
                JSONArray educationList = (JSONArray) nextManager.get("education");
                Iterator<JSONObject> education = educationList.iterator();
                while (education.hasNext()) {
                    JSONObject nextEducation = education.next();
                    String level = (String) nextEducation.get("level");
                    String nameSchool = (String) nextEducation.get("name");
                    String start_date = (String) nextEducation.get("start_date");
                    String end_date = (String) nextEducation.get("end_data");
                    double grade = ((Number) nextEducation.get("grade")).doubleValue();
                    try {
                        Education managerEd = new Education(start_date, end_date, nameSchool, level, grade);
                        managerAllEd.add(managerEd);
                    } catch (InvalidDatesException e) {
                        System.err.println(e.getMessage());
                    }
                }
                Collections.sort(managerAllEd); //sortez colectia de education

                //citirea experientelor din fisier si transformarea lor in arraylist
                JSONArray experienceList = (JSONArray) nextManager.get("experience");
                Iterator<JSONObject> experience = experienceList.iterator();
                while (experience.hasNext()) {
                    JSONObject nextExperience = experience.next();
                    String company = (String) nextExperience.get("company");
                    String position = (String) nextExperience.get("position");
                    String start_date = (String) nextExperience.get("start_date");
                    String end_date = (String) nextExperience.get("end_date");
                    try {
                        Experience managerExp = new Experience(start_date, end_date, position, company);
                        managerAllExp.add(managerExp);
                    } catch (InvalidDatesException e) {
                        System.err.println(e.getMessage());
                    }

                }
                String currentCompany = managerAllExp.get(managerAllExp.size() - 1).company;
                Collections.sort(managerAllExp); //sortam colectia de experiente

                //creem instanta de resume a managerului
                Consumer.Resume resume = new Consumer.Resume.ResumeBuilder()
                        .ed(managerAllEd)
                        .info(managerInfo)
                        .exp(managerAllExp)
                        .build();
                //cream un obiect de tip Manager cu toate datele necesare
                Manager oneManager = new Manager(null, resume, currentCompany, (int) salary);

                //Setarea parolei si username pentru manageri
                String username = "manager";
                String password = currentCompany + "2021";
                oneManager.setUsername(username);
                oneManager.setPassword(password);

                //crearea companiilor si a instantuierea departamentelor in dependenta de manager
                Company aCompany = new Company(currentCompany, oneManager);
                Departament depIT = depFactory.factory("IT");
                Departament depManag = depFactory.factory("Management");
                Departament depMarke = depFactory.factory("Marketing");
                Departament depFin = depFactory.factory("Finance");
                aCompany.add(depIT);
                aCompany.add(depManag);
                aCompany.add(depMarke);
                aCompany.add(depFin);
                app.add(aCompany);
            }

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            //citirea listei de employees din fisier
            Iterator<JSONObject> employee = employeesList.iterator();
            int countEmployees = 0;
            while (employee.hasNext()) {
                ArrayList<Education> employeeAllEd = new ArrayList<Education>();
                ArrayList<Experience> employeeAllExp = new ArrayList<Experience>();
                ArrayList<String> employeeAllLang = new ArrayList<String>();
                ArrayList<String> employeeAllLevelLang = new ArrayList<String>();

                //citirea datelor personale
                Information employeeInfo = new Information();
                JSONObject nextEmployee = employee.next();
                String name = (String) nextEmployee.get("name");
                String email = (String) nextEmployee.get("email");
                String phone = (String) nextEmployee.get("phone");
                String date_of_birth = (String) nextEmployee.get("date_of_birth");
                String genre = (String) nextEmployee.get("genre");
                JSONArray languagesList = (JSONArray) nextEmployee.get("languages");
                JSONArray levelOfLanguagesList = (JSONArray) nextEmployee.get("languages_level");
                long salary = (long) nextEmployee.get("salary");

                employeeInfo.setNume(name);
                employeeInfo.setEmail(email);
                employeeInfo.setTelefon(phone);
                employeeInfo.setBirthdate(date_of_birth);
                employeeInfo.setSex(genre);

                Iterator<String> language = languagesList.iterator();
                while (language.hasNext()) {
                    String nextLang = language.next();
                    employeeAllLang.add(nextLang);
                }
                Iterator<String> levelLanguage = levelOfLanguagesList.iterator();
                while (levelLanguage.hasNext()) {
                    String nextLevel = levelLanguage.next();
                    employeeAllLevelLang.add(nextLevel);
                }
                employeeInfo.setLanguages(employeeAllLang);
                employeeInfo.setLevelOfLanguages(employeeAllLevelLang);


                //citirea educatiei si transformarea in arraylist
                JSONArray educationList = (JSONArray) nextEmployee.get("education");
                Iterator<JSONObject> education = educationList.iterator();
                while (education.hasNext()) {
                    JSONObject nextEducation = education.next();
                    String level = (String) nextEducation.get("level");
                    String nameSchool = (String) nextEducation.get("name");
                    String start_date = (String) nextEducation.get("start_date");
                    String end_date = (String) nextEducation.get("end_data");
                    double grade = ((Number) nextEducation.get("grade")).doubleValue();
                    try {
                        Education employeeEd = new Education(start_date, end_date, nameSchool, level, grade);
                        employeeAllEd.add(employeeEd);
                    } catch (InvalidDatesException e) {
                        System.err.println(e.getMessage());
                    }
                }
                Collections.sort(employeeAllEd);

                //citirea experientelor si transformarea in arraylist
                JSONArray experienceList = (JSONArray) nextEmployee.get("experience");
                Iterator<JSONObject> experience = experienceList.iterator();
                String currentDepartament = "";
                while (experience.hasNext()) {
                    JSONObject nextExperience = experience.next();
                    String company = (String) nextExperience.get("company");
                    String position = (String) nextExperience.get("position");
                    currentDepartament = (String) nextExperience.get("departament");
                    String start_date = (String) nextExperience.get("start_date");
                    String end_date = (String) nextExperience.get("end_date");
                    try {
                        Experience employeeExp = new Experience(start_date, end_date, position, company);
                        employeeAllExp.add(employeeExp);
                    } catch (InvalidDatesException e) {
                        System.err.println(e.getMessage());
                    }

                }
                String currentCompany = employeeAllExp.get(employeeAllExp.size() - 1).company;
                Collections.sort(employeeAllExp);
                Consumer.Resume resume = new Consumer.Resume.ResumeBuilder()
                        .ed(employeeAllEd)
                        .info(employeeInfo)
                        .exp(employeeAllExp)
                        .build();
                ArrayList<Consumer> listFriends = new ArrayList<Consumer>();

                //crearea obiectului de tip employee
                Employee oneEmployee = new Employee(listFriends, resume, currentCompany, (int) salary);

                //crearea hashmap-ului AllConsumers
                Consumer employeeConsumer = oneEmployee;
                countEmployees++;
                String key = "E" + countEmployees;
                AllConsumers.put(key, employeeConsumer);
                allConsumers.add(employeeConsumer);

                //adaugarea angajatului in departamentul corect
                Company currCom = app.getCompany(currentCompany);
                if (currentDepartament.compareTo("IT") == 0)
                    currCom.getDepartaments().get(0).add(oneEmployee);
                else if (currentDepartament.compareTo("Management") == 0)
                    currCom.getDepartaments().get(1).add(oneEmployee);
                else if (currentDepartament.compareTo("Marketing") == 0)
                    currCom.getDepartaments().get(2).add(oneEmployee);
                else currCom.getDepartaments().get(3).add(oneEmployee);
            }


//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            //citirea listei de useri din fisier

            Iterator<JSONObject> user = usersList.iterator();
            int countUsers = 0;
            while (user.hasNext()) {
                ArrayList<String> userAllLang = new ArrayList<String>();
                ArrayList<String> userAllLevelLang = new ArrayList<String>();
                ArrayList<String> userAllIntComp = new ArrayList<String>();
                ArrayList<Education> userAllEd = new ArrayList<Education>();
                ArrayList<Experience> userAllExp = new ArrayList<Experience>();
                Information userInfo = new Information();

                // citirea datelor personale a userului
                JSONObject nextUser = user.next();
                String name = (String) nextUser.get("name");
                StringTokenizer st = new StringTokenizer(name, " ");
                String lastname = st.nextElement().toString();
                String firstname = st.nextElement().toString();
                String email = (String) nextUser.get("email");
                String phone = (String) nextUser.get("phone");
                String date_of_birth = (String) nextUser.get("date_of_birth");
                String genre = (String) nextUser.get("genre");
                JSONArray languagesList = (JSONArray) nextUser.get("languages");
                JSONArray levelOfLanguagesList = (JSONArray) nextUser.get("languages_level");
                JSONArray interestedCompanies = (JSONArray) nextUser.get("interested_companies");
                userInfo.setNume(name);
                userInfo.setEmail(email);
                userInfo.setTelefon(phone);
                userInfo.setBirthdate(date_of_birth);
                userInfo.setSex(genre);

                Iterator<String> language = languagesList.iterator();
                while (language.hasNext()) {
                    String nextLang = language.next();
                    userAllLang.add(nextLang);
                }
                Iterator<String> levelLanguage = levelOfLanguagesList.iterator();
                while (levelLanguage.hasNext()) {
                    String nextLevel = levelLanguage.next();
                    userAllLevelLang.add(nextLevel);
                }
                Iterator<String> interestedComp = interestedCompanies.iterator();
                while (interestedComp.hasNext()) {
                    String nextComp = interestedComp.next();
                    userAllIntComp.add(nextComp);
                }
                userInfo.setLanguages(userAllLang);
                userInfo.setLevelOfLanguages(userAllLevelLang);

                //citirea educatiei si transformarea in arraylist
                JSONArray educationList = (JSONArray) nextUser.get("education");
                Iterator<JSONObject> education = educationList.iterator();
                while (education.hasNext()) {
                    JSONObject nextEducation = education.next();
                    String level = (String) nextEducation.get("level");
                    String nameSchool = (String) nextEducation.get("name");
                    String start_date = (String) nextEducation.get("start_date");
                    String end_date = (String) nextEducation.get("end_data");
                    double grade = ((Number) nextEducation.get("grade")).doubleValue();
                    try {
                        Education userEd = new Education(start_date, end_date, nameSchool, level, grade);
                        userAllEd.add(userEd);
                    } catch (InvalidDatesException e) {
                        System.err.println(e.getMessage());
                    }

                }
                Collections.sort(userAllEd);

                //citirea experientelor si transformarea in arraylist
                JSONArray experienceList = (JSONArray) nextUser.get("experience");
                Iterator<JSONObject> experience = experienceList.iterator();
                while (experience.hasNext()) {
                    JSONObject nextExperience = experience.next();
                    String company = (String) nextExperience.get("company");
                    String position = (String) nextExperience.get("position");
                    String start_date = (String) nextExperience.get("start_date");
                    String end_date = (String) nextExperience.get("end_date");
                    try {
                        Experience userExp = new Experience(start_date, end_date, position, company);
                        userAllExp.add(userExp);
                    } catch (InvalidDatesException e) {
                        System.err.println(e.getMessage());
                    }

                }
                Collections.sort(userAllExp);
                Consumer.Resume resume = new Consumer.Resume.ResumeBuilder()
                        .ed(userAllEd)
                        .info(userInfo)
                        .exp(userAllExp)
                        .build();
                ArrayList<Consumer> listFriends = new ArrayList<Consumer>();

                //crearea obiectului de tip User
                User oneUser = new User(listFriends, resume, interestedCompanies);
                //generarea parolei si username pentru fiecare user
                String username = "user";
                String password = firstname+"2021";
                oneUser.setUsername(username);
                oneUser.setPassword(password);
                //adaugarea userului in hashmap-ul de consumatori
                Consumer userConsumer = oneUser;
                countUsers++;
                String key = "U" + countUsers;
                AllConsumers.put(key, userConsumer);
                allConsumers.add(userConsumer);
                app.add(oneUser);
            }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            //citirea listei de recruiters din fisier
            Iterator<JSONObject> recruiter = recruitersList.iterator();
            int countRecruiters = 0;
            while (recruiter.hasNext()) {
                ArrayList<String> recruiterAllLang = new ArrayList<String>();
                ArrayList<String> recruiterAllLevelLang = new ArrayList<String>();
                Information recruiterInfo = new Information();
                ArrayList<Education> recruiterAllEd = new ArrayList<Education>();
                ArrayList<Experience> recruiterAllExp = new ArrayList<Experience>();

                //citirea datelor personale a recruiterului
                JSONObject nextRecruiter = recruiter.next();
                String name = (String) nextRecruiter.get("name");
                String email = (String) nextRecruiter.get("email");
                String phone = (String) nextRecruiter.get("phone");
                String date_of_birth = (String) nextRecruiter.get("date_of_birth");
                String genre = (String) nextRecruiter.get("genre");
                long salary = (long) nextRecruiter.get("salary");
                JSONArray languagesList = (JSONArray) nextRecruiter.get("languages");
                JSONArray levelOfLanguagesList = (JSONArray) nextRecruiter.get("languages_level");
                recruiterInfo.setNume(name);
                recruiterInfo.setEmail(email);
                recruiterInfo.setTelefon(phone);
                recruiterInfo.setBirthdate(date_of_birth);
                recruiterInfo.setSex(genre);

                Iterator<String> language = languagesList.iterator();
                while (language.hasNext()) {
                    String nextLang = language.next();
                    recruiterAllLang.add(nextLang);
                }
                Iterator<String> levelLanguage = levelOfLanguagesList.iterator();
                while (levelLanguage.hasNext()) {
                    String nextLevel = levelLanguage.next();
                    recruiterAllLevelLang.add(nextLevel);
                }
                recruiterInfo.setLanguages(recruiterAllLang);
                recruiterInfo.setLevelOfLanguages(recruiterAllLevelLang);

                //citirea educatiei recruiterului din fisier si tranformarea in arraylist
                JSONArray educationList = (JSONArray) nextRecruiter.get("education");
                Iterator<JSONObject> education = educationList.iterator();
                while (education.hasNext()) {
                    JSONObject nextEducation = education.next();
                    String level = (String) nextEducation.get("level");
                    String nameSchool = (String) nextEducation.get("name");
                    String start_date = (String) nextEducation.get("start_date");
                    String end_date = (String) nextEducation.get("end_data");
                    double grade = ((Number) nextEducation.get("grade")).doubleValue();
                    try {
                        Education recruiterEd = new Education(start_date, end_date, nameSchool, level, grade);
                        recruiterAllEd.add(recruiterEd);
                    } catch (InvalidDatesException e) {
                        System.err.println(e.getMessage());
                    }
                }
                Collections.sort(recruiterAllEd);

                //citirea experientelor recruiterului din fisier si tranformarea in arraylist
                JSONArray experienceList = (JSONArray) nextRecruiter.get("experience");
                Iterator<JSONObject> experience = experienceList.iterator();
                while (experience.hasNext()) {
                    JSONObject nextExperience = experience.next();
                    String company = (String) nextExperience.get("company");
                    String position = (String) nextExperience.get("position");
                    String start_date = (String) nextExperience.get("start_date");
                    String end_date = (String) nextExperience.get("end_date");
                    try {
                        Experience recruiterExp = new Experience(start_date, end_date, position, company);
                        recruiterAllExp.add(recruiterExp);
                    } catch (InvalidDatesException e) {
                        System.err.println(e.getMessage());
                    }
                }
                String currentCompany = recruiterAllExp.get(recruiterAllExp.size() - 1).company;
                Collections.sort(recruiterAllExp);
                Consumer.Resume resume = new Consumer.Resume.ResumeBuilder()
                        .ed(recruiterAllEd)
                        .info(recruiterInfo)
                        .exp(recruiterAllExp)
                        .build();
                ArrayList<Consumer> listFriends = new ArrayList<Consumer>();

                //crearea obiectului de tip recruiter
                Recruiter oneRecruiter = new Recruiter(listFriends, resume, currentCompany, (int) salary, 5);

                //adaugarea in hashmap-ul de consumatori
                Consumer recruiterConsumer = oneRecruiter;
                countRecruiters++;
                String key = "R" + countRecruiters;
                AllConsumers.put(key, recruiterConsumer);
                allConsumers.add(recruiterConsumer);
                Company currCompany = app.getCompany(currentCompany);
                currCompany.getDepartaments().get(0).add(oneRecruiter);  //adaugarea in lista de recruiteri a companiei
                currCompany.add(oneRecruiter);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //citirea fisierului de job-uri cu toate restrictille
        BufferedReader br = new BufferedReader(new FileReader("Jobs.txt"));
        int nrCompanies = Integer.parseInt(br.readLine());
        for (int i = 0; i < nrCompanies; i++) {
            String companyName = br.readLine();
            int nrJobs = Integer.parseInt(br.readLine());
            Company com = app.getCompany(companyName);
            for (int j = 0; j < nrJobs; j++) {
                ArrayList<User> candidates = new ArrayList<User>();
                String position = br.readLine();
                int nrPositions = Integer.parseInt(br.readLine());
                int salary = Integer.parseInt(br.readLine());
                String gradYearMin = br.readLine();
                String gradYearMax = br.readLine();
                String gradExpMin = br.readLine();
                String gradExpMax = br.readLine();
                String AverageMin = br.readLine();
                String AverageMax = br.readLine();
                //crearea constrangerilor
                Constraint gradYear = new Constraint();
                gradYear.setMinLimit(gradYearMin);
                gradYear.setMaxLimit(gradYearMax);
                Constraint gradExp = new Constraint();
                gradExp.setMinLimit(gradExpMin);
                gradExp.setMaxLimit(gradExpMax);
                Constraint average = new Constraint();
                average.setMinLimit(AverageMin);
                average.setMaxLimit(AverageMax);
                //crearea unui job nou
                Job newJob = new Job(position, companyName, gradYear, gradExp, average, nrPositions, salary);
                com.getDepartaments().get(0).add(newJob); //adaugarea job-ului in dep de IT
            }
        }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //citirea listei de constinte din fisier
        BufferedReader buff = new BufferedReader(new FileReader("listAcquintances.txt"));
        String line = buff.readLine();
        int nr_vertex = Integer.parseInt(line);
        while ((line = buff.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(line, ":");
            String fromNode = st.nextElement().toString();
            String toNode = st.nextElement().toString();
            Consumer currConsumer = AllConsumers.get(fromNode); //crearea hashmap-ului de consumatori cu datele citite
            Consumer friendOfConsumer = AllConsumers.get(toNode);
            currConsumer.add(friendOfConsumer);
        }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //citirea constrangerilor pentru pattern-ul strategy, daca userul este interesat intr-o anumita pozitie
        //sau vrea un salariu minim
        BufferedReader br1 = new BufferedReader(new FileReader("min_Salary.txt"));
        int nrUsers = Integer.parseInt(br1.readLine());
        for (int i = 0; i < nrUsers; i++) {
            User u = app.getUsers().get(i);
            String line1 = br1.readLine();
            StringTokenizer st = new StringTokenizer(line1, ":");
            String strateg = st.nextElement().toString();
            u.setStrategy(strateg);
            if (strateg.equalsIgnoreCase("MinSalary")) {
                String minSalary = st.nextElement().toString();
                int min = Integer.parseInt(minSalary);
                u.setMinSalary(min);
            }
            else if (strateg.equalsIgnoreCase("position")) {
                String position = st.nextElement().toString();
                u.setDesiredPosition(position);
            }
        }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //userii aplica la joburi si se adauga in lista de observeri a companiei
        for (int it = 0; it < app.getUsers().size(); it++) {
             User nextUser = app.getUsers().get(it);
             ArrayList<Job> openJobs = app.getJobs(nextUser.getCompanies());
             for (int j = 0; j < openJobs.size(); j++) {
                 Job wantedJob = openJobs.get(j);
                 String nameCom = wantedJob.getCompanyName();
                 Company company = app.getCompany(nameCom);
                 if (wantedJob.state == false) {
                     wantedJob.apply(nextUser);  //aplicare
                     if (company.contains(nextUser) == false) {
                         company.addObserver(nextUser);     //adauga observer
                         nextUser.add(company);
                     }
                 }
             }
         }

//        //Pentru angajare automata, decomentati urmatoarele linii

//         for (int i = 0 ; i < app.getCompanies().size(); i++) {
//             Company com = app.getCompanies().get(i);
//             Manager boss = com.getManager();
//             ArrayList <Job> allJobs = com.getJobs();
//             for (int j = 0; j < allJobs.size(); j++) {
//                 Job job = allJobs.get(j);
//                 if (job.candidates.size() > 0) {
//                     boss.process(job);
//                 }
//                 job.state = true;
//             }
//            }
//         System.out.println("ANGAJATI:\n");
//         System.out.println("Gooogle:" + "\n" +app.getCompanies().get(0).getDepartaments().get(0).getEmployees());
//         System.out.println("AMAZON:" + "\n" +app.getCompanies().get(1).getDepartaments().get(0).getEmployees());


    }
}
