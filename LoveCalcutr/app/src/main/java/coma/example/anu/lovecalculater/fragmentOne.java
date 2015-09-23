package coma.example.anu.lovecalculater;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Anu on 9/10/2015.
 */
public class fragmentOne extends Fragment {
    TextView numberCount;
    int i = 0;
    int count;
    ImageView Imagev,circleAbove,circlebehind;
    Animation myAnimation;
    MediaPlayer player;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myAnimation = AnimationUtils.loadAnimation(this, R.anim.animation_result);
        //Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        counttimer(view);
    }

    public void counttimer(final View view) {
        count = randInt();
        numberCount = (TextView)view.findViewById(R.id.number);

        Imagev = (ImageView) view.findViewById(R.id.love_icon);
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int timeToBlink = 50;    //in milissegunds
                try{Thread.sleep(timeToBlink);}catch (Exception e) {}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        circleAbove.setVisibility(View.INVISIBLE);
                        if(i<count){
                            i++;
                            numberCount.setText("" + i + "%");
                            myAnimation.start();
                            counttimer(view);
                        }
                        else{
                            numberCount.setTextSize(45);
                            circleAbove.setVisibility(View.VISIBLE);
                            player.start();
                            blink(numberCount);
                        }
                    }
                });
            }
        }).start();
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
                        myAnimation.start();
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
    int randInt()
    {
        return 40;
    }
}

