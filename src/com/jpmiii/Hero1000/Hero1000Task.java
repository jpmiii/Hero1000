package com.jpmiii.Hero1000;

//import org.bukkit.entity.Player;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.jpmiii.Hero1000.Hero1000;

public class Hero1000Task extends BukkitRunnable {
	private final Hero1000 plugin;

	public Hero1000Task(Hero1000 plugin) {
		this.plugin = plugin;
	}

	public void run() {
		for (Player ply : plugin.getServer()
				.getWorld("newworld")
				.getPlayers()) {
			if (plugin.perms.has(ply, "hero.follow") && !ply.hasMetadata("NPC")){
				plugin.getLogger().info(ply.getDisplayName() + ply.getLocation().toString());
			}
			
		}

	}
}
