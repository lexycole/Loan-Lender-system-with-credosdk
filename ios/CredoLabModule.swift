import Foundation
import CredoAppCore
import CredoAppMusic
import CredoAppContacts
import CredoAppCalendarReminders
import CredoAppCalendarEvents
import CredoAppMedia
import CredoAppIovation
import React

@objc(CredoAppModule)
class CredoAppModule: RCTEventEmitter {

  @objc(execute:authKey:referenceNumber:resolver:rejecter:)
  func execute(_ url: String, authKey: String, referenceNumber: String, resolver resolve: @escaping RCTPromiseResolveBlock, rejecter reject: @escaping RCTPromiseRejectBlock) -> Void {
    let credoAppService = CredoAppService.Builder(url: url, authKey: authKey)
      .addModule(MusicModule())
      .addModule(ContactsModule())
      .addModule(CalendarRemindersModule())
      .addModule(CalendarEventsModule())
      .addModule(MediaModule())
      .addModule(IovationModule())
      .build()
    let result = credoAppService.execute(referenceNumber: referenceNumber)
    switch result {
      case .success(let msg):
        resolve("Success \(msg)")
      
      case .error(let errorCode, let errorMsg):
        reject("ERROR: code - \(errorCode); msg - \(errorMsg); rn - \(referenceNumber)", nil, nil)
      }
  }
  
   @objc dynamic func startTracking() -> Void {
        DispatchQueue.main.async(execute: {
            BehavioralModule.startTracking()
        })
    }

    @objc dynamic func stopTracking() -> Void {
        BehavioralModule.stopTracking()
    }

    @objc dynamic func updateScreen(_ screen: String) -> Void {
        print(screen)
        
        Environment.Hybrid.updateScreen(screen)
    }

    open override func supportedEvents() -> [String] {
        return ["onStart", "onFinish"]
    }
 
    @objc
    override static func requiresMainQueueSetup() -> Bool { return true; }
}

