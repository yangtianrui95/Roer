package hebust.graduation.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import hebust.graduation.beans.Feed;
import hebust.graduation.widget.FeedItemView;

public class FeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private interface ViewType {
        int NORMAL = 0x01;
    }

    private List<Feed.FeedItem> mFeedItems;

    public FeedAdapter(@NonNull List<Feed.FeedItem> feedItems) {
        mFeedItems = feedItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ViewType.NORMAL:
            default:
                return new CommonViewHolder(new FeedItemView(parent.getContext()));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.itemView instanceof FeedView) {
            ((FeedView) holder.itemView).onBind(mFeedItems.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mFeedItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return ViewType.NORMAL;
    }

    private class CommonViewHolder extends RecyclerView.ViewHolder {

        CommonViewHolder(View itemView) {
            super(itemView);
        }

    }
}
