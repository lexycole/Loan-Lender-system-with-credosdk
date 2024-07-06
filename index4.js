
import { NativeModules, Platform } from 'react-native';

const { RNCredoappsdk } = NativeModules;

class CredoAppService {
    _ignorePermissions = true
    _forceResolvePermissions = true
    _modules = []

    setForceResolvePermissions(force) {
        this._forceResolvePermissions = force
    }

    setIgnorePermissions(ignore) {
        this._ignorePermissions = ignore
    }

    addModuleAsync(module) {
        return new Promise((resolve, reject) => {
            module.getInfoAsync()
                .then((result) => {
                    this._modules.push(result);
                    resolve(result);
                })
                .catch((err) => {
                    reject(err);
                });
        });
    }

    collectAsync() {
        return RNCredoappsdk.execute(Platform.OS === 'android' ? this._ignorePermissions : this._forceResolvePermissions, this._modules)
    }
};
export default CredoAppService;

