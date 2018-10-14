package com.proclubstudio.camar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class DetailGempaActivity extends AppCompatActivity implements OnMapReadyCallback{
//             intent.putExtra("txt_magnitude",formattedMagnitude);
//                intent.putExtra("txt_time",formattedTime);
//                intent.putExtra("txt_kedalaman",formattedDepth);
//                intent.putExtra("txt_keterangan",keterangan_gempa);
//                intent.putExtra("txt_longtitude",Longtitude);
//                intent.putExtra("txt_latitude",Latitude);

    String txt_magnitude,
            txt_time,
            txt_kedalaman,
            txt_keterangan,
            txt_longtitude,
            txt_latitude,
            statusTsunami;

    TextView txt_waktuGempa,
            lokasiGempa,
            txt_magnitudoGempa,
            txt_kedalamanGempa,
            txt_indicatorTsunami,
            txt_informasiGempa;

    ImageView img_indicator_gempa,
    img_indicatorGempa;
    Double lot;
    Double lat;
    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_gempa);


        getIncomingIntent();
        //Map
        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager()
        .findFragmentById(R.id.mapView2);
        mapFragment.getMapAsync(this);
        //inisialisasi
        txt_waktuGempa = (TextView)findViewById(R.id.txt_timeDialog);
        txt_magnitudoGempa  = (TextView)findViewById(R.id.txt_magnitudeDetail);
        txt_kedalamanGempa = (TextView)findViewById(R.id.txt_kedalamanDetail);
        txt_indicatorTsunami = (TextView)findViewById(R.id.txt_indicatorTsunamiDialog);
        txt_informasiGempa = (TextView)findViewById(R.id.txt_informasiGempa);
        lokasiGempa = (TextView)findViewById(R.id.txt_locationDialog);

        //DoubleLonLat
        lot = Double.parseDouble(txt_longtitude);
        lat = Double.parseDouble(txt_latitude);

        //setText
        lokasiGempa.setText("Gempa Berada Di "+txt_keterangan);
        txt_waktuGempa.setText(txt_time);
        txt_magnitudoGempa.setText(txt_magnitude);
        txt_kedalamanGempa.setText(txt_kedalaman);
        txt_indicatorTsunami.setText(statusTsunami);
        txt_informasiGempa.setText("Lokasi Gempa Berada di "+txt_keterangan +
                " Berada Pada "+txt_longtitude +" LS , "+txt_latitude+" BT");

    }


    private void getIncomingIntent(){
        if (getIntent().hasExtra("txt_magnitude")){
            txt_magnitude = getIntent().getStringExtra("txt_magnitude");
            txt_time = getIntent().getStringExtra("txt_time");
            txt_kedalaman = getIntent().getStringExtra("txt_kedalaman");
            txt_keterangan = getIntent().getStringExtra("txt_keterangan");
            txt_longtitude = getIntent().getStringExtra("txt_longtitude");
            txt_latitude = getIntent().getStringExtra("txt_latitude");
            statusTsunami = getIntent().getStringExtra("statusTsunami");
            Log.d("Check PutExtra", txt_kedalaman.toString() + txt_time.toString() + txt_keterangan.toString() +
                    txt_magnitude.toString()+txt_longtitude.toString() + txt_latitude.toString() );
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng test = new LatLng(lat,lot);
        mMap.addMarker(new MarkerOptions().position(test).title("Gempa Berada Di Titik Ini"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(test));
    }
}
