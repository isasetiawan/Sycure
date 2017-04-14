package com.dwi.mm.sycure.Pembantu;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by azaqo on 4/13/2017.
 */


public class YoutubeHelper {

    public YoutubeHelper(After after) {
        this.after = after;
    }

    private OkHttpClient okHttpClient = new OkHttpClient();
    private After after;
    public void loadVideoDetails(String url){
        Request request = new Request.Builder()
                .url("https://www.youtube.com/oembed?url="+url+"&format=json")
                .get()
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    after.lakukan(response.body().string());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public interface After{
        public void lakukan(String data) throws JSONException;
    }

}
