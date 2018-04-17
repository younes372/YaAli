package com.example.younes.yaali;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by younes on 4/17/2018.
 */

public class YaAli extends BroadcastReceiver {

	private Context context;

	@SuppressLint( "WrongConstant" )
	@Override
	public void onReceive( Context context, Intent intent ) {
		this.context = context;
		boolean mode = false;

		int mode_audio = ( ( AudioManager ) context.getSystemService( "audio" ) ).getRingerMode( );
		switch ( mode_audio ) {
			case 0:
				mode = true;
				break;
			case 1:
				mode = true;
				break;
		}

		if ( !mode ) {
			MediaPlayer player = new MediaPlayer( );
			try {
				AssetFileDescriptor fa = this.context.getAssets( ).openFd( "yaali.mp3" );
				player.setDataSource( fa.getFileDescriptor(),fa.getStartOffset(),fa.getLength());
				fa.close();
				player.prepare();
				player.start();
			} catch ( IOException e ) {
				e.printStackTrace( );
			}


		}
	}


}
