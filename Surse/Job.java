package temaPOO;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class Job {
    private String name, companyName;
    boolean state;
    Constraint GraduationYear;
    Constraint ExperienceYears;
    Constraint Average;
    ArrayList<User> candidates = new ArrayList<User>();
    int noPositions;
    int salary;
    public Job (String name, String companyName, Constraint GraduationYear, Constraint ExperienceYears, Constraint Average, int noPositions, int salary) {
        this.name = name;
        this.companyName = companyName;
        this.ExperienceYears = ExperienceYears;
        this.GraduationYear = GraduationYear;
        this.Average = Average;
        this.noPositions = noPositions;
        this.salary = salary;
        this.state = false;
    }

    public String getName() { return name; }
    public String getCompanyName() { return companyName; }
    public int getSalary() { return salary; }
    public Constraint getAverage() { return Average; }
    public Constraint getExperienceYears() { return ExperienceYears; }
    public int getNoPositions() { return noPositions; }
    public Constraint getGraduationYear() { return GraduationYear; }
    public void setExperienceYears(Constraint experienceYears) {
        ExperienceYears = experienceYears;
    }

    @Override
    public String toString() {
        return name;
    }
    //functia de aplicare a userului la o companie
    public void apply(User user) {
        String motherCom = this.companyName;
        Application app = Application.getInstance();
        Recruiter recruiter = app.getCompany(motherCom).getRecruiter(user);
        //verificam daca intalneste contrangerile
        if (this.meetsRequirments(user) == true){
            this.candidates.add(user);
        }
        recruiter.evaluate(this, user);
    }

    public boolean meetsRequirments(User user) {
        Integer graduationYear = user.getGraduationYear();
        Integer experienceYears = user.getExperienceYears();
        //pastrez 3 stari pentru fiecare constrangere
        boolean stateEd = false;
        boolean stateExp = false;
        boolean stateMedia = false;
        Integer gradYearMax;
        Integer gradYearMin;
        Integer expMax;
        Integer expMin;
        double average = user.meanGPA();

        if (GraduationYear.getMaxLimit().equals("null"))
            gradYearMax = null;
        else gradYearMax = Integer.parseInt(GraduationYear.getMaxLimit());
        if (GraduationYear.getMaxLimit().equals("null"))
            gradYearMin = null;
        else gradYearMin = Integer.parseInt(GraduationYear.getMinLimit());
        if (ExperienceYears.getMaxLimit().equals("null"))
            expMax = null;
        else expMax = Integer.parseInt(ExperienceYears.getMaxLimit());
        if (ExperienceYears.getMinLimit().equals("null"))
            expMin = null;
        else expMin = Integer.parseInt(ExperienceYears.getMinLimit());
        double averageMin = Double.parseDouble(Average.getMinLimit());

        //conditii pentru a intalni contrangerile
        if (graduationYear == null && gradYearMin == null && gradYearMax == null)
            stateEd = true;
        if (graduationYear != null && gradYearMin != null && gradYearMax != null && graduationYear >= gradYearMin && graduationYear <= gradYearMax)
            stateEd = true;
        if (graduationYear != null && gradYearMin == null && gradYearMax == null)
            stateEd = true;
        if (expMax == null && experienceYears >= expMin)
            stateExp = true;

        if (expMax != null && experienceYears >= expMin && experienceYears <= expMax)
            stateExp = true;
        if (average >= averageMin)
            stateMedia = true;
        //toate starile trebuie sa fie adevarata pentru ca userul sa poate aplica
        if (stateEd && stateExp && stateMedia)
            return true;
        else return false;

    }

}
