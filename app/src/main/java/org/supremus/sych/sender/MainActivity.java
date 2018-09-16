package org.supremus.sych.sender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnPreview = findViewById(R.id.btn_preview);
        btnPreview.setOnClickListener(this);
        etMsg = findViewById(R.id.msg_text);
    }

    @Override
    public void onClick(View v) {
        String theMessage = etMsg.getText().toString();
        PreviewActivity.launch(this, theMessage);
    }
}
