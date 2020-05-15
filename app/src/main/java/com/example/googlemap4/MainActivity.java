package com.example.googlemap4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.enableDefaults();

        TextView status1 = (TextView)findViewById(R.id.result); //파싱된 결과확인!

        boolean dutyAddr = false, dutyName = false, dutyMapimg = false, dutyTel1 = false, wgs84Lat = false;
        boolean wgs84Lon = false, inStatUpdateDatetime = false;
        boolean initem = false;

        String dutyaddr = null, dutyname = null, dutymapimg = null, dutytel1 = null, wgs84lat = null, wgs84lon = null, statUpdateDatetime = null;


        try{
            URL url = new URL("http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown?"
                    + "&pageNo=1&numOfRows=50&ServiceKey="
                    + "F77yKbNru09hHXTILpM4deFOvEm1VcftAogISo4Z4umzbU%2BXKvdGYdS48vRrzecwHdBtJA23BfxP8sPgDfRplg%3D%3D "
            ); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("dutyAddr")){ //title 만나면 내용을 받을수 있게 하자
                            dutyAddr = true;
                        }
                        if(parser.getName().equals("dutyName")){ //address 만나면 내용을 받을수 있게 하자
                            dutyName = true;
                        }
                        if(parser.getName().equals("dutyMapimg")){ //mapx 만나면 내용을 받을수 있게 하자
                            dutyMapimg = true;
                        }
                        if(parser.getName().equals("dutyTel1")){ //mapy 만나면 내용을 받을수 있게 하자
                            dutyTel1 = true;
                        }
                        if(parser.getName().equals("wgs84Lat")){ //mapy 만나면 내용을 받을수 있게 하자
                            wgs84Lat = true;
                        }
                        if(parser.getName().equals("wgs84Lon")){ //mapy 만나면 내용을 받을수 있게 하자
                            wgs84Lon = true;
                        }

                        if(parser.getName().equals("statUpdateDatetime")){ //mapy 만나면 내용을 받을수 있게 하자
                            inStatUpdateDatetime = true;
                        }
                        if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                            status1.setText(status1.getText()+"에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(dutyAddr){ //isTitle이 true일 때 태그의 내용을 저장.
                            dutyaddr = parser.getText();
                            dutyAddr = false;
                        }
                        if(dutyMapimg){ //isAddress이 true일 때 태그의 내용을 저장.
                            dutymapimg = parser.getText();
                            dutyMapimg = false;
                        }
                        if(dutyName){ //isMapx이 true일 때 태그의 내용을 저장.
                            dutyname = parser.getText();
                            dutyName = false;
                        }
                        if(dutyTel1){ //isMapy이 true일 때 태그의 내용을 저장.
                            dutytel1 = parser.getText();
                            dutyTel1 = false;
                        }
                        if(wgs84Lat){ //isMapy이 true일 때 태그의 내용을 저장.
                            wgs84lat = parser.getText();
                            wgs84Lat = false;
                        }
                        if(wgs84Lon){ //isMapy이 true일 때 태그의 내용을 저장.
                            wgs84lon = parser.getText();
                            wgs84Lon = false;
                        }

                        if(inStatUpdateDatetime){ //isMapy이 true일 때 태그의 내용을 저장.
                            statUpdateDatetime = parser.getText();
                            inStatUpdateDatetime = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            status1.setText(status1.getText()+"주소 : "+ dutyaddr +"\n 동 : " + dutymapimg
                                    +"\n 약국이름 : " + dutyname +  "\n 약국전화번호 : " + dutytel1+ "\n latitude : " + wgs84lat
                                    +"\n longitude : " +wgs84lon + "충전기상태갱신시각 : " +statUpdateDatetime+"\n");
                            initem = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch(Exception e){
            status1.setText("에러가..났습니다...");
        }

    }
}
