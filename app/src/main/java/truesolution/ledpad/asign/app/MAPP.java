package truesolution.ledpad.asign.app;

import android.app.Application;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.Display;
import android.view.WindowManager;

/**
 * The type MAPP.
 */
public class MAPP extends Application {
	/**
	 * Init
	 */
	public static final int INIT_                   = 0;
	
	public static final int START_                   = 1;
	
	/**
	 * MediaPlayer Start
	 */
	public static boolean mIsStart          = false;
	
	/**
	 * LCD Size and Half LCD Size
	 */
	public static int LCD_W, /**
	 * The Lcd h.
	 */
	LCD_H, /**
	 * The Lcd hw.
	 */
	LCD_HW, /**
	 * The Lcd hh.
	 */
	LCD_HH;
	
	/**
	 * 상태바를 제외한 영역
	 */
	public static int LCD_D_H;

	@Override
	public void onCreate() {
		super.onCreate();
		
		// Display Area
		Display display = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		LCD_W = display.getWidth();
		LCD_H = display.getHeight();
		LCD_HW = LCD_W / 2;
		LCD_HH = LCD_H / 2;
	}
}
