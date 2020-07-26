import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JTextField;

import org.json.JSONException;
import org.json.JSONObject;

public class Creator 
{
	private JTextField textField;
	private ArrayList<String> patternList=new ArrayList<String>();
	private JSONObject jObject=Connector.getInstance().getObject();
	public Creator(JTextField textField)
	{
		this.textField=textField;
		
	}
	
	public ArrayList<String> getPattern()
	{
		return this.patternList;
	}
	
	public void changeElement(String element,int index)
	{
	//	System.out.println(index);
		if(patternList.isEmpty()==true || patternList.size()-1<index)
		{
			patternList.add(element);
		}
		else
		{
			patternList.set(index, element);
		}		
	}
	
	public void removeElement(int index)
	{
		patternList.remove(index);
	}
	
	public String getOutput()
	{
		String output = "";
		for(int i=0;i<patternList.size();i++)
		{
			output+=patternList.get(i);
		}
		System.out.println(output);
		return output;
	}
	
	public String getElement(String element)
	{
		try 
		{
		//	JSONObject jObject=connector.getObject();
			System.out.println("Element:"+element);
			return jObject.getString(element);
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public String[] getKeys(int index)
	{
		
		Iterator iterator=jObject.keys();
		System.out.println("JSONArray length:"+jObject.length());
		String[] model = new String[jObject.length()];
		int i=0;
		while(iterator.hasNext())
		{
			model[i]=(String)iterator.next();
			i++;
		}		
		return model;
		}
		
	}

