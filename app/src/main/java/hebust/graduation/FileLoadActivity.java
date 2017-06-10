package hebust.graduation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

// 大文件下载测试
public class FileLoadActivity extends AppCompatActivity {


    private static final String LINK = "http://gdown.baidu.com/data/wisegame/91319a5a1dfae322/baidu_16785426.apk";
    public static final int THREAD_NUM = 5;
    public static final String FILE_NAME = "roer.apk";


    public static List<String> requestHeader = new ArrayList<>();
    public static List<String> responseHeader = new ArrayList<>();

    @BindView(R.id.activity_file_load)
    LinearLayout mLlContent;

    @BindView(R.id.btn_start)
    Button mBtnStart;


    public static void startActivity(Context context) {
        final Intent intent = new Intent(context, FileLoadActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_load);
        ButterKnife.bind(this);
        setTitle("FileDownLoadTest");
    }

    public void click_startLoadFile(View view) throws IOException {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), FILE_NAME);
        //noinspection ResultOfMethodCallIgnored
        file.delete();
        if (file.createNewFile()) {
            String path = file.getAbsoluteFile().toString();
            addToContent(String.format("Path: %s\n", path));
            DownLoadTask downLoadTask = new DownLoadTask(LINK, THREAD_NUM, path);
            downLoadTask.setOnCompleteCallBack(new DownLoadTask.OnCompleteCallBack() {
                @Override
                public void onComplete(String filePath, final boolean isError) {
                    App.getHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < requestHeader.size(); i++) {
                                addToContent("--------------\n");
                                addToContent(requestHeader.get(i));
                                addToContent(responseHeader.get(i));
                                mBtnStart.setText(isError ? "error" : "success");
                            }
                        }
                    });
                }
            });
            downLoadTask.start();
            Toast.makeText(this, "开始下载", Toast.LENGTH_LONG).show();
            view.setEnabled(false);
        }
    }

    private void addToContent(final String s) {
        if (TextUtils.isEmpty(s)) {
            return;
        }
        final TextView tv = getTextView(s);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLlContent.addView(tv, layoutParams);
    }


    private TextView getTextView(String s) {
        if (TextUtils.isEmpty(s)) {
            return null;
        }

        final TextView textView = new TextView(this);
        textView.setPadding(0, 10, 0, 0);
        textView.setTextSize(15);
        textView.setText(s);
        textView.setTextColor(getResources().getColor(android.R.color.black));
        return textView;
    }
}
