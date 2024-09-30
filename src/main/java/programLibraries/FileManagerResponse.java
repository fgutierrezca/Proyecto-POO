package programLibraries;

/**
 * @author gutierrezc
 * @date 21/03/2023
 * @version 0.1.0
 * @description Esta clase contiene los métodos de la interfaz IFileManagerResponse
 */

public class FileManagerResponse implements IFileManagerResponse{

	private boolean status;
	private String name;
	private String extension;
	private String content;
	
	public FileManagerResponse() {
		// TODO Auto-generated constructor stub
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
}
