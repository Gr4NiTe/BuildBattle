package me.buildbattle.faidrun.manager.vote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import me.buildbattle.faidrun.BuildBattle;
import me.buildbattle.faidrun.GameStatus;
import me.buildbattle.faidrun.arenas.Arena;
import me.buildbattle.faidrun.arenas.ArenaManager;
import me.buildbattle.faidrun.board.ScoreboardManager;
import me.buildbattle.faidrun.utils.TitleManager;

public class RatingRunnable extends BukkitRunnable {

	public static List<RatingPlayer> rates = new ArrayList<RatingPlayer>();
	public static UUID actual;
	public static String win = null;
	
	private int id;
	
	public RatingRunnable() {
		for(UUID uuid : ArenaManager.arenaPlayer.keySet()) {
			rates.add(new RatingPlayer(uuid));
		}
	}
	
	@Override
	public void run() {
		
		//ID != 0
		if(id != 0) {
			RatingPlayer rating = getRatePointByUUID(actual);
			for(Entry<UUID, Integer> points : rating.actualRate.entrySet()) {
				rating.addVote(points.getValue());
			}
			int finalRating = rating.getVote();
			rating.vote = finalRating;
		}
		
		//FIN DE PARTIE
		if(RatingPlayer.joueurs.size() == 0) {
			
			actual = null;
			GameStatus.setStatus(GameStatus.FINISH);
			
			Map<UUID, Integer> votes = new HashMap<UUID, Integer>();
			for(RatingPlayer rpp : rates) {
				votes.put(rpp.getUUID(), rpp.getVote() / Bukkit.getOnlinePlayers().size());
			}
			
			RatingComparator comparator = new RatingComparator(votes);
			TreeMap<UUID, Integer> filtre = new TreeMap<UUID, Integer>(comparator);
			filtre.putAll(votes);
			
			int position = 1;
			for(Entry<UUID, Integer> winner : filtre.entrySet()) {
				UUID winUUID = winner.getKey();
				String winName = ArenaManager.names.get(winUUID);
				
				if(position == 1) {
					RatingRunnable.win = winName;
					Arena winCuboid = ArenaManager.arenaPlayer.get(winUUID).getCuboid();
					for(Player allPlayers : Bukkit.getOnlinePlayers()) {
						allPlayers.teleport(winCuboid.getCenter());
						TitleManager.sendTitle(allPlayers, "§e§lBuildBattle", "§b§lGagnant de la partie: §c§l" + winName, 40);
						ScoreboardManager.scoreboardGame.get(allPlayers).setLine(10, ChatColor.GRAY + "Gagnant:");
						ScoreboardManager.scoreboardGame.get(allPlayers).setLine(9, ChatColor.GREEN + " " + ChatColor.BOLD + winName);
					}
					Bukkit.broadcastMessage(ChatColor.GOLD + "-----------------------------------------------------");
					Bukkit.broadcastMessage(BuildBattle.instance.centerText("§b" + position + "er: §a" + winName + " §7- §c" + winner.getValue() + " Points"));
				} else if(position <= 3) {
					Bukkit.broadcastMessage(BuildBattle.instance.centerText("§b" + position + "ème: §a" + winName + " §7- §c" + winner.getValue() + " Points"));
				} else {
					Player player = Bukkit.getPlayer(winUUID);
					if(player != null) player.sendMessage("§a \n§bVotre position: §e" + position + "ème §7- §c" + winner.getValue() + " Points");
				}
				
				position++;
			}
			Bukkit.broadcastMessage(ChatColor.GOLD + "-----------------------------------------------------");
			BuildBattle.instance.finish();
			this.cancel();
			return;
		}
		
		//TP RANDOM
		Random random = new Random();
		UUID uuid = RatingPlayer.joueurs.get(random.nextInt(RatingPlayer.joueurs.size()));
		String playerName = ArenaManager.names.get(uuid);
		Arena arena = ArenaManager.arenaPlayer.get(uuid).getCuboid();
		actual = uuid;
		RatingPlayer.joueurs.remove(uuid);
		for(Player playerOnline : Bukkit.getOnlinePlayers()) {
			ScoreboardManager.scoreboardGame.get(playerOnline).setLine(9, ChatColor.GREEN + playerName);
			playerOnline.teleport(arena.getCenter());
			TitleManager.sendTitle(playerOnline, "§e§lBuildBattle", "§aCréateur de cette construction: §c§l" + playerName, 20);
			TitleManager.sendActionBar(playerOnline, "§b§lVOUS AVEZ §c§l15 SECONDES §b§lPOUR VOTER !");
		}
		
		// ID
		id++;
	}
	
	public static RatingPlayer getRatePointByUUID(UUID uuid) {
		for(RatingPlayer player : rates) {
			if(player.getUUID() == uuid) {
				return player;
			}
		}
		return null;
	}

}
