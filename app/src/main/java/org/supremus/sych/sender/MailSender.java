package org.supremus.sych.sender;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

public class MailSender {
    private Intent mailIntent;
    private ComponentName mailName = null;
    private Context context;

    MailSender(AppCompatActivity parent) {
        context = parent;
        mailIntent = new Intent(Intent.ACTION_SENDTO);
        mailIntent.setType("text/plain");
        mailIntent.setData(Uri.parse(String.format("mailto:%s", parent.getString(R.string.mail_recipient))));
        mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {parent.getString(R.string.mail_recipient)});
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, parent.getString(R.string.mail_subject));
        mailName = mailIntent.resolveActivity(parent.getPackageManager());
    }

    public boolean hasActivity() {
        return mailName != null;
    }

    public void send(String text) {
        mailIntent.putExtra(Intent.EXTRA_TEXT, text);
        context.startActivity(mailIntent);
    }
}