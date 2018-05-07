package util;

import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class ShellUtil {
	
	
			/** 
		     * 运行shell 
		     *  
		     * @param shStr 
		     *            需要执行的shell 
		     * @return 
		     * @throws IOException 
		     */  
		    public static List runShell(String shStr) throws Exception {  
		        List<String> strList = new ArrayList();  
		  
		        Process process;  
		        process = Runtime.getRuntime().exec(new String[]{"/bin/sh","-c",shStr},null,null);  
		        InputStreamReader ir = new InputStreamReader(process  
		                .getInputStream());  
		        LineNumberReader input = new LineNumberReader(ir);  
		        String line;  
		        process.waitFor();  
		        while ((line = input.readLine()) != null){  
		            strList.add(line);  
		        }  
		        return strList;  
		    }  
}
