package hebust.graduation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import ytr.roer.RequestQueue;
import ytr.roer.Response;
import ytr.roer.RoerError;
import ytr.roer.StringRequest;

public class MainActivity extends AppCompatActivity {

    private TextView mTvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvText = (TextView) findViewById(R.id.id_tv_text);

        RequestQueue requestQueue = RequestQueue.newRequestQueue();
        requestQueue.add(new StringRequest("http://120.24.167.150/string", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mTvText.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(RoerError error) {

            }
        }));
    }
}
