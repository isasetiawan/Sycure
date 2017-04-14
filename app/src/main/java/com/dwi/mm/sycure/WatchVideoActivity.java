package com.dwi.mm.sycure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WatchVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    YouTubePlayerView playerView;
    String idvideo;

    @BindView(R.id.title)
    TextView judul;
    @BindView(R.id.artist)
    TextView artist;

    String author;
    String jdlu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_video);
        ButterKnife.bind(this);

        idvideo = getIntent().getStringExtra("idvideo");
        playerView = (YouTubePlayerView) findViewById(R.id.player);
        playerView.initialize("AIzaSyCevA2g05EA0yyMCHIzq3hCs9lEFfegtxk",this);

        jdlu = getIntent().getStringExtra("judul");
        author = getIntent().getStringExtra("artist");
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo(idvideo);
            artist.setText(author);
            judul.setText(jdlu);

        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError()){
            youTubeInitializationResult.getErrorDialog(this,1).show();
        } else {
            String error = String.format("Error karena", youTubeInitializationResult.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1){
            getProvider().initialize("AIzaSyCevA2g05EA0yyMCHIzq3hCs9lEFfegtxk",this);
        }
    }

    protected YouTubePlayer.Provider getProvider(){
        return playerView;
    }
}
