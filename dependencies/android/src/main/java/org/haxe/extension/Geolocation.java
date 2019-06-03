package org.haxe.extension;


import android.app.Activity;
import android.content.res.AssetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.util.Log;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import org.haxe.lime.HaxeObject;

/* 
	You can use the Android Extension class in order to hook
	into the Android activity lifecycle. This is not required
	for standard Java code, this is designed for when you need
	deeper integration.
	
	You can access additional references from the Extension class,
	depending on your needs:
	
	- Extension.assetManager (android.content.res.AssetManager)
	- Extension.callbackHandler (android.os.Handler)
	- Extension.mainActivity (android.app.Activity)
	- Extension.mainContext (android.content.Context)
	- Extension.mainView (android.view.View)
	
	You can also make references to static or instance methods
	and properties on Java classes. These classes can be included 
	as single files using <java path="to/File.java" /> within your
	project, or use the full Android Library Project format (such
	as this example) in order to include your own AndroidManifest
	data, additional dependencies, etc.
	
	These are also optional, though this example shows a static
	function for performing a single task, like returning a value
	back to Haxe from Java.
*/
public class Geolocation extends Extension {
	private static final String TAG = "trace[java]";
	private static HaxeObject callback = null;
	
	private static final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            //your code here
 //           Log.d(TAG, "got location " + location);
            callback.call("locationChanged", new Object[] {
                location.getTime(),
                location.getLatitude(), 
                location.getLongitude(), 
                location.hasAccuracy() ? location.getAccuracy() : null, 
                location.hasSpeed() ? location.getSpeed() : null, 
 //               location.hasSpeed() ? location.getSpeedAccuracyMetersPerSecond() : null, 
                location.hasBearing() ? location.getBearing() : null,
 //               location.hasBearing() ? location.getBearingAccuracyDegrees() : null,
                location.hasAltitude() ? location.getAltitude() : null,
//                location.hasAltitude() ? location.getVerticalAccuracyMeters() : null,
                location.getElapsedRealtimeNanos()
            });
        }
        
        @Override 
        public void onStatusChanged(String provider, int status, Bundle extras) { 
        } 
        
        @Override 
        public void onProviderEnabled(String provider) { 
        } 
        
        @Override 
        public void onProviderDisabled(String provider) { 
        }
    };
    
	public static void startService (HaxeObject cb, float refresh_distance, int refresh_time) {
		if (refresh_distance == -1)
            refresh_distance = 1.5f;
		if (refresh_time == -1)
            refresh_time = 800;
        final int rt=refresh_time;
        final float rd=refresh_distance;
        callback = cb;
		Extension.mainActivity.runOnUiThread(new Runnable() {
            public void run() {
                LocationManager mLocationManager = (LocationManager) Extension.mainContext.getSystemService(Context.LOCATION_SERVICE);

                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, rt, rd, mLocationListener);
            }
        });
	}
	
	
	/**
	 * Called when an activity you launched exits, giving you the requestCode 
	 * you started it with, the resultCode it returned, and any additional data 
	 * from it.
	 */
	public boolean onActivityResult (int requestCode, int resultCode, Intent data) {
		
		return true;
		
	}

	/**
	 * Called when the activity receives th results for permission requests.
	 */
	public boolean onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

		return true;

	}
	
	
	/**
	 * Called when the activity is starting.
	 */
	public void onCreate (Bundle savedInstanceState) {
		
		
		
	}
	
	
	/**
	 * Perform any final cleanup before an activity is destroyed.
	 */
	public void onDestroy () {
		
		
		
	}
	
	
	/**
	 * Called as part of the activity lifecycle when an activity is going into
	 * the background, but has not (yet) been killed.
	 */
	public void onPause () {
		
		
		
	}
	
	
	/**
	 * Called after {@link #onStop} when the current activity is being 
	 * re-displayed to the user (the user has navigated back to it).
	 */
	public void onRestart () {
		
		
		
	}
	
	
	/**
	 * Called after {@link #onRestart}, or {@link #onPause}, for your activity 
	 * to start interacting with the user.
	 */
	public void onResume () {
		
		
		
	}
	
	
	/**
	 * Called after {@link #onCreate} &mdash; or after {@link #onRestart} when  
	 * the activity had been stopped, but is now again being displayed to the 
	 * user.
	 */
	public void onStart () {
		
		
		
	}
	
	
	/**
	 * Called when the activity is no longer visible to the user, because 
	 * another activity has been resumed and is covering this one. 
	 */
	public void onStop () {
		
		
		
	}
	
	
}