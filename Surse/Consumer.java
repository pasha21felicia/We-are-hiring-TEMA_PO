package temaPOO;
import java.util.*;

public abstract class Consumer {
    boolean visited;
    int grad = 0;
    ArrayList<Consumer> listAcquintances = new ArrayList<Consumer>();
    Resume resume = new Resume.ResumeBuilder().build();
    public Consumer(ArrayList<Consumer> listAcquintances, Resume resume) {
        this.listAcquintances = listAcquintances;
        this.resume = resume;
    }
    public Consumer() {

    }
    //Resume builder clasa interna
    public static class Resume{
        private Information info;
        private ArrayList<Education> ed;
        private ArrayList<Experience> exp;

        private Resume(Resume.ResumeBuilder builder){
            this.info = builder.info;
            this.ed = builder.ed;
            this.exp = builder.exp;
        }

        public ArrayList<Education> getEd() {
            return ed;
        }

        public ArrayList<Experience> getExp() {
            return exp;
        }

        public Information getInfo() {
            return info;
        }
        static class ResumeBuilder {
            private Information info;
            private ArrayList<Education> ed;
            private ArrayList<Experience> exp;

            public ResumeBuilder(){
                ed = new ArrayList<>();
                exp = new ArrayList<>();
            }

            public Resume.ResumeBuilder info(Information info){
                this.info = info;
                return this;
            }

            public Resume.ResumeBuilder ed(ArrayList<Education> ed){
                this.ed = ed;
                return this;
            }

            public Resume.ResumeBuilder exp(ArrayList<Experience> exp){
                this.exp = exp;
                return this;
            }

            public Resume build(){
                Resume resume = new Resume(this);
                return resume;
            }

        }
    }

    public Resume getResume() {
        return resume;
    }
    public void add(Consumer consumer){
        this.listAcquintances.add(consumer);
    }
    public void remove(Consumer consumer){
        this.listAcquintances.remove(consumer);
    }
    public void add(Education education){
        this.resume.ed.add(education);
    }
    public void add(Experience experience){
        this.resume.exp.add(experience);
    }
    public Double meanGPA() {
        double sum = 0;
        int counter = 0;
       for (Iterator it = this.resume.ed.listIterator(); it.hasNext();) {
           Education nextEd = (Education)it.next();
           counter++;
           sum += nextEd.GPA;
       }
       double media = sum/counter;
       return media;
    }
    public void unVisit(ArrayList<Consumer> allFriends) {
        for (Iterator it = allFriends.listIterator(); it.hasNext();) {
            Consumer nextCon = (Consumer) it.next();
            nextCon.visited = false;
        }
    }
    //bfs cu lista de vecini
    public int getDegreeInFriendship(Consumer consumer){
        LinkedList<Consumer> queue = new LinkedList<Consumer>();
        queue.add(this);
        this.visited = true; //mentin o stare daca nodul este sau nu visitat
        this.grad = 0;
        while(!queue.isEmpty()) {
            Consumer element = queue.remove(); //scot nodul din lista
            //verific daca am gasit
            if (element.getResume().getInfo().getNume().equals(consumer.getResume().getInfo().getNume())){
                return element.grad;
            }
            ArrayList<Consumer> neighbours = element.listAcquintances; //verific lista de prieteni
            for (int i = 0; i < neighbours.size(); i++) {
                Consumer n = neighbours.get(i);
                if (n != null && !n.visited) {
                    queue.add(n);
                    n.grad = element.grad+1;
                    n.visited = true;
                }
            }
            unVisit(element.listAcquintances); //marchez nodurile nevisitate pentru a putea calcula gradul corect
            //si la urmatoarele rulari a algoritmului
        }
        return -1;
    }
    public Integer getGraduationYear(){
        Education currEd;
        for (int i = 0; i < this.resume.ed.size(); i++) {
            currEd = this.resume.ed.get(i);
            if (currEd.levelOfEducation.equals("college")) {
                if (currEd.endDate == null) {
                    return null;
                } else {
                    String graduationYear = currEd.endDate.substring(6, 10);
                    int yearInt = Integer.parseInt(graduationYear);
                    return yearInt;
                }
            }
        }
        return null;
    }

    public Integer getExperienceYears() {
        int YearExp = 0;
        int nrYears = 0;
        String monthS, yearS;
        int monthI, yearI;
        String monthE, yearE;
        int monthIE, yearIE;
        for (Iterator it = this.resume.exp.listIterator(); it.hasNext(); ) {
            Experience nextExp = (Experience) it.next();
            monthS = nextExp.startDate.substring(3, 5);
            yearS = nextExp.startDate.substring(6, 10);
            monthI = Integer.parseInt(monthS);
            yearI = Integer.parseInt(yearS);

            if (nextExp.endDate != "null") {
                monthE = nextExp.endDate.substring(3, 5);
                yearE = nextExp.endDate.substring(6, 10);
                monthIE = Integer.parseInt(monthE);
                yearIE = Integer.parseInt(yearE);

            } else {
                monthIE = 12;
                yearIE = 2020;
            }
            YearExp = yearIE - yearI;
            if (monthIE > monthI)
                YearExp++;
            nrYears += YearExp;
        }
        return nrYears;
    }

}
