package jacmobile.com.weather.injection;

import android.app.Activity;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jacmobile.com.weather.activities.AActivity;
import jacmobile.com.weather.activities.WeatherActivity;

/**
 * Here it provides the dependencies those have same lifetime of one activity in your MyCoolApp
 */
@Module(
        complete = true,    // Here we enable object graph validation
        library = true,
        addsTo = ApplicationScopeModule.class, // Important for object graph validation at compile time
        injects = {
                WeatherActivity.class,
        }
)
public class ActivityScopeModule {

    private final AActivity mActivity;

    public ActivityScopeModule(AActivity activity) {
        mActivity = activity;
    }

    @Provides
    @Singleton
    @ForActivity Context providesActivityContext() {
        return mActivity;
    }

    @Provides
    @Singleton Activity providesActivity() {
        return mActivity;
    }
}
