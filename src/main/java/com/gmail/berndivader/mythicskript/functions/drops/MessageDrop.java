package com.gmail.berndivader.mythicskript.functions.drops;

import ch.njol.skript.Skript;
import ch.njol.skript.lang.function.Function;
import io.lumine.xikage.mythicmobs.drops.Drop;
import io.lumine.xikage.mythicmobs.drops.DropMetadata;
import io.lumine.xikage.mythicmobs.drops.IMessagingDrop;
import io.lumine.xikage.mythicmobs.io.MythicLineConfig;

public class MessageDrop extends Drop implements IMessagingDrop {
	
	Function<?>function;
	Object[][]parameters;

	public MessageDrop(String line, MythicLineConfig mlc, Function<?>f) {
		super(line,mlc);
		
		function=f;
		parameters=new Object[2][];
		
	}

	@Override
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
