package com.example.nabchallenge.extension

import com.example.nabchallenge.utils.formatDateString
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

/***
 * Created by Khoa Nguyen on 09/12/2021.
 * Copyright (c) 2021 Propzy. All rights reserved.
 * Email: khoantt91@gmail.com
 */

@RunWith(RobolectricTestRunner::class)
class DateFormatExtensionTest {

    @Test
    fun formatDateFromLong() {
        val expectedResult = "Thu, 09 Dec 2021"
        val actualResult = 1639049697094.formatDateString()
        assertThat(actualResult).isEqualTo(expectedResult)
    }

}