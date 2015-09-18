package logic;

//import java.io.FileInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//Clase que permite leer el archivo properties.
public class ReadScores {
	
	public static ReadScores file = null;
	public static Properties props = new Properties();
	InputStream input =null;
	
	private ReadScores(){
		
		try{
			input= new FileInputStream("scores.properties");
			props.load(input);
		}
		catch (IOException e){
			e.printStackTrace();
		}
	}
	
	//Instancia el Archivo Properties |Solo se instancia una vez|
	public static synchronized ReadScores getInstance(){
		if (file ==null){
			file = new ReadScores();
		}
		return file;
	}
	
	public String getSetting(String key){
		return props.getProperty(key);
	}

}
