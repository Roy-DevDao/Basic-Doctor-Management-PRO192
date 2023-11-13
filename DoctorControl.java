/**
 *
 * @author Phan Thanh Bao DE180439
 */
package Controller;

import Model.DoctorManager;
import Model.DoctorModel;
import View.Menu;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class DoctorControl extends Menu {

    static String[] menu = {"Add a Doctor", "Update a Doctor", "Delete a Doctor", "Search a Doctor", "Save to File", "Display all Doctor", "Exit"};
    private DoctorManager doctorList = new DoctorManager();

    public DoctorControl() {
        super("Doctor Management", menu);
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                doctorList.add();
                break;
            case 2:
                doctorList.update();
                break;
            case 3:
                doctorList.delete();
                break;
            case 4:
                doctorList.run();
                break;
            case 5:
                doctorList.saveToFile("doctorOutput");
                break;
            case 6:
                doctorList.sortAndDisplay();
                break;
            case 7:
                System.exit(0);
        }
    }

    public static void main(String[] args) {
        DoctorControl dc = new DoctorControl();
        dc.run();
    }

}
