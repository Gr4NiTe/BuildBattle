package me.buildbattle.faidrun.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.buildbattle.faidrun.GameStatus;

public class PlayerMoveListener implements Listener {
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if(GameStatus.isStatus(GameStatus.LOBBY)) {
			if(player.getLocation().getBlockY() < 0) {
				player.teleport(new Location(Bukkit.getWorld("world"), -329.5, 140, -203.5, 90.0f, 0.0f));
			}
		}
	}
	
}