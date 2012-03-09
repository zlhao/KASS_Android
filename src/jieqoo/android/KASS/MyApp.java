package jieqoo.android.KASS;

import java.util.Hashtable;
import android.app.Application;

public class MyApp extends Application {
	@Override  
	public void onCreate() {   
	super.onCreate();   
	setUserName(""); //��ʼ��ȫ�ֱ���   
	}   

	
	//�û���
	private String username="";
	private boolean signIN=false;
	public void setUserName(String name)
	{
		username=name.toString();
	}
	public String getUserName()
	{
		return username.toString();
	}
	
	//������Ϣ
	
	//�쳣��Ϣ���
	private Hashtable errors = new Hashtable();	
	public void setErrors(Hashtable errors)
	{
		this.errors=errors;
	}	
	public String getError(String id)
	{
		return errors.get(id).toString();
	}
	
	//��Ϣ����
	String title,content,address,price,time;
	public void clearWants()
	{		
		title="";
		content="";
		address="";
		price="";
		time="";
	}
	public void setWant(String title1,String content1,String address1,String price1,String time1)
	{
		title=title1;
		content=content1;
		address=address1;
		price=price1;
		time=time1;
	}
	public void setTitle(String title1){	title=title1;	}
	public String getTitle(){	return title;	}
	public void setContent(String content1){	content=content1;	}
	public String getContent(){	return content;	}
	public void setAddress(String address1){	address=address1;	}
	public String getAddress(){	return address;	}
	public void setPrice(String price1){	price=price1;	}
	public String getPrice(){	return price;	}
	public void setTime(String time1){	time=time1;	}
	public String getTime(){	return time;	}
}