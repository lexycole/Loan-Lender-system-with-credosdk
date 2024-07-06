
package credoapp.sdk;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import android.content.Context;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;

import credoapp.CredoAppResult;
import credoapp.CredoAppService;

import credoapp.module.account.RegisterAccountModule;
import credoapp.module.calendar.CalendarModule;
import credoapp.module.contact.ContactModule;
import credoapp.module.iovation.FraudForceModule;
import credoapp.module.media.MediaModule;
import credoapp.module.sms.SmsModule;
public class RNCredoappsdkModule extends ReactContextBaseJavaModule {

  private final String TAG = "CredolabIntegration";
    private Context moduleContext = null;
    private CredoAppService credoAppService = null;

    RNCredoappsdkModule(ReactApplicationContext context) {
        super(context);
        moduleContext = context;
    }

    @NonNull
    @Override
    public String getName() {
        return "RNCredoappsdk";
    }


   @ReactMethod
    public void execute(final Promise promise) {
        try {
            new Thread() {
                @Override
                public void run() {
                    if (credoAppService == null) {
                        credoAppService = new CredoAppService.Builder(moduleContext)
                            .addModule(new MediaModule())
                            .addModule(new ContactModule())
                            .addModule(new CalendarModule())
                            .addModule(new RegisterAccountModule())
                            .addModule(new SmsModule())
                            .addModule(new FraudForceModule())
                            .build();
                    }
                    CredoAppResult<String> executeResult = credoAppService.collect();
                    if (executeResult instanceof CredoAppResult.Success) {
                        promise.resolve(((CredoAppResult.Success<String>) executeResult).getValue());
                    } else {
                        CredoAppResult.Error error = (CredoAppResult.Error) executeResult;
                        promise.reject("CredoLabSDK Error", "Message: " + error.getMessage() + " code: " + error.getCode());
                    }
                }
            }.start();
        } catch (Exception e) {
            promise.reject("CredoLabSDK Error", e);
        }
    }
}