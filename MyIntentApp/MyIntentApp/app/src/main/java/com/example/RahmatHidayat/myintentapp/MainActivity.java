package com.example.RahmatHidayat.myintentapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnPindahActivity;
    private Button btnPindahDataActivity;
    private Button btnDialNumber;
    private Button btnPindahActivityObject;
    private Button btnPindahUntukResult;
    private TextView tvResult;
    private int REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPindahActivity = (Button) findViewById(R.id.btn_pindah_activity);
        btnPindahActivity.setOnClickListener(this);

        btnPindahDataActivity = (Button) findViewById(R.id.btn_pindah_dengan_data);
        btnPindahDataActivity.setOnClickListener(this);

        btnDialNumber = (Button) findViewById(R.id.btn_dial_number);
        btnDialNumber.setOnClickListener(this);

        btnPindahActivityObject=(Button) findViewById(R.id.btn_move_activity_object);
        btnPindahActivityObject.setOnClickListener(this);

        btnPindahUntukResult=(Button) findViewById(R.id.btn_move_for_result);
        btnPindahUntukResult.setOnClickListener(this);

        tvResult=(TextView) findViewById(R.id.tv_result);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE){
            if(resultCode==MoveForResultActivity.RESULT_CODE){
                int selectedValue= data.getIntExtra(MoveForResultActivity.EXTRA_SELECTED_VALUE,0);
                tvResult.setText("Hasil : "+selectedValue);
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_pindah_activity:
                Intent moveIntent = new Intent(MainActivity.this, MoveActivity.class);
                startActivity(moveIntent);
                break;

            case R.id.btn_pindah_dengan_data:
                Intent moveDataIntent = new Intent(MainActivity.this, MoveWithDataActivity.class);
                moveDataIntent.putExtra(MoveWithDataActivity.EXTRA_NAME, "DicodingAcademy Boy");
                moveDataIntent.putExtra(MoveWithDataActivity.EXTRA_AGE, 5);
                startActivity(moveDataIntent);
                break;

            case R.id.btn_dial_number:
                String phoneNumber = "081210841382";
                Intent dialNumberIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + phoneNumber));
                startActivity(dialNumberIntent);
                break;

            case R.id.btn_move_activity_object:
                Person mPerson=new Person();
                mPerson.setName("DicodingAcademy");
                mPerson.setAge(5);
                mPerson.setEmail("academy@dicoding.com");
                mPerson.setCity("Bandung");

                Intent moveWithObjectIntent= new Intent(MainActivity.this,MoveWithDataActivity.class);
                moveWithObjectIntent.putExtra(MoveWithObjectActivity.EXTRA_PERSON,mPerson);
                startActivity(moveWithObjectIntent);
                break;

            case R.id.btn_move_for_result:
                Intent moveForResultIntent=new Intent(MainActivity.this,MoveForResultActivity.class);
                startActivityForResult(moveForResultIntent,REQUEST_CODE);
        }


    }
}
