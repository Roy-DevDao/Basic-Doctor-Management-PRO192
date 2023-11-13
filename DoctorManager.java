/**
 *
 * @author Phan Thanh Bao DE180439
 */
package Model;

import View.Menu;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import view.Validation;

public class DoctorManager extends Menu {

    private Map<String, DoctorModel> doctors;
    Validation val = new Validation();
    List<DoctorModel> tempList;

    public DoctorManager() {
        super("Search Doctor ", new String[]{"Search By Code", "Search By Name", "Search By Specialization", "Search by Availability", "Exit Search"});
        doctors = new HashMap<>();
        loadData("doctorInput.txt");
    }

    private void loadData(String fName) {
        String doc = "";
        try (FileReader f = new FileReader(fName); BufferedReader br = new BufferedReader(f)) {
            while ((doc = br.readLine()) != null) {
                
                String[] d = doc.split(",");
                String code = d[0].split("=")[1];
                String name = d[1].split("=")[1];
                String specialization = d[2].split("=")[1];
                Integer availability = val.checkInt(d[3].split("=")[1]);
                if ((availability != null) && (code != null) && (name != null) && (specialization != null)) {
                    doctors.put(code, new DoctorModel(code, name, specialization, availability));
                }
            }
            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

    public void sortAndDisplay() {
        if (doctors.isEmpty()) {
            System.out.println("Empty List. No sorting can be performed!");
            return;
        }
        tempList = new ArrayList<>(doctors.values());

        Collections.sort(tempList, new Comparator<DoctorModel>() {
            @Override
            public int compare(DoctorModel o1, DoctorModel o2) {
                return o1.getCode().compareTo(o2.getCode());
            }
        });

        System.out.println("List of Doctor \n --------------------------");
        for (DoctorModel dm : tempList) {
            System.out.println(dm.toString());
        }
    }

    public DoctorModel search(String code) {
        Predicate<DoctorModel> d = new Predicate<DoctorModel>() {
            @Override
            public boolean test(DoctorModel t) {
                return t.getCode().equals(code);
            }
        };
        for (DoctorModel dm : doctors.values()) {
            if (d.test(dm)) {
                return dm;
            }
        }
        return null;

    }

    public void add() {
        String nCode;
        while (true) {
            nCode = val.inputPattern("Enter code (DExxx):", "DE\\d{3}");
            if (val.codeExist(nCode, doctors)) {
                System.out.println("Code exist. Enter again: ");
                continue;
            }
            break;
        }
        String nName = val.inputNonBlankStr("Name of new doctor: ");
        String nSpecialization = val.inputNonBlankStr("Specialization of new doctor: ");
        Integer nAvailability = val.getInt("Availability of new doctor: ", 0, 10);
        doctors.put(nCode, new DoctorModel(nCode, nName, nSpecialization, nAvailability));
        System.out.println("Doctor add successfully!");
    }

    public void update() {
        if (doctors.isEmpty()) {
            System.out.println("Empty List. No updated can be performed!");
        } else {
            String uCode = val.inputPattern("Enter code (DExxx):", "DE\\d{3}");
            DoctorModel dm = this.search(uCode);
            if (dm == null) {
                System.out.println("Doctor " + uCode + " doesn't existed");
            } else {
                String oName = dm.getName();
                String nName = val.inputNonBlankStr("Old Name: " + oName + ", New Name: ");
                dm.setName(nName);
                String oSpecialization = dm.getSpecialization();
                String nSpecialization = val.inputNonBlankStr("Old Specialization: " + oSpecialization + ", New Specialization: ");
                dm.setSpecialization(nSpecialization);
                Integer oAvailability = dm.getAvailability();
                Integer nAvailability = val.getInt("Old Availability: = " + oAvailability + ", New Availability: ", 0, 10);
                dm.setAvailability(nAvailability);
                System.out.println("Doctor " + uCode + " updated seccessfully!");
            }
        }
    }

    public void delete() {
        if (doctors.isEmpty()) {
            System.out.println("Empty List. No updated can be performed!");
        } else {
            String delCode = val.inputPattern("Enter code (DExxx):", "DE\\d{3}");
            if (val.codeExist(delCode, doctors)) {
                doctors.remove(delCode);
                System.out.println("Doctor " + delCode + " removed successfully!");
            } else {
                System.out.println("Doctor " + delCode + " doesn't exist");
            }
        }
    }

    public void searchByCode() {
        if (doctors.isEmpty()) {
            System.out.println("Empty List. No updated can be performed!");
        } else {
            String sCode = val.inputPattern("Enter code (DExxx):", "DE\\d{3}");
            DoctorModel dm = this.search(sCode);
            if (dm == null) {
                System.out.println("Doctor " + sCode + " doesn't existed!");
            } else {
                System.out.println("Result: ");
                System.out.println(dm.toString());
            }
        }
    }

    public void searchByName() {
        if (doctors.isEmpty()) {
            System.out.println("Empty List. No updated can be performed!");
        } else {
            String sName = val.inputNonBlankStr("Enter Doctor name to search: ");
            System.out.println("Result: ");
            for (DoctorModel dt : doctors.values()) {
                if (dt.getName().equalsIgnoreCase(sName)) {
                    System.out.println(dt.toString());
                }
            }
        }
    }

    public void searchBySpecialization() {
        if (doctors.isEmpty()) {
            System.out.println("Empty List. No updated can be performed!");
        } else {
            String sSpec = val.inputNonBlankStr("Enter Doctor Specialization to search: ");
            System.out.println("Result: ");
            for (DoctorModel dt : doctors.values()) {
                if (dt.getSpecialization().equalsIgnoreCase(sSpec)) {
                    System.out.println(dt.toString());
                }
            }
        }
    }

    public void searchByInt() {
        if (doctors.isEmpty()) {
            System.out.println("Empty List. No updated can be performed!");
        } else {
            int sAb = val.getInt("Enter Doctor availability to search: ", 0, 10);
            System.out.println("Result: ");
            for (DoctorModel dt : doctors.values()) {
                if (dt.getAvailability() == sAb) {
                    System.out.println(dt.toString());
                }
            }
        }
    }

    public void saveToFile(String fName) {
        try (FileWriter fw = new FileWriter(fName)) {
            for (DoctorModel dm : tempList) {
                fw.write(dm.toString());
                fw.write("\n");
            }
            fw.close();
            System.out.println("Write successfull!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1:
                searchByCode();
                break;
            case 2:
                searchByName();
                break;
            case 3:
                searchBySpecialization();
                break;
            case 4:
                searchByInt();
                break;
            case 5:
                return;
            default:
                System.out.println("Wrong input!");
        }
    }

}
