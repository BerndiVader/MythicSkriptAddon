package com.gmail.berndivader.mythicskript.functions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.gmail.berndivader.mythicskript.Main;
import com.gmail.berndivader.mythicskript.functions.conditions.CompareEntitiesCondition;
import com.gmail.berndivader.mythicskript.functions.conditions.CompareEntityLocationCondition;
import com.gmail.berndivader.mythicskript.functions.conditions.CompareLocationsCondition;
import com.gmail.berndivader.mythicskript.functions.conditions.EntityCondition;
import com.gmail.berndivader.mythicskript.functions.conditions.LocationCondition;
import com.gmail.berndivader.mythicskript.functions.mechanics.SkriptfunctionMechanic;

import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicConditionLoadEvent;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMechanicLoadEvent;

public class Functions implements Listener {
	
	public static void register() {
		new Functions();
	}
	
	public Functions() {
		Main.plugin.getServer().getPluginManager().registerEvents(this, Main.plugin);
	}
	
	@EventHandler
	public void onMythicMobsCustomMechanicsLoad(MythicMechanicLoadEvent e) {
		switch(e.getMechanicName().toLowerCase()) {
		case "skriptfunction":
		case "skmechanic":
			e.register(new SkriptfunctionMechanic(e.getContainer().getConfigLine(),e.getConfig()));
			break;
		}
	}
	
	@EventHandler
	public void onMythicMobsCustomConditionsLoad(MythicConditionLoadEvent e) {
		switch(e.getConditionName().toLowerCase()) {
		case "skentitycondition":
		case "skentity":
			e.register(new EntityCondition(e.getConditionName(),e.getConfig()));
			break;
		case "sklocationcondition":
		case "sklocation":
			e.register(new LocationCondition(e.getConditionName(),e.getConfig()));
			break;
		case "skentitycomparecondition":
		case "skentitycompare":
			e.register(new CompareEntitiesCondition(e.getConditionName(),e.getConfig()));
			break;
		case "sklocationcomparecondition":
		case "sklocationconpare":
			e.register(new CompareLocationsCondition(e.getConditionName(),e.getConfig()));
			break;
		case "skentitylocationcomparecondition":
		case "skentitylocationcompare":
			e.register(new CompareEntityLocationCondition(e.getConditionName(),e.getConfig()));
			break;
		}
	}

}
