package me.buildbattle.faidrun;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.buildbattle.faidrun.arenas.Arena;
import me.buildbattle.faidrun.arenas.ArenaManager;
import me.buildbattle.faidrun.listeners.ListenerManager;
import me.buildbattle.faidrun.runnables.ScoreboardRunnable;
import me.buildbattle.faidrun.utils.WorldManager;

public class BuildBattle extends JavaPlugin {
	
	public static String PREFIX = ChatColor.GRAY + "[" + ChatColor.RED + "BuildBattle" + ChatColor.GRAY + "] ";
	
	public static int MINPLAYER = 2;
	
	public static BuildBattle instance;
	
	@Override
	public void onLoad() {
		instance = this;
		GameStatus.setStatus(GameStatus.LOBBY);
	}
	
	@Override
	public void onDisable() {
		WorldManager.deleteWorld("world");
		File from = new File("mapreset");
		File to = new File("world");
		try {
			WorldManager.copyFolder(from, to);
		} catch (IOException e) {
			System.err.println("Erreur lors de la copie de la map!");
		}
		super.onDisable();
	}
	
	@Override
	public void onEnable() {
		new ListenerManager(this).registerListener();
		new ScoreboardRunnable().runTaskTimer(instance, 0L, 20L);
	}
	
	public boolean isCancelled(Player player, Location location) {
		Arena cuboid = ArenaManager.arenaPlayer.get(player.getUniqueId()).getCuboid();
		if(cuboid.IsArena(location)) return false;
		return true;
	}
	
	public String centerText(String text) {
		int space = (int) Math.round((80.0D - 1.4D * text.length()) / 2.0D);
		return repeat(" ", space) + text;
	}

	private String repeat(String text, int times) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < times; i++) {
			stringBuilder.append(text);
		}
		return stringBuilder.toString();
	}
	
	public void finish() {
		Bukkit.getScheduler().runTaskLater(instance, new Runnable() {		
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()) {
					player.kickPlayer(PREFIX + ChatColor.GOLD + "MERCI D'AVOIR JOUÉ﻿ ! :)");
				}
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "restart");
			}
		}, 200L); 
	}
	/**
	 * Scoreboard
	 * 
	 */
}