package com.diego.services;

import com.diego.Main;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class PlaneServiceTest {

    PlaneService planeService;

    public PlaneServiceTest() {
        planeService = new PlaneService();
    }

    @Test
    void shouldFillPlacesReturnTheRightPlaneWithA4x4() {
        float expectSatisfaction = 100;
        String[][] input = {
                { "4", "4" },
                { "1w", "2", "3" },
                { "4", "5", "6", "7" },
                { "8" },
                { "9", "10", "11w" },
                { "12w" },
                { "13", "14" },
                { "15", "16" }
        };

        String[][] expected = {
                { "1w", "2", "3", "8" },
                { "4", "5", "6", "7" },
                { "11w", "9", "10", "12w" },
                { "13", "14", "15", "16" }
        };

        String[][] result = planeService.handlePlaces(input);

        Assert.assertArrayEquals(expected, result);
        Assert.assertEquals(expectSatisfaction, planeService.getSatisfaction(), 0);
    }

    @Test
    void shouldFillPlacesReturnTheRightPlaneWithProblemOnTogether() {
        int expectSatisfaction = 90;
        String[][] input = {
                { "4", "5" },
                { "1w", "2", "3" },
                { "4", "5", "6", "7" },
                { "8" },
                { "9", "10", "11w" },
                { "12", "13w" },
                { "14w", "15", "16", "17", "18w" },
                { "19", "20" }
        };

        String[][] expected = {
                { "1w", "2", "3", "8", "19" },
                { "4", "5", "6", "7", "20" },
                { "11w", "9", "10", "12", "13w" },
                { "14w", "15", "16", "17", "18w" },
        };

        String[][] result = planeService.handlePlaces(input);

        Assert.assertArrayEquals(expected, result);
        Assert.assertEquals(expectSatisfaction, planeService.getSatisfaction(), 0);
    }

    @Test
    void shouldFillPlacesReturnTheRightPlaneWithProblemOnTogetherMoreThan2() {
        float expectSatisfaction = 85;
        String[][] input = {
                { "4", "5" },
                { "1w", "2", "3" },
                { "4", "5", "6", "7" },
                { "8" },
                { "9", "10", "11w" },
                { "12", "13w" },
                { "14w", "15", "16", "17" },
                { "19", "20", "21" }
        };

        String[][] expected = {
                { "1w", "2", "3", "8", "19" },
                { "4", "5", "6", "7", "20" },
                { "11w", "9", "10", "12", "13w" },
                { "14w", "15", "16", "17", "21" },
        };

        String[][] result = planeService.handlePlaces(input);

        Assert.assertArrayEquals(expected, result);
        Assert.assertEquals(expectSatisfaction, planeService.getSatisfaction(), 0);
    }

    @Test
    void shouldFillPlacesReturnTheRightPlaneWithProblemOnWidow() {
        float expectSatisfaction = 92;
        String[][] input = {
                { "2", "6" },
                { "1w", "2", "3" },
                { "4w", "5" },
                { "6" },
                { "7w", "8" },
                { "9w", "10" },
                { "11w", "12" },
        };

        String[][] expected = {
                { "1w", "2", "3", "6", "5", "4w" },
                { "7w", "8", "11w", "12", "10", "9w" },
        };

        String[][] result = planeService.handlePlaces(input);

        Assert.assertArrayEquals(expected, result);
        Assert.assertEquals(expectSatisfaction, planeService.getSatisfaction(), 0);
    }
}
