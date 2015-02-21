package jacmobile.com.weather.injection;

import android.content.Context;
import android.location.LocationManager;
import android.view.LayoutInflater;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jacmobile.com.weather.activities.WeatherActivity;

@Module(
        complete = false,
        library = true,
        injects = {
                WeatherActivity.class,
        }
)
public class AndroidAppModule {
    /* package */ static Context sApplicationContext = null;

    /**
     * Allow the application context to be injected but require that it be annotated with
     * {@link ForApplication @Annotation} to explicitly differentiate it from an activity context.
     */
    @Provides
    @Singleton
    @ForApplication Context provideApplicationContext() {
        return sApplicationContext;
    }

    @Provides
    @Singleton LocationManager provideLocationManager() {
        return (LocationManager) sApplicationContext.getSystemService(Context.LOCATION_SERVICE);
    }

    @Provides
    @Singleton LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(sApplicationContext);
    }

    @Provides
    @Singleton Bus provideBus() {
        return new Bus(ThreadEnforcer.ANY);
    }
}