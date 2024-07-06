
package credoapp.behavioral;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableArray;
import java.lang.Class;
import android.content.Context;
import androidx.annotation.NonNull;
import com.facebook.react.bridge.Callback;
import credoapp.module.behavioral.BehavioralModule;

public class RNCredoappsdkBehavioralModule extends ReactContextBaseJavaModule {

    private Context moduleContext = null;

    RNCredoappsdkBehavioralModule(ReactApplicationContext context) {
        super(context);
        moduleContext = context;
        credoapp.module.behavioral.utils.Environment.Companion.setPlatformType(credoapp.module.behavioral.utils.PlatformType.REACT);
    }

    @NonNull
    @Override
    public String getName() {
        return "RNCredoappsdkBehavioralModule";
    }

    @ReactMethod
    public void execute(Callback callback) {
        callback.invoke(BehavioralModule.class.getCanonicalName());
    }

    @ReactMethod 
    public void startTracking() {
        BehavioralModule.startTracking();
    }

    @ReactMethod
    public void stopTracking() {
        BehavioralModule.stopTracking();
    }

    @ReactMethod
    public void updateScreen(String screen) {
        credoapp.module.behavioral.utils.Environment.Companion.getHybrid().updateScreen(screen);
    }
}