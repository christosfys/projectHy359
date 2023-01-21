
package mainClasses;

import java.io.BufferedReader;
import java.io.IOException;
import com.google.gson.Gson;
import mainClasses.User;
/**
 *
 * @author micha
 */
public class JSON_Converter {
    
    public String getJSONFromAjax(BufferedReader reader) throws IOException{
	StringBuilder buffer = new StringBuilder();
	String line;
	while ((line = reader.readLine()) != null) {
		buffer.append(line);
	}
	String data = buffer.toString();
	return data;
}

 
    
}
