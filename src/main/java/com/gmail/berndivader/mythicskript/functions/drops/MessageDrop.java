package com.gmail.berndivader.mythicskript.functions.drops;

import javax.annotation.Nullable;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.function.Function;
import io.lumine.mythic.api.config.MythicLineConfig;
import io.lumine.mythic.api.drops.DropMetadata;
import io.lumine.mythic.api.drops.IMessagingDrop;
import io.lumine.mythic.core.drops.Drop;

public class MessageDrop extends Drop implements IMessagingDrop {
	
	Function<?>function;
	Object[][]parameters;

	public MessageDrop(String line, MythicLineConfig mlc, Function<?>f) {
		super(line,mlc);
		
		function=f;
		parameters=new Object[2][];
		
	}

	@Override
	@Nullable
	public String getRewardMessage(DropMetadata data, double value) {
		parameters[0]=new DropMetadata[] {data};
		parameters[1]=new Double[] {value};
		Object[]result=function.execute(parameters);
		if(result!=null&&(result[0] instanceof String)) {
			return (String)result[0];
		}
		Skript.warning("The return for "+function.getName()+" expects a string but got "+result==null?"NULL":result[0].getClass().getName());
		return null;
	}

}
