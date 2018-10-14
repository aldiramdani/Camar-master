package com.proclubstudio.camar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.proclubstudio.camar.adapter.ListAdapter;
import com.proclubstudio.camar.api.JSONRespone;
import com.proclubstudio.camar.api.RequestInterface;
import com.proclubstudio.camar.model.JSON;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListGempaFragment extends Fragment {
    private HomeFragment homeFragment;
    private ArrayList<JSON>data;
    private RecyclerView ListGempaRecycler;
    public View view;
    private ListAdapter listAdapter;
    public ListGempaFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_list_gempa, container, false);
        initViews();
        return view;
    }

    public void initViews(){

        //deklarasiRecycler
        ListGempaRecycler = view.findViewById(R.id.list_gempaRecycler);
        ListGempaRecycler.setHasFixedSize(true);
        //kostumisasiRecyclerView
        RecyclerView.LayoutManager listLayoutManager=  new LinearLayoutManager(getActivity());
        ListGempaRecycler.setLayoutManager(listLayoutManager);
        LoadJSON();
    }

    public void LoadJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://35.240.181.2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface
                .class);
        Call<JSONRespone> call = request.getJSON();
        call.enqueue(new Callback<JSONRespone>() {
            @Override
            public void onResponse(Call<JSONRespone> call,
                                   Response<JSONRespone> response) {
                JSONRespone jsonRespone = response.body();
                data = new ArrayList<>(Arrays.asList(jsonRespone.getData()));
                Log.d("JSON2",data.toString());
                listAdapter = new ListAdapter(getContext(),data,1);
                ListGempaRecycler.setAdapter(listAdapter);
            }

            @Override
            public void onFailure(Call<JSONRespone> call, Throwable t) {
                Log.d("Error",t.getMessage());
            }
        });
    }

}
