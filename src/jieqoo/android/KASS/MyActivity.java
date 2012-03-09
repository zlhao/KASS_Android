package jieqoo.android.KASS;

import android.app.TabActivity;
import android.os.Bundle;
import android.widget.TabHost;


public class MyActivity extends TabActivity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.myactivity);
		
		TabHost tabHost=this.getTabHost();
        
		String want=(String) this.getText(R.string.Iwant);
		String provide=(String) this.getText(R.string.Iprovide);
		tabHost.addTab(tabHost.newTabSpec(want)
        		.setIndicator(want,getResources().getDrawable(R.drawable.ic_launcher))
        		.setContent(R.id.myactivity_tab1));
        
		tabHost.addTab(tabHost.newTabSpec(provide)
        		.setIndicator(provide,getResources().getDrawable(R.drawable.ic_launcher))
        		.setContent(R.id.myactivity_tab2));

	}
}