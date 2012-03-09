package jieqoo.android.KASS;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Post_1 extends Activity implements OnClickListener{
	
	//控件定义
	EditText txt_title,txt_content;
	TextView txv_address;
	Button btn_next,btn_map;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_1);
		
		//控件初始化
		controlsInit();
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		
		MyApp myapp=((MyApp)getApplicationContext());
		txt_title.setText((myapp).getTitle());
		txt_content.setText(myapp.getContent());
		txv_address.setText(myapp.getAddress());
	}
	
	//控件初始化
	public void controlsInit()
	{
		btn_next=( Button ) findViewById(R.id.post_1_btn_next);
		btn_next.setOnClickListener(this);	
		
		btn_map=( Button ) findViewById(R.id.post_1_btn_map);
		btn_map.setOnClickListener(this);
		
		txt_title=(EditText) findViewById(R.id.post_1_txt_title);
		txt_title.addTextChangedListener(textWatcher);
		txt_title.setFocusable(true);
		
		txt_content=(EditText) findViewById(R.id.post_1_txt_content);		
		
		txv_address=(TextView) findViewById(R.id.post_1_txv_address);
	}
	
	public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.post_1_btn_next:				
				setMessage();
				
				Intent next = new Intent();
				next.setClass(Post_1.this, Post_2.class);
				startActivity(next);
				finish();
				break;
			
			case R.id.post_1_btn_map:
				setMessage();
				
				Intent map = new Intent();
				map.setClass(Post_1.this, GoogleMap.class);
				startActivity(map);
				break;
		}
	}

	public void setMessage()
	{
		MyApp myapp=((MyApp)getApplicationContext());
		myapp.setTitle(txt_title.getText().toString().trim());
		myapp.setContent(txt_content.getText().toString().trim());
	}
	
	private TextWatcher textWatcher = new TextWatcher() 
	{
		 @Override
		 public void onTextChanged(CharSequence s, int start, int before,int count)
		 {			 
		 }

		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			if(txt_title.getText().toString().trim().equals(""))
				 btn_next.setEnabled(false);
			 else
				 btn_next.setEnabled(true);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
			// TODO Auto-generated method stub
			
		}           
	};
}