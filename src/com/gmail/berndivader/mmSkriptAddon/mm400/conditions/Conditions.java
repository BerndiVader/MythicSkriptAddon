package com.gmail.berndivader.mmSkriptAddon.mm400.conditions;

import ch.njol.skript.Skript;

public class Conditions {
	public static void register() {
		Skript.registerCondition(ConditionEntityIsActiveMob.class, 
				"%entity% [is ]instanceof activemob");
		Skript.registerCondition(ActiveMobIsDead.class, 
				"%activemob% isdead");
		Skript.registerCondition(HasThreatTable.class, 
				"%activemob% has threattable");
		Skript.registerCondition(ConditionAmHasCustomSpawner.class,
				"%activemob% has mythicspawner");
	}
}
