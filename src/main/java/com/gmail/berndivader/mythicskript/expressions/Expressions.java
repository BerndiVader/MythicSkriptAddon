package com.gmail.berndivader.mythicskript.expressions;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

import com.gmail.berndivader.mythicskript.classes.MobItem;
import com.gmail.berndivader.mythicskript.expressions.drops.GetAllDrops;
import com.gmail.berndivader.mythicskript.expressions.drops.GetLootBagItems;
import com.gmail.berndivader.mythicskript.expressions.drops.GetLootBagOthers;
import com.gmail.berndivader.mythicskript.expressions.mythicitem.ItemStackForMythicItemName;
import com.gmail.berndivader.mythicskript.expressions.mythicmob.GetAllMythicMobs;
import com.gmail.berndivader.mythicskript.expressions.mythicmob.GetEntityType;
import com.gmail.berndivader.mythicskript.expressions.mythicmob.GetMythicMobByName;
import com.gmail.berndivader.mythicskript.expressions.mythicspawner.*;
import com.gmail.berndivader.mythicskript.expressions.skillmetadata.GetCaster;
import com.gmail.berndivader.mythicskript.expressions.skillmetadata.GetCause;
import com.gmail.berndivader.mythicskript.expressions.skillmetadata.GetEntityTargets;
import com.gmail.berndivader.mythicskript.expressions.skillmetadata.GetOriginLocation;
import com.gmail.berndivader.mythicskript.expressions.skillmetadata.GetPower;
import com.gmail.berndivader.mythicskript.expressions.skillmetadata.GetTargetLocations;
import com.gmail.berndivader.mythicskript.expressions.skillmetadata.GetTrigger;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.mobs.MythicMob;
import io.lumine.xikage.mythicmobs.skills.SkillTargeter;
import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;

public class Expressions {
	public static void register() {
		
		//ActiveMob Expressions
		
		Skript.registerExpression(SpawnMythicMob.class, ActiveMob.class, ExpressionType.SIMPLE, 
				"spawn mythicmob %string% at location %location% in world %object%");
		Skript.registerExpression(GetActiveMobs.class, ActiveMob.class, ExpressionType.SIMPLE,
				"all activemobs in world %string%","all activemobs");
		Skript.registerExpression(GetActiveMob.class, ActiveMob.class, ExpressionType.SIMPLE, 
				"activemob of %entity%","activemob instance %entity%");
		Skript.registerExpression(GetMobByUUID.class, ActiveMob.class, ExpressionType.SIMPLE, "activemob by uuid %string%");
		Skript.registerExpression(GetEntityOfMob.class, Entity.class, ExpressionType.SIMPLE, "entity of activemob %activemob%");
		Skript.registerExpression(GetLocation.class, Location.class, ExpressionType.SIMPLE,"location of activemob %activemob%");
		Skript.registerExpression(GetWorld.class, World.class, ExpressionType.SIMPLE, "world of activemob %activemob%");

		Skript.registerExpression(GetLastAggro.class, Entity.class, ExpressionType.SIMPLE, "lastaggro of activemob %activemob%");
		Skript.registerExpression(GetTopThreat.class, Entity.class, ExpressionType.SIMPLE, "toptarget of activemob %activemob%");
		Skript.registerExpression(GetUUID.class, String.class, ExpressionType.SIMPLE,"uuid of activemob %activemob%");
		Skript.registerExpression(GetHealth.class, Number.class, ExpressionType.SIMPLE,"health of activemob %activemob%");
		Skript.registerExpression(GetMaxHealth.class, Number.class, ExpressionType.SIMPLE, "maxhealth of activemob %activemob%");
		Skript.registerExpression(GetMythicMobConfig.class, String.class, ExpressionType.SIMPLE,"mlc %string% of activemob %activemob%");
		Skript.registerExpression(GetFaction.class, String.class, ExpressionType.SIMPLE,"faction of activemob %activemob%");
		Skript.registerExpression(GetStance.class, String.class, ExpressionType.SIMPLE,"stance of activemob %activemob%");
		Skript.registerExpression(GetLevel.class, Number.class, ExpressionType.SIMPLE,"level of activemob %activemob%");
		Skript.registerExpression(GetPlayerKills.class, Number.class, ExpressionType.SIMPLE,"playerkills of activemob %activemob%");
		Skript.registerExpression(GetSignal.class, String.class, ExpressionType.SIMPLE,"lastsignal of activemob %activemob%");
		Skript.registerExpression(GetDisplayName.class, String.class, ExpressionType.SIMPLE, "displayname of activemob %activemob%");
		Skript.registerExpression(GetMobType.class, String.class, ExpressionType.SIMPLE, "mobtype of activemob %activemob%");
		Skript.registerExpression(GetOwner.class, Entity.class, ExpressionType.SIMPLE, "owner of activemob %activemob%");
		Skript.registerExpression(GetOwnerUUID.class,String.class, ExpressionType.SIMPLE, "owneruuid of activemob %activemob%");
		Skript.registerExpression(GetThreatTable.class, Entity.class, ExpressionType.SIMPLE, "get threattable of activemob %activemob%");
		Skript.registerExpression(GetThreatValueOf.class, Number.class, ExpressionType.SIMPLE, "get threatvalue of %entity% from activemob %activemob%");
		Skript.registerExpression(GetTargetSelector.class, SkillTargeter.class, ExpressionType.SIMPLE, "mythicmobs targeter %string%");
		Skript.registerExpression(GetEntitiesFromSelector.class, Entity.class, ExpressionType.SIMPLE, "targetentities of %entity% for targeter %skilltargeter%");
		Skript.registerExpression(GetLocationsFromSelector.class, Location.class, ExpressionType.SIMPLE, "targetlocations of %entity% for targeter %skilltargeter%");
		Skript.registerExpression(ConvertToMythicMob.class, ActiveMob.class, ExpressionType.SIMPLE,
				"convert %entity% into mythicmob %string% with level %number%");

		//MythicSpawner Expressions

		Skript.registerExpression(GetMythicSpawnerByActiveMob.class, MythicSpawner.class, ExpressionType.SIMPLE, "mythicspawner of activemob %activemob%");
		Skript.registerExpression(GetMythicSpawnerByName.class,MythicSpawner.class,ExpressionType.SIMPLE,"mythicspawner of name %string%");
		Skript.registerExpression(SpawnerName.class, String.class, ExpressionType.SIMPLE, "name of mythicspawner %mythicspawner%");
		Skript.registerExpression(SpawnerLocation.class, Location.class, ExpressionType.SIMPLE, "location of mythicspawner %mythicspawner%");
		Skript.registerExpression(GetSpawnerWorld.class, World.class, ExpressionType.SIMPLE, "world of mythicspawner %mythicspawner%");
		Skript.registerExpression(SpawnerCooldown.class, Number.class, ExpressionType.SIMPLE, "cooldown of mythicspawner %mythicspawner%",
				"remainingcooldown of mythicspawner %mythicspawner%");
		Skript.registerExpression(SpawnerWarmup.class, Number.class, ExpressionType.SIMPLE, "warmup of mythicspawner %mythicspawner%",
				"remainingwarmup of mythicspawner %mythicspawner%");
		Skript.registerExpression(GetMythicSpawners.class, MythicSpawner.class, ExpressionType.SIMPLE, 
				"all mythicspawners in world %string%","all mythicspawners");
		Skript.registerExpression(GetMaxMobsFromSpawner.class, Number.class, ExpressionType.SIMPLE, "number of activemobs from mythicspawner %mythicspawner%",
				"number of maxmobs from mythicspawner %mythicspawner%");
		Skript.registerExpression(GetAllMobsFromSpawner.class, ActiveMob.class, ExpressionType.SIMPLE, "all activemobs of mythicspawner %mythicspawner%");
		Skript.registerExpression(MobtypeOfSpawner.class, String.class, ExpressionType.SIMPLE, "mobtype of mythicspawner %mythicspawner%");
		Skript.registerExpression(GetMovLevelOfSpawner.class, Number.class, ExpressionType.SIMPLE, "moblevel of mythicspawner %mythicspawner%");
		
		//mobdrops, mobitems, lootbag & mythicitem
		
		Skript.registerExpression(GetLootBagOthers.class,String.class,ExpressionType.SIMPLE,"other drop[s] [of] [lootbag] %lootbag%");
		Skript.registerExpression(GetAllDrops.class, MobItem.class, ExpressionType.SIMPLE, "all items of mobdrop %mobdrop%");
		Skript.registerExpression(GetLootBagItems.class,ItemStack.class,ExpressionType.SIMPLE,"physical drop[s] [of] [lootbag] %lootbag%");
		Skript.registerExpression(ItemStackForMythicItemName.class,ItemStack.class,ExpressionType.SIMPLE,"itemstack of mythicitem %string%");
		
		//MythicMob types
		
		Skript.registerExpression(GetAllMythicMobs.class, MythicMob.class, ExpressionType.SIMPLE,"all mythicmob types");
		Skript.registerExpression(GetMythicMobByName.class, MythicMob.class, ExpressionType.SIMPLE, "mythicmob with name %string%");
		Skript.registerExpression(GetEntityType.class, EntityType.class, ExpressionType.SIMPLE, "entitytype of mythicmob %mythicmob%");
		
		/*
		 * SkillMetadata Expressions
		 */
		
		Skript.registerExpression(GetCaster.class,Entity.class,ExpressionType.SIMPLE,"[get] caster [of] [skilldata] %skilldata%");
		Skript.registerExpression(GetCause.class,String.class,ExpressionType.SIMPLE,"[get] cause [of] [skilldata] %skilldata%");
		Skript.registerExpression(GetEntityTargets.class,Entity.class,ExpressionType.SIMPLE,"[get] entitytargets [of] [skilldata] %skilldata%");
		Skript.registerExpression(GetTargetLocations.class,Location.class,ExpressionType.SIMPLE,"[get] locationtargets [of] [skilldata] %skilldata%");
		Skript.registerExpression(GetOriginLocation.class,Location.class,ExpressionType.SIMPLE,"[get] origin [of] [skilldata] %skilldata%");
		Skript.registerExpression(GetPower.class,Float.class,ExpressionType.SIMPLE,"[get] power [of] [skilldata] %skilldata%");
		Skript.registerExpression(GetTrigger.class,Entity.class,ExpressionType.SIMPLE,"[get] trigger [entity] [of] [skilldata] %skilldata%");
		
		/*
		 * DropMetadata Expressions
		 */
		
		Skript.registerExpression(com.gmail.berndivader.mythicskript.expressions.dropmetadata.GetDropper.class,Entity.class,ExpressionType.SIMPLE,"[get] dropper [of] [dropdata] %dropdata%");
		Skript.registerExpression(com.gmail.berndivader.mythicskript.expressions.dropmetadata.GetCaster.class,Entity.class,ExpressionType.SIMPLE,"[get] caster [of] [dropdata] %dropdata%");
		Skript.registerExpression(com.gmail.berndivader.mythicskript.expressions.dropmetadata.GetCause.class,Entity.class,ExpressionType.SIMPLE,"[get] cause [of] [dropdata] %dropdata%");
		Skript.registerExpression(com.gmail.berndivader.mythicskript.expressions.dropmetadata.GetTrigger.class,Entity.class,ExpressionType.SIMPLE,"[get] trigger [of] [dropdata] %dropdata%");
		Skript.registerExpression(com.gmail.berndivader.mythicskript.expressions.dropmetadata.GetAmount.class,Float.class,ExpressionType.SIMPLE,"[get] amount [of] [dropdata] %dropdata%");
		Skript.registerExpression(com.gmail.berndivader.mythicskript.expressions.dropmetadata.GetGenerations.class,Integer.class,ExpressionType.SIMPLE,"[get] generations [of] [dropdata] %dropdata%");
		
	}
}
