package me.buildbattle.faidrun.listeners.player;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {
	
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		event.setFormat(event.getPlayer().getName() + ": " + ChatColor.GRAY + event.getMessage());
	}

}
