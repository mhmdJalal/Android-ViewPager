package com.codepolitan.viewpager2.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.codepolitan.viewpager2.R;
import com.codepolitan.viewpager2.adapter.ArtikelAdapter;
import com.codepolitan.viewpager2.config.Config;
import com.codepolitan.viewpager2.model.Artikel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class NewsFragment extends Fragment {

    ArrayList<Artikel> artikels = new ArrayList<>();
    ArtikelAdapter artikelAdapter;
    RecyclerView recyclerView;
    ProgressDialog dialog;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading...");
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        readNews();
        artikelAdapter = new ArtikelAdapter(artikels, getContext());
        recyclerView.setAdapter(artikelAdapter);
        dialog.show();
        return v;
    }

//    Method untuk mengambi ldata dari api
    private void readNews(){
//        Instansiasi/ buat objek AsyncHttpClient
        AsyncHttpClient client = new AsyncHttpClient();
//        panggil method get dari asynchttpclient, dengan parameter pertama URL API, parameter kedua AsyncHttpResponseHandler
        client.get(Config.BASE_URL +"posts/category/news",
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        String response = "";
                        Log.d("NewsFragment", "onSuccess: Pembacaan data API sukses");

                        try {
                            // convert format data api dari byte ke string
                            response = new String(responseBody, "UTF-8");

                            /* Instansiasi json object */
                            JSONObject object = new JSONObject(response);
                            /* Instansiasi objek JSONArray dari JSONObject */
                            JSONArray result = object.getJSONArray("result");
                            /* Mengambil key-value code dari object */
                            int code = object.getInt("code");
                            /* mengosongkan kelas Artikel */

                            for (int a = 0; a < result.length(); a++) {
                                JSONObject jsonObject = result.getJSONObject(a);
                                String id, slug, title, author_name, author_image, description, date, link, total_view, thumbnails;

                                id = jsonObject.getString("id");
                                slug = jsonObject.getString("slug");
                                title = jsonObject.getString("title");
                                author_name = jsonObject.getString("author_name");
                                author_image = jsonObject.getString("author_image");
                                description = jsonObject.getString("description");
                                date = jsonObject.getString("date");
                                link = jsonObject.getString("link");
                                thumbnails = jsonObject.getString("thumbnail");
                                total_view = jsonObject.getString("total_views");

                                Artikel artikel = new Artikel(id, slug, title, author_name, author_image, description, date, link, thumbnails, total_view);
                                artikels.add(artikel);
                            }

                            artikelAdapter.notifyDataSetChanged();
                        } catch (JSONException e){
                            e.printStackTrace();
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }

                        Log.d("NewsFragment", "onSuccess: " + response);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Log.d("NewsFragment", "onFailure: Pembacaan API gagal" + statusCode);
                    }
                });
        dialog.dismiss();
    }

}
