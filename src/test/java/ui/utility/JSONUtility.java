package ui.utility;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import ui.constants.Env;
import ui.pojos.Config;
import ui.pojos.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class JSONUtility {
	
	public static String readJSON(Env env)  {
		
		Gson gson = new Gson();
		File jsonFile = new File(System.getProperty("user.dir") + "//config//config.json");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(jsonFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Config config = gson.fromJson(fileReader, Config.class);
		Environment environment = config.getEnvironments().get(env.toString().toUpperCase());
		return environment.getUrl();
	}

	private static final ObjectMapper objectMapper = new ObjectMapper();

	// Generic method to read JSON and return a Map
	public static Map<String, Object> readJsonAsMap(String filePath) throws IOException {
		return objectMapper.readValue(new File(filePath), Map.class);
	}

	// Generic method to read JSON and return a JsonNode (for flexible access)
	public static JsonNode readJsonAsTree(String filePath) throws IOException {
		return objectMapper.readTree(new File(filePath));
	}

	// Generic method to read JSON and convert it into a given class type
	public static <T> T readJsonAsObject(String filePath, Class<T> clazz) throws IOException {
		return objectMapper.readValue(new File(filePath), clazz);
	}

}
