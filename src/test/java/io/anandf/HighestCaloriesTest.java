package io.anandf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class HighestCaloriesTest {

    private static final String TEST_INPUT_FILE = System.getProperty("java.io.tmpdir") + "test_input.txt";

    @BeforeClass
    public static void setup() throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(TEST_INPUT_FILE)))) {
            for (int i = 0, j = 0; i < 100; i++, j++) {
                writer.write("" + i);
                writer.newLine();
                if (j == 4) {
                    j = 0;
                    writer.newLine();
                }
            }
            writer.flush();
        } finally {
            System.out.println("Successfully wrote the test input file");
        }
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void sumOfTop3Calories() throws Exception {
        List<Integer> top3Calories = HighestCalories.getMaxNCalorie(TEST_INPUT_FILE, 3);
        int actualValue = top3Calories.stream().reduce(0, (x, y) -> x + y);
        Assert.assertEquals("Highest calorie should be", 1086, actualValue);
    }

    @Test
    public void sumOfTop0Calories() throws Exception {
        List<Integer> top0Calories = HighestCalories.getMaxNCalorie(TEST_INPUT_FILE, 0);
        Assert.assertTrue(top0Calories.isEmpty());
        int actualValue = top0Calories.stream().reduce(0, (x, y) -> x + y);
        Assert.assertEquals("Highest calorie should be", 0, actualValue);
    }

    @Test
    public void topCalorie() throws Exception {
        int maxCalorie = HighestCalories.getMaxCalorie(TEST_INPUT_FILE);
        Assert.assertEquals("Highest calorie should be", 378, maxCalorie);
    }
}
