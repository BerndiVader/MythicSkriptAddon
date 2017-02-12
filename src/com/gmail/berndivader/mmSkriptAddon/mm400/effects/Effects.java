package com.gmail.berndivader.mmSkriptAddon.mm400.effects;

import ch.njol.skript.Skript;

public class Effects {
	public static void register() {
		Skript.registerEffect(SetLastAggroCause.class, "set lastaggro of activemob %activemob% to %entity%");
		Skript.registerEffect(DropCombat.class, "dropcombat for activemob %activemob%");
		Skript.registerEffect(SetTarget.class, "set %entity% to new target of activemob %activemob%");
		Skript.registerEffect(SetFaction.class, "set faction of activemob %activemob% to %string%");
		Skript.registerEffect(SetStance.class, "set stance of activemob %activemob% to %string%");
		Skript.registerEffect(SetLevel.class, "set level of activemob %activemob% to %number%");
		Skript.registerEffect(SetPlayerKills.class, "set kills of activemob %activemob% to %number%");
		Skript.registerEffect(SetOwner.class, "set owner of activemob %activemob% to %entity%","set owner of activemob %activemob% to %string%");
		Skript.registerEffect(SendSignal.class, "send signal %string% to activemob %activemob% with trigger %entity%");
		Skript.registerEffect(RemoveMob.class, "remove activemob %activemob%");
	};
}
