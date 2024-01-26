package com.gmail.berndivader.mythicskript.functions.conditions;

import ch.njol.skript.lang.function.Function;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.skills.placeholders.PlaceholderString;
import io.lumine.mythic.core.skills.SkillCondition;
import io.lumine.mythic.core.skills.conditions.ConditionAction;

public abstract class Condition extends SkillCondition {
	
	protected final Function<?>function;
	protected Object[][]parameters;
	
	public Condition(String line, MythicLineConfig mlc, Function<?>f) {
		super(line);
		
		function=f;
		
		String a = mlc.getString("action", "");

		if (a.length() != 0) {
			String[] arr = a.split(" ");
			if (ConditionAction.isAction(arr[0])) {
				this.ACTION = ConditionAction.valueOf(arr[0].toUpperCase());
				if (arr.length > 1)
					this.actionVar = PlaceholderString.of(arr[1]);
			} else {
				this.conditionVar = arr[0];
				if (arr.length > 1 && ConditionAction.isAction(arr[1])) {
					this.ACTION = ConditionAction.valueOf(arr[1].toUpperCase());
					if (arr.length > 2)
						this.actionVar = PlaceholderString.of(arr[2]);
				}
			}
		}
	}
}
