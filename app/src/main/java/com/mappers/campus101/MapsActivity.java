package com.mappers.campus101;

import com.mappers.campus101.models.Location;

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
import android.widget.FrameLayout;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mappers.campus101.http.VolleyManager;
import com.mappers.campus101.models.Building;

/*
 * Map activity
 * @author Kaan Özkara & Yılmaz Korkmaz
 * @date 22.04.2016
 * TODO: Show user's location, Handle the Task Button
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, View.OnClickListener {

    private GoogleMap mMap;
    private FrameLayout frameLayout;
    private MyRelativeLayout relativeLayout ;
    private Button buttonQR;
    private Button buttonTask;
    private Button buttonTeam;
    private VolleyManager mapManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        frameLayout = new FrameLayout(this);
        relativeLayout = new MyRelativeLayout(this);


        buttonQR = (Button) findViewById(R.id.buttonQR);
        buttonTask = (Button) findViewById(R.id.buttonTask);
        buttonTeam = (Button) findViewById(R.id.buttonTeam);

        buttonTask.setOnClickListener(this);
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
        com.mappers.campus101.models.Location loc_odeon = new com.mappers.campus101.models.Location(39.875425, 32.751971);
        com.mappers.campus101.models.Location loc_library = new com.mappers.campus101.models.Location(39.870344, 32.749576);
        com.mappers.campus101.models.Location loc_B_Building = new com.mappers.campus101.models.Location(39.868754, 32.748069);
        com.mappers.campus101.models.Location loc_G_Building = new com.mappers.campus101.models.Location(39.868684, 32.749662);
        com.mappers.campus101.models.Location loc_T_Building = new com.mappers.campus101.models.Location(39.868279, 32.749241);
        com.mappers.campus101.models.Location loc_SB_Building = new com.mappers.campus101.models.Location(39.868273, 32.748187);
        com.mappers.campus101.models.Location loc_SA_Building = new com.mappers.campus101.models.Location(39.867774, 32.748294);
        com.mappers.campus101.models.Location loc_AH_Buildings = new com.mappers.campus101.models.Location(39.867902, 32.749418);
        com.mappers.campus101.models.Location loc_M_Building = new com.mappers.campus101.models.Location(39.867514, 32.749425);
        com.mappers.campus101.models.Location loc_EB_Building = new com.mappers.campus101.models.Location(39.871197, 32.750064);
        com.mappers.campus101.models.Location loc_dinary = new com.mappers.campus101.models.Location(39.870598, 32.750541);
        com.mappers.campus101.models.Location loc_EE_Building = new com.mappers.campus101.models.Location(39.872082, 32.750721);
        com.mappers.campus101.models.Location loc_sportCenter = new com.mappers.campus101.models.Location(39.866629, 32.748454);
        com.mappers.campus101.models.Location loc_FF_Building = new com.mappers.campus101.models.Location(39.865929, 32.748818);
        com.mappers.campus101.models.Location loc_V_Building = new com.mappers.campus101.models.Location(39.867032, 32.749414);

        // initializing buildings
        Building odeon = new Building( 0, "Odeon", loc_odeon);
        Building library = new Building( 14, "Library", loc_library);
        Building B_Building = new Building(1, "B Building", loc_B_Building);
        Building G_Building = new Building(2, "G Building", loc_G_Building);
        Building T_Building = new Building(3, "T Building", loc_T_Building);
        Building SB_Building = new Building(4, "SB Building", loc_SB_Building);
        Building SA_Building = new Building(5, "SA Building", loc_SA_Building);
        Building AH_Buildings = new Building(6, "AH Building", loc_AH_Buildings);
        Building M_Building = new Building(7, "M Building", loc_M_Building);
        Building EB_Building = new Building(8, "EB Building", loc_EB_Building);
        Building dinary = new Building( 9, "Dinary", loc_dinary);
        Building EE_Building = new Building(10, "EE Building", loc_EE_Building);
        Building sportCenter = new Building(11, "Sport Center", loc_sportCenter);
        Building FF_Building = new Building(12, "FF Building", loc_FF_Building);
        Building V_Building = new Building(13, "V Building", loc_V_Building);


        //Adding Markers
        mMap.addMarker(new MarkerOptions().position(odeon.getLocation()).title("ODEON"));
        mMap.addMarker(new MarkerOptions().position(library.getLocation()).title("Kütüphane"));
        mMap.addMarker(new MarkerOptions().position(B_Building.getLocation()).title("Hukuk Fakültesi"));
        mMap.addMarker(new MarkerOptions().position(G_Building.getLocation()).title("G Binası"));
        mMap.addMarker(new MarkerOptions().position(T_Building.getLocation()).title("T Binası"));
        mMap.addMarker(new MarkerOptions().position(SB_Building.getLocation()).title("SB Binası"));
        mMap.addMarker(new MarkerOptions().position(SA_Building.getLocation()).title("SA Binası"));
        mMap.addMarker(new MarkerOptions().position(AH_Buildings.getLocation()).title("İnsani Bilimler Fakuültesi"));
        mMap.addMarker(new MarkerOptions().position(M_Building.getLocation()).title("İktsat Binası"));
        mMap.addMarker(new MarkerOptions().position(EB_Building.getLocation()).title("Mühendislik ve Rektörlük Binası"));
        mMap.addMarker(new MarkerOptions().position(dinary.getLocation()).title("Yemekhane"));
        mMap.addMarker(new MarkerOptions().position(EE_Building.getLocation()).title("Elektrik Elektronik Müh. Binası"));
        mMap.addMarker(new MarkerOptions().position(sportCenter.getLocation()).title("Merkez Spor Salonu"));
        mMap.addMarker(new MarkerOptions().position(FF_Building.getLocation()).title("Güzel Sanatlar Fakültesi"));
        mMap.addMarker(new MarkerOptions().position(V_Building.getLocation()).title("İşletme Fakültesi"));


        mMap.moveCamera(CameraUpdateFactory.newLatLng(odeon.getLocation()));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(17));
        MarkerOptions mp = new MarkerOptions();
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        //mMap.setPadding(0,150,0,0);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

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
        mMap.setMyLocationEnabled(false);
        mapManager.getTeamMembers(this);
    }

/*
    @Override
    public void onLocationChanged(android.location.Location location) {;
        MarkerOptions mOpt = new MarkerOptions();
        mOpt.position(new LatLng(location.getLatitude(),location.getLongitude()));
        mOpt.title("Current Position");
        mMap.addMarker(mOpt);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng( location.getLatitude(), location.getLongitude() ),17));
*/
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
            relativeLayout.requestDisallowInterceptTouchEvent(true);
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();

            String teamMembersString ;
            teamMembersString = "ID " + "                            " + "NAME" + "\n" + mapManager.getTeamMembers(this); //To be implemented later

            alertDialog.setTitle("Team Members");
            alertDialog.setMessage(teamMembersString);           //Sample Team String

            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        else if ( v == buttonTask)
        {
            AlertDialog alert = new AlertDialog.Builder(this).create();

            alert.setTitle("Task:");
            alert.setMessage("0 task");

            alert.setButton(AlertDialog.BUTTON_NEUTRAL,"Okay",
                    new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

                    });
            alert.show();
        }


    }

}
