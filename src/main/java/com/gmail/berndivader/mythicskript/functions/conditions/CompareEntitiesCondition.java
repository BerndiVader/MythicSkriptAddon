package com.gmail.berndivader.mythicskript.functions.conditions;

import org.bukkit.entity.Entity;

import ch.njol.skript.lang.function.Function;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.conditions.IEntityComparisonCondition;

public class CompareEntitiesCondition extends Condition implements IEntityComparisonCondition {
	
	public CompareEntitiesCondition(String line, MythicLineConfig mlc,Function<?>f) {
		super(line,mlc,f);
		
		parameters=new Object[2][];
	}

	@Override
	public boolean check(AbstractEntity aCaster, AbstractEntity aTarget) {
		parameters[0]=new Entity[] {aCaster.getBukkitEntity()};
		parameters[1]=new Entity[] {aTarget.getBukkitEntity()};
		Object[]result=function.execute(parameters);
		if(result!=null&&(result[0] instanceof Boolean)) {
			return (boolean)result[0];
		}
		return false;
	}

}
