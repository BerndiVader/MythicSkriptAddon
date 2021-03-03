package com.gmail.berndivader.mythicskript.functions.conditions;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import ch.njol.skript.lang.function.Function;
import ch.njol.skript.lang.function.Functions;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.conditions.ConditionAction;
import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;

public class LocationCondition extends SkillCondition implements ILocationCondition {
	
	Function<?>function;
	Object[][]parameters;
	int locationPos;
	String name;

	public LocationCondition(String line, MythicLineConfig mlc) {
		super(line);
		this.ACTION=ConditionAction.REQUIRED;
		
		this.name=mlc.getString("name","");
		function=Functions.getFunction(name);
		if(function!=null) {
			parameters=new Object[function.getParameters().length][];
			locationPos=-1;
			for(int i=0;i<function.getParameters().length;i++) {
				String type=function.getParameter(i).getType().getCodeName();
				if(type.equals("location")) {
					locationPos=i;
					break;
				}
			}
		} else {
			Bukkit.getLogger().warning("Cant find function "+name);
		}
	}

	@Override
	public boolean check(AbstractLocation aLocation) {
		if(function!=null) {
			if(locationPos>-1) parameters[locationPos]=new Location[]{BukkitAdapter.adapt(aLocation)};
			Object[]result=function.execute(parameters);
			if(result!=null&&(result[0] instanceof Boolean)) {
				return (boolean)result[0];
			}
		} else {
			Bukkit.getLogger().warning("Cant find function "+name);
		}
		return false;
	}

}
