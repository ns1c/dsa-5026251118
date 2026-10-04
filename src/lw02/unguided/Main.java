package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    static final int MAX_BORROW = 2;

    public static void main(String[] args) {

        LinkedList<String[]> requests = new LinkedList<>();
        LinkedList<String[]> bookList = new LinkedList<>();
        LinkedList<String[]> memberList = new LinkedList<>();
        LinkedList<String[]> successful = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failedRequests = new Stack<>();

        bookList.add(new String[]{"Kalkulus", "2"});
        bookList.add(new String[]{"Fisika", "1"});
        bookList.add(new String[]{"Statistika", "2"});

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("borrowing.txt")
        );
 Boolean exists = true;
        while (scanner.hasNext()) {
            String[] request = new String[2];
            request[0] = scanner.next();
            request[1] = scanner.next();
            requests.add(request);
            
            for (String[] m : memberList) {
                if (m[0].equals(request[0])) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                memberList.add(new String[]{request[0], "0"});
            }
        }
        scanner.close();

        queue.addAll(requests);

        while (!queue.isEmpty()) {
            String[] request = queue.poll();
            String name = request[0];
            String requestedBook = request[1];

            String[] memberData = null;
            for (String[] m : memberList) {
                if (m[0].equals(name)) {
                    memberData = m;
                    break;
                }
            }

            String[] bookData = null;
            for (String[] b : bookList) {
                if (b[0].equals(requestedBook)) {
                    bookData = b;
                    break;
                }
            }

int stock = Integer.parseInt(bookData[1]);
            int borrowed = Integer.parseInt(memberData[1]);

            if (stock > 0 && borrowed < MAX_BORROW) {
            } else {
                failedRequests.push(request);
            }
        }

        System.out.println("=== Successfully Processed Requests ===");
        for (String[] r : successful) {
            System.out.println(r[0] + " " + r[1]);
        }

        System.out.println("\n=== Remaining Book Stock ===");
        for (String[] b : bookList) {
            System.out.println(b[0] + " : " + b[1]);
        }

        System.out.println("\n=== Failed Requests ===");
        while (!failedRequests.isEmpty()) {
            String[] r = failedRequests.pop();
            System.out.println(r[0] + " " + r[1]);
        }
    }
}