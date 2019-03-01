package com.diego.services;

import com.diego.utils.ArrayUtils;
import com.diego.utils.IntegerUtils;

import java.util.Arrays;
import java.util.Collections;

public class PlaneService {
    private final static String INVALID_DIMENSION = "Invalid dimensions of the plane";
    private int countInsatisfaction = 0;
    private int countPeople = 0;

    public float getSatisfaction() {
        return satisfaction;
    }

    private float satisfaction;

    /**
     * Main method to fill the right places on the plane
     *
     * @param input information about the plane (plane size and people)
     * @return the plane filled
     */
    public String[][] handlePlaces(String[][] input) {
        String[][] plane = createPlane(input[0]);

        String[][] ignoreds = fillPlaces(input, plane);
        if (ignoreds != null)
            handleIgnored(plane, ignoreds);

        calculateSatisfaction();

        return plane;
    }

    /**
     * Calculate the people satisfaction
     *
     * @return the satisfaction
     */
    protected void calculateSatisfaction() {
        if (this.countInsatisfaction == 0)
            this.satisfaction = 100;
        else
            this.satisfaction = (100 - (this.countInsatisfaction * 100) / this.countPeople);
    }

    /**
     * Create the plane passing the first line from input
     *
     * @param sizePlane Array with the plane size
     * @return The matrix created following the sizePlane
     */
    protected String[][] createPlane(String[] sizePlane) {
        if (sizePlane.length != 2 || !IntegerUtils.isInteger(sizePlane[0]) || !IntegerUtils.isInteger(sizePlane[1]))
            throw new IllegalArgumentException(INVALID_DIMENSION);

        int rows = Integer.parseInt(sizePlane[0]);
        int cols = Integer.parseInt(sizePlane[1]);
        return new String[rows][cols];
    }

    /**
     * Handle the collection of people that didn't have the places together
     *
     * @param plane    Plane Matrix
     * @param ignoreds Ignoreds people
     */
    protected void handleIgnored(String[][] plane, String[][] ignoreds) {
        int countToDeleted = 1;
        for (String[] ignored : ignoreds) {
            if (ignored == null) {
                break;
            }
            this.countInsatisfaction = this.countInsatisfaction + ignored.length;
            while (ignored.length != 0) {
                String[] ignoredTry;
                if (countToDeleted >= ignored.length) {
                    ignoredTry = ignored.clone();
                } else {
                    ignoredTry = Arrays.copyOfRange(ignored, 0, ignored.length - countToDeleted);
                }

                if (fillPlaceRow(plane, ignoredTry)) {
                    for (String i : ignoredTry) {
                        ignored = ArrayUtils.removeElements(ignored, i);
                    }
                    countToDeleted = 1;
                } else {
                    countToDeleted++;
                }
            }
        }
    }

    /**
     * Try to fill all the people on the places
     * If have some people that couldn't keep together, return it
     *
     * @param input input people
     * @param plane Matrix plane
     * @return People couldn't keep together
     */
    protected String[][] fillPlaces(String[][] input, String[][] plane) {
        String[][] ignoreds = new String[plane.length][];

        boolean firstLine = true;
        int countIgnored = 0;
        for (String[] rowInput : input) {
            if (firstLine) {
                firstLine = false;
                continue;
            }

            if (!fillPlaceRow(plane, rowInput)) {
                ignoreds[countIgnored] = rowInput;
                countIgnored++;
            }

        }
        if (countIgnored == 0)
            return null;

        return ignoreds;
    }

    /**
     * Check if it's possible fill the entire row on the plane
     * If it's possible add them on the plane
     *
     * @param plane    Plane Matrix
     * @param rowInput Collection of people
     * @return was possible to add the entire row
     */
    protected boolean fillPlaceRow(String[][] plane, String[] rowInput) {

        for (int rowPlane = 0; rowPlane < plane.length; rowPlane++) {
            if (isPossible(plane[rowPlane], rowInput)) {
                fillPlaceCol(rowInput, plane, rowPlane);
                return true;
            }
        }
        return false;
    }

    /**
     * Choose the rights coloumn in the plane
     * It is considering the opitional for sit in the window
     * Return the right sit for the people sit together in case of the last person sit on the right window
     *
     * @param countWindow  Count of the people want to sit on the window
     * @param valueInput   the person
     * @param plane        matrix plane
     * @param rowPlane     the plane row
     * @param lastStartCol the last person added
     * @return the right coloumn
     */
    protected int selectRightCol(int countWindow, String valueInput, String[][] plane, int rowPlane, int lastStartCol) {
        if (countWindow > 0) {
            if (valueInput.contains("w")) {
                // check if the first window is available
                if (plane[rowPlane][0] == null)
                    return 0;
                    // check if the last window is available
                else if (plane[rowPlane][plane[rowPlane].length - 1] == null)
                    return plane[rowPlane].length - 1;
                    // all the window is unavailable
                else
                    return 1;
            } else {
                // case the last place was in the right window
                if (lastStartCol == (plane[rowPlane].length - 1))
                    // then needs add the person together
                    return plane[rowPlane].length - 2;
                else
                    return 1;
            }
        }
        return 0;
    }

    /**
     * Add the person on the plane
     * Consider the opintional to sit on the window
     *
     * @param rowInput collection of people
     * @param plane    matrix plane
     * @param rowPlane row of the plane
     */
    protected void fillPlaceCol(String[] rowInput, String[][] plane, int rowPlane) {
        int countWindow = 0;
        for (String valueInput : rowInput) {
            if (valueInput.contains("w"))
                countWindow++;
        }
        int startCol = 0;
        this.countPeople = this.countPeople + rowInput.length;
        for (String valueInput : rowInput) {
            startCol = selectRightCol(countWindow, valueInput, plane, rowPlane, startCol);

            // this means that the person can't sit in the window
            if (valueInput.contains("w") && startCol == 1) {
                this.countInsatisfaction++;
            }

            for (int colPlane = startCol; colPlane < plane[rowPlane].length; colPlane++) {
                if (plane[rowPlane][colPlane] == null) {
                    plane[rowPlane][colPlane] = valueInput;
                    break;
                }
            }
        }
    }

    /**
     * CHeck if it is possible add the collection of people together
     *
     * @param rowPlane row of the plane
     * @param rowInput collection of people
     * @return if it is possible
     */
    protected boolean isPossible(String[] rowPlane, String[] rowInput) {
        int length = Collections.frequency(Arrays.asList(rowPlane), null);
        return (length >= rowInput.length);
    }

}
