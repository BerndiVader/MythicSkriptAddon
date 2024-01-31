package com.gmail.berndivader.mythicskript.functions.mechanics;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.function.Function;
import ch.njol.skript.lang.function.Functions;
import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.INoTargetSkill;
import io.lumine.mythic.api.skills.ITargetedEntitySkill;
import io.lumine.mythic.api.skills.ITargetedLocationSkill;
import io.lumine.mythic.api.skills.SkillMetadata;
import io.lumine.mythic.api.skills.SkillResult;
import io.lumine.mythic.bukkit.BukkitAdapter;
import io.lumine.mythic.core.skills.SkillMechanic;

public class SkriptfunctionMechanic extends SkillMechanic implements INoTargetSkill,ITargetedEntitySkill,ITargetedLocationSkill {
	
	Function<?>function;
	Object[][]parameters;
	int dataPos,locationPos,entityPos;
	String name;
	
	public SkriptfunctionMechanic(SkillMechanic skill,MythicLineConfig mlc) {
		super(skill.getManager(),skill.getFile(), mlc.getLine(), mlc);
		
		name=mlc.getString("name","");
		function=Functions.getGlobalFunction(name);
		if(function!=null) {
			parameters=new Object[function.getParameters().length][];
			dataPos=locationPos=entityPos=-1;
			for(int i=0;i<function.getParameters().length;i++) {
				String type=function.getParameter(i).getType().getCodeName();
				switch(type) {
				case "skilldata":
					dataPos=i;
					break;
				case "location":
					locationPos=i;
					break;
				case "entity":
					entityPos=i;
					break;
				}
			}
		} else {
			Skript.warning("Cant find function "+name);
		}
	}

	@Override
	public SkillResult castAtLocation(SkillMetadata meta, AbstractLocation aLocation) {
		if(dataPos>-1) parameters[dataPos]=new SkillMetadata[] {meta};
		if(locationPos>-1) parameters[locationPos]=new Location[] {BukkitAdapter.adapt(aLocation)};
		if(entityPos>-1) parameters[entityPos]=new Entity[0];
		function.execute(parameters);
		return SkillResult.SUCCESS;
	}

	@Override
	public SkillResult castAtEntity(SkillMetadata meta, AbstractEntity aEntity) {
		if(dataPos>-1) parameters[dataPos]=new SkillMetadata[] {meta};
		if(locationPos>-1) parameters[locationPos]=new Location[0];
		if(entityPos>-1) parameters[entityPos]=new Entity[] {aEntity.getBukkitEntity()};
		function.execute(parameters);
		return SkillResult.SUCCESS;
	}

	@Override
	public SkillResult cast(SkillMetadata meta) {
		if(dataPos>-1) parameters[dataPos]=new SkillMetadata[] {meta};
		if(locationPos>-1) parameters[locationPos]=new Location[0];
		if(entityPos>-1) parameters[entityPos]=new Entity[0];
		function.execute(parameters);
		return SkillResult.SUCCESS;
	}

}
