import java.util.ArrayList;

import javax.swing.JTextField;

import org.json.JSONException;
import org.json.JSONObject;

public class Creator 
{
	private JTextField textField;
	private ArrayList<String> patternList=new ArrayList<String>();
	private Connector connector=Connector.getInstance();
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
		
		if(patternList.size()<=index)
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
			JSONObject jObject=connector.getObject();
			return jObject.getString(element);
		} 
		catch (JSONException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
