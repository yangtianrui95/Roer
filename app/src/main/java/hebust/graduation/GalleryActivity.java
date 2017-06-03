package hebust.graduation;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hebust.graduation.widget.GalleryItemView;
import hebust.graduation.widget.GalleryView;

public class GalleryActivity extends AppCompatActivity {

    @BindView(R.id.id_rv_gallery)
    RecyclerView mRvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);
        setTitle("ImageLoaderTest");
        mRvList.setAdapter(new GalleryAdapter(Arrays.asList(Constants.IMAGE_URLS)));

        mRvList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.top = 5;
                outRect.bottom = 5;
                outRect.left = 5;
                outRect.right = 5;
            }
        });

    }


    public static void startActivity(Context context) {
        final Intent intent = new Intent(context, GalleryActivity.class);
        context.startActivity(intent);
    }


    static class GalleryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        private List<String> mList;


        GalleryAdapter(List<String> list) {
            mList = list;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new CommonHolder(new GalleryItemView(parent.getContext()));
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder.itemView instanceof GalleryView) {
                ((GalleryView) holder.itemView).bind(mList.get(position));
            }
        }

        @Override
        public int getItemCount() {
            if (mList == null) {
                return 0;
            }
            return mList.size();
        }

        private static class CommonHolder extends RecyclerView.ViewHolder {

            public CommonHolder(View itemView) {
                super(itemView);
            }
        }
    }
}
