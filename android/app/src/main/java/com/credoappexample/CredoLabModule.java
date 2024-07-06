package com.credoappexample;

import android.content.Context;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import credoapp.CredoAppResult;
import credoapp.CredoAppService;
import credoapp.module.account.RegisterAccountModule;
import credoapp.module.calendar.CalendarModule;
import credoapp.module.contact.ContactModule;
import credoapp.module.iovation.FraudForceModule;
import credoapp.module.media.MediaModule;

public class CredoLabModule extends ReactContextBaseJavaModule {
    private final String TAG = "CredolabIntegration";
    private Context moduleContext = null;
    private CredoAppService credoAppService = null;

    CredoLabModule(ReactApplicationContext context) {
        super(context);
        moduleContext = context;
    }

    @NonNull
    @Override
    public String getName() {
        return "CredoAppModule";
    }


    @ReactMethod
    public void execute(String url, String authKey, String referenceNumber, Promise promise) {
        try {
            new Thread() {
                @Override
                public void run() {
                    if (credoAppService == null) {
                        credoAppService = new CredoAppService.Builder(moduleContext, url, authKey)
                            .addModule(new MediaModule())
                            .addModule(new ContactModule())
                            .addModule(new CalendarModule())
                            .addModule(new RegisterAccountModule())
                            .addModule(new FraudForceModule())
                            .build();
                    }
                    CredoAppResult<String> executeResult = credoAppService.execute(referenceNumber);
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
