package hebust.graduation.data;

/**
 * Created by tianrui on 17-5-28.
 */
public interface MediaDataSource {

    public interface LoadMediaDataCallback {

        void onLoadTextSuccess(String text);

        void onLoadTextError();
    }


    void refreshData(LoadMediaDataCallback callback);

}
