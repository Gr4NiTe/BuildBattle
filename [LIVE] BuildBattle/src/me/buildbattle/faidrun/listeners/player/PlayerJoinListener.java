package me.buildbattle.faidrun.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.buildbattle.faidrun.BuildBattle;
import me.buildbattle.faidrun.GameStatus;
import me.buildbattle.faidrun.board.ScoreboardManager;
import me.buildbattle.faidrun.runnables.LobbyRunnable;

public class PlayerJoinListener implements Listener {
	
	@EventHandler(ignoreCancelled=true, priority=EventPriority.HIGH)
	public void onPlayerJoin(PlayerJoinEvent event){
		
		Player player = event.getPlayer();
		
		/* UN STATUS AUTRE QUE LE LOBBY */
		if(!(GameStatus.isStatus(GameStatus.LOBBY))) {
			event.setJoinMessage(null);
			player.kickPlayer(ChatColor.RED + "La partie est déjà en cours.");
			return;
		}
		
		event.setJoinMessage(BuildBattle.PREFIX + ChatColor.YELLOW + player.getName() + ChatColor.GRAY + " a rejoint la partie " + ChatColor.GREEN + "(" + Bukkit.getOnlinePlayers().size() +
				"/" + Bukkit.getMaxPlayers() + ")");
		
		/* PARAMETRES */
		player.setGameMode(GameMode.ADVENTURE);
		player.setMaxHealth(20);
		player.setHealth(20);
		player.setFoodLevel(20);
		player.getInventory().clear();
		player.teleport(new Location(Bukkit.getWorld("world"), -329.5, 140, -203.5, 90.0f, 0.0f));
		new ScoreboardManager(player).loadScoreboard();
		
		/* RUNNABLE */
		if((Bukkit.getOnlinePlayers().size() >= BuildBattle.MINPLAYER) && (!(LobbyRunnable.start))) {
			new LobbyRunnable().runTaskTimer(BuildBattle.instance, 0L, 20L);
			LobbyRunnable.start = true;
		}
	}
	
}