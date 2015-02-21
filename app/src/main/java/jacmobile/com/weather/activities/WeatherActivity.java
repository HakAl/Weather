package jacmobile.com.weather.activities;

import android.os.Bundle;
import android.util.Log;

import com.google.common.eventbus.Subscribe;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import jacmobile.com.weather.eventbus.events.AEvent;
import jacmobile.com.weather.eventbus.events.NetworkError;
import jacmobile.com.weather.eventbus.events.NetworkEvent;

public class WeatherActivity extends AActivity {
    @Inject Bus bus;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
        }
    }

    @Override protected void onStart() {
        super.onStart();
        this.bus.register(this);
    }

    @Override protected void onStop() {
        super.onStop();
        this.bus.unregister(this);
    }

    @Subscribe public void busEvent(AEvent event) {
        if (event instanceof NetworkEvent) {
        }

        if (event instanceof NetworkError) {
            Log.e("Network Exception", ((NetworkError) event).getError().toString());
        }
    }


}