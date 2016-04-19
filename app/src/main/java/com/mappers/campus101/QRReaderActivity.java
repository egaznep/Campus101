package com.mappers.campus101;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/*
 * QR reader activity
 * @author Kadir Can Çelik
 * @date 17.04.2016
 */

public class QRReaderActivity extends Activity implements ZXingScannerView.ResultHandler {
    // Instance variable
    private ZXingScannerView mScannerView;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZXingScannerView(this);
        setContentView(R.layout.activity_qrreader);
    }

    @Override
    protected void onResume () {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        Log.v ("QR: ", rawResult.getText());
    }
}