package com.gmail.berndivader.mythicskript.conditions;

import com.gmail.berndivader.mythicskript.conditions.mythicitem.ItemStackisMythicItem;

import ch.njol.skript.Skript;

public class Conditions {
	public static void register() {
		Skript.registerCondition(ConditionEntityIsActiveMob.class, 
				"%entity% [is ]instanceof activemob", "%entity% is [an ]activemob");
		Skript.registerCondition(ActiveMobIsDead.class, 
				"activemob %activemob% isdead");
		Skript.registerCondition(HasThreatTable.class, 
				"activemob %activemob% has threattable");
		Skript.registerCondition(ConditionAmHasCustomSpawner.class,
				"activemob %activemob% has mythicspawner");
		Skript.registerCondition(ConditionSpawnerContainsMob.class,
				"mythicspawner %mythicspawner% contains activemob %activemob%");
		Skript.registerCondition(ActiveMobHasImmunityTable.class, 
				"activemob %activemob% has immunitytable");
		Skript.registerCondition(ItemStackisMythicItem.class,
				"%itemstack% is [a ]mythicitem");
	}
}
