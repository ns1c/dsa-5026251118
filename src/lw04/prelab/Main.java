package lw04.prelab;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("branches.txt"));
        int branchCount = sc.nextInt();
        int[] stabilityValues = new int[branchCount];
        for (int i = 0; i < branchCount; i++) {
            stabilityValues[i] = sc.nextInt();
        }
        int targetStabilityValuesCount = sc.nextInt();
        int[] targetStabilityValues = new int[targetStabilityValuesCount];
        for (int i = 0; i < targetStabilityValuesCount; i++) {
            targetStabilityValues[i] = sc.nextInt();
        }
        sc.close();

        for (int i = 1; i <= 3; i++) {
            int j = i - 1;
            while (j >= 0 && stabilityValues[j + 1] > stabilityValues[j]) {
                int temp = stabilityValues[j];
                stabilityValues[j] = stabilityValues[j + 1];
                stabilityValues[j + 1] = temp;
                j--;
            }
        }

        for (int i = 0; i < stabilityValues.length; i++) {
            if (i > 0) System.out.print(" ");
            System.out.print(stabilityValues[i]);
        }
        System.out.println();

        for (int target : targetStabilityValues) {
            boolean isFound = false;
            for (int i = 0; i < branchCount; i++) {
                if (stabilityValues[i] == target) {
                    System.out.println(target + ": FOUND at index " + i);
                    isFound = true;
                    break;
                }
            }
            if (!isFound)
                System.out.println(target + ": NOT FOUND");
        }
    }
}