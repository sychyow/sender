package org.supremus.sych.sender;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PreviewActivity extends AppCompatActivity {

    private final static String MSG = "MSG";

    TextView tvPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        tvPreview = findViewById(R.id.tv_preview);
        String theMessage = this.getIntent().getExtras().getString(MSG);
        tvPreview.setText(theMessage);
    }

    public static void launch(Activity parent, String textMessage) {
        final Intent intent = new Intent(parent, PreviewActivity.class);
        intent.putExtra(MSG, textMessage);
        parent.startActivity(intent);
    }
}
