package com.jpmiii.Hero1000;



import java.util.Iterator;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import com.jpmiii.Hero1000.Hero1000Listener;
import com.jpmiii.Hero1000.Hero1000Task;

//import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;


public class Hero1000 extends JavaPlugin {
	public Permission perms = null;

	public void onEnable() {
		// getLogger().info("onEnable has been invoked!");

		this.saveDefaultConfig();

		getServer().getPluginManager().registerEvents(
				new Hero1000Listener(this), this);
		setupPermissions();
		BukkitTask t = new Hero1000Task(this).runTaskTimer(this, 6000, 6000);

		if(getServer().getPluginManager().getPlugin("Citizens") == null || getServer().getPluginManager().getPlugin("Citizens").isEnabled() == false) {
			getLogger().severe("Citizens 2.0 not found or not enabled");
			getServer().getPluginManager().disablePlugin(this);	
			return;
		}

		final Iterator<Recipe> recipes = getServer().recipeIterator();
		Recipe recipe;
		ItemStack result;

		while (recipes.hasNext()) {
			if ((recipe = recipes.next()) == null)
				continue;

			if ((result = recipe.getResult()) == null)
				continue;

			if (result.getType() == Material.ENCHANTMENT_TABLE)
				recipes.remove();
		}
 
		//Register your trait with Citizens.        
		net.citizensnpcs.api.CitizensAPI.getTraitFactory().registerTrait(net.citizensnpcs.api.trait.TraitInfo.create(Hero1000Trait.class).withName("hero"));	
	}

	private boolean setupPermissions() {
		RegisteredServiceProvider<Permission> rsp = getServer()
				.getServicesManager().getRegistration(Permission.class);
		perms = rsp.getProvider();
		return perms != null;
	}

	public void onDisable() {

		getLogger().info("onDisable has been invoked!");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (cmd.getName().equalsIgnoreCase("n")) {

			if (!(sender instanceof Player)) {
				
				getLogger().info("player only");
				return true;
			}
			Player player = (Player) sender;
			if (args.length > 0) {
				if (args[0].equalsIgnoreCase("1")){
					this.getServer().dispatchCommand(player, "ex walk <player.location.cursor_on>");
				}
				return true;
			}
		}
		if (cmd.getName().equalsIgnoreCase("hero")) {
			// doSomething

			if (!(sender instanceof Player)) {
				this.reloadConfig();
				
				getLogger().info("config reloaded");
				return true;
			}

			Player player = (Player) sender;
			if (args.length > 0) {
			




				if (args[0].equalsIgnoreCase("end")) {
					if (player.isOp()) {
		                World nether = Bukkit.getWorld(this.getConfig().getString("worldName") + "_the_end");
		                Location loc = nether.getSpawnLocation();
		                ((Player) sender).teleport(loc);
		                return true;
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("nether")) {
					if (player.isOp()) {
		                World nether = Bukkit.getWorld(this.getConfig().getString("worldName") + "_nether");
		                Location loc = nether.getSpawnLocation();
		                ((Player) sender).teleport(loc);
		                return true;
					}
					return true;
				}
				if (args[0].equalsIgnoreCase("world")) {
					if (player.isOp()) {
		                World nether = Bukkit.getWorld(this.getConfig().getString("worldName"));
		                Location loc = nether.getSpawnLocation();
		                ((Player) sender).teleport(loc);
		                return true;
					}
					return true;
				}

			}

			

		}
		
		return false;
	}
}
