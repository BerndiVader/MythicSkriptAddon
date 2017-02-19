package com.gmail.berndivader.mmSkriptAddon.mm400.effects;

import com.gmail.berndivader.mmSkriptAddon.mm400.effects.mobitems.AddItemToMobDrop;
import com.gmail.berndivader.mmSkriptAddon.mm400.effects.mobitems.ChangeMaterialOfMobItem;
import com.gmail.berndivader.mmSkriptAddon.mm400.effects.mobitems.RemoveMobItem;
import com.gmail.berndivader.mmSkriptAddon.mm400.effects.mythicspawner.ActivateMythicSpawner;
import com.gmail.berndivader.mmSkriptAddon.mm400.effects.mythicspawner.AttachMobToSpawner;
import com.gmail.berndivader.mmSkriptAddon.mm400.effects.mythicspawner.CooldownMythicSpawner;
import com.gmail.berndivader.mmSkriptAddon.mm400.effects.mythicspawner.MakeSpawnerSpawn;
import com.gmail.berndivader.mmSkriptAddon.mm400.effects.mythicspawner.SetMobTypeOfSpawner;
import com.gmail.berndivader.mmSkriptAddon.mm400.effects.mythicspawner.SetMovLevelofSpawner;
import com.gmail.berndivader.mmSkriptAddon.mm400.effects.mythicspawner.WarmupMythicSpawner;

import ch.njol.skript.Skript;

public class Effects {
	public static void register() {
		
		//activemob
		
		Skript.registerEffect(SetLastAggroCause.class, "set lastaggro of activemob %activemob% to %entity%");
		Skript.registerEffect(DropCombat.class, "dropcombat for activemob %activemob%");
		Skript.registerEffect(SetTarget.class, "set %entity% to new target of activemob %activemob%");
		Skript.registerEffect(SetFaction.class, "set faction of activemob %activemob% to %string%");
		Skript.registerEffect(SetStance.class, "set stance of activemob %activemob% to %string%");
		Skript.registerEffect(SetLevel.class, "set level of activemob %activemob% to %number%");
		Skript.registerEffect(SetPlayerKills.class, "set kills of activemob %activemob% to %number%");
		Skript.registerEffect(SetOwner.class, "set owner of activemob %activemob% to %entity%",
				"set owner of activemob %activemob% to %string% by uuid");
		Skript.registerEffect(SendSignal.class, "send signal %string% to activemob %activemob% with trigger %entity%");
		Skript.registerEffect(RemoveMob.class, "remove activemob %activemob%");
		Skript.registerEffect(SetHealth.class, "set health of activemob %activemob% to %number%");
		Skript.registerEffect(SetMaxHealth.class, "set maxhealth of activemob %activemob% to %number%");
		Skript.registerEffect(MakeMobCastSkill.class, 
				"make activemob %activemob% cast skill %string% with trigger %entity% at target %entity%",
				"make activemob %activemob% cast skill %string% with trigger %entity% at location %location%");
		
		//mythicspawner
		
		Skript.registerEffect(ActivateMythicSpawner.class, "activate mythicspawner %mythicspawner%",
				"deactivate mythicspawner %mythicspawner%");
		Skript.registerEffect(CooldownMythicSpawner.class, "set cooldown of mythicspawner %mythicspawner% to %number%",
				"set remainingcooldown of mythicspawner %mythicspawner% to %number%");
		Skript.registerEffect(WarmupMythicSpawner.class, "set warmup of mythicspawner %mythicspawner% to %number%",
				"set remainingwarmup of mythicspawner %mythicspawner% to %number%");
		Skript.registerEffect(SetMobTypeOfSpawner.class, "set mobtype of mythicspawner %mythicspawner% to %string%");
		Skript.registerEffect(SetMovLevelofSpawner.class, "set moblevel of mythicspawner %mythicspawner% to %number%");
		Skript.registerEffect(MakeSpawnerSpawn.class, "make mythicspawner %mythicspawner% spawn");
		Skript.registerEffect(AttachMobToSpawner.class, "attach activemob %activemob% to mythicspawner %mythicspawner%");

		//mobdrops & mobitems
		
		Skript.registerEffect(RemoveMobItem.class, "remove mobitem %mobitem% from mobdrop %mobdrop%","clear mobdrop %mobdrop%");
		Skript.registerEffect(ChangeMaterialOfMobItem.class, "set material of mobitem %mobitem% to %string%");
		Skript.registerEffect(AddItemToMobDrop.class, "add item %itemstack% to mobdrop %mobdrop%");
	};
}
