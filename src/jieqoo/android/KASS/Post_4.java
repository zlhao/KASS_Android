package jieqoo.android.KASS;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Post_4 extends Activity  implements OnClickListener{
	
	EditText txt_title,txt_content,txt_price,txt_time;
	Button btn_to1,btn_to2,btn_to3,btn_cencel,btn_ok;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_4);
		
		controlsInit();
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		
		MyApp myapp=((MyApp)getApplicationContext());
		txt_title.setText((myapp).getTitle());
		txt_content.setText(myapp.getContent());
		txt_price.setText(myapp.getPrice());
		txt_time.setText(myapp.getTime());
	}
	
	public void controlsInit()
	{	
		txt_title=(EditText) findViewById(R.id.post_4_txt_title);
		txt_content=(EditText) findViewById(R.id.post_4_txt_content);
		txt_price=(EditText) findViewById(R.id.post_4_txt_price);
		txt_time=(EditText) findViewById(R.id.post_4_txt_time);
		
		btn_to1=( Button ) findViewById(R.id.post_4_btn_to1);
		btn_to1.setOnClickListener(this);
		
		btn_to2=( Button ) findViewById(R.id.post_4_btn_to2);
		btn_to2.setOnClickListener(this);
		
		btn_to3=( Button ) findViewById(R.id.post_4_btn_to3);
		btn_to3.setOnClickListener(this);
		
		btn_cencel=( Button ) findViewById(R.id.post_4_btn_cancel);
		btn_cencel.setOnClickListener(this);
		
		btn_ok=( Button ) findViewById(R.id.post_4_btn_OK);
		btn_ok.setOnClickListener(this);
	}
	
	public void onClick(View v)
	{
		switch(v.getId())
		{			
			case R.id.post_4_btn_to1:
				Intent to1 = new Intent();
				to1.setClass(Post_4.this, Post_1.class);
				startActivity(to1);
				finish();
				break;
				
			case R.id.post_4_btn_to2:
				Intent to2 = new Intent();
				to2.setClass(Post_4.this, Post_2.class);
				startActivity(to2);
				finish();
				break;
				
			case R.id.post_4_btn_to3:
				Intent to3 = new Intent();
				to3.setClass(Post_4.this, Post_3.class);
				startActivity(to3);
				finish();
				break;
			
			case R.id.post_4_btn_cancel:
				finish();
				break;
				
			case R.id.post_4_btn_OK:
				//保存数据
				saveData();
				finish();
				break;
		}
	}
	
	//提交用户需求
	public void saveData(){}	
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) { 
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
		{ 
			Intent to3 = new Intent();
			to3.setClass(Post_4.this, Post_3.class);
			startActivity(to3);
			finish();
		
			return true;
		}
		else
			return false;
	}
}
