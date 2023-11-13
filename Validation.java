package view;

import Model.DoctorModel;
import java.util.Map;
import java.util.Scanner;

public class Validation {

    private static Scanner sc = new Scanner(System.in);

    public int getInt(String msg, int min, int max) {
        while (true) {
            System.out.println(msg);
            try {
                int n = Integer.parseInt(sc.nextLine());
                if (min <= n && n <= max) {
                    return n;
                }
            } catch (NumberFormatException ex) {
                System.out.println("Wrong format");
            }
        }
    }

    public boolean codeExist(String code, Map<String, DoctorModel> doctors) {
        for (DoctorModel b : doctors.values()) {
            if (b.getCode().equals(code)) {
                System.out.println("Success");
                return true;
            }
        }

        return false;
    }

    public Integer checkInt(String msg) {
        try {
            return Integer.parseInt(msg);
        } catch (NumberFormatException ex) {
            System.out.println("Invalid number");
            return null;
        }
    }

    public String inputPattern(String msg, String pattern) {
        String data;
        do {
            System.out.println(msg);
            data = sc.nextLine().trim();
        } while (!data.matches(pattern));
        return data;
    }

    public String inputNonBlankStr(String msg) {
        String data;
        do {
            System.out.println(msg);
            data = sc.nextLine();

        } while (data.length() == 0);
        return data;
    }

}
