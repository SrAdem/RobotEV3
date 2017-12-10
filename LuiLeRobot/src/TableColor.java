/**
 * Objectifs : renvoyer en String les différentes couleurs présentes sur la table de jeu.
 * 
 * Relations : aucune.
 * 
 * Classes utilisées par TableColor : aucune.
 * 
 * Classes utilisant TableColor : ColorSensor.
 * 
 * Définitions des attributs: 	Blue, Red, Green, Yellow, Black, Grey et White sont des énumération de la classe TableColor et
 * 								color de type String renvoie la couleur détectée.
 * 
 * Procédures externes : toString().
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
