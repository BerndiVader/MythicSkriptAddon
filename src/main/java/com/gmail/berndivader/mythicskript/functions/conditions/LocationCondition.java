package com.gmail.berndivader.mythicskript.functions.conditions;

import org.bukkit.Location;

import ch.njol.skript.lang.function.Function;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.conditions.ILocationCondition;

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
