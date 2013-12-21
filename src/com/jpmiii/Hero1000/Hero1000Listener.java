package com.jpmiii.Hero1000;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.jpmiii.Hero1000.Hero1000;

public class Hero1000Listener implements Listener {
	private Hero1000 plugin;

	public Hero1000Listener(Hero1000 plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void normalLogin(PlayerLoginEvent event) {

	}

}
