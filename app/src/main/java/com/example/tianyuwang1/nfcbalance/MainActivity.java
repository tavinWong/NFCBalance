package com.example.tianyuwang1.nfcbalance;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    NfcAdapter nfcAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(nfcAdapter!=null && nfcAdapter.isEnabled()){
            Toast.makeText(this,"NFC enabled", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this,"NFC disabled", Toast.LENGTH_LONG).show();
        }
    }

    protected void onNewIntent(Intent intent){
        Toast.makeText(this, "NFC intent received!", Toast.LENGTH_LONG).show();

        Intent nfcTriggerIntent = new Intent("com.example.tianyuwang1.nfccardcheck.CardDetailActivity");
        startActivity(nfcTriggerIntent);

        super.onNewIntent(intent);
    }

    protected void onResume() {

        Intent nfcIntent = new Intent(this, MainActivity.class);
        nfcIntent.addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, nfcIntent, 0);

        IntentFilter[] intentFilter = new IntentFilter[]{};

        nfcAdapter.enableForegroundDispatch(this, pendingIntent, intentFilter, null);



        super.onResume();

    }

    protected void onPause() {

        nfcAdapter.disableForegroundDispatch(this);
        super.onPause();

    }
}
