
//http://www.androidhive.info/2012/04/android-spinner-dropdown-example/
//http://www.mkyong.com/android/android-progress-bar-example/
package coma.example.anu.lovecalculater;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Build;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends Activity implements OnItemSelectedListener {

    Button btnStartProgress;
    //ProgressDialog progressBar;
    private static final int PROGRESS = 0x1;
    private ProgressDialog mProgress;
    private int progressBarStatus = 0;
    private long time_pass = 0;
    private Handler mHandler = new Handler();
    private LinearLayout LL;
    private String meter = "hello";
    Spinner spinner;
    String meterValue;
    EditText text1;
    EditText text2;
    int flag1 = 0;
    int flag2 = 0;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        tv = (TextView)findViewById(R.id.text1);
        text1 = (EditText)findViewById(R.id.first_person);
        text2 = (EditText)findViewById(R.id.second_person);
        spinner = (Spinner) findViewById(R.id.meter);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> meter_options = new ArrayList<String>();
        meter_options.add("Love Meter");
        meter_options.add("Hate Meter");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, meter_options);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
      //  onFocusChange();
    }


    /*private void onFocusChange(){
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        text1 = (EditText)findViewById(R.id.first_person);
                        text1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                            @TargetApi(Build.VERSION_CODES.GINGERBREAD)
                            @Override
                            public void onFocusChange(View v, boolean focus) {
                                if (focus) {

                                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                                    String firstPerson = text1.getText().toString();
                                    if (firstPerson.isEmpty() || (isAlphanumeric(firstPerson))){
                                        Drawable errorIcon = getResources().getDrawable(R.drawable.error_icon);
                                        text1.setError("Can't Leave Empty",errorIcon);

                                    }


                                }

                            }
                        });
                    }
                });
            }
        }).start();
    }*/
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        meterValue = spinner.getSelectedItem().toString();
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
        meterValue = "Love Meter";

    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public void sendbutton(View view) {
        String firstPerson = text1.getText().toString();
        String secondPerson = text2.getText().toString();
            if((firstPerson.isEmpty()) || (secondPerson.isEmpty()) || (isAlphanumeric(firstPerson)) || (isAlphanumeric(secondPerson))){
                Drawable errorIcon = getResources().getDrawable(R.drawable.error_icon);
                text1.setError("Can't Leave Empty",errorIcon);
                }
            else {
                Intent intent = new Intent(this, Result.class);
                // set the new task and clear flags
                intent.putExtra("meter_value_pass", meter);
                intent.putExtra("first_person_pass", firstPerson);
                intent.putExtra("second_person_pass", secondPerson);
                text1.setText("");
                text2.setText("");
                startActivity(intent);
                }
    }
    boolean isAlphanumeric(String abc){
    Pattern p = Pattern.compile("[^a-zA-Z]");
    boolean hasSpecialChar = p.matcher(abc).find();
    return hasSpecialChar;
    }
}
