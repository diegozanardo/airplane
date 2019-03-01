package com.diego;

import com.diego.services.PlaneService;
import com.diego.utils.ArrayUtils;

import java.io.FileNotFoundException;

public class Main {

    private static String INVALID_FILE = "The file is not valid";

    public static void main(String[] args) throws FileNotFoundException {

        String pathFile;

        if (args.length > 0 && args[0] != null) {
            pathFile = args[0];
        } else {
            throw new IllegalArgumentException(INVALID_FILE);
        }

        String[][] input = ArrayUtils.convertFileToArray(pathFile);

        PlaneService planeSerive = new PlaneService();

        String[][] plane = planeSerive.handlePlaces(input);

        printMatrix(plane);

        System.out.println(planeSerive.getSatisfaction() + "%");
    }

    public static void printMatrix(String[][] plane) {
        for (int i = 0; i < plane.length; i++) {
            for (int j = 0; j < plane[i].length; j++) {
                System.out.print(plane[i][j] + " ");
            }
            System.out.println();
        }
    }
}
