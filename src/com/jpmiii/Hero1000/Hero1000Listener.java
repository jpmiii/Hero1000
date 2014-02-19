package com.jpmiii.Hero1000;




import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitTask;

import com.jpmiii.Hero1000.Hero1000;

public class Hero1000Listener implements Listener {
	private Hero1000 plugin;

	public Hero1000Listener(Hero1000 plugin) {
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void noBreak(BlockBreakEvent event) {
		if ((event.getBlock().getType() == Material.OBSIDIAN)
				&& (event.getBlock().getX() > 290)
				&& (event.getBlock().getX() < 380)
				&& (event.getBlock().getZ() > 1040)
				&& (event.getBlock().getZ() < 1060)) {
			event.setCancelled(true);
		}

	}
	@EventHandler(priority = EventPriority.HIGH)
	public void noPlacelava(PlayerBucketEmptyEvent event) {
		if (event.getBucket() == Material.LAVA_BUCKET) {
			plugin.getLogger().info("lava");
			if (!event.getPlayer().hasPermission("hero.lava")){
				event.setCancelled(true);
			}
			
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void noPlace(BlockPlaceEvent event) {
		
		if ((event.getBlock().getType() == Material.OBSIDIAN)
				&& (event.getBlock().getX() > 290)
				&& (event.getBlock().getX() < 380)
				&& (event.getBlock().getZ() > 1040)
				&& (event.getBlock().getZ() < 1060)) {
			event.setCancelled(true);
		}

	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void normalLogin(PlayerLoginEvent event) {
		for (Player ply : plugin.getServer().getOnlinePlayers()) {
			if (plugin.perms.has(ply, "hero.alert")) {
				ply.playSound(ply.getLocation(), Sound.WOLF_HOWL, 1, 1);
			}
		}
		

	}
	
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void bedin(PlayerBedEnterEvent event) {
		String st = "nope";
		if (event.getPlayer().hasPermission("hero.upgrade")) {
			st = "yep";
		} else {
			event.setCancelled(true);
		}
		
		plugin.getLogger().info(st);
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void canhold(PlayerItemHeldEvent event) {
		
		
		
		if (!(event.getPlayer().hasPermission("hero.upgrade"))) {
			Integer hold  = event.getNewSlot();
			Material mat2 = Material.AIR;

			if (event.getPlayer().getInventory().getItem(hold) != null) {
				mat2 = event.getPlayer().getInventory().getItem(hold).getType();
			}
			


			// plugin.getLogger().info(mat2.toString());
			if (mat2 != Material.STONE_SPADE
			&& mat2 != Material.BOAT
			&& mat2 != Material.WRITTEN_BOOK
			&& mat2 != Material.SAND
			&& mat2 != Material.COOKED_BEEF) {
				event.getPlayer().getInventory().setItem(event.getPlayer().getInventory().firstEmpty(), event.getPlayer().getInventory().getItem(hold));
				event.getPlayer().getInventory().setItem(hold, new ItemStack(Material.AIR));
			}
		
			
		} 		
	}
	/*
	@EventHandler(priority = EventPriority.MONITOR)
	public void name(Event event) {
		String st = "yep";
		plugin.getLogger().info(st);
	}*/

}
