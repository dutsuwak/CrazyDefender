package logic;

//import java.io.FileInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


//Clase que permite leer el archivo properties.
public class ReadProperties {
	
	public static ReadProperties file = null;
	public static Properties props = new Properties();
	InputStream input =null;
	
	private ReadProperties(){
		
		try{
			input= new FileInputStream("config.properties");
			props.load(input);
		}
		catch (IOException e){
			e.printStackTrace();
		}
		finally{
			if (input !=null){
				try{
					input.close();
				}
				catch (IOException ex){
					ex.printStackTrace();
				}
			}
		}
	}
	
	//Instancia el Archivo Properties |Solo se instancia una vez|
	public static synchronized ReadProperties getInstance(){
		if (file ==null){
			file = new ReadProperties();
		}
		return file;
	}
	
	public String getSetting(String key){
		return props.getProperty(key);
	}

}
