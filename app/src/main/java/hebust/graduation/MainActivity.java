package hebust.graduation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ytr.roer.OkHttpStack;
import ytr.roer.Roer;
import ytr.roer.RoerConfiguration;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.id_tv_text)
    TextView mTvText;

    @BindView(R.id.id_iv_img)
    ImageView mIvImage;

    //http://blog.csdn.net/u010687392/article/details/46906657

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        RoerConfiguration.RoerConfigurationBuilder builder = new RoerConfiguration.RoerConfigurationBuilder();
        RoerConfiguration configuration = builder.networkPoolSize(1).httpStack(new OkHttpStack()).build();

        Roer.getInstance().adjustConfiguration(configuration);

        Roer.getInstance().bind("http://120.24.167.150/jpeg", mIvImage);
    }
}
