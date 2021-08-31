package com.example.fyp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.Transaction;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class MoodActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DatabaseReference mRatingBarCh;
    RecyclerView recyclerView;
    private SmileRating mSmileRating;
    FloatingActionButton add;
    FirebaseAuth fAuth;

    CalendarView compactCalendar;
    Calendar c;
    String todaysDate;
    String currentTime;
  String cause;
    RadioButton work;
    private TextView tvDisplayChoice;
    String weekDay;
    String stringdate;
    Button submit;
    Chip periodChip;
    ChipGroup chg;
    String periods;
    String[] listItems;
    HashMap<String, Object> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        tvDisplayChoice = findViewById(R.id.tvDisplayChoice);

        Button btnSelectChoice = findViewById(R.id.btnSelectChoice);
    //    builder.setTitle("Select an Animal");
      //  mSmileRating = findViewById(R.id.ratingView);
        recyclerView = findViewById(R.id.recyclerView);
        //cause = findViewById(R.id.school);
    submit = findViewById(R.id.submitMood);
        fAuth = FirebaseAuth.getInstance();

        // SmileRating smileRating = (SmileRating) findViewById(R.id.ratingView);


        add = findViewById(R.id.fab);


        hashMap = new HashMap<>();


        tvDisplayChoice = findViewById(R.id.tvDisplayChoice);
        FirebaseUser rUser = fAuth.getCurrentUser();
        assert rUser != null;
        String userId = rUser.getUid();

        mRatingBarCh = FirebaseDatabase.getInstance().getReference("Mood").child(userId);
        String key = mRatingBarCh.push().getKey();
        mRatingBarCh = mRatingBarCh.child(key);


        btnSelectChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listItems = new String[]{"Happy", "Excited", "Stressed","Sad","Nervous"};
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MoodActivity.this);
                mBuilder.setTitle("Choose an item");
                mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseUser rUser = fAuth.getCurrentUser();
                        assert rUser != null;
                        String userId = rUser.getUid();
                        mRatingBarCh = FirebaseDatabase.getInstance().getReference("Mood").child(userId);

                        mRatingBarCh.child("feel").setValue(i);
                       // FirebaseDatabase.getInstance().getReference("Mood").child(userId);
                        //hashMap.put("cause",cause);

                        // tvDisplayChoice.setText(listItems.g);
                        dialogInterface.dismiss();
                    }
                });
                // Set the neutral/cancel button click listener
                mBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when click the neutral button
                        dialog.cancel();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();

            }
        });




               // DialogFragment singleChoiceDialog = new SingleChoiceDialogFragment();
                //singleChoiceDialog.setCancelable(false);
               // singleChoiceDialog.show(getSupportFragmentManager(), "Single Choice Dialog");




        //   mRatingBarCh = FirebaseDatabase.getInstance().getReference();
        //   mRatingBarCh = mRatingBarCh.child("Mood");
        // String rating = Integer.toString(level);
        //   mRatingBarCh = FirebaseDatabase.getInstance().getReference().child("Mood");
        //    mRatingBarCh.child("Mood").setValue(rating);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
        Date date = new Date();
        Date newDate = new Date(date.getTime());
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
        stringdate = dt.format(newDate);

       SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
        c = Calendar.getInstance();
        weekDay = dayFormat.format(c.getTime());
      //  todaysDate = c.get(Calendar.YEAR) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DAY_OF_MONTH);
        Log.d("DATE", "Date: " + todaysDate);
        currentTime = pad(c.get(Calendar.HOUR)) + ":" + pad(c.get(Calendar.MINUTE));
       // weekDay = c.get(Calendar.DAY_OF_WEEK);
        Log.d("TIME", "Time: " + currentTime);
        FirebaseUser rUser = fAuth.getCurrentUser();
        assert rUser != null;
        String userId = rUser.getUid();

     //    chg = findViewById(R.id.moodChipGroup);
       // int chipId = chg.getCheckedChipId();

//        cause = periodChip.getText().toString();
       // periodChip = findViewById(chg.getCheckedChipId());
      //  chg = (ChipGroup) findViewById(R.id.moodChipGroup);
       // int chipId = chg.getCheckedChipId();
       // Chip periodChip = (Chip) findViewById(chg.getCheckedChipId());
       // cause = periodChip.getText().toString();

        mRatingBarCh = FirebaseDatabase.getInstance().getReference("Mood").child(userId);
     //  String key = mRatingBarCh.push().getKey();
      //  mRatingBarCh = mRatingBarCh.child(key);

      //  ChipGroup chgy = (ChipGroup) findViewById(R.id.moodChipGroup);
        //   int chipIdd = chgy.getCheckedChipId();
       // Chip moodChips =  findViewById(chgy.getCheckedChipId());
        //cause = moodChips.getText().toString();
      //  Log.i(TAG, "cause" + cause);
     //   hashMap.put("date", stringdate);
        //hashMap.put("cause",cause);

        // hashMap.put("timestamp", ServerValue.TIMESTAMP);
     //   hashMap.put("date", weekDay);
       //hashMap.put("date", stringdate);
        hashMap.put("date", stringdate);
        ChipGroup chgy = (ChipGroup) findViewById(R.id.moodChipGroup);
        Chip moodChips =  findViewById(chgy.getCheckedChipId());
        cause = moodChips.getText().toString();
        hashMap.put("cause",cause);

       //hashMap.put("cause",cause);
       // submit.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
               // ChipGroup chgy = (ChipGroup) findViewById(R.id.moodChipGroup);
               // Chip moodChips =  findViewById(chgy.getCheckedChipId());
            //    cause = moodChips.getText().toString();
             //   hashMap.put("cause",cause);
          //      Log.i(TAG, "cause" + cause);

             //   public void onPositiveButtonClicked(String[] list, int position) {
                    //mRatingBarCh.child("feel").setValue(position);


             //   }



                //Log.i("nice", cause);
         //   }
    //    });

        mRatingBarCh.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {


                      Toast.makeText(MoodActivity.this, "ok!!",
                       Toast.LENGTH_SHORT).show();
                    //   startActivity(new Intent(Register.this, MainActivity.class));
                } else {

                    Toast.makeText(MoodActivity.this, "error!!",
                            Toast.LENGTH_SHORT).show();
                    //   progressBar.setVisibility(View.GONE);
                }
            }
        });
            }
        });

        //  mRatingBarCh = FirebaseDatabase.getInstance().getReference("Journal").child(userId);
        // HashMap<String, String> hashMap = new HashMap<>();
        // mRatingBarCh = FirebaseDatabase.getInstance().getReference().child("Mood");
        //  mRatingBarCh.child("Mood");






        /*Typeface typeface = Typeface.createFromAsset(getAssets(), "MetalMacabre.ttf");
        mSmileRating.setTypeface(typeface);*/
    }





    public void onRatingSelected(int level, boolean reselected) {
        Log.i(TAG, "Rated as: " + level + " - " + reselected);
        String rating = Integer.toString(level);
        //  mRatingBarCh.push().setValue(rating);
        // FirebaseUser rUser = fAuth.getCurrentUser();
        //   assert rUser != null;
        //   String userId = rUser.getUid();
        //  mRatingBarCh = FirebaseDatabase.getInstance().getReference("Mood").child(userId);
        //  mRatingBarCh = FirebaseDatabase.getInstance().getReference().child("Mood");
     //   mRatingBarCh.child("Feeling").setValue(rating);
        //  mRatingBarCh.child("Mood");
        //   DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        // mRatingBarCh = (DatabaseReference) mRatingBarCh.child("Users").orderByChild("uid").equalTo(userId);


    }

    private String pad(int time) {
        if (time < 10)
            return "0" + time;
        return String.valueOf(time);

    }






}
