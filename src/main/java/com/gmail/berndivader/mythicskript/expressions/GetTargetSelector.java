package com.gmail.berndivader.mythicskript.expressions;

import java.util.Optional;

import org.jetbrains.annotations.Nullable;

import org.bukkit.event.Event;

import com.gmail.berndivader.mythicskript.Utils;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import io.lumine.mythic.core.skills.SkillTargeter;
import io.lumine.mythic.core.skills.targeters.CustomTargeter;

public class GetTargetSelector extends SimpleExpression<SkillTargeter> {
	private Expression<String> targeterString;

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends SkillTargeter> getReturnType() {
		return SkillTargeter.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean arg2, ParseResult arg3) {
		this.targeterString = (Expression<String>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean var2) {
		return getClass().getSimpleName()+e!=null?"@"+e.getEventName():"";
	}

	@Override
	@Nullable
	protected SkillTargeter[] get(Event e) {
	    Optional<SkillTargeter> maybeTargeter = Optional.empty();
		String targeterName = this.targeterString.getSingle(e);
		targeterName = targeterName.startsWith("@")?targeterName:"@"+targeterName;
		maybeTargeter = Optional.of((SkillTargeter)Utils.parseSkillTargeter(targeterName));
		if (maybeTargeter.isPresent()) {
            SkillTargeter targeter = maybeTargeter.get();
   			if (targeter instanceof CustomTargeter
   					&&((CustomTargeter) targeter).getTargeter().isPresent()) {
   				targeter=((CustomTargeter) targeter).getTargeter().get();
   			}
            return new SkillTargeter[] {targeter};
		}
		return null;
	}
}
