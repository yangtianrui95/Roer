package hebust.graduation.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Collections;
import java.util.List;

public class Feed extends BaseBean implements Parcelable {

    private List<FeedItem> data;


    public Feed() {
    }

    protected Feed(Parcel in) {
        //noinspection unchecked
        data = in.readArrayList(FeedItem.class.getClassLoader());
    }

    public static final Creator<Feed> CREATOR = new Creator<Feed>() {
        @Override
        public Feed createFromParcel(Parcel in) {
            return new Feed(in);
        }

        @Override
        public Feed[] newArray(int size) {
            return new Feed[size];
        }
    };

    public List<FeedItem> getData() {
        if (data == null) {
            data = Collections.emptyList();
        }
        return data;
    }

    public void setData(List<FeedItem> data) {
        this.data = data;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
    }

    public static class FeedItem implements Parcelable {
        private String id;
        private String source;
        private String pic;
        private String title;
        private String content;

        public FeedItem() {
        }

        FeedItem(Parcel in) {
            id = in.readString();
            source = in.readString();
            pic = in.readString();
            title = in.readString();
            content = in.readString();
        }

        public static final Creator<FeedItem> CREATOR = new Creator<FeedItem>() {
            @Override
            public FeedItem createFromParcel(Parcel in) {
                return new FeedItem(in);
            }

            @Override
            public FeedItem[] newArray(int size) {
                return new FeedItem[size];
            }
        };

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "FeedItem{" +
                    "id='" + id + '\'' +
                    ", source='" + source + '\'' +
                    ", pic='" + pic + '\'' +
                    ", title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(id);
            dest.writeString(source);
            dest.writeString(pic);
            dest.writeString(title);
            dest.writeString(content);
        }
    }

    @Override
    public String toString() {
        return "Feed{" +
                "data=" + data +
                '}';
    }
}
