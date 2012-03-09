package jieqoo.android.KASS;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;

public class Post extends Activity implements OnClickListener {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.post);
		
		Button btn_new=( Button ) findViewById(R.id.post_btn_new);
		btn_new.setOnClickListener( this);
	}
	
	public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.post_btn_new:
				//数据集数据
				MyApp myapp=((MyApp)getApplicationContext());
				myapp.clearWants();
				
				Intent intent = new Intent();
				intent.setClass(Post.this, Post_1.class);
				startActivity(intent);
				break;
		}
	}
}
