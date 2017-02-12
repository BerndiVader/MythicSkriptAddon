#MythicMobs Skript Addon:


## Classes:

#### activemob
#### mythicspawner


## Events:

### on activemob spawnevent
Called whenever MythicMobs spawns an ActiveMob. Returns event-activemob & event-entity.


## Conditions:

### %entity% instanceof activemob
true if the entity is a activemob:
```
on damage of player:
	if attacker instanceof activemob:
	    set {_am} to attacker
```

### %activemob% is dead
True if the ActiveMob is dead
	
### %activemob% has threattable
true if the activemob has a threattable:
```
on damage of player:
	if attacker instanceof activemob:
		set {_am} to attacker
		if {_am} has threattable:
			dropcombat for {_am}
```

### %activemob% has mythicspawner
true if the activemob has a mythicspawner:
```		
on activemob spawnevent:
	if event-activemob has mythicspawner:
		set {_mythicspawner} to mythicspawner of event-activemob
```			


## Expressions:

### activemob of %entity%
Get the activemob instance of the entity: set {_am} to activemob of event-entity

### location of %activemob%
Returns the location of the activemob: set {_loc} to location of {_am}

### world of %activemob%
Returns the world of the ActiveMob: set {_world} to world of event-activemob

### entity of %activemob%
Get the entity instance of the ActiveMob: set {_entity} to entity of event-activemob

### activemob by uuid %string%
Get the ActiveMob by UUID. Return NULL if the entitiy is no ActiveMob.

### all activemobs [in world %string%]
Get all ActiveMobs on the whole server or, if a world is present in the expression, of the world:
```
Example:
	on right click on entity:
		set {_count} to 0
		loop all activemobs in world "world":
			broadcast "displayname of loop-value"
			add 1 to {_count}
			broadcast "%{_count}%"
```	

### displayname of %activemob%
Returns the displayname as string of the ActiveMob, if it has one.

### owner of %activemob%
Returns the owner of the ActiveMob as entity, if there is a owner present.

### lastaggro of %activemob%
Get the entity instance of the entity which caused the last trigger on the activemob: set {_lastaggro} to lastaggro of {_am}

### toptarget of %activemob%
Get the entity instance of the activemobs target, or the topthreatholder if the activemob has a threadtable: set {_target} to toptarget of {_am}

### uuid of %activemob%
Returns a string with the uuid of the activemob: set {_uuid} to uuid of {_am}

### health of %activemob%
Returns a number with the amount of health the activemob actually has left: set {_health} to health of {_am}

### mlc %string% of %activemob%
%string% can be every YAML node of the mobs yml config. Returns the string of the %string% yml node of the activemob: set {_mobtype} to mlc {_node} of {_am}

### faction of %activemob%
Returns the faction name the activemob is in: set {_faction} to faction of {_am}

### stance of %activemob%
Returns the stance of the activemob, if it has one: set {_stance} to stance of {_am}

### level of %activemob%
Returns the level of the activemob: set {_lvl} to level of {_am}

### playerkills of %activemob%
Returns the playerkills of the activemob: set {_kills} to playerkills of {_am}

### lastsignal of %activemob%
Returns the lastest signal of the activemob, if there is one: set {_signal} to lastsignal of {_am}


### mythicspawner of %activemob%
Returns the MythicSpawner instance of the activemob, if it have one: set {_ms} to mythicspawner of {_am}

### location of %mythicspawner%
Returns the Location of the MythicSpawner.

### world of %mythicspawner%
Returns the world of the MythicSpawner.

### all mythicspawners in world %string% - OR - all mythicspawners
Returns all MythicSpawners in the world %string% or if if used without world condition returns all MythicSpawners on the Server.

### all activemobs of spawner %mythicspawner%
Returns all the ActiveMobs of the MythicSpawner


## Effects:

### set lastaggro of %activemob% to %entity%
Set the LastAggroEntity to a new entity: set lastaggro of {_am} to event-entity

### set dropcombat for %activemob%
Clears the target, the threattable & calls the dropcombat trigger (if threattable is enabled) for the activemob: dropcombat for {_am}

### set %entity% to new target of %activemob%
Gives the ActiveMob a new valid target. Must be an entity: set attacker to new target of {_am}

### set faction of %activemob% to %string%
Set the MythicMobs Faction Tag for the ActiveMob: set faction of event-activemob to "Orcs"

### set stance of %activemob% to %string%
Change the stance of the ActiveMob: set stance of event-activemob to "fight"

### set level of %activemob% to %number%
Change the level of the ActiveMob: set level of {_am} to 10

### set owner of %activemob% to %entity%
### set owner of %activemob% to %string%
Change the owner of the ActiveMob eighter by %entity% or by UUID in String format.

### set kills of %activemob% to %number%
Set the playerkills of the activemob to a new amount:
```
Example:
	if event-entity instanceof activemob:
		set {_am} to activemob of event-entity
		set {_pks} to playerkills of {_am}
		add 1 to {_pks}
		set kills of {_am} to {_pks}
```

### send signal %string% to %activemob% with trigger %entity%
Use this effect to send the signal %string% to the %activemob% with the trigger %entity%

### remove %activemob%
Use this effect to remove the ActiveMob.
