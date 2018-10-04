package com.gmail.berndivader.mmSkriptAddon;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.berndivader.mmSkriptAddon.mm400.mm400Code;

import ch.njol.skript.Skript;

public class Main extends JavaPlugin {

	public static Plugin plugin;
	public static int mmVer;
	public static String strMMVer;
	
	@Override
	public void onEnable() {
		plugin=this;
		if(Bukkit.getPluginManager().getPlugin("MythicMobs")!=null&&Bukkit.getPluginManager().getPlugin("Skript")!=null){
			Skript.registerAddon(this);
		    strMMVer = Bukkit.getServer().getPluginManager().getPlugin("MythicMobs").getDescription().getVersion().replaceAll("[\\D]", "");
			mm400Code.register();
		}
	}
	
	@Override
	public void onDisable() {
		Bukkit.getLogger().info("mmSkriptAddon disabled!");
		Main.plugin = null;
	}
}
