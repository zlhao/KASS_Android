package jieqoo.android.KASS;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SignUp  extends Activity implements OnClickListener {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup);
		
		Button btn_signin=( Button ) findViewById( R.id.signup_btn_signin );
		btn_signin.setOnClickListener(this);
		
		Button btn_save=( Button ) findViewById( R.id.signup_btn_go );
		btn_save.setOnClickListener(this);
	}
	
	public void onClick(View v )
	{
		switch(v.getId())
		{
			case R.id.signup_btn_signin://跳转到登陆界面
				//Intent intent = new Intent();
				//intent.setClass(SignUp.this, SignIn.class);
				//startActivity(intent);
				finish();
				break;
			case R.id.signup_btn_go://保存用户信息
				SaveUser();
				break;
		}
	}			
	
	protected void SaveUser()
	{
		String username=((EditText ) findViewById(R.id.signup_txt_username)).getText().toString().trim();
		String pw=((EditText ) findViewById(R.id.signup_txt_password)).getText().toString().trim();
		String email=((EditText ) findViewById(R.id.signup_txt_email)).getText().toString().trim();
		String phone=((EditText ) findViewById(R.id.signup_txt_phone)).getText().toString().trim();
		
		if(username.equals(""))
		{
			return;
		}
		
		if(pw.equals(""))
		{
			return;
		}
		
		if(email.equals(""))
		{
			return;
		}
		
		if(phone.equals(""))
		{
			return;
		}
		
		//数据处理
		
		//自动跳转到登陆页面
		this.finish();
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) { 
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) 
		{ 
			Intent intent = new Intent(SignUp.this, Main.class);	 
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);			 
			startActivity(intent);
		
			return true;
		}
		else
			return false;
	}
}
