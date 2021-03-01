package temaPOO;

public class Education implements Comparable<Education>{
    public String startDate, endDate, institutionName, levelOfEducation;
    public double GPA;
    public Education (String startDate, String endDate, String institutionName, String levelOfEducation, double GPA) throws InvalidDatesException  {
        if (startDate == endDate) throw new InvalidDatesException();
        this.startDate = startDate;
        if (endDate == "null")
            this.endDate = null;
        else
            this.endDate = endDate;
        this.institutionName = institutionName;
        this.levelOfEducation = levelOfEducation;
        this.GPA = GPA;
    }

    @Override
    public String toString() {
        return "Education{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", institutionName='" + institutionName + '\'' +
                ", levelOfEducation='" + levelOfEducation + '\'' +
                ", GPA=" + GPA +
                '}';
    }

    @Override
    public int compareTo(final Education obj) {
        Education edu = obj;
        if(edu.endDate != null  && this.endDate != null) {
            if (edu.endDate.compareTo(this.endDate) == 0) {
                int compare = (int) (edu.GPA - this.GPA);
                return compare;
            }
            else return edu.endDate.compareTo(this.endDate);
        }
        else return this.startDate.compareTo(edu.startDate);
    }
}
