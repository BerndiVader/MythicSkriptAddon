package com.gmail.berndivader.mythicskript.functions.conditions;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import ch.njol.skript.lang.function.Function;
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.conditions.IEntityLocationComparisonCondition;

public class CompareEntityLocationCondition extends Condition implements IEntityLocationComparisonCondition {
	
	int entityPos,locationPos;
	
	public CompareEntityLocationCondition(String line, MythicLineConfig mlc, Function<?>f, boolean para0) {
		super(line,mlc,f);
		parameters=new Object[1][];
		
		if(para0) {
			entityPos=0;
			locationPos=1;
		} else {
			locationPos=0;
			entityPos=1;
		}
		
	}

	@Override
	public boolean check(AbstractEntity aCaster, AbstractLocation aTarget) {
		parameters[entityPos]=new Entity[] {aCaster.getBukkitEntity()};
		parameters[locationPos]=new Location[] {BukkitAdapter.adapt(aTarget)};
		Object[]result=function.execute(parameters);
		if(result!=null&&(result[0] instanceof Boolean)) {
			return (boolean)result[0];
		}
		return false;
	}

}
