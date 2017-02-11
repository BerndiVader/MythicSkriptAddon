package com.gmail.berndivader.mmSkriptAddon.mm400.classes;

import javax.annotation.Nullable;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.registrations.Classes;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.spawning.spawners.MythicSpawner;

public class CustomClasses {
	public static void register() {
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
	}
}
