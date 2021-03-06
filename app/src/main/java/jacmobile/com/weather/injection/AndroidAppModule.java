package jacmobile.com.weather.injection;

import android.content.Context;
import android.location.LocationManager;
import android.view.LayoutInflater;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import jacmobile.com.weather.activities.WeatherActivity;
import jacmobile.com.weather.network.NetworkProvider;
import jacmobile.com.weather.network.NetworkService;

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

    @Provides
    @Singleton RequestQueue provideRequestQueue() {
        return Volley.newRequestQueue(sApplicationContext);
    }

    @Provides
    @Singleton NetworkService provideNetworkService(RequestQueue requestQueue, Bus bus, Gson gson) {
        return new NetworkService(new NetworkProvider(requestQueue), bus, gson);
    }


    @Provides
    @Singleton Gson providesGson() {
        GsonBuilder builder = new GsonBuilder();

        //Deserializers
//        builder.registerTypeAdapter(LoginData.class, new LoginDataDeserializer());

        //Serializers
//        builder.registerTypeAdapter(Shipping.class, new ShippingSerializer());

        return builder.create();
    }
}