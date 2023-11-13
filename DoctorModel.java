/**
 *
 * @author Phan Thanh Bao DE180439
 */
package Model;

public class DoctorModel {
    
    private String code, name, specialization;
    private int availability;
    
    public DoctorModel(String code, String name, String specialization, int availability) {
        this.code = code;
        this.name = name;
        this.specialization = specialization;
        this.availability = availability;
    }
    
    public DoctorModel() {
    }
    
    public String getCode() {
        return code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSpecialization() {
        return specialization;
    }
    
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    
    public int getAvailability() {
        return availability;
    }
    
    public void setAvailability(int availability) {
        if (availability >= 0 && availability <= 10) {
            this.availability = availability;
        }
    }
    
    @Override
    public String toString() {
        return String.format("%-10s | %-20s | %-25s | %-10d", code, name, specialization, availability);
    }
    
}
