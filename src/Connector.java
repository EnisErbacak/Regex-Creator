import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class Connector 
{
	private final String PATH="C:\\Users\\Enis\\Home\\Eclipse\\workSpace\\Regex_Creator\\db.json";
	private File file=new File(PATH);
	private FileReader fileReader;
	private JSONTokener tokener;
	
	private static Connector connector=null;
	private Connector()
	{
		
	}
	
	public static Connector getInstance()
	{
		if(connector==null)
		{
			connector=new Connector();
			return connector;
		}
		else 
		{
			return null;
		}
	}
	
	public JSONObject getObject()
	{
		try {
			fileReader=new FileReader(PATH);
			tokener=new JSONTokener(fileReader);
			JSONObject jObject=new JSONObject(tokener);
			return jObject;
			
		} catch (FileNotFoundException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
}
