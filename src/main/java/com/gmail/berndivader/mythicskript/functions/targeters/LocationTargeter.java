package com.gmail.berndivader.mythicskript.functions.targeters;

import java.util.HashSet;

import org.bukkit.Location;

import com.gmail.berndivader.mythicskript.Utils;

import ch.njol.skript.lang.function.Function;
import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.SkillMetadata;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.skills.targeters.ILocationSelector;

public class LocationTargeter extends ILocationSelector {
	
	Function<?>function;
	Object[][]parameters;
	
	public LocationTargeter(MythicLineConfig mlc,Function<?>f) {
		super(Utils.mythicMobs.getSkillManager(),mlc);
		
		function=f;
		parameters=new Object[1][];
		
	}

	@Override
	public HashSet<AbstractLocation> getLocations(SkillMetadata data) {
		
		parameters[0]=new SkillMetadata[] {data};
		Object[]result=function.execute(parameters);
		HashSet<AbstractLocation>targets=new HashSet<>();
		if(result!=null) {
			for(int i1=0;i1<result.length;i1++) {
				targets.add(BukkitAdapter.adapt((Location)result[i1]));
			}
		}
		return targets;
		
	}
	
}
