import Foundation
import CredoAppCore
import React

@objc(RNCredoappsdk)
class RNCredoappsdk: RCTEventEmitter {
    
    @objc(execute:modules:resolver:rejecter:)
    func execute(_ force:Bool, modules: AnyObject, resolver resolve: @escaping RCTPromiseResolveBlock, rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
        executeInternal(force: force, modules: modules, resolve: resolve, reject: reject)
    }
    
    private func executeInternal(force: Bool, modules: AnyObject, resolve: @escaping RCTPromiseResolveBlock, reject: @escaping RCTPromiseRejectBlock) -> Void {
        let builder = CredoAppService.Builder()
            .setForceResolvePermissions(force)
        let modulesArray = RCTConvert.nsArray(modules) as! [NSDictionary]
        
        for moduleInfo in modulesArray {
            let moduleType = moduleInfo["moduleType"] as? String
            do {
                if let module = try createInstance(type: moduleType) {
                    builder.addModule(module)
                }
            } catch {
            }
        }
        
        let credoAppService = builder.build()
        let result = credoAppService.collect()
        switch result {
        case .success(let value):
            resolve(value)
        case .error(let errorCode, let errorMsg):
            reject("ERROR: code - \(errorCode); msg - \(errorMsg);", nil, nil)
        }
    }
    
    open override func supportedEvents() -> [String] {
        return ["onStart", "onFinish"]
    }
    
    private func createInstance(type: String?) -> Module? {
        guard let moduleType = type else {
            return nil
        }
        return MyClassCreator().createInstance(moduleType) as? Module
    }
    
    @objc
    override static func requiresMainQueueSetup() -> Bool { return true; }
}
