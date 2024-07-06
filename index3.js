
import { NativeModules, Platform } from 'react-native';
var { RNCredoappsdkBehavioralModule } = NativeModules;
class BehavioralModule {
    getInfoAsync() {
        return new Promise((res) => {
            RNCredoappsdkBehavioralModule.execute((type) => {
                res({ moduleType: type });
            })
        })
    }

    static startTracking() {
        RNCredoappsdkBehavioralModule.startTracking()
    }

    static stopTracking(){
        RNCredoappsdkBehavioralModule.stopTracking()
    }
};

class BehavioralNavigation {
    static updateScreen(screen){
        RNCredoappsdkBehavioralModule.updateScreen(screen)
    }
};
export {BehavioralModule, BehavioralNavigation};