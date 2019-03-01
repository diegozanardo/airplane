package com.diego.utils;

import com.diego.Main;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class IntegerUtilsTest {

    @Test
    public void shouldIsIntegerReturnTrueWhenCallWithAInteger() {
        String input = "10";

        boolean result = IntegerUtils.isInteger(input);
        Assert.assertTrue(result);
    }

    @Test
    public void shouldIsIntegerReturnFalseWhenCallWithAString() {
        String input = "foo";

        boolean result = IntegerUtils.isInteger(input);
        Assert.assertFalse(result);
    }
}
