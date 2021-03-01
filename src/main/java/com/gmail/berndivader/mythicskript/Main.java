package com.gmail.berndivader.mythicskript;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.berndivader.mythicskript.classes.CustomClasses;
import com.gmail.berndivader.mythicskript.conditions.Conditions;
import com.gmail.berndivader.mythicskript.effects.Effects;
import com.gmail.berndivader.mythicskript.events.Events;
import com.gmail.berndivader.mythicskript.expressions.Expressions;

import ch.njol.skript.Skript;

public class Main extends JavaPlugin {

	public static Plugin plugin;
	
	@Override
	public void onEnable() {
		plugin=this;
		if(Bukkit.getPluginManager().getPlugin("MythicMobs")!=null&&Bukkit.getPluginManager().getPlugin("Skript")!=null){
			Skript.registerAddon(this);
			register();
		}
	}
	
	@Override
	public void onDisable() {
		Bukkit.getLogger().info("MythicSk disabled!");
		Main.plugin = null;
	}
	
	static void register() {
		CustomClasses.register();
		Events.register();
		Conditions.register();
		Expressions.register();
		Effects.register();
	}	
}
