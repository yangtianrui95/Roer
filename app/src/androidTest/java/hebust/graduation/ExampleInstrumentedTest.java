package hebust.graduation;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import ytr.roer.Request;
import ytr.roer.RequestQueue;
import ytr.roer.Response;
import ytr.roer.RoerError;
import ytr.roer.StringRequest;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("hebust.graduation", appContext.getPackageName());

        RequestQueue requestQueue = RequestQueue.newRequestQueue();
        requestQueue.add(new StringRequest("http://120.24.167.150/", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(RoerError error) {

            }
        }));
    }
}
