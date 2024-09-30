package programLibraries;

/**
 * @author gutierrezc
 * @date 21/03/2023
 * @version 0.1.0
 * @description Esta clase contiene los métodos de la interfaz IFileManager y es la que hace el proceso de lectura del archivo txt
 */

import java.io.*;

public class FileManager implements IFileManager{
	
	private String path;

	public FileManager() {
		// TODO Auto-generated constructor stub
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String path() {
		return this.path;
	}
	
	public FileManagerResponse read(String fileName) {
		
		FileManagerResponse fmResponse = new FileManagerResponse();
		
		String fileReplace = fileName.replaceAll("(\\/\\w+.*)*\\/", "");
		StringBuilder result = new StringBuilder("");
		
		try {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));		
			String[] path = fileReplace.split("\\.");
			
			fmResponse.setStatus(true);
			fmResponse.setName(path[0]);
			fmResponse.setExtension(path[1]);
			
			String linea;
            while ((linea = in.readLine()) != null) {
                result.append(linea);
                result.append("\n");
            }
            
            fmResponse.setContent(result.toString());
			this.path = fileName;
			
		}catch(Exception e) {
			
			System.out.println("No se encontró el archivo");
			fmResponse.setStatus(false);
		
		}
		
		return fmResponse;
	}
	
	public FileManagerResponse write(String fileName, String content) {
		
		FileManagerResponse fmResponse = new FileManagerResponse();
		
		String fileReplace = fileName.replaceAll("(\\/\\w+.*)*\\/", "");
		StringBuilder result = new StringBuilder("");
		
		try {
			
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));		
			String[] path = fileReplace.split("\\.");
			
			fmResponse.setStatus(true);
			fmResponse.setName(path[0]);
			fmResponse.setExtension(path[1]);
			
			String linea;
            while ((linea = in.readLine()) != null) {
                result.append(linea);
                result.append("\n");
            }
            result.append(content);
            
            fmResponse.setContent(result.toString());
			this.path = fileName;
			
			File file = new File(fileName);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.write(result.toString());
            bw.close();
			
		}catch(Exception ex) {
			
			try {
            	
				String filePath = fileName.trim().replace(fileReplace, "");
                File fileCarpeta = new File(filePath);
				File fileArchive = new File(fileName);
                
                if (!fileCarpeta.exists()) {
					if (fileCarpeta.mkdirs()) {
						System.out.println("Directorio creado");
						fileArchive.createNewFile();
					} else {
						System.out.println("Error al crear directorio");
					}
                }

				FileWriter fw = new FileWriter(fileArchive);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(content);
				bw.close();

				BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));		
				String[] path = fileReplace.split("\\.");
				
				fmResponse.setStatus(true);
				fmResponse.setName(path[0]);
				fmResponse.setExtension(path[1]);
				
				String linea;
				while ((linea = in.readLine()) != null) {
					result.append(linea);
					result.append("\n");
				}
				
				fmResponse.setContent(result.toString());
				this.path = fileName;
                
            } catch (Exception e) {
            	
            	System.out.println("No se pudo completar la acción");
            	
            }
				
		}
		
		return fmResponse;
	
	}
	
}
