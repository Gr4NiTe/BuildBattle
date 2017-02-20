package me.buildbattle.faidrun.listeners.server;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import me.buildbattle.faidrun.BuildBattle;
import me.buildbattle.faidrun.GameStatus;

public class ServerPingListener implements Listener {
	
	@EventHandler
	public void onServerListPing(ServerListPingEvent event) {
		if(GameStatus.isStatus(GameStatus.LOBBY)) {
			event.setMotd(BuildBattle.PREFIX + ChatColor.AQUA + "La partie va bientôt commencer.");
		} else {
			event.setMotd(BuildBattle.PREFIX + ChatColor.RED + "La partie est déjà en cours.");
		}
	}	
}