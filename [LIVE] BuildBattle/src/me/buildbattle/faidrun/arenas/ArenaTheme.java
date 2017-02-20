package me.buildbattle.faidrun.arenas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArenaTheme {
	
	private List<String> themes = new ArrayList<String>();
	public static String theme;
	
	public ArenaTheme() {
		themes.add("Voiture");
		themes.add("Maison");
		themes.add("Chameau");
		themes.add("Cuisine");
		themes.add("Frigo");
		themes.add("Souris");
		themes.add("Bateau");
		themes.add("Pokémon");
		themes.add("Ordinateur");
		themes.add("Carotte");
		themes.add("Vache");
		themes.add("Plage");
		themes.add("Ile");
		themes.add("Java");
		themes.add("Foot");
		themes.add("Squelette");
		themes.add("Télévision");
		themes.add("Singe");
		themes.add("Banane");
		themes.add("Noël");
		themes.add("Lune");
		themes.add("Panda");
		themes.add("Girafe");
		themes.add("Four");
		themes.add("Montagne");
		themes.add("Pizza");
		themes.add("Serpent");
		themes.add("Fusée");
		themes.add("Kébab");
		themes.add("Chat");
		themes.add("Chien");
		themes.add("Cheminée");
		themes.add("Train");
		themes.add("Vélo");
		themes.add("Bus");
		themes.add("Volcan");
		themes.add("Piano");
		themes.add("Hélicoptère");
		themes.add("Guitar");
		themes.add("Mario");
		themes.add("Téléphone");
		themes.add("Igloo");
		themes.add("Sonic");
		themes.add("Ferme");
		themes.add("Spiderman");
		themes.add("Playstation");
		themes.add("Toilette");
		themes.add("PopCorn");
		themes.add("Zombie");
		
		/* RANDOM */
		Random random = new Random();
		if(theme != null) return;
		theme = themes.get(random.nextInt(themes.size()));
	}
	
	public static String getTheme() {
		return theme;
	}
}
