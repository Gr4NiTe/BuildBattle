package me.buildbattle.faidrun.listeners;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import me.buildbattle.faidrun.listeners.player.PlayerChatListener;
import me.buildbattle.faidrun.listeners.player.PlayerDropListener;
import me.buildbattle.faidrun.listeners.player.PlayerInteractListener;
import me.buildbattle.faidrun.listeners.player.PlayerJoinListener;
import me.buildbattle.faidrun.listeners.player.PlayerMoveListener;
import me.buildbattle.faidrun.listeners.player.PlayerQuitListener;
import me.buildbattle.faidrun.listeners.server.ServerPingListener;
import me.buildbattle.faidrun.listeners.world.BlockListener;
import me.buildbattle.faidrun.listeners.world.EntityListener;

public class ListenerManager {
	
	public Plugin plugin;
	public PluginManager pluginManager;
	
	public ListenerManager(Plugin plugin) {
		this.plugin = plugin;
		this.pluginManager = Bukkit.getPluginManager();
	}
	
	public void registerListener() {
		pluginManager.registerEvents(new PlayerJoinListener(), plugin);
		pluginManager.registerEvents(new PlayerQuitListener(), plugin);
		pluginManager.registerEvents(new PlayerInteractListener(), plugin);
		pluginManager.registerEvents(new PlayerMoveListener(), plugin);
		pluginManager.registerEvents(new PlayerDropListener(), plugin);
		pluginManager.registerEvents(new PlayerChatListener(), plugin);
		
		pluginManager.registerEvents(new BlockListener(), plugin);
		pluginManager.registerEvents(new EntityListener(), plugin);
		
		pluginManager.registerEvents(new ServerPingListener(), plugin);
	}
}