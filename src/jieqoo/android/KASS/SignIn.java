package jieqoo.android.KASS;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SignIn  extends Activity implements OnClickListener {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin);
		
		Button btn_signup=( Button ) findViewById( R.id.signin_btn_signup );
		btn_signup.setOnClickListener(this);
		
		Button btn_signin=( Button ) findViewById(R.id.signin_btn_OK);
		btn_signin.setOnClickListener(this);
	}

	public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.signin_btn_signup:
				Intent intent = new Intent();
				intent.setClass(SignIn.this, SignUp.class);
				startActivity(intent);
				break;
			case R.id.signin_btn_OK:
				checkUser();
				break;
			
		}
	}
	
	public void checkUser()
	{
		String username=((TextView) findViewById(R.id.signin_txt_username)).getText().toString().trim();
		String password=((TextView) findViewById(R.id.signin_txt_password)).getText().toString().trim();
		
		//用户验证		
		
		//更新本地数据库
		SharedPreferences sets = getSharedPreferences("auto_log", 2); //首先获取一个SharedPreferences对象 
		sets.edit()
		.putInt("auto_log", 1)
		.putString("name", username)
		.putString("password", password)
		.commit();
		
		//更新系统缓存数据
		MyApp myapp=((MyApp)getApplicationContext());
		myapp.setUserName(username);
		
		//测试
		TextView usr=(TextView) findViewById(R.id.signin_txt_password);
		usr.setText(myapp.getUserName());
	}
	

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) { 
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
		{ 
			Intent intent = new Intent(SignIn.this, Main.class);	 
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);			 
			startActivity(intent);
		
			return true;
		}
		else
			return false;
	}
}
