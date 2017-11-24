package monopoly;


import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;


public class FileLoader {
	private String fileName;
	private String fileText;
	
	
	
	
	public FileLoader(String fileName) {
	
		this.fileName = fileName;
		fileText = readFile();
	
	}
	
	
	public String readFile() 
			
	{
			  byte[] encoded = null;
			try {
				encoded = Files.readAllBytes(Paths.get(fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new String(encoded, Charset.defaultCharset());
	}


	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileText() {
		return fileText;
	}
	public void setFileText(String fileText) {
		this.fileText = fileText;
	}
	
	
	
	
}
