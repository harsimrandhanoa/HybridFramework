package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadTestsJson {

	public JSONArray getTestArray(String testName) throws FileNotFoundException, IOException, ParseException {

		String path = System.getProperty("user.dir") + "//src//test//resources//json//testcases//Testcases.json";
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(new FileReader(new File(path)));

		return (JSONArray) json.get(testName);
	}

}
