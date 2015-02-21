package jacmobile.com.weather.eventbus.events;

public class NetworkEvent extends AEvent {
    public NetworkEvent(String data) {
        super(data);
    }
}