package com.gmail.berndivader.mythicskript.functions.conditions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import ch.njol.skript.lang.function.Function;
import ch.njol.skript.lang.function.Functions;
import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.conditions.ConditionAction;
import io.lumine.xikage.mythicmobs.skills.conditions.IEntityCondition;

public class EntityCondition extends SkillCondition implements IEntityCondition {
	
	Function<?>function;
	Object[][]parameters;
	int entityPos;
	String name;

	public EntityCondition(String line, MythicLineConfig mlc) {
		super(line);
		this.ACTION=ConditionAction.REQUIRED;
		
		this.name=mlc.getString("name","");
		function=Functions.getFunction(name);
		if(function!=null) {
			parameters=new Object[function.getParameters().length][];
			entityPos=-1;
			for(int i=0;i<function.getParameters().length;i++) {
				if(function.getParameter(i).getType().getCodeName().equals("entity")) {
					entityPos=i;
					break;
				}
			}
		} else {
			Bukkit.getLogger().warning("Cant find function "+name);
		}
	}

	@Override
	public boolean check(AbstractEntity aEntity) {
		if(function!=null) {
			if(entityPos>-1) parameters[entityPos]=new Entity[] {aEntity.getBukkitEntity()};
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
