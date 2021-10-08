package com.project.mangaloader.ui.view.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
import com.project.mangaloader.source.styles.StyleSource;

public class StyleActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CircleProgressBar progressBar;
    private ImageButton back;
    private TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);
        progressBar = findViewById(R.id.styleprogressBar);
        back=findViewById(R.id.backstyle);
        title=findViewById(R.id.titleofPage);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title.setText("سبک ها");
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(this);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        flexboxLayoutManager.setJustifyContent(JustifyContent.SPACE_AROUND);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        recyclerView = findViewById(R.id.style_Rec);
        recyclerView.setLayoutManager(flexboxLayoutManager);
        StyleSource styleSource = new StyleSource(this, recyclerView, progressBar);
        styleSource.execute();
    }
}