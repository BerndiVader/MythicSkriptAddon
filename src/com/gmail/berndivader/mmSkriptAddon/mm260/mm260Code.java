package com.gmail.berndivader.mmSkriptAddon.mm260;

import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.registrations.Classes;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;

public class mm260Code {
	public static void register() {
		Classes.registerClass(new ClassInfo<ActiveMob>(ActiveMob.class,"activemob").name("ActiveMob").user("activemob"));
	}

}
