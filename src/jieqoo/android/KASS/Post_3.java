package jieqoo.android.KASS;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class Post_3 extends Activity  implements OnClickListener{
	
	//定义
	Button btn_next,btn_to1,btn_to2;
	TextView txv_time;
	ListView lstv_time;
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post_3);		
		
		controlsInit();
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		
		MyApp myapp=((MyApp)getApplicationContext());
		txv_time.setText(myapp.getTime().toString().trim());
	}
	
	public void controlsInit()
	{
		btn_next=( Button ) findViewById(R.id.post_3_btn_next);
		btn_next.setOnClickListener(this);
		
		btn_to1=( Button ) findViewById(R.id.post_3_btn_to1);
		btn_to1.setOnClickListener(this);
		
		btn_to2=( Button ) findViewById(R.id.post_3_btn_to2);
		btn_to2.setOnClickListener(this);
		
		txv_time=(TextView) findViewById(R.id.post_3_txv_time);
		txv_time.addTextChangedListener(textWatcher);
		
		List<String> data = new ArrayList<String>();
		data.add("1个小时");
		data.add("3个小时");
		data.add("6个小时");
		data.add("12个小时");
		data.add("1天");
		data.add("3天");
		data.add("15天");
		lstv_time=(ListView) findViewById(R.id.post_3_lstv_time);
		lstv_time.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,data));		
		lstv_time.setOnItemClickListener(new OnItemClickListener()
		{   
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3)
			{
				// TODO Auto-generated method stub 
				txv_time.setText(arg0.getItemAtPosition(arg2).toString().trim());
			}  
		 });  

	}
	
	public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.post_3_btn_next:				
				MyApp myapp=((MyApp)getApplicationContext());
				myapp.setTime(txv_time.getText().toString().trim());
				
				Intent next = new Intent();
				next.setClass(Post_3.this, Post_4.class);
				startActivity(next);
				finish();
				break;
				
			case R.id.post_3_btn_to1:
				Intent to1 = new Intent();
				to1.setClass(Post_3.this, Post_1.class);
				startActivity(to1);
				finish();
				break;
				
			case R.id.post_3_btn_to2:
				Intent to2 = new Intent();
				to2.setClass(Post_3.this, Post_2.class);
				startActivity(to2);
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
			if(txv_time.getText().toString().trim().equals(""))
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
			Intent to2 = new Intent();
			to2.setClass(Post_3.this, Post_2.class);
			startActivity(to2);
			finish();
		
			return true;
		}
		else
			return false;
	}
}