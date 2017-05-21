package hebust.graduation;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by tianrui on 17-5-19.
 */
public class MyGradView extends GridView {

    public MyGradView(Context context) {
        super(context);
    }

    public MyGradView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyGradView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
