#ifndef STATIC_LINK
#define IMPLEMENT_API
#endif

#if defined(HX_WINDOWS) || defined(HX_MACOS) || defined(HX_LINUX)
#define NEKO_COMPATIBLE
#endif


#include <hx/CFFI.h>
#include "Utils.h"


using namespace extension_geolocation;

static void data_ready(long long itme, float lat, float lng, float accu, float speed, float bearing, float alt, long long epoch){
	
}

static value extension_geolocation_start_service(value callback, value dist, value time) {
	
	int returnValue = startService(&data_ready);
	return alloc_null();
	
}
DEFINE_PRIM (extension_geolocation_start_service, 3);


static value extension_geolocation_stop_service() {
	
	stopService();
	return alloc_null();
	
}
DEFINE_PRIM (extension_geolocation_stop_service, 0);


extern "C" void extension_geolocation_main () {
	
	val_int(0); // Fix Neko init
	
}
DEFINE_ENTRY_POINT (extension_geolocation_main);



extern "C" int extension_geolocation_register_prims () { return 0; }