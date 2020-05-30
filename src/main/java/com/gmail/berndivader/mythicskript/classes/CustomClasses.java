package com.gmail.berndivader.mythicskript.classes;

import javax.annotation.Nullable;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import io.lumine.xikage.mythicmobs.drops.LootBag;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.mobs.MythicMob;
import io.lumine.xikage.mythicmobs.skills.SkillTargeter;
import io.lumine.xikage.mythicmobs.skills.targeters.ConsoleTargeter;
import io.lumine.xikage.mythicmobs.skills.targeters.IEntitySelector;
import io.lumine.xikage.mythicmobs.skills.targeters.ILocationSelector;
import io.lumine.xikage.mythicmobs.skills.targeters.MTTriggerLocation;
import io.lumine.xikage.mythicmobs.skills.targeters.OriginTargeter;
import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;

public class CustomClasses {
	public static void register() {
		
		Classes.registerClass(new ClassInfo<LootBag>(LootBag.class,"lootbag").name("lootbag").user("lootbag")
				.defaultExpression(new EventValueExpression<LootBag>(LootBag.class))
				.parser(new Parser<LootBag>() {
					
					@Override
					@Nullable
					public LootBag parse(String lootbag, ParseContext context) {
						return null;
					}
					
					@Override
					public String getVariableNamePattern() {
						return ".+";
					}
					@Override
					public String toString(LootBag lootBag, int flags) {
						return lootBag.toString();
					}
					@Override
					public String toVariableNameString(LootBag arg0) {
						return arg0.toString();
					}
				})
			);
			
		Classes.registerClass(new ClassInfo<MythicMob>(MythicMob.class,"mythicmob").name("mythicmob").user("mythicmob")
				.defaultExpression(new EventValueExpression<MythicMob>(MythicMob.class))
				.parser(new Parser<MythicMob>() {
					@Override
					@Nullable
					public MythicMob parse(String mythicmob, ParseContext context) {
						return null;
					}
					@Override
					@Nullable
					public String toString(MythicMob mm, int flags) {
						return mm.getInternalName();
					}
					@Override
					public String toVariableNameString(MythicMob mm) {
						return mm.getInternalName();
					}
					@Override
					public String getVariableNamePattern() {
						return ".+";
					}
		}));
		Classes.registerClass(new ClassInfo<ActiveMob>(ActiveMob.class,"activemob").name("activemob").user("activemob")
				.defaultExpression(new EventValueExpression<ActiveMob>(ActiveMob.class))
				.parser(new Parser<ActiveMob>() {
					@Override
					@Nullable
					public ActiveMob parse(String activemob, ParseContext context) {
						return null;
					}
					@Override
					@Nullable
					public String toString(ActiveMob am, int flags) {
						return am.getType().getInternalName();
					}
					@Override
					public String toVariableNameString(ActiveMob am) {
						return am.getUniqueId().toString();
					}
					@Override
					public String getVariableNamePattern() {
						return ".+";
					}
		}));
		Classes.registerClass(new ClassInfo<MythicSpawner>(MythicSpawner.class,"mythicspawner").name("mythicspawner").user("mythicspawner")
				.defaultExpression(new EventValueExpression<MythicSpawner>(MythicSpawner.class))
				.parser(new Parser<MythicSpawner>() {
					@Override
					@Nullable
					public MythicSpawner parse(String mythicSpawner, ParseContext context) {
						return null;
					}
					@Override
					@Nullable
					public String toString(MythicSpawner ms, int flags) {
						if (ms!=null) return ms.getInternalName();
						return null;
					}

					@Override
					public String toVariableNameString(MythicSpawner ms) {
						return ms.getName();
					}

					@Override
					public String getVariableNamePattern() {
						return ".+";
					}
					
		}));
		Classes.registerClass(new ClassInfo<MythicDrops>(MythicDrops.class,"mobdrop").name("mobdrop").user("mobdrop")
				.defaultExpression(new EventValueExpression<MythicDrops>(MythicDrops.class))
				.parser(new Parser<MythicDrops>() {

					@Override
					@Nullable
					public MythicDrops parse(String var1, ParseContext var2) {
						return null;
					}

					@Override
					@Nullable
					public String toString(MythicDrops drops, int var2) {
						return Integer.toString(drops.getDrops().size());
					}

					@Override
					public String toVariableNameString(MythicDrops drops) {
						return drops.getDrops().toString();
					}

					@Override
					public String getVariableNamePattern() {
						return ".+";
					}
		}));
		Classes.registerClass(new ClassInfo<MobItem>(MobItem.class,"mobitem").name("mobitem").user("mobitem")
				.defaultExpression(new EventValueExpression<MobItem>(MobItem.class))
				.parser(new Parser<MobItem>() {

					@Override
					@Nullable
					public MobItem parse(String var1, ParseContext var2) {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					@Nullable
					public String toString(MobItem item, int var2) {
						// TODO Auto-generated method stub
						return item.getItem().toString();
					}

					@Override
					public String toVariableNameString(MobItem var1) {
						// TODO Auto-generated method stub
						return var1.toString();
					}

					@Override
					public String getVariableNamePattern() {
						// TODO Auto-generated method stub
						return ".+";
					}
		}));
		Classes.registerClass(new ClassInfo<SkillTargeter>(SkillTargeter.class,"skilltargeter").name("skilltargeter").user("skilltargeter")
				.defaultExpression(new EventValueExpression<SkillTargeter>(SkillTargeter.class))
				.parser(new Parser<SkillTargeter>() {
					@Override
					@Nullable
					public SkillTargeter parse(String targeter, ParseContext context) {
						return null;
					}
					@Override
					@Nullable
					public String toString(SkillTargeter targeter, int flags) {
						String type = "unsupported";
						if (targeter instanceof IEntitySelector) {
							type = "EntitySelector";
						} else if ((targeter instanceof ILocationSelector) || (targeter instanceof OriginTargeter) 
								|| (targeter instanceof MTTriggerLocation)) {
							type = "LocationSelector";
						} else if (targeter instanceof ConsoleTargeter) {
							type = "ConsoleTargeter";
						}
						return type;
					}
					@Override
					public String toVariableNameString(SkillTargeter targeter) {
						return targeter.toString();
					}
					@Override
					public String getVariableNamePattern() {
						return ".+";
					}
		}));
	}
}
