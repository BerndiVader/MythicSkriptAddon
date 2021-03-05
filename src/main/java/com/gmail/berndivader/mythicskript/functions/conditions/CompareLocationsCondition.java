package com.gmail.berndivader.mythicskript.functions.conditions;

import org.bukkit.Location;

import ch.njol.skript.lang.function.Function;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.conditions.ILocationComparisonCondition;

public class CompareLocationsCondition extends Condition implements ILocationComparisonCondition {
	
	public CompareLocationsCondition(String line, MythicLineConfig mlc, Function<?>f) {
		super(line,mlc,f);
		
		parameters=new Object[2][];
	}

	@Override
	public boolean check(AbstractLocation aCaster, AbstractLocation aTarget) {
		parameters[0]=new Location[] {BukkitAdapter.adapt(aCaster)};
		parameters[1]=new Location[] {BukkitAdapter.adapt(aTarget)};
		Object[]result=function.execute(parameters);
		if(result!=null&&(result[0] instanceof Boolean)) {
			return (boolean)result[0];
		}
		return false;
	}

}
