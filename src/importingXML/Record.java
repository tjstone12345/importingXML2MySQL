package importingXML;

public class Record {
	private String Name;
	private String Last_Ip_Address;
	private String Serial_Number;
	private String Resolution;

	public Record() {

	}

	public Record(String Name, String Last_Ip_Address, String Serial_Number, String Resolution) {
		this.Name = Name;
		this.Last_Ip_Address = Last_Ip_Address;
		this.Serial_Number = Serial_Number;
		this.Resolution = Resolution;
	}

	public String getName() {
		return Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getLastIPAddress() {
		return Last_Ip_Address;
	}

	public void setLastIPAddress(String LastIPAddress) {
		this.Last_Ip_Address = LastIPAddress;
	}

	public String getSerialNumber() {
		return Serial_Number;
	}

	public void setSerialNumber(String SerialNumber) {
		this.Serial_Number = SerialNumber;
	}

	public String getResolution() {
		return Resolution;
	}

	public void setResolution(String Resolution) {
		this.Resolution = Resolution;
	}

	public String toString() {
		return "Name : " + this.Name + "\nLast IP Address : " + this.Last_Ip_Address + "\nSerial Number : "
				+ this.Serial_Number + "\nResolution : " + this.Resolution;
	}
}
