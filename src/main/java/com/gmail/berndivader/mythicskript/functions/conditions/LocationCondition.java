package com.gmail.berndivader.mythicskript.functions.conditions;

import org.bukkit.Location;

import ch.njol.skript.lang.function.Function;
import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.conditions.ILocationCondition;
import io.lumine.mythic.bukkit.BukkitAdapter;

public class LocationCondition extends Condition implements ILocationCondition {
	
	public LocationCondition(String line, MythicLineConfig mlc,Function<?>f) {
		super(line,mlc,f);
		
		parameters=new Object[1][];

	}

	@Override
	public boolean check(AbstractLocation aLocation) {
		parameters[0]=new Location[]{BukkitAdapter.adapt(aLocation)};
		Object[]result=function.execute(parameters);
		if(result!=null&&(result[0] instanceof Boolean)) {
			return (boolean)result[0];
		}
		return false;
	}

}
