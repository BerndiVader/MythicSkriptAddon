package com.gmail.berndivader.mythicskript.effects;

import com.gmail.berndivader.mythicskript.effects.dropmetadata.SetAmount;
import com.gmail.berndivader.mythicskript.effects.mobitems.AddItemToMobDrop;
import com.gmail.berndivader.mythicskript.effects.mobitems.ChangeMaterialOfMobItem;
import com.gmail.berndivader.mythicskript.effects.mobitems.RemoveMobItem;
import com.gmail.berndivader.mythicskript.effects.mobitems.SetOtherLootForLootBag;
import com.gmail.berndivader.mythicskript.effects.mobitems.SetPhysicalLootForLootBag;
import com.gmail.berndivader.mythicskript.effects.mythicspawner.ActivateMythicSpawner;
import com.gmail.berndivader.mythicskript.effects.mythicspawner.AttachMobToSpawner;
import com.gmail.berndivader.mythicskript.effects.mythicspawner.CooldownMythicSpawner;
import com.gmail.berndivader.mythicskript.effects.mythicspawner.MakeSpawnerSpawn;
import com.gmail.berndivader.mythicskript.effects.mythicspawner.SetMobTypeOfSpawner;
import com.gmail.berndivader.mythicskript.effects.mythicspawner.SetMovLevelofSpawner;
import com.gmail.berndivader.mythicskript.effects.mythicspawner.WarmupMythicSpawner;

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
		Skript.registerEffect(MakePlayerCastSkill.class, 
				"make player %entity% cast skill %string% with trigger %entity% at entity %entity% with delay %number% and repeat %number%",
				"make player %entity% cast skill %string% with trigger %entity% at location %location% with delay %number% and repeat %number%",
				"make player %entity% cast skill %string% with trigger %entity% at self with delay %number% and repeat %number%");
		Skript.registerEffect(ModThreatofEntity.class, "inc threat of %entity% by %number% from activemob %activemob%", "dec threat of %entity% by %number% from activemob %activemob%");
		Skript.registerEffect(RemoveThreatEntity.class, "remove threat of %entity% from activemob %activemob%");
		Skript.registerEffect(ClearThreatTable.class, "clear threattable of activemob %activemob%");
		
		Skript.registerEffect(SetDamage.class, "set damage of activemob %activemob% to %number%");
		Skript.registerEffect(SetKnockbackResist.class, "set knockbackresist of activemob %activemob% to %number%");
		Skript.registerEffect(SetArmor.class, "set armor of activemob %activemob% to %number%");
		Skript.registerEffect(SetSpeed.class, "set speed of activemob %activemob% to %number%");
		Skript.registerEffect(SetAttackSpeed.class, "set attackspeed of activemob %activemob% to %number%");
		Skript.registerEffect(SetFollowRange.class, "set followrange of activemob %activemob% to %number%");
		
		Skript.registerEffect(TriggerSkill.class, 
				"trigger %string% for activemob %activemob%",
				"trigger %string% for activemob %activemob% with triggerentity %entity%");
		
		Skript.registerEffect(RemoveMythicFromEntity.class, "remove mythic from activemob %activemob%");
		
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

		//mobdrops & mobitems & lootbags
		
		Skript.registerEffect(SetPhysicalLootForLootBag.class, "set [physical] loot [for] [lootbag] %lootbag% to [(%-itemstack%|%-itemstacks%)]");
		Skript.registerEffect(SetOtherLootForLootBag.class, "set [other] loot [for] [lootbag] %lootbag% to [(%-string%|%-strings%)]");
		
		Skript.registerEffect(RemoveMobItem.class, "remove mobitem %mobitem% from mobdrop %mobdrop%","clear mobdrop %mobdrop%");
		Skript.registerEffect(ChangeMaterialOfMobItem.class, "set material of mobitem %mobitem% to %string%");
		Skript.registerEffect(AddItemToMobDrop.class, "add item %itemstack% to mobdrop %mobdrop%");
		
		/*
		 * dropmeta
		 */
		
		Skript.registerEffect(SetAmount.class, "set amount [for] [dropdata] %dropdata% to %number%");
	};
}
