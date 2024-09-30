package programLibraries;

public interface IFileManager {
	//Imprime la ruta actual desde donde se están creando archivos
	String path(); 
	//Lee el contenido de un archivo de texto en una ruta relativa o absoluta "fileName"
	FileManagerResponse read(String fileName); 
	//Escribe el contenido de un archivo de texto en una ruta relativa o absoluta "fileName"
	FileManagerResponse write(String fileName, String content); 
}
