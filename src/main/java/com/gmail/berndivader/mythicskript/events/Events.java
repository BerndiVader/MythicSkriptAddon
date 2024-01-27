package com.gmail.berndivader.mythicskript.events;

import org.jetbrains.annotations.Nullable;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import com.gmail.berndivader.mythicskript.events.skript.MythicSkriptConditionEvent;
import com.gmail.berndivader.mythicskript.events.skript.MythicSkriptSkillEvent;
import com.gmail.berndivader.mythicskript.events.skript.MythicSkriptSpawnEvent;
import com.gmail.berndivader.mythicskript.events.skript.MythicSkriptSpawnerSpawnEvent;
import com.gmail.berndivader.mythicskript.expressions.event.*;
import com.gmail.berndivader.mythicskript.classes.*;
import com.gmail.berndivader.mythicskript.effects.conditions.SetConditionMeet;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import io.lumine.mythic.bukkit.events.MythicMobLootDropEvent;
import io.lumine.mythic.core.drops.LootBag;
import io.lumine.mythic.core.mobs.ActiveMob;
import io.lumine.mythic.core.spawning.spawners.MythicSpawner;

public class Events {
	
	static BukkitEvents bukkitEvents;
	
	public static void register() {
		
		Events.bukkitEvents=new BukkitEvents();
		
		Skript.registerEvent("MythicMobLootDropEvent", SimpleEvent.class, MythicMobLootDropEvent.class, "mythicmob lootdrop [event]");
		EventValues.registerEventValue(MythicMobLootDropEvent.class, ActiveMob.class, new Getter<ActiveMob, MythicMobLootDropEvent>() {
			@Override
			@Nullable
			public ActiveMob get(MythicMobLootDropEvent e) {
				return e.getMob();
		}}, 0);
		EventValues.registerEventValue(MythicMobLootDropEvent.class, LootBag.class, new Getter<LootBag, MythicMobLootDropEvent>() {
			@Override
			@Nullable
			public LootBag get(MythicMobLootDropEvent e) {
				return e.getDrops();
		}}, 0);
		EventValues.registerEventValue(MythicMobLootDropEvent.class, LootBag.class, new Getter<LootBag, MythicMobLootDropEvent>() {
			@Override
			@Nullable
			public LootBag get(MythicMobLootDropEvent e) {
				return e.getDrops();
		}}, 0);
		Skript.registerEvent("MythicSkriptSpawnEvent", SimpleEvent.class, MythicSkriptSpawnEvent.class, "mythicmob spawnevent");
		EventValues.registerEventValue(MythicSkriptSpawnEvent.class, ActiveMob.class, new Getter<ActiveMob, MythicSkriptSpawnEvent>() {
			@Override
			@Nullable
			public ActiveMob get(MythicSkriptSpawnEvent e) {
				return e.getActiveMob();
		}}, 0);
		EventValues.registerEventValue(MythicSkriptSpawnEvent.class, Entity.class, new Getter<Entity, MythicSkriptSpawnEvent>() {
			@Override
			@Nullable
			public Entity get(MythicSkriptSpawnEvent e) {
				return e.getEntity();
		}}, 0);
		Skript.registerEvent("MythicMobDeathEvent", SimpleEvent.class, MythicMobDeathEvent.class, "mythicmob deathevent");
		EventValues.registerEventValue(MythicMobDeathEvent.class, ActiveMob.class, new Getter<ActiveMob, MythicMobDeathEvent>() {
			@Override
			@Nullable
			public ActiveMob get(MythicMobDeathEvent e) {
				return e.getMob();
		}}, 0);
		EventValues.registerEventValue(MythicMobDeathEvent.class, Location.class, new Getter<Location, MythicMobDeathEvent>() {
			@Override
			@Nullable
			public Location get(MythicMobDeathEvent e) {
				return e.getEntity().getLocation();
		}}, 0);
		EventValues.registerEventValue(MythicMobDeathEvent.class, Entity.class, new Getter<Entity, MythicMobDeathEvent>() {
			@Override
			@Nullable
			public Entity get(MythicMobDeathEvent e) {
				return e.getEntity();
		}}, 0);
		EventValues.registerEventValue(MythicMobDeathEvent.class, MythicDrops.class, new Getter<MythicDrops, MythicMobDeathEvent>() {
			@Override
			@Nullable
			public MythicDrops get(MythicMobDeathEvent e) {
				MythicDrops md = new MythicDrops(e.getDrops());
				return md;
		}}, 0);
		Skript.registerEvent("MythicSkriptSpawnerSpawnEvent", SimpleEvent.class, MythicSkriptSpawnerSpawnEvent.class, "mythicspawner spawnevent");
		EventValues.registerEventValue(MythicSkriptSpawnerSpawnEvent.class, MythicSpawner.class, new Getter<MythicSpawner, MythicSkriptSpawnerSpawnEvent>() {
			@Override
			@Nullable
			public MythicSpawner get(MythicSkriptSpawnerSpawnEvent e) {
				return e.getMs();
		}}, 0);
		EventValues.registerEventValue(MythicSkriptSpawnerSpawnEvent.class, ActiveMob.class, new Getter<ActiveMob, MythicSkriptSpawnerSpawnEvent>() {
			@Override
			@Nullable
			public ActiveMob get(MythicSkriptSpawnerSpawnEvent e) {
				return e.getAm();
		}}, 0);
		Skript.registerEvent("MythicSkriptSkillEvent", SimpleEvent.class, MythicSkriptSkillEvent.class, "mythicmobs skriptskillevent");
		EventValues.registerEventValue(MythicSkriptSkillEvent.class, Entity.class, new Getter<Entity, MythicSkriptSkillEvent>() {
			@Override
			@Nullable
			public Entity get(MythicSkriptSkillEvent e) {
				return e.getCaster().getEntity().getBukkitEntity();
		}}, 0);
		EventValues.registerEventValue(MythicSkriptSkillEvent.class, Location.class, new Getter<Location, MythicSkriptSkillEvent>() {
			@Override
			@Nullable
			public Location get(MythicSkriptSkillEvent e) {
				return e.getTargetLocation();
		}}, 0);
		EventValues.registerEventValue(MythicSkriptSkillEvent.class, ActiveMob.class, new Getter<ActiveMob, MythicSkriptSkillEvent>() {
			@Override
			@Nullable
			public ActiveMob get(MythicSkriptSkillEvent e) {
				if (e.getCaster() instanceof ActiveMob) {
					return (ActiveMob)e.getCaster();
				}
				return null;
		}}, 0);
		Skript.registerExpression(DeathEventAttacker.class, Entity.class, ExpressionType.SIMPLE, "event-killer");
		Skript.registerExpression(EventTarget.class, Entity.class, ExpressionType.SIMPLE, "skill-target");
		Skript.registerExpression(EventTrigger.class, Entity.class, ExpressionType.SIMPLE, "skill-trigger");
		Skript.registerExpression(EventSkillName.class, String.class, ExpressionType.SIMPLE, "skill-name");
		Skript.registerExpression(EventSkillArgs.class, String.class, ExpressionType.SIMPLE, "skill-args");
		Skript.registerExpression(TargetType.class, String.class, ExpressionType.SIMPLE, "skill-targettype");
		
		Skript.registerEvent("MythicSkriptConditionEvent", SimpleEvent.class, MythicSkriptConditionEvent.class, "mythicmobs skriptconditionevent");
		Skript.registerExpression(ConditionActiveMob.class, ActiveMob.class, ExpressionType.SIMPLE, "condition-activemob");
		Skript.registerExpression(ConditionEntity.class, Entity.class, ExpressionType.SIMPLE, "condition-entity");
		Skript.registerExpression(ConditionTargetEntity.class, Entity.class, ExpressionType.SIMPLE, "condition-targetentity");
		Skript.registerExpression(ConditionLocation.class, Location.class, ExpressionType.SIMPLE, "condition-location");
		Skript.registerExpression(ConditionTargetLocation.class, Location.class, ExpressionType.SIMPLE, "condition-targetlocation");
		Skript.registerExpression(ConditionName.class, String.class, ExpressionType.SIMPLE, "condition-name");
		Skript.registerExpression(ConditionArgs.class, String.class, ExpressionType.SIMPLE, "condition-args");
		Skript.registerExpression(MeetCondtion.class, Boolean.class, ExpressionType.SIMPLE, "condition-meet");
		Skript.registerEffect(SetConditionMeet.class, "set condition meet to %boolean%");
	}
}
