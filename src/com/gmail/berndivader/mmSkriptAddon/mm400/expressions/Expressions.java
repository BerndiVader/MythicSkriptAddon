package com.gmail.berndivader.mmSkriptAddon.mm400.expressions;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import com.gmail.berndivader.mmSkriptAddon.mm400.expressions.mythicspawner.*;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;

public class Expressions {
	public static void register() {
		
		//ActiveMob Expressions

		Skript.registerExpression(ExprGetActiveMobs.class, ActiveMob.class, ExpressionType.SIMPLE,
				"all activemobs in world %string%","all activemobs");
		Skript.registerExpression(ExprGetActiveMob.class, ActiveMob.class, ExpressionType.SIMPLE, 
				"activemob of %entity%","activemob instance %entity%");
		Skript.registerExpression(ExprGetMobByUUID.class, ActiveMob.class, ExpressionType.SIMPLE, "activemob by uuid %string%");
		Skript.registerExpression(ExprGetEntityOfMob.class, Entity.class, ExpressionType.SIMPLE, "entity of %activemob%");
		Skript.registerExpression(ExprGetLocation.class, Location.class, ExpressionType.SIMPLE,"location of %activemob%");
		Skript.registerExpression(ExprGetWorld.class, World.class, ExpressionType.SIMPLE, "world of %activemob%");

		Skript.registerExpression(ExprGetLastAggro.class, Entity.class, ExpressionType.SIMPLE, "lastaggro of %activemob%");
		Skript.registerExpression(ExprGetTopThreat.class, Entity.class, ExpressionType.SIMPLE, "toptarget of %activemob%");
		Skript.registerExpression(ExprGetUUID.class, String.class, ExpressionType.SIMPLE,"uuid of %activemob%");
		Skript.registerExpression(ExprGetHealth.class, Number.class, ExpressionType.SIMPLE,"health of %activemob%");
		Skript.registerExpression(ExprGetMythicMobConfig.class, String.class, ExpressionType.SIMPLE,"mlc %string% of %activemob%");
		Skript.registerExpression(ExprGetFaction.class, String.class, ExpressionType.SIMPLE,"faction of %activemob%");
		Skript.registerExpression(ExprGetStance.class, String.class, ExpressionType.SIMPLE,"stance of %activemob%");
		Skript.registerExpression(ExprGetLevel.class, Number.class, ExpressionType.SIMPLE,"level of %activemob%");
		Skript.registerExpression(ExprGetPks.class, Number.class, ExpressionType.SIMPLE,"playerkills of %activemob%");
		Skript.registerExpression(ExprGetSignal.class, String.class, ExpressionType.SIMPLE,"lastsignal of %activemob%");
		Skript.registerExpression(ExprGetDisplayName.class, String.class, ExpressionType.SIMPLE, "displayname of %activemob%");
		Skript.registerExpression(ExprGetOwner.class, Entity.class, ExpressionType.SIMPLE, "owner of %activemob%");
		
		//MythicSpawner Expressions

		Skript.registerExpression(ExprGetMythicSpawner.class, MythicSpawner.class, ExpressionType.SIMPLE, "mythicspawner of %activemob%");
		Skript.registerExpression(SpawnerLocation.class, Location.class, ExpressionType.SIMPLE, "location of %mythicspawner%");
		Skript.registerExpression(GetSpawnerWorld.class, World.class, ExpressionType.SIMPLE, "world of %mythicspawner%");
		Skript.registerExpression(GetMythicSpawners.class, MythicSpawner.class, ExpressionType.SIMPLE, 
				"all mythicspawners in world %string%","all mythicspawners");
		Skript.registerExpression(GetAllMobsFromSpawner.class, ActiveMob.class, ExpressionType.SIMPLE, "all activemobs of spawner %mythicspawner%");
//		Skript.registerExpression(MobtypeOfSpawner.class, MythicMob.class, ExpressionType.SIMPLE, "mythicmobtype of %mythicspawner%");
	}
}
