package temaPOO;

public class InvalidDatesException extends Exception{
    public String getMessage() {
        return "InvalidDatesException: start date is the same as end date";
    }

}
