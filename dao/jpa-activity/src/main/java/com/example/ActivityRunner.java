package com.example;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class ActivityRunner {

    public static void main(String[] args) {

        ActivityDAO activityDAO = new ActivityDAOImpl();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your choice:");
        System.out.println();
        System.out.println("1. Insert");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice){

            case 1:
                System.out.println("Enter activity name, description and duration:");
                System.out.println();
                Activity result = activityDAO.insert(new Activity(null, scanner.nextLine(), scanner.nextLine(), scanner.nextLine()));
                System.out.println(result);

            break;
        }

    }
}
