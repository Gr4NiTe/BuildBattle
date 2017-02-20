package me.buildbattle.faidrun.runnables;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.buildbattle.faidrun.GameStatus;
import me.buildbattle.faidrun.board.ScoreboardManager;

public class ScoreboardRunnable extends BukkitRunnable {

	public ScoreboardRunnable() {}
	
	@Override
	public void run() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			if(ScoreboardManager.scoreboardGame.containsKey(player)) {
				
				/** LOBBY **/
				if(GameStatus.isStatus(GameStatus.LOBBY)) {
					ScoreboardManager.scoreboardGame.get(player).setLine(4, ChatColor.GRAY + "Joueurs: " + ChatColor.GREEN + Bukkit.getOnlinePlayers().size() + "/" + Bukkit.getMaxPlayers());
				}
				
			}
		}
	}

}
