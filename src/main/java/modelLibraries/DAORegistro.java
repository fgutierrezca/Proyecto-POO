package modelLibraries;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import programLibraries.FileManager;
import programLibraries.FileManagerResponse;

/**
 * 
 * @author fgutierrezc@unah.hn
 * @version 0.1.0
 * @date 03/05/2023
 * @description Esta clase se encarga de crear el registro en el model.csv y devuelve el json correspondiente
 *
 */

public class DAORegistro {

	public DAORegistro() {
		// TODO Auto-generated constructor stub
	}
	
	//MÉTODOS PARA LA INICIALIZACIÓN O ACTUALIZACIÓN DE LA PÁGINA
	
	/**
	 * 
	 * @param fm
	 * @return FileManagerResponse, devuelve el contenido del modelo de datos
	 */
	
	private FileManagerResponse extraerModelCSV(FileManager fm) {
		
		FileManagerResponse fmr = fm.read(fm.path());
		
		if(fmr.getStatus()) {
			return fmr;
		}else {
			fmr = fm.write(fm.path(), "");
		}
		
		return fmr;
	}
	
	/**
	 * 
	 * @param fmr
	 * @param session
	 * @return String, analiza el contenido guardado en el csv antes de ser enviado al FrontEnd
	 */

	private String modelAnalisis(FileManagerResponse fmr, HttpSession session) {
		
		Validator validator = new Validator();
		
		StringBuilder result = new StringBuilder("[");
		
		String[] columns = fmr.getContent().split("\n");
		String[] datos;
		
		DTORegistro dto = new DTORegistro();
		
		for(int i=0; i<columns.length; i++) {
			dto.setTotalTwitts(dto.getTotalTwitts() + 1);
			
			datos = columns[i].split(",");
			
			if(Integer.parseInt(datos[5]) >= 100000) {
				dto.setComments(dto.getComments() + 1);
			}
			
			if(Integer.parseInt(datos[6]) >= 100000) {
				dto.setShares(dto.getShares() + 1);
			}
			
			if(Integer.parseInt(datos[7]) >= 100000) {
				dto.setLikes(dto.getLikes() + 1);
			}
			
			if(Integer.parseInt(datos[8]) >= 100000) {
				dto.setVisual(dto.getVisual() + 1);
			}

			if(i == columns.length-1) {
				result.append(String.format("{"));
				result.append(String.format("\"Nombre\":\"%s\",", validator.postClean(datos[0])));
				result.append(String.format("\"Cuenta\":\"%s\",", validator.postClean(datos[1])));
				String text = validator.postClean(datos[4]);
				result.append(String.format("\"Contenido\":\"%s\",", validator.cortarTexto(text)));
				result.append(String.format("\"identificador\":\"%s\"", i+1));
				result.append(String.format("}"));
			}else {
				result.append(String.format("{"));
				result.append(String.format("\"Nombre\":\"%s\",", validator.postClean(datos[0])));
				result.append(String.format("\"Cuenta\":\"%s\",", validator.postClean(datos[1])));
				String text = validator.postClean(datos[4]);
				result.append(String.format("\"Contenido\":\"%s\",", validator.cortarTexto(text)));
				result.append(String.format("\"identificador\":\"%s\"", i+1));
				result.append(String.format("},"));
			}
			
		}
		
		session.setAttribute(Config.DATOS, dto);
		
		result.append(String.format("]"));
		
		return result.toString();
	}
	
	/**
	 * 
	 * @param fm
	 * @param session
	 * @return String, devuelve el JSON que se enviará de nuevo al FrontEnd post análisis del contenido del csv
	 */
	
	public String returnJSON(FileManager fm, HttpSession session) {
		
		FileManagerResponse fmr = extraerModelCSV(fm);
		
		StringBuilder result = new StringBuilder("{");
		
		if(fmr.getContent().length() > 5) {
			String data = modelAnalisis(fmr, session);
			
			DTORegistro dto = (DTORegistro) session.getAttribute(Config.DATOS);
			
			result.append("\"status\":true,");
			result.append(String.format("\"TotalTwitts\":\"%s\",", dto.getTotalTwitts()));
			result.append(String.format("\"TotalComments\":\"%s\",", dto.getComments()));
			result.append(String.format("\"TotalShares\":\"%s\",", dto.getShares()));
			result.append(String.format("\"TotalLikes\":\"%s\",", dto.getLikes()));
			result.append(String.format("\"TotalVisual\":\"%s\",", dto.getVisual()));
			result.append(String.format("\"services\":%s", data));
		}else {
			result.append("\"status\":false");
		}
		
		
		result.append("}");
		
		return result.toString();
	}
	
	//MÉTODOS PARA EL REGISTRO DE DATOS
	
	/**
	 * 
	 * @param request
	 * @param fm
	 * @return FileManagerResponse, que tiene incluido el último registro
	 */
	
	private FileManagerResponse includeModelCSV(HttpServletRequest request, FileManager fm) {
		
		Validator validator = new Validator();
		Enumeration<String> variables = request.getParameterNames();
		StringBuilder result = new StringBuilder("");
		
		int counter = 1;
		
		while(variables.hasMoreElements()) {
			
			String parameterName = variables.nextElement();
			String parameterValue = request.getParameter(parameterName);
			
			if(parameterName.matches("multimedia")) {
				result.append(String.format("%sURLSEPARATOR", parameterValue));
				counter++;
				continue;
			} 
				
			if(parameterName.matches("contentRes")) {
				result.append(String.format("%s,", validator.preClean(parameterValue)));
				System.out.print(validator.preClean(parameterValue));
				counter++;
				continue;
			}
			
			if(counter > 10) {
				result.append(String.format("%sURLSEPARATOR", parameterValue));
				counter++;
				continue;
			}
			
			result.append(String.format("%s,", parameterValue));
			
			counter++;
		}
		
		FileManagerResponse fmr = fm.write(fm.path(), result.toString());
		
		return fmr;
	}
	
	/**
	 * 
	 * @param fmr
	 * @param session
	 * @return String[], analiza la session cuando se haga un nuevo registro
	 */

	private String[] sessionAnalisis(FileManagerResponse fmr, HttpSession session) {
		
		String[] columns = fmr.getContent().split("\n");
		String[] datos;
		
		if(session.getAttribute(Config.DATOS) != null) {
			DTORegistro dto = (DTORegistro) session.getAttribute(Config.DATOS);
			
			dto.setTotalTwitts(dto.getTotalTwitts() + 1);
			
			datos = columns[columns.length-1].split(",");
			
			if(Integer.parseInt(datos[5]) >= 100000) {
				dto.setComments(dto.getComments() + 1);
			}
			
			if(Integer.parseInt(datos[6]) >= 100000) {
				dto.setShares(dto.getShares() + 1);
			}
			
			if(Integer.parseInt(datos[7]) >= 100000) {
				dto.setLikes(dto.getLikes() + 1);
			}
			
			if(Integer.parseInt(datos[8]) >= 100000) {
				dto.setVisual(dto.getVisual() + 1);
			}
			
			session.setAttribute(Config.DATOS, dto);
		}else {
			
			DTORegistro dto = new DTORegistro();
			dto.setTotalTwitts(1);
			
			datos = columns[columns.length-1].split(",");
			
			if(Integer.parseInt(datos[5]) >= 100000) {
				dto.setComments(1);
			}
			
			if(Integer.parseInt(datos[6]) >= 100000) {
				dto.setShares(1);
			}
			
			if(Integer.parseInt(datos[7]) >= 100000) {
				dto.setLikes(1);
			}
			
			if(Integer.parseInt(datos[8]) >= 100000) {
				dto.setVisual(1);
			}
			
			session.setAttribute(Config.DATOS, dto);
		}
		
		return datos;
	}
	
	/**
	 * 
	 * @param request
	 * @param fm
	 * @param session
	 * @return String, devuelve el JSON que se enviará de nuevo al FrontEnd post ingreso de un nuevo registro
	 */
	
	public String returnJSON(HttpServletRequest request, FileManager fm, HttpSession session) {
		Validator validator = new Validator();
		
		FileManagerResponse fmr = includeModelCSV(request, fm);
		
		String datos[] = sessionAnalisis(fmr, session);
		
		StringBuilder result = new StringBuilder("{");
		
		DTORegistro dto = (DTORegistro) session.getAttribute(Config.DATOS);
		
		if(fmr.getStatus()) {
			result.append("\"status\":true,");
			result.append(String.format("\"Nombre\":\"%s\",", validator.postClean(datos[0])));
			result.append(String.format("\"Cuenta\":\"%s\",", validator.postClean(datos[1])));
			String text = validator.postClean(datos[4]);
			result.append(String.format("\"Contenido\":\"%s\",", validator.cortarTexto(text)));
			result.append(String.format("\"TotalTwitts\":\"%s\",", dto.getTotalTwitts()));
			result.append(String.format("\"TotalComments\":\"%s\",", dto.getComments()));
			result.append(String.format("\"TotalShares\":\"%s\",", dto.getShares()));
			result.append(String.format("\"TotalLikes\":\"%s\",", dto.getLikes()));
			result.append(String.format("\"TotalVisual\":\"%s\"", dto.getVisual()));
		}else {
			result.append("\"status\":false,");
			result.append("\"message\":\"No se pudo procesar una respuesta\"");
		}
		result.append("}");
		return result.toString();
	}
	
	//MÉTODOS PARA LA DESCRIPCIÓN DE UN REGISTRO EN ESPECÍFICO
	
	//Se usa el mismo método extraermodelcsv

	/**
	 * 
	 * @param fmr
	 * @param id
	 * @return String, devuelve el contenido del registro en el modelo de datos
	 */
	
	private String busquedaRegistro(FileManagerResponse fmr, String id) {
		
		Validator validator = new Validator();
		
		StringBuilder result = new StringBuilder("");
		
		String[] columns = fmr.getContent().split("\n");
		String[] datos;
		
		int var = Integer.parseInt(id) - 1;
		
		DTORegistro dto = new DTORegistro();
		
		for(int i=0; i<columns.length; i++) {
			
			if(i == var) {
				datos = columns[i].split(",");
				
				System.out.println(datos.length);
				
				result.append(String.format("\"nombre\":\"%s\",", datos[0]));
				result.append(String.format("\"cuenta\":\"%s\",", datos[1]));
				result.append(String.format("\"creacion\":\"%s\",", datos[2]));
				result.append(String.format("\"registro\":\"%s\",", datos[3]));
				result.append(String.format("\"content\":\"%s\",", validator.postClean(datos[4])));
				result.append(String.format("\"comments\":\"%s\",", datos[5]));
				result.append(String.format("\"retwitts\":\"%s\",", datos[6]));
				result.append(String.format("\"likes\":\"%s\",", datos[7]));
				result.append(String.format("\"visual\":\"%s\",", datos[8]));
				result.append(String.format("\"multimedia\":\"%s\"", datos[9].trim()));
			}

		}
		
		return result.toString();
	}
	
	/**
	 * 
	 * @param fm
	 * @param id
	 * @return String, retorna el JSON con todos los datos del registro en específico
	 */
	
	public String returnJSON(FileManager fm, String id) {
		
		FileManagerResponse fmr = extraerModelCSV(fm);
		
		String data = busquedaRegistro(fmr, id);
		
		StringBuilder result = new StringBuilder("{");
		
		result.append("\"status\":true,");
		result.append(data);
		
		result.append("}");
		
		return result.toString();
	}
	
	//MÉTODOS USADOS PARA LA LIMPIEZA DEL MODELOS DE DATOS
	
	/**
	 * 
	 * @param fm
	 * @param session
	 * @return boolean, si se logra limpiar el modelo correctamente se envía true, en su contrario false
	 */
	
	private boolean limpiar(FileManager fm, HttpSession session) {
		try {
			File file = new File(fm.path());
	        FileWriter fw = new FileWriter(file);
	        BufferedWriter bw = new BufferedWriter(fw);
	        
	        bw.write("");
	        bw.close();
	        
	        DTORegistro dto = new DTORegistro();
	        session.setAttribute(Config.DATOS, dto);
	        
	        String fileReplace = fm.path().replaceAll("(\\/\\w+.*)*\\/", "");
	        String filePath = fm.path().trim().replace(fileReplace, "");
	        
	        DateFormat dateform = new SimpleDateFormat("yyyy-MMMM-d-HH-mm-ss");
	        String url = String.format("%sRemovidoEl-%s.log", filePath, dateform.format(new Date()));
	        
	        FileManagerResponse fmr = fm.write(url, "");
	        
	        return true;
		}catch(Exception e) {
			System.out.print("No se pudo procesar");
		}
		
		return false;
	}
	
	/**
	 * 
	 * @param fm
	 * @param session
	 * @return String, los datos restaurados de la session, es decir 0
	 */
	
	public String datosRestauradosJSON(FileManager fm, HttpSession session) {
		
		boolean response = limpiar(fm, session);
		
		StringBuilder result = new StringBuilder("{");
		
		DTORegistro dto = (DTORegistro) session.getAttribute(Config.DATOS);
		
		if(response) {
			result.append("\"status\":true,");
			result.append(String.format("\"TotalTwitts\":\"%s\",", dto.getTotalTwitts()));
			result.append(String.format("\"TotalComments\":\"%s\",", dto.getComments()));
			result.append(String.format("\"TotalShares\":\"%s\",", dto.getShares()));
			result.append(String.format("\"TotalLikes\":\"%s\",", dto.getLikes()));
			result.append(String.format("\"TotalVisual\":\"%s\"", dto.getVisual()));
		}else {
			result.append("\"status\":false");
		}
		result.append("}");
		
		return result.toString();
	}
}