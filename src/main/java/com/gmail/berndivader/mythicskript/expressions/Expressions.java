package com.gmail.berndivader.mythicskript.expressions;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import com.gmail.berndivader.mythicskript.classes.MobItem;
import com.gmail.berndivader.mythicskript.expressions.drops.GetAllDrops;
import com.gmail.berndivader.mythicskript.expressions.mythicmob.GetAllMythicMobs;
import com.gmail.berndivader.mythicskript.expressions.mythicmob.GetEntityType;
import com.gmail.berndivader.mythicskript.expressions.mythicmob.GetMythicMobByName;
import com.gmail.berndivader.mythicskript.expressions.mythicspawner.*;

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
		Skript.registerExpression(ExprGetActiveMobs.class, ActiveMob.class, ExpressionType.SIMPLE,
				"all activemobs in world %string%","all activemobs");
		Skript.registerExpression(ExprGetActiveMob.class, ActiveMob.class, ExpressionType.SIMPLE, 
				"activemob of %entity%","activemob instance %entity%");
		Skript.registerExpression(ExprGetMobByUUID.class, ActiveMob.class, ExpressionType.SIMPLE, "activemob by uuid %string%");
		Skript.registerExpression(ExprGetEntityOfMob.class, Entity.class, ExpressionType.SIMPLE, "entity of activemob %activemob%");
		Skript.registerExpression(ExprGetLocation.class, Location.class, ExpressionType.SIMPLE,"location of activemob %activemob%");
		Skript.registerExpression(ExprGetWorld.class, World.class, ExpressionType.SIMPLE, "world of activemob %activemob%");

		Skript.registerExpression(ExprGetLastAggro.class, Entity.class, ExpressionType.SIMPLE, "lastaggro of activemob %activemob%");
		Skript.registerExpression(ExprGetTopThreat.class, Entity.class, ExpressionType.SIMPLE, "toptarget of activemob %activemob%");
		Skript.registerExpression(ExprGetUUID.class, String.class, ExpressionType.SIMPLE,"uuid of activemob %activemob%");
		Skript.registerExpression(ExprGetHealth.class, Number.class, ExpressionType.SIMPLE,"health of activemob %activemob%");
		Skript.registerExpression(ExprGetMaxHealth.class, Number.class, ExpressionType.SIMPLE, "maxhealth of activemob %activemob%");
		Skript.registerExpression(ExprGetMythicMobConfig.class, String.class, ExpressionType.SIMPLE,"mlc %string% of activemob %activemob%");
		Skript.registerExpression(ExprGetFaction.class, String.class, ExpressionType.SIMPLE,"faction of activemob %activemob%");
		Skript.registerExpression(ExprGetStance.class, String.class, ExpressionType.SIMPLE,"stance of activemob %activemob%");
		Skript.registerExpression(ExprGetLevel.class, Number.class, ExpressionType.SIMPLE,"level of activemob %activemob%");
		Skript.registerExpression(ExprGetPks.class, Number.class, ExpressionType.SIMPLE,"playerkills of activemob %activemob%");
		Skript.registerExpression(ExprGetSignal.class, String.class, ExpressionType.SIMPLE,"lastsignal of activemob %activemob%");
		Skript.registerExpression(ExprGetDisplayName.class, String.class, ExpressionType.SIMPLE, "displayname of activemob %activemob%");
		Skript.registerExpression(ExprGetMobType.class, String.class, ExpressionType.SIMPLE, "mobtype of activemob %activemob%");
		Skript.registerExpression(ExprGetOwner.class, String.class, ExpressionType.SIMPLE, "owner of activemob %activemob%");
		Skript.registerExpression(ExprGetOwnerUUID.class,String.class, ExpressionType.SIMPLE, "owneruuid of activemob %activemob%");
		Skript.registerExpression(ExprGetThreatTable.class, Entity.class, ExpressionType.SIMPLE, "get threattable of activemob %activemob%");
		Skript.registerExpression(ExprGetThreatValueOf.class, Number.class, ExpressionType.SIMPLE, "get threatvalue of %entity% from activemob %activemob%");
		Skript.registerExpression(getTargetSelector.class, SkillTargeter.class, ExpressionType.SIMPLE, "mythicmobs targeter %string%");
		Skript.registerExpression(getEntitiesFromSelector.class, Entity.class, ExpressionType.SIMPLE, "targetentities of %entity% for targeter %skilltargeter%");
		Skript.registerExpression(getLocationsFromSelector.class, Location.class, ExpressionType.SIMPLE, "targetlocations of %entity% for targeter %skilltargeter%");
		Skript.registerExpression(ConvertToMythicMob.class, ActiveMob.class, ExpressionType.SIMPLE,
				"convert %entity% into mythicmob %string% with level %number%");

		//MythicSpawner Expressions

		Skript.registerExpression(ExprGetMythicSpawner.class, MythicSpawner.class, ExpressionType.SIMPLE, "mythicspawner of activemob %activemob%");
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
		
		//mobdrops & mobitems
		
		Skript.registerExpression(GetAllDrops.class, MobItem.class, ExpressionType.SIMPLE, "all items of mobdrop %mobdrop%");
		
		//MythicMob types
		
		Skript.registerExpression(GetAllMythicMobs.class, MythicMob.class, ExpressionType.SIMPLE,"all mythicmob types");
		Skript.registerExpression(GetMythicMobByName.class, MythicMob.class, ExpressionType.SIMPLE, "mythicmob with name %string%");
		Skript.registerExpression(GetEntityType.class, EntityType.class, ExpressionType.SIMPLE, "entitytype of mythicmob %mythicmob%");
	}
}
