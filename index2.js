
import { NativeModules } from 'react-native';

const { RNCredoappsdk } = NativeModules;

class CredoAppService {
    static execute(url, authKey, referenceNumber, ignorePermissions) {
        ignorePermissions = ignorePermissions == undefined || 
            ignorePermissions == null ? 
            true : 
            ignorePermissions;
        return RNCredoappsdk.executeV2(url, authKey, referenceNumber, ignorePermissions)
    }
}; 
module.exports = CredoAppService;
export default RNCredoappsdk;

