/**
 * Objectifs : renvoyer en String les diff�rentes couleurs pr�sentes sur la table de jeu.
 * 
 * Relations : aucune.
 * 
 * Classes utilis�es par TableColor : aucune.
 * 
 * Classes utilisant TableColor : ColorSensor.
 * 
 * D�finitions des attributs: 	Blue, Red, Green, Yellow, Black, Grey et White sont des �num�ration de la classe TableColor et
 * 								color de type String renvoie la couleur d�tect�e.
 * 
 * Proc�dures externes : toString().
 */

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
	
	/**
	 * Return the detected color in a string.
	 */
	public String toString() {
		return this.color;
	}
}
