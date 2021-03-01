package temaPOO;

import java.nio.charset.IllegalCharsetNameException;

public class Experience implements Comparable<Experience>{
    public String startDate, endDate, position, company;
    public Experience (String startDate, String endDate, String position, String company) throws InvalidDatesException {
        if (startDate == endDate) throw new InvalidDatesException();
        this.startDate = startDate;
        if (endDate == "null")
            this.endDate = null;
        else
            this.endDate = endDate;
        this.position = position;
        this.company = company;
    }

    @Override
    public String toString() {
        return "Experience{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", position='" + position + '\'' +
                ", company='" + company + '\'' +
                '}';
    }

    @Override
    public int compareTo(final Experience obj) {
        Experience exp = obj;
        if (exp.endDate != null && this.endDate != null)
            return this.endDate.compareTo(exp.endDate);
        else return this.company.compareTo(exp.company);
    }

}
