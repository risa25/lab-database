package computerScience;

import java.sql.Blob;

public class EquipmentStuff {
	private String name;
	private byte[] image;
	
//	public EquipmentStuff(String name, byte[] image) {
//		this.name = name;
///		this.image = image;
//	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public byte[] getTbImage() {
		return image;
	}
	
	public void setImage(byte[] image) {
		this.image = image;
	}
	
}
