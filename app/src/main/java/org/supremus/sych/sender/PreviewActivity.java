package org.supremus.sych.sender;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class PreviewActivity extends AppCompatActivity implements View.OnClickListener{

    private final static String MSG = "MSG";
    private static Intent intent = null;
    private MailSender sender = null;

    private TextView tvPreview;

    public static void launch(Activity parent, String textMessage) {
        if (intent==null) intent = new Intent(parent, PreviewActivity.class);
        intent.putExtra(MSG, textMessage);
        parent.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        tvPreview = findViewById(R.id.tv_preview);
        String theMessage = Objects.requireNonNull(this.getIntent().getExtras()).getString(MSG);
        tvPreview.setText(theMessage);
        final Button btnSend = findViewById(R.id.btn_send);

        sender = new MailSender(this);
        if (sender.hasActivity()) {
            btnSend.setOnClickListener(this);
        } else {
            btnSend.setText(R.string.mail_absent);
            btnSend.setEnabled(false);
            Toast.makeText(this, getString(R.string.no_email_msg), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        Intent mailIntent = sender.getSendIntent(tvPreview.getText().toString());
        startActivity(mailIntent);
    }
}
