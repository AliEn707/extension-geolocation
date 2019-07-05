#import <Foundation/Foundation.h>
#import <AVFoundation/AVFoundation.h>
#import <CoreLocation/CoreLocation.h>

#import "Utils.h"

//#include <stdio.h>
// #include <hx/CFFI.h>
// #import "CameraMic.h"
// #import <AudioToolbox/AudioToolbox.h>

@interface Geolocation:NSObject<CLLocationManagerDelegate>
{
	CLLocationManager *locationManager; 
	callback_data callback;
}

- (void)init;
- (void)start:(callback_data*)cb;
- (void)stop;

@end

@implementation Geolocation

- (Geolocation*)init {
    if (self = [super init]){
	    locationManager = [[CLLocationManager alloc] init];
	    locationManager.delegate = self;
	    locationManager.desiredAccuracy = kCLLocationAccuracyBest;
	    geocoder = [[CLGeocoder alloc] init];
	    NSLog(@"haxe[ios]: geolocation inited");
	}
	return self;
}

- (void)start:(callback_data)cb {
    callback = cb;
    // fetching current location start from here
    [locationManager startUpdatingLocation];
}

- (void)stop {
// stopping locationManager 
    [locationManager stopUpdatingLocation];
}

- (void)locationManager:(CLLocationManager *)manager didUpdateLocations:(NSArray *)locations {
    CLLocation* l = [locations lastObject];
    cb(1,1,1,1,1,1,1,1);
}

- (void)locationManager:(CLLocationManager *)manager didFailWithError:(NSError *)error {
    NSLog(@"failed to fetch current location : %@", error);
}

@end

namespace extension_geolocation{

	Geolocation *loc=0;
	int started
    
	int startService(callback_data cb){
		if(!loc){
			loc = [[Geolocation alloc] init];
		}
		[loc start:cb];
	}

	void startService(){
		if (loc)
			[loc stop];
	}

}