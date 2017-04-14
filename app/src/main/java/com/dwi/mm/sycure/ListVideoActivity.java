package com.dwi.mm.sycure;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dwi.mm.sycure.Pembantu.YoutubeHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListVideoActivity extends AppCompatActivity {

    String[] vidurls = {
            "hYZmK46--Mc",
            "e4yipKfO8nA",
            "weIwrDTU2kw",
            "9PxE3Fy68Ao",
            "hJbRpHZr_d0",
            "tf77HZVDI80",
            "2ymyRJwFtV8",
            "NFOuMSejnO4",
            "hf4lYSg1XpI",
            "yQLCWiekZV0",
            "WChIPWn9TJE",
            "ab-j4-KzPcg",
            "6p_yaNFSYao",
            "01QDnS2Tp_E",
            "7CTsdbf81W8",
            "--V2GuvBHOU",
            "6lArtWtxPUw",
    };

    @BindView(R.id.listvideo)
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_video);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new VideoAdapter());
    }

     class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder>{
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,parent,false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            final ViewHolder holderr = holder;
            YoutubeHelper penolong = new YoutubeHelper(new YoutubeHelper.After() {
                @Override
                public void lakukan(String data) throws JSONException {
                    final JSONObject dat = new JSONObject(data);
                    final String a = dat.getString("title");
                    final String b = dat.getString("author_name");
                    final String c = dat.getString("thumbnail_url");
                    ListVideoActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            holderr.jdul.setText(a);
                            holderr.desc.setText(b);
                        }
                    });
                    OkHttpClient ok = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(c)
                            .get()
                            .build();
                    ok.newCall(request).enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            final Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
                            ListVideoActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    holderr.video.setImageBitmap(bmp);
                                    holderr.video.setScaleType(ImageView.ScaleType.CENTER_CROP);
                                    holderr.video.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(ListVideoActivity.this,WatchVideoActivity.class);
                                            intent.putExtra("idvideo",vidurls[position]);
                                            intent.putExtra("judul",a);
                                            intent.putExtra("artist",b);
                                            startActivity(intent);
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            });

            penolong.loadVideoDetails("https://www.youtube.com/watch?v="+vidurls[position]);
        }

        @Override
        public int getItemCount() {
            return vidurls.length;
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            @BindView(R.id.orang)
            TextView jdul;
            @BindView(R.id.pesan)
            TextView desc;
            @BindView(R.id.video)
            ImageView video;


            ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
}
