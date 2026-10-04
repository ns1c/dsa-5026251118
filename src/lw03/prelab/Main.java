package lw03.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
 
    static void runPlaylist() {
        Scanner input = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        List<String> songs = new ArrayList<>();
 
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] tokens = line.split(" ", 2);
            String command = tokens[0];
 
            if (command.equals("ADD")) {
                songs.add(tokens[1]);
            } else if (command.equals("INSERT")) {
                String[] insertParts = tokens[1].split(" ", 2);
                int position = Integer.parseInt(insertParts[0]);
                songs.add(position, insertParts[1]);
            } else if (command.equals("REMOVE")) {
                if (songs.contains(tokens[1])) {
                    songs.remove(tokens[1]);
                }
            }
        }
 
        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + songs.size());
        for (int i = 0; i < songs.size(); i++) {
            System.out.println((i + 1) + ": " + songs.get(i));
        }
    }
 
    static void runParticipants() {
        Scanner input = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        Set<String> uniqueNames = new LinkedHashSet<>();
        int repeated = 0;
 
        while (input.hasNextLine()) {
            String name = input.nextLine();
            if (uniqueNames.contains(name)) {
                repeated++;
            } else {
                uniqueNames.add(name);
            }
        }
 
        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + uniqueNames.size());
        int number = 1;
        for (String name : uniqueNames) {
            System.out.println(number + ". " + name);
            number++;
        }
        System.out.println("Duplicate registrations: " + repeated);
    }
 
    static void runInventory() {
        Scanner input = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        Map<String, Integer> stock = new LinkedHashMap<>();
        int failedSales = 0;
 
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] tokens = line.split(" ");
            String type = tokens[0];
            String product = tokens[1];
            int quantity = Integer.parseInt(tokens[2]);
 
            if (type.equals("ADD")) {
                if (stock.containsKey(product)) {
                    stock.put(product, stock.get(product) + quantity);
                } else {
                    stock.put(product, quantity);
                }
            } else if (type.equals("SELL")) {
                if (stock.containsKey(product) && stock.get(product) >= quantity) {
                    stock.put(product, stock.get(product) - quantity);
                } else {
                    failedSales++;
                }
            }
        }
 
        System.out.println("===== Problem 3 =====");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("Failed sales: " + failedSales);
    }
 
    public static void main(String[] args) {
        runPlaylist();
        System.out.println();
        runParticipants();
        System.out.println();
        runInventory();
    }
}