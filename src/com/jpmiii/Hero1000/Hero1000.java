package com.jpmiii.Hero1000;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;



import com.jpmiii.Hero1000.Hero1000Listener;
import com.jpmiii.Hero1000.Hero1000Task;



//import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;




public class Hero1000 extends JavaPlugin{
    public static Permission perms = null;
    
    
	public void onEnable() {
		// getLogger().info("onEnable has been invoked!");

		this.saveDefaultConfig();

		
		

		getServer().getPluginManager().registerEvents(
				new Hero1000Listener(this), this);
		setupPermissions();
		BukkitTask t = new Hero1000Task(this).runTaskTimer(this, 1200, 1200);


		}
	
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
	public void onDisable() {
		
		getLogger().info("onDisable has been invoked!");
	}

}
