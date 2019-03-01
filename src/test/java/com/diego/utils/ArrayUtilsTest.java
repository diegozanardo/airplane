package com.diego.utils;

import com.diego.Main;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ArrayUtilsTest {

    @Test
    public void shouldremoveElements(){
        String[] input = { "1w", "2", "3" };
        String delete = "1w";
        String[] expected = { "2", "3" };

        String[] result = ArrayUtils.removeElements(input, delete);

        Assert.assertArrayEquals(result, expected);
    }
}
