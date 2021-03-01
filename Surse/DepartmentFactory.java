package temaPOO;

import java.util.ArrayList;

public class DepartmentFactory {
    private static DepartmentFactory factory = null;
    public static DepartmentFactory getInstance() {
        if (factory==null)
            factory = new DepartmentFactory();
        return factory;
    }
    public DepartmentFactory() {

    }
    private Departament createIT(String name) { return new IT(); }
    private Departament createManagement(String name) { return new Management(); }
    private Departament createMarketing(String name) {
        return new Marketing();
    }
    private Departament createFinance(String name) {
        return new Finance();
    }
    public Departament factory(String type) {
        switch (type) {
            case "IT":
                return createIT(type);
            case "Management":
                return createManagement(type);
            case "Marketing":
                return createMarketing(type);
            case "Finance":
                return createFinance(type);
            default:
                return null;
        }
    }

}
