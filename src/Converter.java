import java.util.HashMap;

public class Converter 
{
	HashMap<String,String> container=new HashMap<String,String>();
	public Converter()
	{
		
		container.put("Starts", "$");
		container.put("Ends", "#");
	}
	
	
	public 	String getElement(String regItem)
	{
		return container.get(regItem);
	}
}
