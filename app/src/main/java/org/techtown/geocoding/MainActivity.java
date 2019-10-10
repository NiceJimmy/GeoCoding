package org.techtown.geocoding;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final TextView tv = (TextView) findViewById(R.id.textView2);
        final TextView tv2 = (TextView) findViewById(R.id.textView4); // 결과창
        Button b1 = (Button)findViewById(R.id.button1);
        Button b2 = (Button)findViewById(R.id.button2);

        final EditText et1 = (EditText)findViewById(R.id.editText);
        final EditText et2 = (EditText)findViewById(R.id.editText2);
        final EditText et3 = (EditText)findViewById(R.id.editText3);

        final Geocoder geocoder = new Geocoder(this);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 위도,경도 입력 후 변환 버튼 클릭
                List<Address> list = null;
                try {
                    double d1 = Double.parseDouble(et1.getText().toString());
                    double d2 = Double.parseDouble(et2.getText().toString());

                    list = geocoder.getFromLocation(
                            d1, // 위도
                            d2, // 경도
                            10); // 얻어올 값의 개수

                    geocoder.getFromLocation(d1,d2,10);

                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("test", "입출력 오류 - 서버에서 주소변환시 에러발생");
                }
                if (list != null) {
                    if (list.size()==0) {
                        tv.setText("해당되는 주소 정보는 없습니다");
                    } else {
                        tv.setText(list.get(0).getAddressLine(0)
                        );
                    }
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Address> list = null;


                String str = et3.getText().toString();
                try {
                    list = geocoder.getFromLocationName(
                            str, // 지역 이름
                            10); // 읽을 개수
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("test","입출력 오류 - 서버에서 주소변환시 에러발생");
                }

                if (list != null) {
                    if (list.size() == 0) {
                        tv2.setText("해당되는 주소 정보는 없습니다");
                    } else {
                        tv2.setText(list.get(0).getLatitude()+" / "+list.get(0).getLongitude());

                    }
                }
            }
        });





    }
}
