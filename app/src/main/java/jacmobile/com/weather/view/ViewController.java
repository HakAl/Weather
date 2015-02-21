package jacmobile.com.weather.view;

import android.content.res.Resources;
import android.view.View;

import jacmobile.com.weather.activities.WeatherActivity;
import jacmobile.com.weather.eventbus.events.Update;

public abstract class ViewController implements View.OnClickListener {
    private View root;
    private Update update;

    public abstract Update provideUpdate();

    public abstract void onCreateView(View root);

    public abstract void onResume();

    protected WeatherActivity context() {
        return (WeatherActivity) root.getContext();
    }

    protected String string(int resId) {
        return root.getContext().getString(resId);
    }

    protected Resources resources() {
        return context().getResources();
    }

    protected View view(int resId) {
        return context().findViewById(resId);
    }
}