package me.buildbattle.faidrun.runnables;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.buildbattle.faidrun.BuildBattle;
import me.buildbattle.faidrun.GameStatus;
import me.buildbattle.faidrun.board.ScoreboardManager;
import me.buildbattle.faidrun.manager.GameManager;

public class LobbyRunnable extends BukkitRunnable {

	public static int timer = 120;
	public static boolean start = false;
	
	public LobbyRunnable() {}
	
	@Override
	public void run() {
		
		/* SECURITE STATUS */
		if(!(GameStatus.isStatus(GameStatus.LOBBY))) {
			timer = 60;
			start = false;
			this.cancel();
			return;
		}
		
		/* MANQUE DE JOUEURS */
		if(Bukkit.getOnlinePlayers().size() < BuildBattle.MINPLAYER) {
			Bukkit.broadcastMessage(ChatColor.RED + "Il n'y a pas assez de joueurs pour démarrer la partie.");
			timer = 60;
			start = false;
			sendXPExtern();
			this.cancel();
			return;
		}
		
		/* DEBUT DE GAME */
		if(timer == 0){
			new GameManager().loadGame();
			this.cancel();
			return;
		}
		
		/* JEU DE TIMER */
		if((timer == 60) || (timer == 30) || (timer == 15) || (timer == 10) || (timer <= 5 && timer != 0)){
			Bukkit.broadcastMessage(BuildBattle.PREFIX + ChatColor.YELLOW + "La partie commence dans " + ChatColor.GOLD + timer + getSecond());
		}
		
		sendXPExtern();
		timer--;
	}
	
	public void sendXPExtern() {
		for(Player player : Bukkit.getOnlinePlayers()) {
			player.setLevel(timer);
			if(ScoreboardManager.scoreboardGame.containsKey(player)) {
                ScoreboardManager.scoreboardGame.get(player).setLine(5, ChatColor.GRAY + "Début dans: " + ChatColor.GREEN + new SimpleDateFormat("mm:ss").format(new Date(LobbyRunnable.timer * 1000)));
            }
		}
	}
	
	private String getSecond(){
        return timer == 1 ? " seconde" : " secondes";
    }
}