package openfl.extension;


import haxe.Int64;
import haxe.Timer.delay;

#if android
import lime.system.JNI;
import com.player03.android6.Permissions;
#elseif ios
import lime.system.CFFI;	
#end

class Geolocation {
	private static var _running:Bool = false;
	
	public static function startService (cb:Dynamic->Void, dist:Float, time:Int):Void{
		if (_running)
			return;
	#if mobile	
		if (checkPermission(startService.bind(cb, dist, time), Permissions.ACCESS_FINE_LOCATION)){
			extension_geolocation_start_service(new GeoCallback(cb), dist, time);
		}
		_running = true;
	#end
	}
	
	public static function stopService (cb:Dynamic->Void, dist:Float, time:Int):Void{
		if (!_running)
			return;
	#if mobile		
		extension_geolocation_stop_service();
		_running = false;
	#end
	}
	
	private static function checkPermission(action:Void->Void, p:String){
	#if android
		if (!Permissions.hasPermission(p)){
			Permissions.requestPermission(p);
			delay(action, 2000);
			return false;
		}
	#end
		return true;
	}	
	
	#if android
	private static var extension_geolocation_start_service = JNI.createStaticMethod ("org.haxe.extension.Geolocation", "startService", "(Lorg/haxe/lime/HaxeObject;FI)V");
	private static var extension_geolocation_stop_service = JNI.createStaticMethod ("org.haxe.extension.Geolocation", "stopService", "()V");
	#elseif ios
	private static var extension_geolocation_start_service = CFFI.load ("extension_geolocation", "extension_geolocation_sample_method", 1);
	private static var extension_geolocation_stop_service = CFFI.load ("extension_geolocation", "extension_geolocation_sample_method", 1);
	#end
}

class GeoCallback{
	private var _callback:Dynamic->Void;
	
	public function new(cb:Dynamic->Void){
		_callback = cb;
	}
	
	public function locationChanged(
			time:Int64,
			latitude:Float, 
			longitude:Float, 
			accuracy:Null<Float>, 
			speed:Null<Float>,  
			bearing:Null<Float>,
			altitude:Null<Float>,
			epoch:Int64){ 
		
/*		trace(time);
		trace(epoch);
		trace(latitude);
		trace(longitude);
		trace(accuracy);
		trace(speed);
		trace(altitude);
		trace(bearing);
*/
		_callback({
			latitude: latitude,
			longitude: longitude,
			accuracy: accuracy,
			speed: speed,
			bearing: bearing,
			altitude: altitude,
			time: time,
			epoch: epoch
		});
	}
}