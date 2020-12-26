package com.bien.easyorder;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    String[] urls = {
            //"https://youtu.be/ECcbO41yBvs",
            "http://l-aurita.com/CookRo.html",
            //"https://youtu.be/tAXTgL5KJuI",
            "http://l-aurita.com/CookRo.html",
            //"https://youtu.be/kY-d4rRPcUk",
            "http://l-aurita.com/CookRo.html",
            "https://youtu.be/r1CLGoxoSJY",
            "https://youtu.be/IoPP1AOM9FI",
            "https://youtu.be/DHQUX2Dpxeg",
            "https://youtu.be/Ac7RbVa7JdU",
            "https://youtu.be/jbPR2SJuFHg",
            "https://youtu.be/B2cZCl-Um_I",
            "https://youtu.be/4IvISVSNEEk",
            "https://youtu.be/i38uPxXQxA0",
            "https://youtu.be/e2n1AivVBRU",
            "https://youtu.be/kMkHKMfs3RA",
            "https://youtu.be/YzrXpyK1fZc",
            "https://youtu.be/3vUtRRZG0xY",
            "https://youtu.be/vzKuobmi3iI",
            "https://youtu.be/R881mYa5xHg",
            "https://youtu.be/mSMdadgE48Y",
            "https://youtu.be/lf_JTD2PHcc",
            "https://youtu.be/l2tve3AZNqU",
            "https://youtu.be/nYvm8e9Sd04",
            "https://youtu.be/sod4I37nOoA",
            "https://youtu.be/1Gztp8JowpQ",
            "https://youtu.be/UALUSn9rHYc",
            "https://youtu.be/h_6CreFslBE",
            "https://youtu.be/_TtrZNq2Xqc"
    };

    private List<ItemModel> photoList() {
        List<ItemModel> items = new ArrayList<>();
        items.add(new ItemModel(R.drawable.p0, "D1"));
        items.add(new ItemModel(R.drawable.p1, "D2"));
        items.add(new ItemModel(R.drawable.p2, "D3"));
        items.add(new ItemModel(R.drawable.p3, "D4"));
        items.add(new ItemModel(R.drawable.p4, "D5"));
        items.add(new ItemModel(R.drawable.p5, "D6"));
        items.add(new ItemModel(R.drawable.p6, "D7"));
        items.add(new ItemModel(R.drawable.p7, "D8"));
        items.add(new ItemModel(R.drawable.p8, "D9"));
        items.add(new ItemModel(R.drawable.p9, "10"));
        items.add(new ItemModel(R.drawable.p10, "11"));
        items.add(new ItemModel(R.drawable.p11, "12"));
        items.add(new ItemModel(R.drawable.p12, "13"));
        items.add(new ItemModel(R.drawable.p13, "14"));
        items.add(new ItemModel(R.drawable.p14, "15"));
        items.add(new ItemModel(R.drawable.p15, "16"));
        items.add(new ItemModel(R.drawable.p16, "17"));
        items.add(new ItemModel(R.drawable.p17, "18"));
        items.add(new ItemModel(R.drawable.p18, "19"));
        items.add(new ItemModel(R.drawable.p19, "20"));
        items.add(new ItemModel(R.drawable.p20, "21"));
        items.add(new ItemModel(R.drawable.p21, "22"));
        items.add(new ItemModel(R.drawable.p22, "23"));
        items.add(new ItemModel(R.drawable.p23, "24"));
        items.add(new ItemModel(R.drawable.p24, "25"));
        items.add(new ItemModel(R.drawable.p25, "26"));
        return items;
    }

    String back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Bundle bundle = getIntent().getExtras();

        ArrayList<String> rez = bundle.getStringArrayList("value");
        back = bundle.getString("nume");

        LinearLayout gallery = findViewById(R.id.gallery);
        LayoutInflater inflater = LayoutInflater.from(this);

        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.finalsong2);
        mediaPlayer.start();

        List<ItemModel> items = photoList();

        if (rez.size() == 0) {
            View view = inflater.inflate(R.layout.item, gallery, false);
            ImageView img = view.findViewById(R.id.imageView2);
            img.setImageResource(R.drawable.no_img);
            gallery.addView(view);
        }
        else
        {
            if (rez.size() == 1){

                final TypeWriter tw = (TypeWriter) findViewById(R.id.tv2);
                tw.setText("");
                tw.setCharacterDelay(150);
                tw.animateText("Voilà!");
            }
            else
            if (rez.size() > 1){
                final TypeWriter tw = (TypeWriter) findViewById(R.id.tv3);
                tw.setText("");
                tw.setCharacterDelay(20);
                tw.animateText("Scroll pentru mai multe!");
            }

            for (int i = 0; i < rez.size(); i++) {
                String msg = rez.get(i);

                View view = inflater.inflate(R.layout.item, gallery, false);

                ImageView img = view.findViewById(R.id.imageView2);

                String before_last = msg.substring(msg.length() - 2);           //are 2 caractere
                String last = before_last.substring(before_last.length() - 1);  //ar tb sa fie 0..9
                String first = before_last.substring(before_last.length() - 2, before_last.length() - 1);  //ar tb sa fie D sau 1

                int poz = 0;

                if (msg.length() == 2)      //daca e intre D1..D9
                    poz = Integer.parseInt(last);
                else
                    poz = Integer.parseInt(before_last);

                img.setImageResource(items.get(poz - 1).getImage());
                final int numar = poz - 1;

                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent openLinksIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urls[numar]));
                        startActivity(openLinksIntent);
                    }
                });

                gallery.addView(view);
            }

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        mediaPlayer.release();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean isApplicationSentToBackground(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
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

    public void onBtnClicked(View v)
    {
        Intent intent;
        if (back.equals("DisplayMessageActivity"))
            intent = new Intent(this, DisplayMessageActivity.class);
        else
            intent = new Intent(this, MainActivity3.class);

        startActivity(intent);
    }

}