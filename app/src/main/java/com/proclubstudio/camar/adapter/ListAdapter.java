package com.proclubstudio.camar.adapter;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.proclubstudio.camar.R;
import com.proclubstudio.camar.model.JSON;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
{
    private Context mContext;
    public ArrayList<JSON> Data;
    String formattedMagnitude;
    String formattedDepth;
    Integer indicatorTsunami;
    String statusTsunami;
    int category;
    Dialog detailGempaDialog;
    public ListAdapter(Context context,ArrayList<JSON> Data,int category){
        mContext = context;
        this.Data = Data;
        this.category = category;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_listgempa,parent,false);

        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {

        formattedMagnitude = formatMagnitude(Data.get(position).getMag());
        formattedDepth = formatDepth(Data.get(position).getDepth());
        indicatorTsunami = (Data.get(position).getTsunami());
        if (indicatorTsunami == 0){
            statusTsunami = "Tidak Berpotensi Tsunami";
        }else{
            statusTsunami = "Berpotensi Tsunami";
        }
        holder.txt_keteranganGempaList.setText(Data.get(position).getPlace());
        holder.txt_indicatorTsunamiList.setText(statusTsunami);
//        holder.txt_timeList.setText(Data.get(position).getTime());
        holder.txt_kedalamanGempaList.setText(formattedDepth+"KM");
        holder.txt_magnitudoList.setText(formattedMagnitude);

        //dialogIni
        detailGempaDialog = new Dialog(mContext);
        detailGempaDialog.setContentView(R.layout.detail_gempa_dialog);


        holder.BottomHomeList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @Override
    public int getItemCount() {
        if (this.category ==0){
            return  1;
        }
        return Data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_timeHome,
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

        LinearLayout BottomHomeList;

        public ViewHolder(View itemView) {
            super(itemView);
            //textview row_tophome_list
            txt_timeHome = (TextView)itemView.findViewById(R.id.txt_timeHome);
            txt_magnitudoHome = (TextView)itemView.findViewById(R.id.txt_magnitudoHome);
            txt_kedalamanHome = (TextView)itemView.findViewById(R.id.txt_kedalamanHome);
            txt_keteranganGempaHome = (TextView)itemView.findViewById(R.id.txt_keteranganGempaHome);
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
            img_indicatorTsunamiList = (ImageView)itemView.findViewById(R.id.img_indicatorTsunamiList);
            indicatorImageList = (ImageView)itemView.findViewById(R.id.indicatorGempaList);

            //lineralayout
            BottomHomeList = (LinearLayout)itemView.findViewById(R.id.BottomHomeList);
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


}
