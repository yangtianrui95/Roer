package hebust.graduation.beans;

import java.util.Collections;
import java.util.List;

public class Feed extends BaseBean {

    private List<FeedItem> data;

    public List<FeedItem> getData() {
        if (data == null) {
            data = Collections.emptyList();
        }
        return data;
    }

    public void setData(List<FeedItem> data) {
        this.data = data;
    }

    public static class FeedItem {
        private String id;
        private String source;
        private String pic;
        private String title;
        private String content;

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
    }

    @Override
    public String toString() {
        return "Feed{" +
                "data=" + data +
                '}';
    }
}
