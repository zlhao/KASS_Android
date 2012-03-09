package jieqoo.android.KASS;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class GoogleMap extends MapActivity implements OnClickListener{
	//定义
	private LocationListener locationListener;
	TextView txt_location;
	GeoPoint gp;
	MapView mapView;
	EditText txt_address;
	Button btn_ok;
	private Criteria criteria;
	View popView;
	private Handler mHandler;
	private Location location;
	private MapController mapController;
	private LocationManager locationManager;
	int i;
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.googlemap);
        
        controlsInit();
    }
    
    @Override
	public void onResume()
	{
		super.onResume();
		
		MyApp myapp=((MyApp)getApplicationContext());
  		txt_address.setText((myapp).getAddress());
	}
    
    public void controlsInit()
    {
    	btn_ok=( Button ) findViewById(R.id.googlemap_btn_ok);
        btn_ok.setOnClickListener(this);
        
        txt_address=(EditText) findViewById(R.id.googlemap_txt_address);
        
    	openGPSSettings();//判断是否开启GPS
    	//mHandler = new MyHandler();
    	//mHandler.post(runnable);
    	//getLocation();
    	//setMapView();
    }
    
    public void onClick(View v)
	{
		switch(v.getId())
		{
			case R.id.googlemap_btn_ok:
				
				MyApp myapp=((MyApp)getApplicationContext());
		  		myapp.setAddress(txt_address.getText().toString().trim());
		  		
				finish();
				break;
		}
	}
      
    /**
     * 判断是否开启GPS
     */
    private void openGPSSettings() {
        LocationManager alm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "GPS模块正常", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "请开启GPS！", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
        startActivityForResult(intent,0); //此为设置完成后返回到获取界面
    }
    
    /**
     * 设置地图显示
     */
    private void setMapView(){
         this.mapView = (MapView) this.findViewById(R.id.googlemap_map_mapview);
         this.mapView.setBuiltInZoomControls(true);//可以多点触摸放大 
         mapView.setStreetView(true);
         mapView.setBuiltInZoomControls(true);
         this.mapController = mapView.getController(); 
         mapController.setZoom(14);
         
         Drawable drawable = this.getResources().getDrawable(R.drawable.ic_launcher);
         //设置图片的绘制区域大小
         drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
         this.mapController.animateTo(gp);//通过动画方式移动到指定坐标 
         setView();//设置弹出框
         MyOverlay myOverlay = new MyOverlay(drawable,this);
         myOverlay.addOverlay(new OverlayItem(gp, "hello", "i'm in Athens,Greece!"));
         mapView.getOverlays().add(myOverlay);
    }
    
    /**
     * 设置地图标记弹出框样式
    */ 
    private void setView(){
        //popView不为null,即界面刷新重新调用setMapView的时候不用重新创建popView,否则原来popView的监听事件不启作用
        if(popView==null){
            popView = super.getLayoutInflater().inflate(R.layout.pop, null); 
            mapView.addView(popView,  
                      new MapView.LayoutParams(MapView.LayoutParams.WRAP_CONTENT, MapView.LayoutParams.WRAP_CONTENT,  
                    null, MapView.LayoutParams.BOTTOM_CENTER));  
            popView.setVisibility(View.GONE);  
        }
    }
    

    @Override
    protected boolean isRouteDisplayed() {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * 获取位置信息
     */
    private void getLocation(){
        // 获取位置管理服务
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) this.getSystemService(serviceName);
        // 查找到服务信息
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // 高精度
        criteria.setAltitudeRequired(false);          //不要求海拔信息
        criteria.setBearingRequired(false);              //不要求方位信息
        criteria.setCostAllowed(true);                  //是否允许付费
        criteria.setPowerRequirement(Criteria.POWER_LOW); // 低功耗
        String provider = locationManager.getBestProvider(criteria, true); // 获取GPS信息
        location = locationManager.getLastKnownLocation(provider); // 通过GPS获取位置
        updateToNewLocation(location);
        // 设置监听器，自动更新的最小时间为间隔N秒(1秒为1*1000，这样写主要为了方便)或最小位移变化超过N米
        locationListener = new LocationListener(){

            @Override
            public void onLocationChanged(Location location) {
                updateToNewLocation(location);
            }

            @Override
            public void onProviderDisabled(String provider) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void onProviderEnabled(String provider) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void onStatusChanged(String provider, int status,
                    Bundle extras) {
                // TODO Auto-generated method stub
                
            }
            
        };
        locationManager.requestLocationUpdates(provider, 100 * 1000, 500,locationListener);
    }
    /**
     * 获取GeoPoint
     */
    private void updateToNewLocation(Location location) {
        if(true){
            double latitude = 31.16520805*1E6;
            double longitude = 121.4000644*1E6;
//        if (location != null) {
//            double latitude = location.getLatitude()*1E6;  
//            double longitude = location.getLongitude()*1E6;  
            gp = new GeoPoint((int) latitude, (int) longitude); 
            txt_location.setText("维度：" +  latitude+ "\n经度" + longitude);
        } else {
        	txt_location.setText("无法获取地理信息");
        }
    }
    
    /**
     * 发送位置信息到服务器
     */
    Runnable runnable = new Runnable(){
        @Override
        public void run() {
            String provider = locationManager.getBestProvider(criteria, true); 
            location = locationManager.getLastKnownLocation(provider); 
//            double lat = location.getLatitude()*1E6;
//            double lon = location.getLongitude()*1E6;
            double lat = 31.16520805*1E6;
            double lon = 121.4000644*1E6;
            mHandler.postDelayed(runnable, 10*1000);
            Message message = mHandler.obtainMessage();
            message.arg1 = (int)lat;
            message.arg2 = (int)lon;
            mHandler.sendMessage(message);
        }
    };

    /**
     * 自定义Handler
     */
	class MyHandler extends Handler{
        public void handleMessage(Message msg) { 
             i++;
            super.handleMessage(msg);
            int lat = msg.arg1;
            int lon = msg.arg2;
            gp = new GeoPoint((int) lat, (int) lon); 
            mapController.setCenter(gp);
            setMapView();
            mapView.invalidate();
            txt_location.setText(i+": "+"维度：" +  lat+ "\n经度" + lon);
            //tv1.invalidate();
        };   
    }
	
	
}

class MyOverlay extends ItemizedOverlay{
	private ArrayList<OverlayItem> mapOverlays = new ArrayList<OverlayItem>();
	private GoogleMap context;
	private TextView textView1,textView2;
	
	public MyOverlay(Drawable arg0) 
	{        
		super(arg0);        // TODO Auto-generated constructor stub
	}
	
	public MyOverlay(Drawable defaultMarker, Context context)
	{
		this(defaultMarker);
		this.context = (GoogleMap)context;
	}
	
	@Override
	protected OverlayItem createItem(int arg0)
	{        // TODO Auto-generated method stub
		return mapOverlays.get(arg0);
	}
	
	@Override
	public int size()
	{        // TODO Auto-generated method stub
		return mapOverlays.size();
	}
	
	protected boolean onTap(int index)
	{
		MapView.LayoutParams geoLP = (MapView.LayoutParams)context.popView.getLayoutParams();
		geoLP.point = mapOverlays.get(index).getPoint();
		context.mapView.updateViewLayout(context.popView, geoLP);
		context.popView.setVisibility(View.VISIBLE);
		textView1 = (TextView) context.popView.findViewById(R.id.map_bubbleTitle);
		textView2 = (TextView) context.popView.findViewById(R.id.map_bubbleText);
		textView1.setText(mapOverlays.get(index).getTitle());
		textView2.setText(mapOverlays.get(index).getSnippet());
		context.popView.setVisibility(View.VISIBLE);
		ImageView imageView = (ImageView) context.popView.findViewById(R.id.map_bubbleImage);
		imageView.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				context.popView.setVisibility(View.GONE);
			}
		});
		return true;
	}           
	public void addOverlay(OverlayItem overlay)
	{
		mapOverlays.add(overlay);
		populate();
	}
}

