package com.dojah.kyc_sdk_kotlin.core.util

import com.dojah.kyc_sdk_kotlin.data.LocationManager
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LocationManagerTest {

    @Test
    fun `distanceBetween returns zero for identical coordinates`() {
        val point = Pair(6.5244, 3.3792)

        val distance = LocationManager.distanceBetween(point, point)

        assertEquals(0.0, distance, 0.001)
    }

    @Test
    fun `distanceBetween returns expected metres for known Lagos coordinates`() {
        // Approx. 1.1 km apart in Lagos
        val pointA = Pair(6.5244, 3.3792)
        val pointB = Pair(6.5333, 3.3792)

        val distance = LocationManager.distanceBetween(pointA, pointB)

        assertTrue(distance in 900.0..1300.0)
    }

    @Test
    fun `withinRange uses default 50 metre threshold`() {
        val selected = Pair(6.5244, 3.3792)
        val nearby = Pair(6.52445, 3.37925)

        assertTrue(LocationManager.withinRange(selected, nearby))
    }

    @Test
    fun `withinRange returns false when outside default threshold`() {
        val selected = Pair(6.5244, 3.3792)
        val far = Pair(6.5333, 3.3792)

        assertFalse(LocationManager.withinRange(selected, far))
    }

    @Test
    fun `withinRange respects custom range in kilometres`() {
        val selected = Pair(6.5244, 3.3792)
        val far = Pair(6.5333, 3.3792)

        assertTrue(LocationManager.withinRange(selected, far, range = 2.0))
        assertFalse(LocationManager.withinRange(selected, far, range = 0.5))
    }

    @Test
    fun `distanceBetween handles negative coordinates`() {
        val south = Pair(-33.8688, 151.2093)
        val north = Pair(-33.8588, 151.2093)

        val distance = LocationManager.distanceBetween(south, north)

        assertTrue(distance > 1000.0)
    }
}
