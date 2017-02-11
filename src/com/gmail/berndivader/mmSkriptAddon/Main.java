package com.gmail.berndivader.mmSkriptAddon;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.berndivader.mmSkriptAddon.mm251.mm251Code;
import com.gmail.berndivader.mmSkriptAddon.mm260.mm260Code;
import com.gmail.berndivader.mmSkriptAddon.mm400.mm400Code;

import ch.njol.skript.Skript;

public class Main extends JavaPlugin {

	public static Plugin plugin;
	public static int mmVer;
	public static String strMMVer;
	
	@Override
	public void onEnable() {
		plugin = this;
		boolean chk = false;
		chk = Bukkit.getPluginManager().getPlugin("Skript") == null && Bukkit.getPluginManager().getPlugin("MythicMobs") == null ? false : true;
		if (chk) {
			Skript.registerAddon(this);
	    	strMMVer = Bukkit.getServer().getPluginManager().getPlugin("MythicMobs").getDescription().getVersion();
			mmVer = Integer.valueOf(strMMVer.replaceAll("\\.", ""));
			if (mmVer >= 245 && mmVer <= 251) {
				mm251Code.register();
			} else if (mmVer == 260) {
				mm260Code.register();
			} else if (mmVer == 400) {
				mm400Code.register();
			}
		}
	}
	
	@Override
	public void onDisable() {
		Bukkit.getLogger().info("mmSkriptAddon disabled!");
	}
}
