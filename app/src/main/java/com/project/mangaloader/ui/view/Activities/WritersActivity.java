package com.project.mangaloader.ui.view.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;
import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;
import com.project.mangaloader.R;
import com.project.mangaloader.source.writer.WritersSource;

public class WritersActivity extends AppCompatActivity {
RecyclerView recyclerView;
CircleProgressBar circleProgressBar;
    private ImageButton back;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writers);
        back=findViewById(R.id.backWriter);
        title=findViewById(R.id.titleofPage);
        circleProgressBar=findViewById(R.id.writerprogress);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("مترجم ها");

        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        flexboxLayoutManager.setJustifyContent(JustifyContent.SPACE_AROUND);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        recyclerView = findViewById(R.id.writer_Rec);
        recyclerView.setLayoutManager(flexboxLayoutManager);

        WritersSource writersSource=new WritersSource(recyclerView,circleProgressBar,this);
        writersSource.execute();
    }
}