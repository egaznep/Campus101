package com.mappers.campus101;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import com.mappers.campus101.models.Location;

//import android.location.Location;

/*
 * Map activity
 * @author Kaan Özkara & Yılmaz Korkmaz
 * @date 22.04.2016
 * TODO: Show user's location
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, View.OnClickListener
        , ActivityCompat.OnRequestPermissionsResultCallback{

    private GoogleMap mMap;
    private FrameLayout frameLayout;
    private MyRelativeLayout relativeLayout ;
    private Button buttonQR;
    private Button buttonTask;
    private Button buttonTeam;
    private VolleyManager mapManager;

    //For my Location Button
    private  static final int LOCATION_PERMISSION_REQUEST_CODE = 1 ;
    private boolean mPermissionDenied = false ;



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

        //Relating the XML buttons with java counterparts
        buttonQR = (Button) findViewById(R.id.buttonQR);
        buttonTask = (Button) findViewById(R.id.buttonTask);
        buttonTeam = (Button) findViewById(R.id.buttonTeam);

        //Assigning listeners
        buttonTask.setOnClickListener(this);
        buttonTeam.setOnClickListener(this);
        buttonQR.setOnClickListener(this);
        mapManager = App.getRequestManager();


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
        Location loc_odeon = new Location(39.875425, 32.751971);
        Location loc_library = new Location(39.870344, 32.749576);
        Location loc_B_Building = new Location(39.868754, 32.748069);
        Location loc_G_Building = new Location(39.868684, 32.749662);
        Location loc_T_Building = new Location(39.868279, 32.749241);
        Location loc_SB_Building = new Location(39.868273, 32.748187);
        Location loc_SA_Building = new Location(39.867774, 32.748294);
        Location loc_AH_Buildings = new Location(39.867902, 32.749418);
        Location loc_M_Building = new Location(39.867514, 32.749425);
        Location loc_EB_Building = new Location(39.871197, 32.750064);
        Location loc_dinary = new Location(39.870598, 32.750541);
        Location loc_EE_Building = new Location(39.872082, 32.750721);
        Location loc_sportCenter = new Location(39.866629, 32.748454);
        Location loc_FF_Building = new Location(39.865929, 32.748818);
        Location loc_V_Building = new Location(39.867032, 32.749414);
        Location loc_MayFest = new Location(39.868166, 32.751361);
        Location loc_Dorm76_Field = new Location(39.864872, 32.747584);
        Location loc_StudentAffairs = new Location(39.864239, 32.744828);
        Location loc_MPA = new Location(39.869270, 32.755026);
        Location loc_Meteksan = new Location(39.872519, 32.751412);
        Location loc_Statue = new Location(39.869323, 32.748792);
        Location loc_DormSportsCenter = new Location(39.863878, 32.745599);

        // Initializing buildings
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
        Building mayFest = new Building(15, "MayFest Area", loc_MayFest);
        Building dorm76_Field = new Building(16, "Dormatory 76 Grass Area", loc_Dorm76_Field);
        Building studentAffairs = new Building(17, "Student Affairs", loc_StudentAffairs);
        Building musicAndPerformingArts = new Building(18,"Music and Performing Arts", loc_MPA);
        Building meteksan = new Building(19, "Meteksan Market", loc_Meteksan);
        Building statue = new Building(20, "The statue of Ihsan Dogramaci", loc_Statue);
        Building dormSportsCenter = new Building(21,"Dormatory Sports Center", loc_DormSportsCenter);


        //Adding Markers
        mMap.addMarker( new MarkerOptions().position( odeon.getLocation()).title( "ODEON"));
        mMap.addMarker( new MarkerOptions().position( library.getLocation()).title( "Library"));
        mMap.addMarker( new MarkerOptions().position( B_Building.getLocation()).title( "Faculty of Law"));
        mMap.addMarker( new MarkerOptions().position( G_Building.getLocation()).title( "G Building"));
        mMap.addMarker( new MarkerOptions().position( T_Building.getLocation()).title( "T Building"));
        mMap.addMarker( new MarkerOptions().position( SB_Building.getLocation()).title( "Faculty of Science - B"));
        mMap.addMarker( new MarkerOptions().position( SA_Building.getLocation()).title( "Faculty of Science - A"));
        mMap.addMarker( new MarkerOptions().position( AH_Buildings.getLocation()).title( "Faculty of Humanities and Letters"));
        mMap.addMarker( new MarkerOptions().position( M_Building.getLocation()).title( "Faculty of Economics, Administrative and Social Sciences"));
        mMap.addMarker( new MarkerOptions().position( EB_Building.getLocation()).title( "Presidency and Faculty of Engineering"));
        mMap.addMarker( new MarkerOptions().position( dinary.getLocation()).title( "Dinary"));
        mMap.addMarker( new MarkerOptions().position( EE_Building.getLocation()).title( "Electrical and Electronics Engineering Building"));
        mMap.addMarker( new MarkerOptions().position( sportCenter.getLocation()).title( "Main Campus Sport Center"));
        mMap.addMarker( new MarkerOptions().position( FF_Building.getLocation()).title( "Faculty of Art, Design and Architecture"));
        mMap.addMarker( new MarkerOptions().position( V_Building.getLocation()).title( "Faculty of Business Administration"));
        mMap.addMarker( new MarkerOptions().position( mayFest.getLocation()).title( "MayFest Grass Field"));
        mMap.addMarker( new MarkerOptions().position( dorm76_Field.getLocation()).title( "Dormatory 76 Grass Field"));
        mMap.addMarker( new MarkerOptions().position( studentAffairs.getLocation()).title( "Student Affairs"));
        mMap.addMarker( new MarkerOptions().position( musicAndPerformingArts.getLocation()).title( "Faculty of Music and Performing Arts"));
        mMap.addMarker( new MarkerOptions().position( meteksan.getLocation()).title( "Meteksan Market"));
        mMap.addMarker( new MarkerOptions().position( statue.getLocation()).title( "The statue of Ihsan Dogramaci"));
        mMap.addMarker( new MarkerOptions().position( dormSportsCenter.getLocation()).title( "Dormatory Sports Center"));


        //Initilize the camera from ODEON
        mMap.moveCamera(CameraUpdateFactory.newLatLng(odeon.getLocation()));
        mMap.moveCamera(CameraUpdateFactory.zoomTo(17));
        MarkerOptions mp = new MarkerOptions();
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        enableMyLocation();




        //Decisions about basic functions of the map

        mapManager.getTeamMembers();
        mMap.getUiSettings().setMapToolbarEnabled(false);
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }


    private void enableMyLocation() {
        //Requesting the necessary permission to reach the user's location.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
        //If permission is not declined set the location enabled.
        else{
            mMap.setMyLocationEnabled(true);
        }

        }

    protected void onResumeFragments(){
        super.onResumeFragments();
        if(mPermissionDenied){
            showMissingPermissionError();
            mPermissionDenied = false ;
        }
    }

    private void showMissingPermissionError() {
        return ;
    }




    @Override
    public void onLocationChanged(android.location.Location location) {;
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
            //Starting the QRActivity
            Intent qrIntent = new Intent(this, QRReaderActivity.class);
            startActivity(qrIntent);
        }
        //Display the team with an Alert Dialog
        else if( v == buttonTeam)
        {
            relativeLayout.requestDisallowInterceptTouchEvent(true);
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();

            String teamMembersString ;
            teamMembersString = "ID " + "                            " + "NAME" + "\n" + mapManager.getTeamMembers(); //To be implemented later

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
        //Display the Task Button
        else if ( v == buttonTask)
        {
            mapManager.sendTaskRequest(mapManager.getCurrentStudentID(), this);
        }


    }

}
