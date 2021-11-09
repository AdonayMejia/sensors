package com.example.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sensors.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), SensorEventListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this,sensor,0)
        val proximitysensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        sensorManager.registerListener(object : SensorEventListener{
            override fun onSensorChanged(p0: SensorEvent?) {
                val x = p0?.values?.get(0)?:0
                val y = p0?.values?.get(1)?:0
                val z = p0?.values?.get(2)?:0
                if( binding.imageV.x + x.toFloat() < 0) {
                    binding.imageV.setImageResource(R.drawable.shape)

                }else if(binding.imageV.y + y.toFloat() > 0){
                    binding.imageV.setImageResource(R.drawable.circle)
                }
            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

            }

        },proximitysensor,0)

        }

    override fun onSensorChanged(event: SensorEvent?) {
        val x = event?.values?.get(0)?:0
        val y = event?.values?.get(1)?:0
        if(binding.imageV.x + x.toFloat() > 0 && binding.imageV.x + x.toFloat() < 600){
            binding.imageV.x = binding.imageV.x + x.toFloat()
        }
        if(binding.imageV.y + y.toFloat() > 0 && binding.imageV.y + y.toFloat() < 1100){
            binding.imageV.y = binding.imageV.y + y.toFloat()
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}