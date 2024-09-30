package programLibraries;

public interface IFileManagerResponse {
	//Establece el estado de una respuesta: se pudo o no se pudo leer o escribir un archivo
	void setStatus(boolean status);
	//Obtiene el estado de una respuesta: se pudo o no se pudo leer o escribir un archivo
	boolean getStatus();
	//Establece el nombre del archivo leído o creado, sin extensión ni ruta
	void setName(String name); 
	//Obtiene el nombre del archivo leído o creado, sin extensión ni ruta
	String getName(); 
	//Establece la extensión del archivo leído o creado, sin ruta ni nombre
	void setExtension(String extension); 
	//Obtiene la extensión del archivo leído o creado, sin ruta ni nombre
	String getExtension(); 
	//Establece el contenido del archivo leído o creado
	void setContent(String content); 
	//Obtiene el contenido del archivo leído o creado
	String getContent(); 
}
