package com.example.atividadesensores

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.atividadesensores.databinding.FragmentGiroscopicoBinding

class GiroscopicoFragment : Fragment() {
    private lateinit var binding: FragmentGiroscopicoBinding
    private lateinit var sensorManager: SensorManager
    private lateinit var gyroscopicSensor: Sensor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_giroscopico, container, false)
        sensorManager = context?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        gyroscopicSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        sensorManager.registerListener(gyroscopeListener, gyroscopicSensor, SensorManager.SENSOR_DELAY_NORMAL)

        return binding.root
    }

    private var gyroscopeListener = object: SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
//            TODO("Not yet implemented")
        }

        override fun onSensorChanged(event: SensorEvent?) {
            binding.apply {
                axisXValue.text = event!!.values[0].toString()
                axisYValue.text = event.values[1].toString()
                axisZValue.text = event.values[2].toString()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        gyroscopicSensor.also { gyro ->
            sensorManager.registerListener(
                gyroscopeListener,
                gyro,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(gyroscopeListener)
    }

}