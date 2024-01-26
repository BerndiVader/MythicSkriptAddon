package com.gmail.berndivader.mythicskript.classes;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import io.lumine.mythic.api.drops.DropMetadata;
import io.lumine.mythic.api.mobs.MythicMob;
import io.lumine.mythic.api.skills.SkillMetadata;
import io.lumine.mythic.core.drops.LootBag;
import io.lumine.mythic.core.items.MythicItem;
import io.lumine.mythic.core.mobs.ActiveMob;
import io.lumine.mythic.core.skills.SkillTargeter;
import io.lumine.mythic.core.skills.targeters.ConsoleTargeter;
import io.lumine.mythic.core.skills.targeters.IEntitySelector;
import io.lumine.mythic.core.skills.targeters.ILocationSelector;
import io.lumine.mythic.core.skills.targeters.OriginTargeter;
import io.lumine.mythic.core.skills.targeters.TriggerLocationTargeter;
import io.lumine.mythic.core.spawning.spawners.MythicSpawner;


public class MythicClasses {
	public static void register() {
		
		Classes.registerClass(new ClassInfo<MythicItem>(MythicItem.class,"mythicitem").name("mythicitem").user("mythicitem")
				.defaultExpression(new EventValueExpression<MythicItem>(MythicItem.class))
				.parser(new Parser<MythicItem>() {
					
					@Override
					public boolean canParse(ParseContext context) {
						return false;
					}

					@Override
					public String toString(MythicItem arg0, int arg1) {
						return arg0.toString();
					}

					@Override
					public String toVariableNameString(MythicItem arg0) {
						return arg0.toString();
					}
					
				})
		);
		
		Classes.registerClass(new ClassInfo<DropMetadata>(DropMetadata.class,"dropdata").name("dropdata").user("dropdata")
			.defaultExpression(new EventValueExpression<DropMetadata>(DropMetadata.class))
			.parser(new Parser<DropMetadata>() {
				
				@Override
				public boolean canParse(ParseContext context) {
					return false;
				}
				
				@Override
				public String toString(DropMetadata data, int flags) {
					return data.toString();
				}

				@Override
				public String toVariableNameString(DropMetadata o) {
					return o.toString();

				}
				
			})
		);
		
		Classes.registerClass(new ClassInfo<LootBag>(LootBag.class,"lootbag").name("lootbag").user("lootbag")
				.defaultExpression(new EventValueExpression<LootBag>(LootBag.class))
				.parser(new Parser<LootBag>() {
					
					@Override
					public boolean canParse(ParseContext context) {
						return false;
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
		
		Classes.registerClass(new ClassInfo<SkillMetadata>(SkillMetadata.class,"skilldata").name("skilldata").user("skilldata")
				.defaultExpression(new EventValueExpression<SkillMetadata>(SkillMetadata.class))
				.parser(new Parser<SkillMetadata>() {
					
					@Override
					public boolean canParse(ParseContext context) {
						return false;
					}					

					@Override
					public String toString(SkillMetadata data, int flags) {
						return data.toString();
					}

					@Override
					public String toVariableNameString(SkillMetadata data) {
						return data.toString();
					}
					
				})
			);
			
		Classes.registerClass(new ClassInfo<MythicMob>(MythicMob.class,"mythicmob").name("mythicmob").user("mythicmob")
				.defaultExpression(new EventValueExpression<MythicMob>(MythicMob.class))
				.parser(new Parser<MythicMob>() {
					
					@Override
					public boolean canParse(ParseContext context) {
						return false;
					}					

					@Override
					public String toString(MythicMob mm, int flags) {
						return mm.getInternalName();
					}
					
					@Override
					public String toVariableNameString(MythicMob mm) {
						return mm.getInternalName();
					}
		}));
		Classes.registerClass(new ClassInfo<ActiveMob>(ActiveMob.class,"activemob").name("activemob").user("activemob")
				.defaultExpression(new EventValueExpression<ActiveMob>(ActiveMob.class))
				.parser(new Parser<ActiveMob>() {
					
					@Override
					public boolean canParse(ParseContext context) {
						return false;
					}					

					@Override
					public String toString(ActiveMob am, int flags) {
						return am.getType().getInternalName();
					}
					
					@Override
					public String toVariableNameString(ActiveMob am) {
						return am.getUniqueId().toString();
					}
		}));
		Classes.registerClass(new ClassInfo<MythicSpawner>(MythicSpawner.class,"mythicspawner").name("mythicspawner").user("mythicspawner")
				.defaultExpression(new EventValueExpression<MythicSpawner>(MythicSpawner.class))
				.parser(new Parser<MythicSpawner>() {
					@Override
					public boolean canParse(ParseContext context) {
						return false;
					}
					@Override
					public String toString(MythicSpawner ms, int flags) {
						if (ms!=null) return ms.getInternalName();
						return "null";
					}

					@Override
					public String toVariableNameString(MythicSpawner ms) {
						return ms.getName();
					}					
		}));
		Classes.registerClass(new ClassInfo<MythicDrops>(MythicDrops.class,"mobdrop").name("mobdrop").user("mobdrop")
				.defaultExpression(new EventValueExpression<MythicDrops>(MythicDrops.class))
				.parser(new Parser<MythicDrops>() {

					@Override
					public boolean canParse(ParseContext context) {
						return false;
					}

					@Override
					public String toString(MythicDrops drops, int var2) {
						return Integer.toString(drops.getDrops().size());
					}

					@Override
					public String toVariableNameString(MythicDrops drops) {
						return drops.getDrops().toString();
					}
		}));
		Classes.registerClass(new ClassInfo<MobItem>(MobItem.class,"mobitem").name("mobitem").user("mobitem")
				.defaultExpression(new EventValueExpression<MobItem>(MobItem.class))
				.parser(new Parser<MobItem>() {

					@Override
					public boolean canParse(ParseContext context) {
						return false;
					}

					@Override
					public String toString(MobItem item, int var2) {
						return item.getItem().toString();
					}

					@Override
					public String toVariableNameString(MobItem var1) {
						return var1.toString();
					}
		}));
		Classes.registerClass(new ClassInfo<SkillTargeter>(SkillTargeter.class,"skilltargeter").name("skilltargeter").user("skilltargeter")
				.defaultExpression(new EventValueExpression<SkillTargeter>(SkillTargeter.class))
				.parser(new Parser<SkillTargeter>() {
					@Override
					public boolean canParse(ParseContext context) {
						return false;
					}
					@Override
					public String toString(SkillTargeter targeter, int flags) {
						String type = "unsupported";
						if (targeter instanceof IEntitySelector) {
							type = "EntitySelector";
						} else if ((targeter instanceof ILocationSelector) || (targeter instanceof OriginTargeter) 
								|| (targeter instanceof TriggerLocationTargeter)) {
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
		}));
	}
}
