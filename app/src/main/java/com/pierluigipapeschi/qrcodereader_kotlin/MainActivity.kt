package com.pierluigipapeschi.qrcodereader_kotlin

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.SparseArray
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.google.android.gms.vision.barcode.Barcode
import info.androidhive.barcode.BarcodeReader


class MainActivity : AppCompatActivity(), BarcodeReader.BarcodeReaderListener {

    var barcodeReader: BarcodeReader? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transparentToolbar()

        barcodeReader = supportFragmentManager.findFragmentById(R.id.barcode_scanner) as BarcodeReader?
    }

    private fun transparentToolbar() {

        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
        val win = activity.window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }

    override fun onBitmapScanned(sparseArray: SparseArray<Barcode>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onScannedMultiple(barcodes: MutableList<Barcode>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCameraPermissionDenied() {

        finish()
    }

    override fun onScanned(barcode: Barcode?) {

        barcodeReader!!.playBeep()
    }

    override fun onScanError(errorMessage: String?) {

        Toast.makeText(this, "Error scanning", Toast.LENGTH_SHORT).show()
    }
}
