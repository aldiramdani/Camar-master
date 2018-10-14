package com.proclubstudio.camar.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.proclubstudio.camar.DetailGempaActivity;
import com.proclubstudio.camar.R;
import com.proclubstudio.camar.model.JSON;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    public ArrayList<JSON> Data;
    private Context mContext;
    int indicatorTsunami;
    String statusTsunami;

    public DataAdapter(Context context,ArrayList<JSON> Data){

        mContext = context;
        this.Data = Data;
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tophome_list,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {
        //indicatortsunami
        indicatorTsunami = (Data.get(position).getTsunami());
        if (indicatorTsunami == 0){
            statusTsunami = "Tidak Berpotensi Tsunami";
        }else{
            statusTsunami = "Berpotensi Tsunami";
        }
        //inisialisasi
        final String formattedMagnitude = formatMagnitude(Data.get(position).getMag());
        final String formattedDepth = formatDepth(Data.get(position).getDepth());
        final String formattedTime = formatTime(Data.get(position).getTime());
        final String Longtitude = (Data.get(position).getLocation().getCoordinates().get(0)).toString();
        final String Latitude = (Data.get(position).getLocation().getCoordinates().get(1).toString());
        final String keterangan_gempa = (Data.get(position).getPlace());
        //setdata
        holder.txt_timeHome.setText(formattedTime);
        holder.txt_magnitudoHome.setText(formattedMagnitude);
        holder.txt_kedalamanHome.setText(formattedDepth);
        holder.txt_keteranganGempaHome.setText(keterangan_gempa);
        holder.txt_indicatorTsunamiHome.setText(statusTsunami);

        holder.parentTopHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 Intent intent = new Intent(mContext, DetailGempaActivity.class);
                intent.putExtra("txt_magnitude",formattedMagnitude);
                intent.putExtra("txt_time",formattedTime);
                intent.putExtra("txt_kedalaman",formattedDepth);
                intent.putExtra("txt_keterangan",keterangan_gempa);
                intent.putExtra("txt_longtitude",Longtitude);
                intent.putExtra("txt_latitude",Latitude);
                intent.putExtra("statusTsunami",statusTsunami);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {

        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_timeHome,
                txt_indicatorTsunamiHome,
                txt_magnitudoHome,
                txt_kedalamanHome,
                txt_keteranganGempaHome,
                txt_keteranganGempaList,
                txt_indicatorTsunamiList,
                txt_timeList,
                txt_kedalamanGempaList,
                txt_magnitudoList
        ;
        private ImageView img_indicatorTsunamiHome,
                img_indicator_gempaHome,
                img_depthHome,
                img_indicatorTsunamiList,
                indicatorImageList
                        ;
        private FrameLayout parentTopHome;

        public ViewHolder(View itemView) {
            super(itemView);
            //parentlayout
            parentTopHome = (FrameLayout)itemView.findViewById(R.id.parentTopHome);

            //textview row_tophome_list
            txt_timeHome = (TextView)itemView.findViewById(R.id.txt_timeHome);
            txt_magnitudoHome = (TextView)itemView.findViewById(R.id.txt_magnitudoHome);
            txt_kedalamanHome = (TextView)itemView.findViewById(R.id.txt_kedalamanHome);
            txt_keteranganGempaHome = (TextView)itemView.findViewById(R.id.txt_keteranganGempaHome);
            txt_indicatorTsunamiHome = (TextView)itemView.findViewById(R.id.txt_indicatorTsunamiHome);
            //textview row_listgempa
            txt_keteranganGempaList = (TextView)itemView.findViewById(R.id.txt_keteranganGempaList);
            txt_indicatorTsunamiList = (TextView)itemView.findViewById(R.id.txt_indicatorTsunamiList);
            txt_timeList= (TextView)itemView.findViewById(R.id.txt_timeList);
            txt_kedalamanGempaList = (TextView)itemView.findViewById(R.id.txt_kedalamanGempaList);
            txt_magnitudoList = (TextView)itemView.findViewById(R.id.txt_magnitudoList);

            // /imageview row_tophome_list
            img_indicatorTsunamiHome = (ImageView)itemView.findViewById(R.id.img_indicatorTsunamiHome);
            img_depthHome = (ImageView)itemView.findViewById(R.id.img_depthHome);
            img_indicator_gempaHome = (ImageView) itemView.findViewById(R.id.img_indicator_gempaHome);
            //imageview row_listgempa
            img_indicatorTsunamiList = (ImageView)itemView.findViewById(R.id.txt_indicatorTsunamiList);
            indicatorImageList = (ImageView)itemView.findViewById(R.id.indicatorGempaList);
        }
    }

    private String formatMagnitude(double magnitude){
        DecimalFormat magnitudeFormat = new DecimalFormat("0.0");
        return magnitudeFormat.format(magnitude);
    }
    private String formatDepth(double depth){
        DecimalFormat depthFromat = new DecimalFormat("0.0");
        return depthFromat.format(depth);
    }
    private String formatTime(long time){
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy hh:mm:ss aa", cal).toString();
        return date;
    }

}
