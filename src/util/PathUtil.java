package util;

import java.io.File;

public class PathUtil {

	public static String separator(){
		String os = System.getProperty("os.name").toLowerCase();  
		if (os.contains("win")) {
			return "/";
		}
		return File.separator;
		
	}
}
