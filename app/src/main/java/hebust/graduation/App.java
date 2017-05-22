package hebust.graduation;

import android.app.Application;
import android.content.Context;

import ytr.roer.OkHttpStack;
import ytr.roer.Roer;
import ytr.roer.RoerConfiguration;

public class App extends Application {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initRoer();
    }

    private void initRoer() {
        final RoerConfiguration.RoerConfigurationBuilder builder = new RoerConfiguration.RoerConfigurationBuilder();
        final RoerConfiguration configuration = builder.httpStack(new OkHttpStack()).build();
        Roer.getInstance().adjustConfiguration(configuration);
    }
}
