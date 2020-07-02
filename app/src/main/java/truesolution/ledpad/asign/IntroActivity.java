package truesolution.ledpad.asign;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import truesolution.ledpad.asign.fd.FD_DELAY;
import truesolution.ledpad.asign.fd.FD_REQ;

/**
 * Created by TCH
 *
 * @author think.code.help@gmail.com
 * @version 1.0
 * @date 2020. 07. 01.
 */
public class IntroActivity extends MBaseActivity {
	@BindView(R.id.mTVCenter)
	TextView mTVCenter;
	@BindView(R.id.mIntroLogo)
	ImageView mIntroLogo;
	@BindView(R.id.mTVVersion)
	TextView mTVVersion;
	
	/**
	 * Get Error Message
	 *
	 * @return
	 */
	@Override
	public String mGetErrorMessage() {
		return null;
	}
	
	/**
	 * On Confirm
	 */
	@Override
	public void mOnConfirm() {
		finish();
	}
	
	/**
	 * On Yes
	 */
	@Override
	public void mOnYes() {
	}
	
	/**
	 * On Cancel
	 */
	@Override
	public void mOnCancel() {
	}
	
	/**
	 * Initialize
	 */
	@Override
	public void mInit() {
	}
	
	@Override
	public void onPause() {
		super.onPause();
		
		mIsPause = true;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		
		mIsPause = false;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		ButterKnife.bind(this);
		
		PackageInfo pInfo = null;
		try {
			pInfo = getPackageManager().getPackageInfo(
					this.getPackageName(), 0);
		} catch(PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		int _versionCode = pInfo.versionCode;
		String _versionName = getResources().getString(R.string.ver_code_name) + pInfo.versionName;
		
		mTVVersion.setText(_versionName);
		
		mCheckBluetoothPermission();
	}
	
	@Override
	public void onBackPressed() {
	}
	
	/**
	 * Go Main Activity
	 */
	public void mGoMainActivity() {
		finish();
		Intent _intent = new Intent(this, MainActivity.class);
		startActivity(_intent);
	}
	
	@Override
	public void onActivityResult(int _req_code, int _result_code, Intent _data) {
		super.onActivityResult(_req_code, _result_code, _data);
		
		if(_req_code == FD_REQ.REQ_ENABLE_BT) {
			mCheckBluetoothPermission();
		}
	}
	
	private void mCheckBluetoothPermission() {
		BluetoothAdapter _bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		if(_bluetoothAdapter == null) {
			mShowMessageDialog(R.string.notice_bluetooth_on, false);
		} else if(!_bluetoothAdapter.isEnabled()) {
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
					startActivityForResult(enableBtIntent, FD_REQ.REQ_ENABLE_BT);
				}
			}, FD_DELAY.MIN_);
		} else {
			mHandler.postDelayed(new Runnable() {
				@Override
				public void run() {
					mGoMainActivity();
				}
			}, FD_DELAY.INTRO_WAIT);
		}
	}
}