package com.gmail.berndivader.mythicskript.functions;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import com.gmail.berndivader.mythicskript.MythicSkript;
import com.gmail.berndivader.mythicskript.functions.conditions.CompareEntitiesCondition;
import com.gmail.berndivader.mythicskript.functions.conditions.CompareEntityLocationCondition;
import com.gmail.berndivader.mythicskript.functions.conditions.CompareLocationsCondition;
import com.gmail.berndivader.mythicskript.functions.conditions.EntityCondition;
import com.gmail.berndivader.mythicskript.functions.conditions.LocationCondition;
import com.gmail.berndivader.mythicskript.functions.drops.ItemDrop;
import com.gmail.berndivader.mythicskript.functions.drops.MessageDrop;
import com.gmail.berndivader.mythicskript.functions.mechanics.SkriptfunctionMechanic;
import com.gmail.berndivader.mythicskript.functions.targeters.EntityTargeter;
import com.gmail.berndivader.mythicskript.functions.targeters.LocationTargeter;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.function.Function;
import ch.njol.skript.lang.function.Parameter;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicConditionLoadEvent;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicDropLoadEvent;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicMechanicLoadEvent;
import io.lumine.xikage.mythicmobs.api.bukkit.events.MythicTargeterLoadEvent;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;

public class Functions implements Listener {
	
	public static void register() {
		new Functions();
	}
	
	public Functions() {
		MythicSkript.plugin.getServer().getPluginManager().registerEvents(this,MythicSkript.plugin);
	}
	
	@EventHandler
	public void onMythicMobsCustomMechanicsLoad(MythicMechanicLoadEvent e) {
		
		switch(e.getMechanicName().toLowerCase()) {
		case "skriptfunction":
		case "skfunction":
			e.register(new SkriptfunctionMechanic(e.getContainer().getConfigLine(),e.getConfig()));
			break;
		}
	}
	
	@EventHandler
	public void onMythicMobsCustomConditionsLoad(MythicConditionLoadEvent e) {
		
		switch(e.getConditionName().toLowerCase()) {
		case "skfunction":
		case "skriptfunction":
			MythicLineConfig mlc=e.getConfig();
			String name=mlc.getString("name","");
			Function<?>function=ch.njol.skript.lang.function.Functions.getGlobalFunction(name);
			if(function!=null) {
				if(function.getReturnType().getCodeName().equals("boolean")) {
					Parameter<?>[]parameters=function.getParameters();
					int size=parameters.length;
					if(size==1) {
						if (parameters[0].getType().getCodeName().equals("entity")) {
							e.register(new EntityCondition(e.getConditionName(),e.getConfig(),function));
						} else if(parameters[0].getType().getCodeName().equals("location")) {
							e.register(new LocationCondition(e.getConditionName(),e.getConfig(),function));
						} else {
							Skript.warning("No valid parameter found for "+name);
						}
					} else if(size==2) {
						boolean para0=parameters[0].getType().getCodeName().equals("entity");
						boolean para1=parameters[1].getType().getCodeName().equals("entity");
						
						if(para0&&para1) {
							e.register(new CompareEntitiesCondition(e.getConditionName(),e.getConfig(),function));
						} else if(!para0&&!para1) {
							e.register(new CompareLocationsCondition(e.getConditionName(),e.getConfig(),function));
						} else {
							e.register(new CompareEntityLocationCondition(e.getConditionName(),e.getConfig(),function,para0));
						}
						
					} else {
						Skript.warning("There was an error with the given paramters for "+name);
					}
				} else {
					Skript.warning("The return type for skriptcondition function "+name+" has to be boolean but is "+function.getReturnType().getCodeName());
				}
			} else {
				Skript.warning("Cant find function "+name);
			}
			break;
		}
	}
	
	@EventHandler
	public void onMythicMobsCustomTargeterLoad(MythicTargeterLoadEvent e) {
		
		switch(e.getTargeterName().toLowerCase()) {
		case "skfunction":
		case "skriptfunction":
			MythicLineConfig mlc=e.getConfig();
			String name=mlc.getString("name","");
			Function<?>function=ch.njol.skript.lang.function.Functions.getGlobalFunction(name);
			if(function!=null) {
				String returnType=function.getReturnType().getCodeName();
				if(returnType.equals("location")) {
					e.register(new LocationTargeter(mlc,function));
				} else if(returnType.equals("entity")) {
					e.register(new EntityTargeter(mlc, function));
				} else {
					Skript.warning("Expected return type for skript targeter "+name+" has to be a entity or location list but is "+returnType);
				}
			} else {
				Skript.warning("Cant find function "+name);
			}
			break;
		}
		
	}
	
	@EventHandler
	public void onMythicMobsDropLoad(MythicDropLoadEvent e) {
		
		switch(e.getDropName().toLowerCase()) {
		case "skfunction":
		case "skriptfunction":
			String name=e.getConfig().getString("name","");
			Function<?>function=ch.njol.skript.lang.function.Functions.getGlobalFunction(name);
			if(function!=null) {
				switch(function.getReturnType().getCodeName()) {
				case "itemstack":
					e.register(new ItemDrop(e.getContainer().getConfigLine(),e.getConfig(),function));
					break;
				case "string":
					e.register(new MessageDrop(e.getContainer().getConfigLine(),e.getConfig(),function));
					break;
				}
			} else {
				Skript.warning("Cant find function "+name);
			}
			break;
		}
		
	}
}
