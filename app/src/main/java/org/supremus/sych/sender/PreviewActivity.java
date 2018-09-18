package org.supremus.sych.sender;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
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

    private TextView tvPreview;
    private Intent mailIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        tvPreview = findViewById(R.id.tv_preview);
        String theMessage = Objects.requireNonNull(this.getIntent().getExtras()).getString(MSG);
        tvPreview.setText(theMessage);
        final Button btnSend = findViewById(R.id.btn_send);
        mailIntent = new Intent(Intent.ACTION_SENDTO);
        mailIntent.setType("text/plain");
        mailIntent.setData(Uri.parse(String.format("mailto:%s",getString(R.string.mail_recipient))));
        final ComponentName mailName = mailIntent.resolveActivity(getPackageManager());
        if (mailName==null) {
            btnSend.setText(R.string.mail_absent);
            btnSend.setEnabled(false);
            Toast.makeText(this, getString(R.string.no_email_msg), Toast.LENGTH_LONG).show();
        } else {
            btnSend.setOnClickListener(this);
        }
    }

    public static void launch(Activity parent, String textMessage) {
        if (intent==null) intent = new Intent(parent, PreviewActivity.class);
        intent.putExtra(MSG, textMessage);
        parent.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {getString(R.string.mail_recipient)});
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.mail_subject));
        mailIntent.putExtra(Intent.EXTRA_TEXT, tvPreview.getText());
        startActivity(mailIntent);
    }
}
