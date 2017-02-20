package me.buildbattle.faidrun.board;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import me.buildbattle.faidrun.arenas.ArenaTheme;
import me.buildbattle.faidrun.runnables.GameRunnable;
import me.buildbattle.faidrun.runnables.LobbyRunnable;

public class ScoreboardManager {
	
	public Player player;
	public ScoreboardSign scoreboardSign;
	
	public static Map<Player, ScoreboardSign> scoreboardGame = new HashMap<Player, ScoreboardSign>();
	
	public ScoreboardManager(Player player) {
		this.player = player;
		this.scoreboardSign = new ScoreboardSign(player, player.getName());
		scoreboardGame.put(player, scoreboardSign);
	}
	
	public void loadScoreboard() {
		this.scoreboardSign.setObjectiveName(ChatColor.YELLOW + " " + ChatColor.BOLD + "BuildBattle");
		this.scoreboardSign.create();
		
		scoreboardGame.get(player).setLine(6, "§2");
		scoreboardGame.get(player).setLine(5, ChatColor.GRAY + "Début dans: " + ChatColor.GREEN + new SimpleDateFormat("mm:ss").format(new Date(LobbyRunnable.timer * 1000)));
		scoreboardGame.get(player).setLine(4, ChatColor.GRAY + "Joueurs: " + ChatColor.GREEN + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
		scoreboardGame.get(player).setLine(3, "§1");
		scoreboardGame.get(player).setLine(2, ChatColor.GRAY + "Map: " + ChatColor.AQUA + "Orignal");
		scoreboardGame.get(player).setLine(1, "§0");
		scoreboardGame.get(player).setLine(0, ChatColor.YELLOW + "www.faiden.fr");
	}
	
	public static void loadScoreboardGame(Player playerTwo) {
		if(ScoreboardManager.scoreboardGame.containsKey(playerTwo)) {
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(10, "§4");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(9, ChatColor.GRAY + "Temps: " + ChatColor.RED + new SimpleDateFormat("mm:ss").format(new Date(GameRunnable.timer * 1000)) );
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(8, "§4");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(7, ChatColor.GRAY + "Thème:");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(6, ChatColor.LIGHT_PURPLE + ArenaTheme.theme);
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(5, "§3");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(4, ChatColor.GRAY + "Joueurs: " + ChatColor.YELLOW + Bukkit.getOnlinePlayers().size());
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(3, "§5");
		}		
	}
	
	public static void loadScoreboardVote(Player playerTwo) {
		if(ScoreboardManager.scoreboardGame.containsKey(playerTwo)) {
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(11, "§5");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(10, ChatColor.GRAY + "Createur:");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(9, ChatColor.GRAY + "§f");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(8, "§4");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(7, ChatColor.GRAY + "Thème:");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(6, ChatColor.LIGHT_PURPLE + ArenaTheme.theme);
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(5, "§3");
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(4, ChatColor.GRAY + "Joueurs: " + ChatColor.YELLOW + Bukkit.getOnlinePlayers().size());
			ScoreboardManager.scoreboardGame.get(playerTwo).setLine(3, "§5");
		}		
	}
}