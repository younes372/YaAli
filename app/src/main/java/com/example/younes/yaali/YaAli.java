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
	private boolean mode;
	private MediaPlayer player=new MediaPlayer();


	@SuppressLint( "WrongConstant" )
	@Override
	public void onReceive( Context context, Intent intent ) {
		this.context = context;
		 mode = false;

		if ( "younes".equals( intent.getStringExtra( "send_play" ) ) ) {
			setup( );
		}else {
			setup( );
		}


	}


	@SuppressLint( "WrongConstant" )
	private void setup( ) {
		switch ( ( ( AudioManager ) context.getSystemService( "audio" ) ).getRingerMode( ) ) {
			case 0:
				mode = true;
			//	Intent data = new Intent( context, MyService.class );
			//	context.startService( data );
				break;
			case 1:
				mode = true;
				break;
			case 2:
				if (!player.isPlaying() )
				setupPlayer();
				break;
		}
	}

	private void setupPlayer( ) {
		if ( !mode ) {
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
