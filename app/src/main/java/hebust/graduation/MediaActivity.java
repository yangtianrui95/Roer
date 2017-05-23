package hebust.graduation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaExtractor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class MediaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media);
    }

    public static void startActivity(Activity context) {
        context.overridePendingTransition(0, 0);
        context.startActivity(new Intent(context, MediaActivity.class));
    }
}
