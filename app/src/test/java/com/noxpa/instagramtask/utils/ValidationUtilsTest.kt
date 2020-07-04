package com.noxpa.instagramtask.utils

import org.junit.Test
import org.junit.Assert.*

class ValidationUtilsTest {

    @Test
    fun testIsValidMentioned() {
        assertEquals(true, isValidMentioned("@ivan_ivan"))
    }

    @Test
    fun testIsValidHashTag() {
        assertEquals(true, isValidHashTag("#future"))
    }
}