package io.anandf;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * Solution for Day 1 problem at https://adventofcode.com/2022
 * Problem Link: https://adventofcode.com/2022/day/1
 * Summary:
 * Part 1 : Find the Reindeer with the highest calorie
 * Part 2 : Find top 3 Reindeers with the highest calories and find the sum of
 * all the calories the 3 reindeers
 * Input file to the puzzle can be found at :
 * https://adventofcode.com/2022/day/1/input
 *
 */
public class HighestCalories {

    private static final String INPUT_FILE_PATH = "/tmp/input";

    public static void main(String[] args) throws IOException {
        System.out.println("Max Calories:" + getMaxCalorie(INPUT_FILE_PATH));
        System.out.println(
                "Sum of Max 3 calories:" + getMaxNCalorie(INPUT_FILE_PATH, 3).stream().reduce(0, (x, y) -> x + y));
    }

    private static int getMaxCalorie(String inputFile) throws IOException {
        int maxCalorie = 0;
        int currentCalories = 0;
        Scanner scanner = new Scanner(new File(inputFile));
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            // If you get an empty line, check if the currentCalorie is greater than the
            // previously known maxCalorie and if so set the maxCalorie to the the
            // currentCalorie.
            if ("".equals(input)) {
                if (currentCalories > maxCalorie) {
                    maxCalorie = currentCalories;
                }
                // Reset the current cal
                currentCalories = 0;

            } // If you get a number keep adding to the current calorie count till you hit an
              // empty line.
            else {
                Integer calorie = Integer.parseInt(input);
                currentCalories += calorie;
            }
        }
        return maxCalorie;
    }

    private static List<Integer> getMaxNCalorie(String inputFile, int nMax) throws IOException {
        PriorityQueue<Integer> maxCalorie = new PriorityQueue<>((x, y) -> Integer.compare(y, x));
        List<Integer> result = new ArrayList<>();
        if (nMax == 0) {
            return result;
        }
        int currentCalories = 0;
        Scanner scanner = new Scanner(new File(inputFile));
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            // If you get an empty line, check if the currentCalorie is greater than the
            // previously known maxCalorie and if so set the maxCalorie to the the
            // currentCalorie.
            if ("".equals(input)) {
                maxCalorie.add(currentCalories);
                // Reset the current cal
                currentCalories = 0;

            } // If you get a number keep adding to the current calorie count till you hit an
              // empty line.
            else {
                Integer calorie = Integer.parseInt(input);
                currentCalories += calorie;
            }
        }

        for (int i = 0; i < nMax; i++) {
            result.add(maxCalorie.poll());
        }
        return result;
    }
}