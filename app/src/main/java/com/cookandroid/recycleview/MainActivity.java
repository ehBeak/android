package com.cookandroid.recycleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MainData> arrayList;
    private MainAdapter mainAdapter;
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. 리사이클러 뷰에서 선언한 아이디 연동
        recyclerView = (RecyclerView)findViewById(R.id.rv);
        // 리니어레이아웃 매니저 생성
        linearLayoutManager = new LinearLayoutManager(this);
        // 2. 리사이클러 뷰에 레이아웃 매니저 설정
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        // 어댑터 생성
        mainAdapter = new MainAdapter(arrayList);
        // 3. 리사이클러 뷰에 어댑터 설정
        recyclerView.setAdapter(mainAdapter);

        // activity main에 추가라는 기능 구현
        Button btn_add = (Button)findViewById(R.id.btn_add);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainData mainData = new MainData(R.drawable.ic_launcher_background,
                        "홍드로이드","리사이클러뷰");

                arrayList.add(mainData);
                mainAdapter.notifyDataSetChanged();
            }
        });
    }
}