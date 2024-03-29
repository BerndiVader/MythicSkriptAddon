package com.gmail.berndivader.mythicskript.functions.conditions;

import org.bukkit.entity.Entity;

import ch.njol.skript.lang.function.Function;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.conditions.IEntityCondition;

public class EntityCondition extends Condition implements IEntityCondition {
	
	public EntityCondition(String line, MythicLineConfig mlc, Function<?>f) {
		super(line,mlc,f);
		
		parameters=new Object[1][];
	}

	@Override
	public boolean check(AbstractEntity aEntity) {
		parameters[0]=new Entity[] {aEntity.getBukkitEntity()};
		Object[]result=function.execute(parameters);
		if(result!=null&&(result[0] instanceof Boolean)) {
			return (boolean)result[0];
		}
		return false;
	}

}
