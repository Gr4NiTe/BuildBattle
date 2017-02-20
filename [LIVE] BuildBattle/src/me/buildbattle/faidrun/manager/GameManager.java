package me.buildbattle.faidrun.manager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import me.buildbattle.faidrun.BuildBattle;
import me.buildbattle.faidrun.GameStatus;
import me.buildbattle.faidrun.arenas.Arena;
import me.buildbattle.faidrun.arenas.ArenaManager;
import me.buildbattle.faidrun.arenas.ArenaTheme;
import me.buildbattle.faidrun.board.ScoreboardManager;
import me.buildbattle.faidrun.runnables.GameRunnable;
import me.buildbattle.faidrun.utils.TitleManager;

public class GameManager {
	
	public GameManager() {
		GameStatus.setStatus(GameStatus.GAME);
		new ArenaTheme();
		Bukkit.broadcastMessage(ChatColor.GOLD + "-----------------------------------------------------");
		Bukkit.broadcastMessage(BuildBattle.instance.centerText(ChatColor.AQUA + "Bienvenue sur le BuildBattle, vous aurez " + ChatColor.YELLOW + "8:00 minutes" + ChatColor.AQUA + " pour faire une construction"
				+ " en fonction du thème proposé."));
		Bukkit.broadcastMessage(BuildBattle.instance.centerText(ChatColor.RED + "Le thème de la partie est: " + ChatColor.GREEN + ArenaTheme.theme));
		Bukkit.broadcastMessage(ChatColor.GOLD + "-----------------------------------------------------");
		new GameRunnable().runTaskTimer(BuildBattle.instance, 0L, 20L);
	}

	public void loadGame() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			
			ArenaManager.names.put(player.getUniqueId(), player.getName());
			if(!(ArenaManager.arenaPlayer.containsKey(player.getUniqueId()))) {
				ArenaManager.arenaPlayer.put(player.getUniqueId(), new ArenaManager());
			}
			Arena arena = ArenaManager.arenaPlayer.get(player.getUniqueId()).getCuboid();
			
			player.setLevel(0);
			player.setGameMode(GameMode.CREATIVE);
			player.teleport(arena.getCenter());
			TitleManager.sendTitle(player, "", ChatColor.RED + "Le thème de la partie: " + ChatColor.GREEN + ArenaTheme.theme, 20);
			ScoreboardManager.loadScoreboardGame(player);
		}
	}
}
