package com.cashcontrol.feature.domain

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import kotlin.math.sqrt

class SensorEventListener(
    private val callback: () -> Unit
) : SensorEventListener {

    private var acceleration = 10f
    private var currentAcceleration = SensorManager.GRAVITY_EARTH
    private var lastAcceleration = SensorManager.GRAVITY_EARTH

    override fun onSensorChanged(event: SensorEvent) {
        val x = event.values[0]
        val y = event.values[1]
        val z = event.values[2]
        lastAcceleration = currentAcceleration
        currentAcceleration = sqrt(x * x + y * y + z * z)
        val delta: Float = currentAcceleration - lastAcceleration
        acceleration = acceleration * 0.9f + delta
        if (acceleration > 15) {
            callback.invoke()
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) = Unit
}