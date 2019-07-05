#ifndef EXTENSION_GEOLOCATION_H
#define EXTENSION_GEOLOCATION_H


namespace extension_geolocation {
	
	typedef void (*callback_data)(long long, float,float,float,float,float,float,long long);
	
	int startService(callback_data cb);
	void stopService();
	
}


#endif