package temaPOO;

public class Constraint {
    private String maxLimit;
    private String minLimit;

    public void setMaxLimit(String maxLimit) {
        this.maxLimit = maxLimit;
    }
    public void setMinLimit(String minLimit) {
        this.minLimit = minLimit;
    }
    public String getMaxLimit() {
        return maxLimit;
    }
    public String getMinLimit() {
        return minLimit;
    }

    @Override
    public String toString() {
        return "Constraint{" +
                "maxLimit='" + maxLimit + '\'' +
                ", minLimit='" + minLimit + '\'' +
                '}';
    }
}
