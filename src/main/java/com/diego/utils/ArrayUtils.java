package com.diego.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public  class  ArrayUtils {

    public static String[] removeElements(String[] input, String deleteMe) {
        List result = new LinkedList();

        for (String item : input)
            if (!deleteMe.equals(item))
                result.add(item);

        return (String[]) result.toArray(new String[result.size()]);
    }

    public static String[][] convertFileToArray(String pathFile) throws FileNotFoundException {
        File file = new File(pathFile);

        int count = 0;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                count++;
                sc.nextLine();
            }
        }

        String[][] input = new String[count][];
        try (Scanner sc = new Scanner(file)) {
            String[] places;
            int row = 0;
            while (sc.hasNextLine()) {
                int col = 0;
                String line = sc.nextLine();
                places = line.split("\\s+");
                input[row] = new String[places.length];
                for (String place : places) {
                    input[row][col] = place.toLowerCase();
                    col++;
                }
                row++;
            }
        }
        return input;
    }
}
