package com.gmail.berndivader.mythicskript.functions.conditions;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import ch.njol.skript.lang.function.Function;
import ch.njol.skript.lang.function.Functions;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.adapters.bukkit.BukkitAdapter;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;
import io.lumine.xikage.mythicmobs.skills.SkillCondition;
import io.lumine.xikage.mythicmobs.skills.conditions.ConditionAction;
import io.lumine.xikage.mythicmobs.skills.conditions.ILocationComparisonCondition;

public class CompareLocationsCondition extends SkillCondition implements ILocationComparisonCondition {
	
	Function<?>function;
	Object[][]parameters;
	int casterPos,targetPos;
	String name;
	

	public CompareLocationsCondition(String line, MythicLineConfig mlc) {
		super(line);
		this.ACTION=ConditionAction.REQUIRED;
		
		this.name=mlc.getString("name","");
		function=Functions.getFunction(name);
		if(function!=null) {
			parameters=new Object[function.getParameters().length][];
			int size=parameters.length;
			casterPos=targetPos=-1;
			if(size>0) {
				casterPos=0;
			}
			if(size>1) {
				targetPos=1;
			}
		} else {
			Bukkit.getLogger().warning("Cant find function "+name);
		}
	}

	@Override
	public boolean check(AbstractLocation aCaster, AbstractLocation aTarget) {
		if(function!=null) {
			if(casterPos>-1) parameters[casterPos]=new Location[] {BukkitAdapter.adapt(aCaster)};
			if(targetPos>-1) parameters[targetPos]=new Location[] {BukkitAdapter.adapt(aTarget)};
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
