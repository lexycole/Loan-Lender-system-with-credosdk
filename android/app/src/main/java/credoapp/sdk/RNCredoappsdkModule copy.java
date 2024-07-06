
package credoapp.sdk;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableArray;
import java.lang.Class;
import java.lang.ClassNotFoundException;
import java.lang.InstantiationException;
import java.lang.IllegalAccessException;

import android.content.Context;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Promise;

import credoapp.CredoAppResult;
import credoapp.CredoAppService;
import credoapp.internal.v1.contract.IModule;

import android.util.Log;

public class RNCredoappsdkModule extends ReactContextBaseJavaModule {

    private Context moduleContext = null;
    private static final int NOT_SUPPORTED_MODULE_CODE = 91;

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
    public void execute(final boolean ignorePermissions, final ReadableArray modules, final Promise promise) {
        executeInternal(ignorePermissions, modules, promise);
    }

    private void executeInternal(final boolean ignorePermissions, final ReadableArray modules, final Promise promise) {
        try {
            new Thread() {
                @Override
                public void run() {
                    CredoAppService.Builder builder = new CredoAppService.Builder(moduleContext);
                    for (int i = 0; i < modules.size(); i++) {
                        try {
                            ReadableMap moduleInfo = modules.getMap(i);
                            String moduleType = moduleInfo.getString("moduleType");
                            IModule module = createInstance(moduleType);
                            if (module != null) {
                                if(!isSupportedModuleVersion(module.getInfo().getVersion())){
                                    String msg = "Module " + module.getInfo().getModuleName() + " version " + module.getInfo().getVersion() + " is not supported";
                                    throw new NotSupportedModuleException(msg);
                                }

                                builder.addModule(module);
                            }
                        } catch (NotSupportedModuleException e) {
                            promise.reject("Error", "Message: " + e.getMessage() + " code: " + NOT_SUPPORTED_MODULE_CODE);
                            return;
                        }
                        catch (Exception e) {
                        }
                    }
                    CredoAppService credoAppService = builder
                            .setIgnorePermissions(ignorePermissions)
                            .build();
                    CredoAppResult<String> executeResult = credoAppService.collect();
                    if (executeResult instanceof CredoAppResult.Success) {
                        promise.resolve(((CredoAppResult.Success<String>) executeResult).getValue());
                    } else {
                        CredoAppResult.Error error = (CredoAppResult.Error) executeResult;
                        promise.reject("Error", "Message: " + error.getMessage() + " code: " + error.getCode());
                    }
                }
            }.start();
        } catch (Exception e) {
            promise.reject("Error", e);
        }
    }

    private IModule createInstance(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class<?> clazz = Class.forName(className);
        if (IModule.class.isAssignableFrom(clazz)) {
            return (IModule) clazz.newInstance();
        }
        return null;
    }

    private boolean isSupportedModuleVersion(String version) {
        int targetMajorVersion = 4;
        String[] versionParts = version.split("\\.");
        int objectMajorVersion;
        
        try {
            objectMajorVersion = Integer.parseInt(versionParts[0]);
        } catch (NumberFormatException e) {
            return true;
        }
        
        return objectMajorVersion >= targetMajorVersion;
    }
}