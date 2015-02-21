package jacmobile.com.weather.activities;

import android.app.Activity;
import android.os.Bundle;

import dagger.ObjectGraph;
import jacmobile.com.weather.injection.ActivityScopeModule;
import jacmobile.com.weather.injection.DaggerApplication;
import jacmobile.com.weather.injection.DaggerInjector;

public abstract class AActivity extends Activity implements DaggerInjector {
    private ObjectGraph mActivityGraph;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerApplication application = (DaggerApplication) getApplication();
        mActivityGraph = application.getObjectGraph().plus(geActivitytModules());
        mActivityGraph.inject(this);
    }

    @Override protected void onDestroy() {
        mActivityGraph = null;
        super.onDestroy();
    }

    @Override public void inject(Object object) {
        mActivityGraph.inject(object);
    }

    @Override public ObjectGraph getObjectGraph() {
        return mActivityGraph;
    }

    protected Object[] geActivitytModules() {
        return new Object[]{
                new ActivityScopeModule(this),
        };
    }
}