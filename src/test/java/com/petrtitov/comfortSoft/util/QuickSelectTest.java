package com.petrtitov.comfortSoft.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.petrtitov.comfortSoft.util.QuickSelect.findNthMaxInList;
import static com.petrtitov.comfortSoft.util.QuickSelectTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class QuickSelectTest {
    private List<Integer> testNumbers = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        testNumbers = QuickSelectTestData.testNumbers;
    }

    @Test
    public void quickSelect()  {
        assertEquals(findNthMaxInList(VALID_INDEX, testNumbers), VALID_INDEX_VALUE);
        assertThrows(IllegalArgumentException.class, () -> findNthMaxInList(INVALID_INDEX, testNumbers));
        assertThrows(IllegalArgumentException.class, () -> findNthMaxInList(NEGATIVE_INDEX, testNumbers));
        assertThrows(IllegalArgumentException.class, () -> findNthMaxInList(NEGATIVE_INDEX, new ArrayList<>()));
    }
}