package coma.example.anu.lovecalculater;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;

public class Result extends Activity {

    TextView myText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        TextView setfirstPerson = (TextView)findViewById(R.id.first_person);
        TextView setsecondPerson = (TextView)findViewById(R.id.second_person);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
            if(b!=null)
            {
                String meterValue = b.getString("meter_value_pass");
                String firstPerson = b.getString("first_person_pass");
                String secondPerson = b.getString("second_person_pass");
                setfirstPerson.setText(firstPerson);
                setsecondPerson.setText(secondPerson);
            }
    }
    private void blink(final TextView txt){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeToBlink = 500;    //in milissegunds
                try{Thread.sleep(timeToBlink);}catch (Exception e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                     //   myAnimation.start();
                        if(txt.getVisibility() == View.VISIBLE){
                            txt.setVisibility(View.INVISIBLE);
                        }else{
                            txt.setVisibility(View.VISIBLE);
                        }
                        blink(txt);
                    }
                });
            }
        }).start();
    }
    private void blink(final ImageView txt) {
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeToBlink = 1000;    //in milissegunds
                try {
                    Thread.sleep(timeToBlink);
                } catch (Exception e) {
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        txt.setVisibility(View.VISIBLE);
                        blink(txt);
                    }
                });
            }
        }).start();
    }
    public void calculate(View view){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentOne hello = new fragmentOne();
        fragmentTransaction.add(R.id.fragment_container, hello, "HELLO");
        fragmentTransaction.commit();
    }

}


