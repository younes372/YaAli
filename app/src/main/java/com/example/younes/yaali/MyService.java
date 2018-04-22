package com.example.younes.yaali;


import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by younes on 12/09/2017.
 */

public class MyService extends Service implements SensorEventListener {

	private SensorManager sensorMgr;
	private float last_x,last_y,last_z;
	private static final int SHAKE_THRESHOLD = 8000;
	private long lastUpdate;



	@Nullable
	@Override
	public IBinder onBind( Intent intent ) {
		return null;
	}


	@Override
	public void onCreate( ) {
		super.onCreate( );
	}

	@Override
	public int onStartCommand( Intent intent, int flags, int startId ) {


		sensorMgr = (SensorManager)getSystemService(SENSOR_SERVICE);
		sensorMgr.registerListener(this
				,sensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
				,SensorManager.SENSOR_DELAY_UI);

		return START_STICKY;
	}


	@Override
	public void onDestroy() {
		sensorMgr.unregisterListener(MyService.this);
		super.onDestroy();
	}

	@Override
	public void onSensorChanged( SensorEvent event ) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			getSensor(event);
		}
	}

	@Override
	public void onAccuracyChanged( Sensor sensor, int accuracy ) {

	}

	private void getSensor( SensorEvent event) {
		float[] values = event.values;
		float x = values[0];
		float y = values[1];
		float z = values[2];

		long curTime = System.currentTimeMillis();

		if ((curTime - lastUpdate) > 100) {
			long diffTime = (curTime - lastUpdate);
			lastUpdate = curTime;

			x = values[SensorManager.DATA_X];
			y = values[SensorManager.DATA_Y];
			z = values[SensorManager.DATA_Z];

			float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000;

			if (speed > SHAKE_THRESHOLD) {

				Intent intent=new Intent( "com.example.younes.yaali_Ali" );
				intent.putExtra( "send_play","younes" );
				sendBroadcast( intent );


			}
			last_x = x;
			last_y = y;
			last_z = z;
		}

	}







}
