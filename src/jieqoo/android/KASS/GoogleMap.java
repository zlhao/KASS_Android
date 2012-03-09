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
	//����
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
        
    	openGPSSettings();//�ж��Ƿ���GPS
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
     * �ж��Ƿ���GPS
     */
    private void openGPSSettings() {
        LocationManager alm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            Toast.makeText(this, "GPSģ������", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "�뿪��GPS��", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
        startActivityForResult(intent,0); //��Ϊ������ɺ󷵻ص���ȡ����
    }
    
    /**
     * ���õ�ͼ��ʾ
     */
    private void setMapView(){
         this.mapView = (MapView) this.findViewById(R.id.googlemap_map_mapview);
         this.mapView.setBuiltInZoomControls(true);//���Զ�㴥���Ŵ� 
         mapView.setStreetView(true);
         mapView.setBuiltInZoomControls(true);
         this.mapController = mapView.getController(); 
         mapController.setZoom(14);
         
         Drawable drawable = this.getResources().getDrawable(R.drawable.ic_launcher);
         //����ͼƬ�Ļ��������С
         drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
         this.mapController.animateTo(gp);//ͨ��������ʽ�ƶ���ָ������ 
         setView();//���õ�����
         MyOverlay myOverlay = new MyOverlay(drawable,this);
         myOverlay.addOverlay(new OverlayItem(gp, "hello", "i'm in Athens,Greece!"));
         mapView.getOverlays().add(myOverlay);
    }
    
    /**
     * ���õ�ͼ��ǵ�������ʽ
    */ 
    private void setView(){
        //popView��Ϊnull,������ˢ�����µ���setMapView��ʱ�������´���popView,����ԭ��popView�ļ����¼���������
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
     * ��ȡλ����Ϣ
     */
    private void getLocation(){
        // ��ȡλ�ù������
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) this.getSystemService(serviceName);
        // ���ҵ�������Ϣ
        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // �߾���
        criteria.setAltitudeRequired(false);          //��Ҫ�󺣰���Ϣ
        criteria.setBearingRequired(false);              //��Ҫ��λ��Ϣ
        criteria.setCostAllowed(true);                  //�Ƿ�������
        criteria.setPowerRequirement(Criteria.POWER_LOW); // �͹���
        String provider = locationManager.getBestProvider(criteria, true); // ��ȡGPS��Ϣ
        location = locationManager.getLastKnownLocation(provider); // ͨ��GPS��ȡλ��
        updateToNewLocation(location);
        // ���ü��������Զ����µ���Сʱ��Ϊ���N��(1��Ϊ1*1000������д��ҪΪ�˷���)����Сλ�Ʊ仯����N��
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
     * ��ȡGeoPoint
     */
    private void updateToNewLocation(Location location) {
        if(true){
            double latitude = 31.16520805*1E6;
            double longitude = 121.4000644*1E6;
//        if (location != null) {
//            double latitude = location.getLatitude()*1E6;  
//            double longitude = location.getLongitude()*1E6;  
            gp = new GeoPoint((int) latitude, (int) longitude); 
            txt_location.setText("ά�ȣ�" +  latitude+ "\n����" + longitude);
        } else {
        	txt_location.setText("�޷���ȡ������Ϣ");
        }
    }
    
    /**
     * ����λ����Ϣ��������
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
     * �Զ���Handler
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
            txt_location.setText(i+": "+"ά�ȣ�" +  lat+ "\n����" + lon);
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

