package com.bien.easyorder;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MediaPlayer mediaPlayer;
    MediaController mediaController;
    VideoView video;
    Button sound_button;
    Button b1;
    Button b3;
    Boolean ok = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        VideoView video = findViewById(R.id.video_view);
        String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.vid_main;
        Uri uri = Uri.parse(videoPath);
        video.setVideoURI(uri);

        mediaController = new MediaController(this);
        video.setMediaController(mediaController);
        video.start();
        mediaController.setAnchorView(video);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.mainsong);

        sound_button = (Button)findViewById(R.id.button2);
        sound_button.setOnClickListener(imgButtonHandler);


        b1 = (Button)findViewById(R.id.button1);
        b3 = (Button)findViewById(R.id.button);
        b1.setOnClickListener(this);
        b3.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        mediaPlayer.stop();
        Intent intent1;
        Intent intent2;
        mediaPlayer.release();

        switch (view.getId()){
            case R.id.button1:
                intent1 = new Intent(this, DisplayMessageActivity.class);
                startActivity(intent1);
                break;
            case R.id.button:
                intent2 = new Intent(this, MainActivity3.class);
                startActivity(intent2);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }

    //butonul de mute/unmute
    View.OnClickListener imgButtonHandler = new View.OnClickListener() {

        public void onClick(View v) {
            if (ok == false) {  //ok = false
                ok = true;
                sound_button.setBackgroundResource(R.drawable.mute);
                mediaPlayer.stop();
                mediaPlayer.release();
            }
            else                //ok = true
            {
                ok = false;
                sound_button.setBackgroundResource(R.drawable.unmute);
                mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.mainsong);
                mediaPlayer.start();
            }
        }
    };


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mediaPlayer.stop();
        mediaPlayer.release();
        finish();
        this.finishAffinity();
    }

    public boolean isApplicationSentToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                video.start();
                mediaController.setAnchorView(video);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onPause() {
        if (isApplicationSentToBackground(this)) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        super.onPause();
    }

}