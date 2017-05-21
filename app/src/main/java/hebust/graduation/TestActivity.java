package hebust.graduation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class TestActivity extends AppCompatActivity {


    private static final String TAG = "tianrui";

    @BindView(R.id.id_tv_text)
    TextView mTvText;

    @BindView(R.id.id_iv_img)
    ImageView mIvImage;

    @BindView(R.id.id_gv_list)
    GridView mGvList;

    //http://blog.csdn.net/u010687392/article/details/46906657

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);

//        final StringRequest request = new StringRequest("http://120.24.167.150/static", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                mTvText.setText(response);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(RoerError error) {
//
//            }
//        });

        // perform actual network request.
//        Roer.getInstance().addRequest(request);
//
//        RoerConfiguration.RoerConfigurationBuilder builder = new RoerConfiguration.RoerConfigurationBuilder();
//        RoerConfiguration configuration = builder.networkPoolSize(1).httpStack(new OkHttpStack()).build();
//
//        Roer.getInstance().adjustConfiguration(configuration);
//
//        Roer.getInstance().bind("http://120.24.167.150/jpeg", mIvImage);

        List<String> l = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            l.add(String.valueOf(i));
        }
        mGvList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, l));
        mGvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "onItemClick: " + position);
                Toast.makeText(TestActivity.this, "onItemClick: " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @OnItemClick(R.id.id_gv_list)
    public void onItemClick(int position){
        Log.d(TAG, "onItemClick: " + position);
    }
}
