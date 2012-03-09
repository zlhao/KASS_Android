package jieqoo.android.KASS;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Main extends TabActivity {
	
	//定义
	TabHost tabHost;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);         
        setContentView(R.layout.main);
 
        //自动登录
        SharedPreferences  setting=this.getSharedPreferences("auto_log",2);
        if(setting.getInt("auto_log",0)==1)
        {
        //获取用户信息
         String name_store=setting.getString("name","");
         //String passwd_store=setting.getString("password", "");

        MyApp myapp=((MyApp)getApplicationContext());
        myapp.setUserName(name_store);
        }
        
		//获取出错信息
        
        builtTabhost();		
	}
	
	@Override
	public void onResume()
	{
		super.onResume();
		
		RadioGroup ragp=(RadioGroup)findViewById(R.id.main_rgrop_maintab);
		ragp.check(R.id.main_rbtn_MyActivity);
		
	}
	
	public void builtTabhost()
	{		
		tabHost=this.getTabHost();
        
        TabSpec ts1=tabHost.newTabSpec("@string/MyActivity").setIndicator("@string/MyActivity");
        ts1.setContent(new Intent(Main.this,MyActivity.class));
        tabHost.addTab(ts1);
        
        TabSpec ts2=tabHost.newTabSpec("@string/Post").setIndicator("@string/Post");
        ts2.setContent(new Intent(Main.this,Post.class));
        tabHost.addTab(ts2);
        
        TabSpec ts3=tabHost.newTabSpec("@string/Browse").setIndicator("@string/Browse");
        ts3.setContent(new Intent(Main.this,Browse.class));
        tabHost.addTab(ts3);
        
        TabSpec ts4=tabHost.newTabSpec("@string/SignIn").setIndicator("@string/SignIn");
        ts4.setContent(new Intent(Main.this,MyProfile.class));
        tabHost.addTab(ts4);
        
        RadioGroup radioGroup=(RadioGroup)findViewById(R.id.main_rgrop_maintab);

        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
		
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				
				switch(checkedId){
				case R.id.main_rbtn_MyActivity:
					tabHost.setCurrentTabByTag("@string/MyActivity");
					break;				
				case R.id.main_rbtn_Post:
					tabHost.setCurrentTabByTag("@string/Post");
					break;
				case R.id.main_rbtn_Browse:
					tabHost.setCurrentTabByTag("@string/Browse");
					break;
				case R.id.main_rbtn_SignIn:					
					tabHost.setCurrentTabByTag("@string/SignIn");
					break;
				}
			}
		});
        
	}
	
	
}
