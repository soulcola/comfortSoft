package com.petrtitov.comfortSoft.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class ExcelUtilsTest {
    private List<Integer> expectedList;
    @BeforeEach
    void setUp() {
        expectedList = QuickSelectTestData.testNumbers;
    }

    @Test
    public void excelTest() throws IOException {
        List<Integer> actual = ExcelUtils.readNumbersFromXlsx("src/test/resources/test.xlsx");
        Assertions.assertEquals(actual, expectedList);
    }
}