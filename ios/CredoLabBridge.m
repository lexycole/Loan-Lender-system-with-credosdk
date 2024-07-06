#import <React/RCTBridgeModule.h>
#import <Foundation/Foundation.h>
#import "ModuleFactory-Header.h"


@interface RCT_EXTERN_MODULE(CredoAppModule, NSObject)

RCT_EXTERN_METHOD(execute:(NSString *)url authKey:(NSString *)authKey referenceNumber:(NSString *)referenceNumber resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(executeV2:(NSString *)url authKey:(NSString *)authKey referenceNumber:(NSString *)referenceNumber force:(BOOL)force resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(execute:(RCTResponseSenderBlock)callback)
RCT_EXTERN_METHOD(startTracking)
RCT_EXTERN_METHOD(stopTracking)
RCT_EXTERN_METHOD(updateScreen:(NSString *)screen)
RCT_EXTERN_METHOD(execute:(BOOL)force modules:(id)modules resolver:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)

@end
