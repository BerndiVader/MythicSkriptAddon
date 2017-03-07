package com.gmail.berndivader.mmSkriptAddon;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.berndivader.mmSkriptAddon.NMS.*;
import com.gmail.berndivader.mmSkriptAddon.mm260.mm260Code;
import com.gmail.berndivader.mmSkriptAddon.mm400.mm400Code;

import ch.njol.skript.Skript;

public class Main extends JavaPlugin {

	public static Plugin plugin;
	public static int mmVer;
	public static String strMMVer;
	private static NMSUtils nmsutils;
	public static NMSUtils NMSUtils() {return nmsutils;}
	
	@Override
	public void onEnable() {
		plugin = this;
		boolean chk = false;
		chk = Bukkit.getPluginManager().getPlugin("Skript") == null && Bukkit.getPluginManager().getPlugin("MythicMobs") == null ? false : true;
		chk = getNMSUtil();
		if (chk) {
			Skript.registerAddon(this);
	    	strMMVer = Bukkit.getServer().getPluginManager().getPlugin("MythicMobs").getDescription().getVersion().replaceAll("[\\D]", "");
			mmVer = Integer.valueOf(strMMVer);
		if (mmVer == 2511) {
				Bukkit.getLogger().info("NOT SUPPORTED YET!");
				mm260Code.register();
				Bukkit.getPluginManager().disablePlugin(Main.plugin);
			} else if (mmVer >= 401) {
				mm400Code.register();
			}
		}
	}
	
	@Override
	public void onDisable() {
		Bukkit.getLogger().info("mmSkriptAddon disabled!");
		Main.plugin = null;
	}
	private boolean getNMSUtil() {
		String v;
		try {v = Bukkit.getServer().getClass().getPackage().getName().replace(".",  ",").split(",")[3];
		} catch (ArrayIndexOutOfBoundsException e) {return false;}
		if (v.equals("v1_8_R3") || v.equals("v1_8_R2")) {nmsutils=new NMSUtil18();}
		else if (v.equals("v1_9_R1") || v.equals("v1_9_R2") || v.equals("v1_10_R1") || v.equals("v1_11_R1")) {nmsutils=new NMSUtil19();}
		return nmsutils!=null;
	}
}
