package me.buildbattle.faidrun.runnables;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.buildbattle.faidrun.arenas.ArenaTheme;
import me.buildbattle.faidrun.board.ScoreboardManager;
import me.buildbattle.faidrun.manager.vote.RatingManager;
import me.buildbattle.faidrun.utils.TitleManager;

public class GameRunnable extends BukkitRunnable {

	public static int timer = 480;
	
	@Override
	public void run() {
		
		/* TIMER 0 */
		if(timer == 0) {
			new RatingManager();
			this.cancel();
			return;
		}
		
		/* SETTINGS */
		for(Player player : Bukkit.getOnlinePlayers()) {
			TitleManager.sendActionBar(player, "§b§lLancement des votes dans: §c§l" + new SimpleDateFormat("mm:ss").format(new Date(timer * 1000)));
			if(ScoreboardManager.scoreboardGame.containsKey(player)) {
				ScoreboardManager.scoreboardGame.get(player).setLine(9, ChatColor.GRAY + "Temps: " + ChatColor.RED + new SimpleDateFormat("mm:ss").format(new Date(GameRunnable.timer * 1000)));
				ScoreboardManager.scoreboardGame.get(player).setLine(6, ChatColor.LIGHT_PURPLE + ArenaTheme.theme);
				ScoreboardManager.scoreboardGame.get(player).setLine(4, ChatColor.GRAY + "Joueurs: " + ChatColor.YELLOW + Bukkit.getOnlinePlayers().size());
			}
		}
		
		timer--;
	}

}
