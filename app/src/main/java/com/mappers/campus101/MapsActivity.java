package com.mappers.campus101;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mappers.campus101.http.VolleyManager;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, View.OnClickListener {

    private GoogleMap mMap;
    private Button buttonQR;
    private Button buttonTeam;
    private Button buttonQuestion;
    private VolleyManager mapManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        buttonQR = (Button) findViewById(R.id.buttonQR);
        buttonTeam = (Button) findViewById(R.id.buttonTeam);
        buttonQuestion = (Button) findViewById(R.id.buttonQuestion);
        buttonTeam.setOnClickListener(this);
        buttonQR.setOnClickListener(this);
        mapManager = new VolleyManager();
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Setting the locations of the buildings of Bilkent
        LatLng odeon = new LatLng(39.875425, 32.751971);
        LatLng library = new LatLng(39.870344, 32.749576);
        LatLng B_Building = new LatLng(39.868754, 32.748069);
        LatLng G_Building = new LatLng(39.868684, 32.749662);
        LatLng T_Building = new LatLng(39.868279, 32.749241);
        LatLng SB_Building = new LatLng(39.868273, 32.748187);
        LatLng SA_Building = new LatLng(39.867774, 32.748294);
        LatLng AH_Buildings = new LatLng(39.867902, 32.749418);
        LatLng M_Building = new LatLng(39.867514, 32.749425);
        LatLng EB_Building = new LatLng(39.871197, 32.750064);
        LatLng dinary = new LatLng(39.870598, 32.750541);
        LatLng EE_Building = new LatLng(39.872082, 32.750721);
        LatLng sportCenter = new LatLng(39.866629, 32.748454);
        LatLng FF_Building = new LatLng(39.865929, 32.748818);
        LatLng V_Building = new LatLng(39.867032, 32.749414);


        //Adding MArkers
        mMap.addMarker(new MarkerOptions().position(odeon).title("ODEON"));
        mMap.addMarker(new MarkerOptions().position(library).title("Kütüphane"));
        mMap.addMarker(new MarkerOptions().position(B_Building).title("Hukuk Fakültesi"));
        mMap.addMarker(new MarkerOptions().position(G_Building).title("G Binası"));
        mMap.addMarker(new MarkerOptions().position(T_Building).title("T Binası"));
        mMap.addMarker(new MarkerOptions().position(SB_Building).title("SB Binası"));
        mMap.addMarker(new MarkerOptions().position(SA_Building).title("SA Binası"));
        mMap.addMarker(new MarkerOptions().position(AH_Buildings).title("İnsani Bilimler Fakuültesi"));
        mMap.addMarker(new MarkerOptions().position(M_Building).title("İktsat Binası"));
        mMap.addMarker(new MarkerOptions().position(EB_Building).title("Mühendislik ve Rektörlük Binası"));
        mMap.addMarker(new MarkerOptions().position(dinary).title("Yemekhane"));
        mMap.addMarker(new MarkerOptions().position(EE_Building).title("Elektrik Elektronik Müh. Binası"));
        mMap.addMarker(new MarkerOptions().position(sportCenter).title("Merkez Spor Salonu"));
        mMap.addMarker(new MarkerOptions().position(FF_Building).title("Güzel Sanatlar Fakültesi"));
        mMap.addMarker(new MarkerOptions().position(V_Building).title("İşletme Fakültesi"));




        mMap.moveCamera(CameraUpdateFactory.newLatLng(odeon));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(17));
        MarkerOptions mp = new MarkerOptions();
        mMap.getUiSettings().setMyLocationButtonEnabled(true);

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)){

            }
            else{
                ActivityCompat.requestPermissions(this, new String[]
                        {Manifest.permission.ACCESS_FINE_LOCATION},0);
            }
        }
        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onLocationChanged(Location location) {
        mMap.clear();
        MarkerOptions mOpt = new MarkerOptions();
        mOpt.position(new LatLng(location.getLatitude(),location.getLongitude()));
        mOpt.title("Current Position");
        mMap.addMarker(mOpt);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng( location.getLatitude(), location.getLongitude() ),17));

    }
    @Override
    public void onClick(View v)
    {
        if(v == buttonQR)
        {
            Intent qrIntent = new Intent(this, QRReaderActivity.class);
            startActivity(qrIntent);
        }
        else if( v == buttonTeam)
        {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            String teamMembersString = "";

            teamMembersString = mapManager.getTeamMembers().get(0);

            alertDialog.setTitle("Team Members");
            alertDialog.setMessage(teamMembersString);

            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();

        }
        else if(v == buttonQuestion )
        {

        }

    }
}
