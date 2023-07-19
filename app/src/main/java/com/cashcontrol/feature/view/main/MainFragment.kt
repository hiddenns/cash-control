package com.cashcontrol.feature.view.main

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.view.View
import com.cashcontrol.data.model.Transaction
import com.cashcontrol.databinding.FragmentMainBinding
import com.cashcontrol.domain.base.BaseFragment
import com.cashcontrol.feature.domain.SensorEventListener
import com.cashcontrol.feature.main.dialogs.ActionBottomSheetDialog
import com.cashcontrol.feature.main.dialogs.ActionDialogSubmitCallBack
import org.kodein.di.instance

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate),
    ActionDialogSubmitCallBack {

    private val viewModel: MainViewModel by instance()

    private val transactionListAdapter = TransactionListAdapter(mutableListOf())

    private var sensorManager: SensorManager? = null
    private var sensorEventListener: SensorEventListener =
        SensorEventListener(this::onShakeDetected)

    private var actionBottomSheetDialog: ActionBottomSheetDialog? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        setupObservers()
        setupSensorManager()
        setupRecyclers()
        setupDialogs()
    }

    override fun onResume() {
        super.onResume()
        registerOrUnregisterSensorManager(true)
    }

    override fun onPause() {
        registerOrUnregisterSensorManager(false)
        super.onPause()
    }

    private fun setupObservers() {
        with(viewModel) {
            transactions.observe { data ->
                transactionListAdapter.updateData(data)
            }

        }
    }

    private fun setListeners() {
        with(binding) {
            btnWalletPicker.setOnClickListener {
                callActionDialog()
            }
        }
    }

    private fun setupRecyclers() {
        with(binding) {
            rv.adapter = transactionListAdapter
        }
    }

    private fun setupDialogs() {
        actionBottomSheetDialog = ActionBottomSheetDialog()
    }

    private fun setupSensorManager() {
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        sensorManager?.registerListener(
            sensorEventListener,
            sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    private fun onShakeDetected() {
        callActionDialog()
    }

    private fun callActionDialog() {
        val isShowing =
            childFragmentManager.findFragmentByTag(ActionBottomSheetDialog.TAG)?.isResumed ?: false

        if (!isShowing)
            ActionBottomSheetDialog.show(childFragmentManager)
    }

    override fun onClose(transaction: Transaction) {
        viewModel.addTransaction(transaction)
    }

    private fun registerOrUnregisterSensorManager(doRegister: Boolean) {
        if (doRegister) {
            sensorManager?.registerListener(
                sensorEventListener, sensorManager?.getDefaultSensor(
                    Sensor.TYPE_ACCELEROMETER
                ), SensorManager.SENSOR_DELAY_NORMAL
            )
        } else {
            sensorManager?.unregisterListener(sensorEventListener)
        }
    }

    companion object {

    }

}