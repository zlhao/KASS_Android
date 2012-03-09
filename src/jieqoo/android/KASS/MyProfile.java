package jieqoo.android.KASS;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MyProfile  extends Activity  {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myprofile);		
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		
		if(1==1)
		{
			Intent intent = new Intent();
			intent.setClass(MyProfile.this, SignIn.class);
			startActivity(intent);
		}
	}
	
}
