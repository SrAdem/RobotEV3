
public enum TableColor {
	Blue ("Blue"),
	Red ("Red"),
	Green ("Green"),
	Yellow ("Yellow"),
	Black ("Black"),
	Grey ("Grey"),
	White ("White");
	
	private String color = "";
	
	TableColor(String color) {
		this.color = color;
	}
	
	public String toString() {
		return this.color;
	}
}
