package jacmobile.com.weather.injection;

import android.content.Context;

import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import jacmobile.com.weather.BuildConfig;

/**
 * Here it provides the dependencies those are used in the whole scope of your MyCoolApp
 */
@Module(
        complete = true,    // Here it enables object graph validation
        library = true,
        addsTo = AndroidAppModule.class, // Important for object graph validation at compile time
        injects = {
                DaggerApplication.class
        }
)
public class ApplicationScopeModule {
    @Provides Picasso providesPicasso(@ForApplication Context context) {
        Picasso picasso = Picasso.with(context);

        // some app-wide common settings
        picasso.setDebugging(BuildConfig.DEBUG);

        return picasso;
    }
}
