package me.buildbattle.faidrun.listeners.player;

import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import me.buildbattle.faidrun.BuildBattle;
import me.buildbattle.faidrun.GameStatus;
import me.buildbattle.faidrun.arenas.ArenaManager;
import me.buildbattle.faidrun.manager.vote.RatingRunnable;

public class PlayerInteractListener implements Listener {
	
	private String rightclick = ChatColor.GRAY + " (Clique droit)";
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		if((event.getItem() != null) && (event.getPlayer() != null)) {
			if(event.getItem().getType() == null) return;
			if(GameStatus.isStatus(GameStatus.VOTE)) {
				if((event.getAction().equals(Action.RIGHT_CLICK_AIR)) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))) {
					if(event.getItem().getType() != Material.AIR) {
						event.setCancelled(true);
						if(event.getItem().getType().equals(Material.STAINED_CLAY)) {
							Player player = event.getPlayer();
							UUID last = RatingRunnable.actual;
							
							/* VOTE POUR LUI MEME */
							if(ArenaManager.names.get(last).equalsIgnoreCase(player.getName())) {
								player.sendMessage(BuildBattle.PREFIX + ChatColor.RED + "Tu ne peux pas voter pour toi même !");
								return;
							}
							
							short data = event.getItem().getDurability();
							
							/* SUPER MOCHE */
							if(data == (short) 14) {
								RatingRunnable.getRatePointByUUID(last).actualRate.put(player.getUniqueId(), 5);
							}
							
							/* MOCHE */
							if(data == (short) 6) {
								RatingRunnable.getRatePointByUUID(last).actualRate.put(player.getUniqueId(), 10);
							}
							
							/* OK */
							if(data == (short) 5) {
								RatingRunnable.getRatePointByUUID(last).actualRate.put(player.getUniqueId(), 15);
							}
							
							/* BIEN */
							if(data == (short) 13) {
								RatingRunnable.getRatePointByUUID(last).actualRate.put(player.getUniqueId(), 20);
							}
							
							/* SUPER BIEN */
							if(data == (short) 11) {
								RatingRunnable.getRatePointByUUID(last).actualRate.put(player.getUniqueId(), 25);
							}
							
							/* MAGNIFIQUE */
							if(data == (short) 4) {
								RatingRunnable.getRatePointByUUID(last).actualRate.put(player.getUniqueId(), 30);
							}
							
							
							String voteName = event.getItem().getItemMeta().getDisplayName().replace(rightclick, "");
							player.sendMessage(BuildBattle.PREFIX + ChatColor.YELLOW + "Vous venez de voter: " + voteName);
						}
					}
				}
			}
		}
	}

}
