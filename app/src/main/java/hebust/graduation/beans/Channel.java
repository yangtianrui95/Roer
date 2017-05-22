package hebust.graduation.beans;

import java.util.List;

/**
 * Created by tianrui on 17-5-20.
 * <p>
 * channel of feed content, in mainActivity tabs.
 */
public class Channel extends BaseBean {

    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
