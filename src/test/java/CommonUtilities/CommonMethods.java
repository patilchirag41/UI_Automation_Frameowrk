package CommonUtilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommonMethods {
	    private static Properties properties = new Properties();
	    // Static method to load the properties file
	    public static void loadProperties(String path) {
	        try (InputStream input = new FileInputStream(path)) {
	            properties.load(input);
	        } catch (IOException ex) {
	            ex.printStackTrace();
	            // Optionally, handle the exception or rethrow
	        }
	    }

	    //static method to get a property value
	    public static String getProperty(String propertiesFile,String propertyName) {
		 String  path = System.getProperty("user.dir")+"src/test/resources/Configuration/"+propertiesFile+".properties";
	      loadProperties(path);
	     String property =  properties.getProperty(propertyName);
	     return property;
	    }

	}


