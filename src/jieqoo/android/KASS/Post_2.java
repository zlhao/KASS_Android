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
import android.widget.TextView;

public class Post_2 extends Activity  implements OnClickListener{
	//定义
	Button btn_next,btn_to1;
	EditText txt_price;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_2);	
		
		controlsInit();
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		
		MyApp myapp=((MyApp)getApplicationContext());
		txt_price.setText(myapp.getPrice());
	}
	
	//控件初始化
		public void controlsInit()
		{
			btn_next=( Button ) findViewById(R.id.post_2_btn_next);			
			btn_next.setOnClickListener(this);
			
			
			btn_to1=( Button ) findViewById(R.id.post_2_btn_to1);
			btn_to1.setOnClickListener(this);
			
			txt_price=( EditText ) findViewById(R.id.post_2_txt_price);
			txt_price.addTextChangedListener(textWatcher); 
		}
	
	public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.post_2_btn_next:				
				MyApp myapp=((MyApp)getApplicationContext());
				myapp.setPrice(txt_price.getText().toString().trim());
				
				Intent next = new Intent();
				next.setClass(Post_2.this, Post_3.class);
				startActivity(next);
				finish();
				break;
				
			case R.id.post_2_btn_to1:
				Intent to1 = new Intent();
				to1.setClass(Post_2.this, Post_1.class);
				startActivity(to1);
				finish();
				break;
		}
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
			if(txt_price.getText().toString().trim().equals(""))
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
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) { 
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
		{ 
			Intent to1 = new Intent();
			to1.setClass(Post_2.this, Post_1.class);
			startActivity(to1);
			finish();
		
			return true;
		}
		else
			return false;
	}
}