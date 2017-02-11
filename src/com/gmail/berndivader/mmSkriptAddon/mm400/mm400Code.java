package com.gmail.berndivader.mmSkriptAddon.mm400;

import com.gmail.berndivader.mmSkriptAddon.mm400.classes.CustomClasses;
import com.gmail.berndivader.mmSkriptAddon.mm400.conditions.*;
import com.gmail.berndivader.mmSkriptAddon.mm400.effects.*;
import com.gmail.berndivader.mmSkriptAddon.mm400.expressions.*;
import com.gmail.berndivader.mmSkriptAddon.mm400.events.*;

public class mm400Code {
	
	public static void register() {
		CustomClasses.register();
		Events.register();
		Conditions.register();
		Expressions.register();
		Effects.register();
	}
}
