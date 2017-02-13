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
		Skript.registerExpression(ExprGetOwner.class, Entity.class, ExpressionType.SIMPLE, "owner of activemob %activemob%");
		
		//MythicSpawner Expressions

		Skript.registerExpression(ExprGetMythicSpawner.class, MythicSpawner.class, ExpressionType.SIMPLE, "mythicspawner of %activemob%");
		Skript.registerExpression(SpawnerLocation.class, Location.class, ExpressionType.SIMPLE, "location of mythicspawner %mythicspawner%");
		Skript.registerExpression(GetSpawnerWorld.class, World.class, ExpressionType.SIMPLE, "world of mythicspawner %mythicspawner%");
		Skript.registerExpression(GetMythicSpawners.class, MythicSpawner.class, ExpressionType.SIMPLE, 
				"all mythicspawners in world %string%","all mythicspawners");
		Skript.registerExpression(GetAllMobsFromSpawner.class, ActiveMob.class, ExpressionType.SIMPLE, "all activemobs of mythicspawner %mythicspawner%");
//		Skript.registerExpression(MobtypeOfSpawner.class, MythicMob.class, ExpressionType.SIMPLE, "mythicmobtype of %mythicspawner%");
	}
}
