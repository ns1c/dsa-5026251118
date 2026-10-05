package lw03.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("enrollment.txt"));
        Map<String, Integer> courses = new LinkedHashMap<>();
        List<String> checks = new ArrayList<>();
        int rejected = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(" ");

            String operation = parts[0];
            String code = parts[1];

            if (operation.equals("REGISTER")) {
                int qty = Integer.parseInt(parts[2]);
                if (qty <= 0) {
                    rejected++;
                } else if (courses.containsKey(code)) {
                    courses.put(code, courses.get(code) + qty);
                } else {
                    courses.put(code, qty);
                }
            } else if (operation.equals("WITHDRAW")) {
                int qty = Integer.parseInt(parts[2]);
                if (qty <= 0) {
                    rejected++;
                } else if (courses.containsKey(code) && courses.get(code) >= qty) {
                    courses.put(code, courses.get(code) - qty);
                } else {
                    rejected++;
                }
            } else {
                if (courses.containsKey(code)) {
                    checks.add(code + ": " + courses.get(code) + " students");
                } else {
                    checks.add(code + ": Not found");
                }
            }
        }

        System.out.println("===== Enrollment Checks =====");
        for (String c : checks) {
            System.out.println(c);
        }

        System.out.println("===== Final Enrollment =====");
        for (String code : courses.keySet()) {
            System.out.println(code + ": " + courses.get(code) + " students");
        }
        System.out.println("Rejected operations: " + rejected);
    }
}