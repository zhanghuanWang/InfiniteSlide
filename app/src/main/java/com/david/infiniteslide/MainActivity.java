package com.david.infiniteslide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goHorizontal(View view) {
        startActivity(new Intent(this,HorizontalActivity.class));
    }

    public void goVertical(View view) {
        startActivity(new Intent(this,VerticalActivity.class));
    }
}
