package modelLibraries;

/**
 * 
 * @author jose.inestroza@unah.edu.hn
 * @version 0.1.0
 * @date 02/05/2023
 * @description Esta clase se encarga de limpiar la información recibida y limpiarla al momento de enviarla, también cortar la información cuando se necesite crear una boleta del registro
 *
 */

public class Validator {

	public Validator() {
		// TODO Auto-generated constructor stub
	}

	public String preClean(String text) {
		text = text.replaceAll("<", "&lt");
		text = text.replaceAll(">", "&gt");
		text = text.replaceAll("\\{", "&lcub");
		text = text.replaceAll("\\}", "&rcub");
		text = text.replaceAll("\"", "&quot");
		text = text.replaceAll("\\,", "&comma");
		
		return text;
	}
	
	public String postClean(String text) {
		text = text.replaceAll("[{}]", "");
		text = text.replaceAll("\n", "<br>");
		text = text.replaceAll("\t", "&nbsp");
		text = text.replaceAll("&comma", "\\,");
		
		return text;
	}
	
	/**
	 * @co-author fgutierrezc@unah.hn 
	 * @param text
	 * @return String, con un texto recortado de 64 caracteres
	 */
	
	public String cortarTexto(String text) {
        String cortString = text.substring(0,63);
		
		return String.format("%s...", cortString);
	}
}
