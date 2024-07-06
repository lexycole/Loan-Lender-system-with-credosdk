#import <Foundation/Foundation.h>
#import "ModuleFactory-Header.h"

@implementation MyClassCreator

- (id)createInstance:(NSString *)className {
    Class class = NSClassFromString(className);
    if ([class respondsToSelector:@selector(alloc)] && [class respondsToSelector:@selector(init)]) {
            id instance = [[class alloc] init];
            return instance;
        } else {
            return nil;
        }
}

@end
