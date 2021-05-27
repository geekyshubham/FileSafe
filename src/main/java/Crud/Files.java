package Crud;

public class Files {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFiledata() {
		return filedata;
	}
	public void setFiledata(String filedata) {
		this.filedata = filedata;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFilepass() {
		return filepass;
	}
	public void setFilepass(String filepass) {
		this.filepass = filepass;
	}
	protected int id;
	protected String filename;
	protected String filedata;
	protected String description;
	protected String filepass;
	
	/** ID
	FILE_NAME
	FILE_DATA
	DESCRIPTION
	FILE_PASSWORD
	userid **/

}