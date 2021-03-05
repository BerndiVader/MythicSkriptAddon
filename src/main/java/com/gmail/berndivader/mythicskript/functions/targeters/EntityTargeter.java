package com.gmail.berndivader.mythicskript.functions.targeters;

import java.util.HashSet;

import org.bukkit.entity.Entity;

import ch.njol.skript.lang.function.Function;
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;

public class EntityTargeter extends IEntitySelector {
	
	Function<?>function;
	Object[][]parameters;
	
	public EntityTargeter(MythicLineConfig mlc,Function<?>f) {
		super(mlc);
		
		function=f;
		parameters=new Object[1][];
		
	}

	@Override
	public HashSet<AbstractEntity> getEntities(SkillMetadata data) {
		
		parameters[0]=new SkillMetadata[] {data};
		Object[]result=function.execute(parameters);
		HashSet<AbstractEntity>targets=new HashSet<>();
		if(result!=null) {
			for(int i1=0;i1<result.length;i1++) {
				targets.add(BukkitAdapter.adapt((Entity)result[i1]));
			}
		}
		return targets;
		
	}
	
}
