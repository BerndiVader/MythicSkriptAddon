package com.gmail.berndivader.mmSkriptAddon.mm400.effects;

import ch.njol.skript.Skript;

public class Effects {
	public static void register() {
		Skript.registerEffect(SetLastAggroCause.class, "set lastaggro of %activemob% to %entity%");
		Skript.registerEffect(DropCombat.class, "dropcombat for %activemob%");
		Skript.registerEffect(SetTarget.class, "set %entity% to new target of %activemob%");
		Skript.registerEffect(SetFaction.class, "set faction of %activemob% to %string%");
		Skript.registerEffect(SetStance.class, "set stance of %activemob% to %string%");
		Skript.registerEffect(SetLevel.class, "set level of %activemob% to %number%");
		Skript.registerEffect(SetPlayerKills.class, "set kills of %activemob% to %number%");
		Skript.registerEffect(SetOwner.class, "set owner of %activemob% to %entity%","set owner of %activemob% to %string%");
		Skript.registerEffect(SendSignal.class, "send signal %string% to %activemob% with trigger %entity%");
		Skript.registerEffect(RemoveMob.class, "remove %activemob%");
	};
}
