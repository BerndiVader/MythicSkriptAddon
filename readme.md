# MythicMobs Skript Addon:
for MythicMobs 4.0.1 Release or higher

### Update 21.7.2017 v0.89a
## added expression convert %entity%(%player%) into mythicmob %string% with level %number%
## added effect remove mythic from activemob %activemob%
### Update 21.6.2017 v0.86a
## added event-location and event-entity to death event
### Update 19.6.2017 v0.85a
## added 1.12 support
### Update 2.3.2017 v0.84a
## look into the updatev084a.txt file for more details
##### Added new class SkillTargeter
##### Added new expression mythicmobs targeter %string%
##### Added new expression targetentities of %entity% for targeter %skilltargeter%
##### Added new expression targetlocations of %entity% for targeter %skilltargeter%

### Update 30.3.2017 v0.83a
## look into the updatev083a.txt file for more details
##### Changed meet condition default to true
##### Added skriptspawncondition for MythicMobs RandomSpawners to work with MythicMobs SnapShot 2105 or greater.

** Update 26.3.2017 v0.82a

Conditions:

// activemob %activemob% has immunitytable
// Test if activemob has immunitytable enabled

Effects:

// set damage of activemob %activemob% to %number%
// change the attackdamage

// set knockbackresist of activemob %activemob% to %number%
// Change Knockback resist

// set armor of activemob %activemob% to %number%
// Change armor stats (only >1.9)

// set attackspeed of activemob %activemob% to %number%
// Change attackspeed (only >1.9)

// set followrange of activemob %activemob% to %number%
// Change follow range

// set speed of activemob %activemob% to %number%
// Change movement speed

// trigger %string% for activemob %activemob%
// trigger %string% for activemob %activemob% with triggerentity %entity%
// Trigger activemob for trigger defined in %string%
// Valid triggers are: DEFAULT, ATTACK, BOW_HIT, BLOCK, COMBAT, CROUCH, UNCROUCH, DAMAGED, DROPCOMBAT, DEATH, ENTERCOMBAT, EXPLODE, INTERACT, KILL, KILLPLAYER, PLAYERDEATH, SHOOT, SIGNAL, SPAWN, SPLASH_POTION, SWING, TARGETCHANGE, TELEPORT, TIMER, USE, READY
```
skript part:

on damage of entity:
	victim instanceof activemob
	event-entity is arrow
	set {_am} to activemob of victim
	trigger "bow_hit" for activemob {_am} with triggerentity attacker
	
Mob.yml:

Monkey:
  Type: zombie
  Display: "MythicMobs Monkey"
  Health: 40
  Armor: 10
  Skills:
  - message{msg="Ouch! Stop shooting arrows at me!"} @trigger ~onBowHit 1
```

# Syntax:

## Classes:

#### activemob
#### activeplayer
#### mythicspawner
#### mobdrop
#### mobitem
#### skilltargeter


## Events:

### on mythicmob spawnevent
#### Called whenever MythicMobs spawns an ActiveMob.
###### Returns:
##### event-activemob
##### event-entity

### on mythicmob deathevent
#### Called whenever a MythicMob dies.
###### Returns: 
##### event-activemob
##### event-mobdrop
##### event-killer
##### event-location
##### event-entity
Example:
```
on mythicmob deathevent:
	send "You killed a MythicMob!" to event-killer
	if displayname of activemob event-activemob is "Super Zombie":
		loop all items of mobdrop event-mobdrop:
			if "%loop-mobitem%" contains "<do_not_drop>":
				remove mobitem loop-mobitem from mobdrop event-mobdrop
```

### on mythicspawner spawnevent
#### Called whenever a MythicMobs CustomSpawner spawns a MythicMob.
###### Returns:
##### event-activemob
##### event-mythicspawner
Example:
```
on mythicspawner spawnevent:
	broadcast "a %displayname of activemob event-activemob% just spawned at %location of activemob event-activemob%"
```

### on mythicmobs skriptskillevent:
#### Called when the mob casted the skill skriptskill.
###### Returns:
##### event-activmob = activemob if the caster is a activemob
##### event-entity = entity who casted the skill
##### skill-targettype = what kind of target? there are 3 possibilities: NONE, ENTITY, LOCATION
##### skill-target = target entity if targettype = ENTITY
##### event-location = location if the targettype is LOCATION
##### skill-trigger = trigger entity
##### skill-name = name of skriptskill skill
##### skill-args = arguments for the skriptskill
Example:
```
MythicMob mob.yml:
skriptmonkey:
  Type: pig
  Display: 'a Skriptmonkey Pig'
  Skills:
  - skriptskill{skill=msg;args="Hallo wie gehts, <target.name>?"} @trigger ~onInteract 1

Skript part:
on mythicmobs skriptskillevent:
	if skill-name is "msg":
		if skill-targettype is "ENTITY":
			skill-target is a player
			send skill-args to skill-target
```

### on mythicmobs skriptconditionevent:
#### Called on a skills skriptcondition.
###### Returns:
##### condition-activemob = activemob if the caster is a activemob
##### condition-entity = entity who cast the skill
##### condition-location = location to check
##### condition-name = name of the condition
##### condition-args = arguments of the condition
##### condition-meet = if the condition is meet or not (you must set this by yourself) standard = true (changed from false to true in update in 0.83a)

Example:
```
For Skill condition:

skskill:
  Conditions:
  - skriptcondition{c=weather;args=clear}
  Coolddown: 1
  Skills:
  - message{msg="Nice weather today! Isnt it, <target.name>?"}
  
For RandomSpawner condition:

RandomMonkey:
  Mobname: Monkey1
  Worlds: world
  Chance: 1
  Priority: 1
  Action: replace
  Conditions:
  - skriptspawncondition{c=region;args=test}

!!!!SKRIPTSPAWNCONDITION ONLY WORK ON MYTHICMOBS SNAPSHOT 2105 OR LATER!!!!
  
Skript part:

on mythicmobs skriptconditionevent:

	if condition-location is set:
		if "%condition-name%" is "region":
			set condition meet to false
			set {_mobtypes::*} to condition-args split at ","
			set {_regionname} to {_mobtypes::1}
			delete {_mobtypes::1}
			# make a list of all arguments
			set {_region::*} to regions at condition-location
			loop {_region::*}:
				if "%loop-value%" contains {_regionname}:
					set condition meet to true
					stop
					
	if condition-name is "weather":
		if condition-args is "clear":
			if weather in world of condition-entity is clear:
				set condition meet to true
			else:
				set condition meet to false
```

## Conditions:

### %entity% instanceof activemob
#### True if the entity is a ActiveMob:

Example:
```
on damage of player:
	if attacker instanceof activemob:
	    set {_am} to activemob of attacker
```

### activemob %activemob% isdead
#### True if the ActiveMob is dead
	
### activemob %activemob% has threattable
#### True if the ActiveMob has a threattable:

Example:
```
on damage of player:
	if attacker instanceof activemob:
		set {_am} to activemob of attacker
		if activemob {_am} has threattable:
			dropcombat for activemob {_am}
```

### activemob %activemob% has mythicspawner
#### true if the activemob has a mythicspawner:
Example:
```		
on activemob spawnevent:
	if activemob event-activemob has mythicspawner:
		set {_mythicspawner} to mythicspawner of event-activemob
```

### mythicspawner %mythicspawner% contains activemob %activemob%
True if the ActiveMob is attached to the MythicSpawner	


## Expressions:


#### for all Entities:


### convert %entity%(%player%) into mythicmob %string% with level %number%
- for all living entities including players.
- if you use a player the mythicmobs type need to be persistent true! and you have to handle world changing and deaths otherwise mythicmobs will crash.

### mythicmobs targeter %string%
- Get a MythicMobs targeter to filter entities/locations for any entity.
- %string% is any valid mythicmmobs targeter used in the mythicmobs targeter format.
- set {_targeter} to mythicmobs targeter "@PIR{r=30}"
- will set the targeter to pir with radius 30

### targetentities of %entity% for targeter %skilltargeter%
- set {_targeter} to mythicmobs targeter "@PIR{r=30}"
- set {_targets::*} to targetentities of event-entity for targeter {_targeter}
- first set the {_targeter} to @pir{r=30} and then filter all players for entity event-entitiy with targeter {_targeter}

### targetlocations of %entity% for targeter %skilltargeter%
- set {_targeter} to mythicmobs targeter "@Ring{radius=10;points=5}"
- set {locations::*} to targetlocations of event-entity for targeter {_targeter}
- first set the {_targeter} to @Ring{radius=10;points=5} and then create a ring of locations for entity event-entitiy with targeter {_targeter}
```
Skript Example:

on right click on entity:
	if event-entity instanceof activemob:
		set {_am} to activemob of event-entity
		set {_targeter} to mythicmobs targeter "@Ring{radius=10;points=5}"
		if "%{_targeter}%" is "EntitySelector":
			set {_targets::*} to targetentities of event-entity for targeter {_targeter}
			broadcast "%{_targets::*}%"
		else if "%{_targeter}%" is "LocationSelector":
			set {_targets::*} to targetlocations of event-entity for targeter {_targeter}
			broadcast "%{_targets::*}%"
```
  

  
#### for ActiveMob:

### activemob of %entity%
Get the activemob instance of the entity: set {_am} to activemob of event-entity

### location of activemob %activemob%
Returns the location of the activemob: set {_loc} to location of {_am}

### world of activemob %activemob%
Returns the world of the ActiveMob: set {_world} to world of event-activemob

### entity of activemob %activemob%
Get the entity instance of the ActiveMob: set {_entity} to entity of event-activemob

### activemob by uuid %string%
Get the ActiveMob by UUID. Return NULL if the entitiy is no ActiveMob.

### all activemobs [in world %string%]
Get all ActiveMobs on the whole server or, if a world is present in the expression, of the world:

Example:
```
on right click on entity:
	set {_count} to 0
	loop all activemobs in world "world":
		broadcast "%displayname of activemob loop-activemob%"
		add 1 to {_count}
	broadcast "%{_count}%"
```	

### displayname of activemob %activemob%
Returns the displayname as string of the ActiveMob, if it has one.

### mobtype of activemob %activemob%
Returns the MythicMob MobType of the ActiveMob as string.

### owner of activemob %activemob%
Returns the owner of the ActiveMob as entity, if there is a owner present.

### lastaggro of activemob %activemob%
Get the entity instance of the entity which caused the last trigger on the activemob: set {_lastaggro} to lastaggro of {_am}

### toptarget of activemob %activemob%
Get the entity instance of the activemobs target, or the topthreatholder if the activemob has a threadtable: set {_target} to toptarget of {_am}

### get threattable of activemob %activemob%
Returns a list with all entities of the activemobs threattable

### get threatvalue of %entity% from activemob %activemob%
Returns the threat value as number for entity out of activemobs threattable NOTE: entity have to be a threat of activemob

### uuid of activemob %activemob%
Returns a string with the uuid of the activemob: set {_uuid} to uuid of {_am}

### health of activemob %activemob%
Returns a number with the amount of health the activemob actually has left: set {_health} to health of {_am}

### maxhealth of activemob %activemob%
Returns a number with the max health of the activemob.

### mlc %string% of activemob %activemob%
%string% can be every YAML node of the mobs yml config. Returns the string of the %string% yml node of the activemob: set {_mobtype} to mlc {_node} of {_am}

### faction of activemob %activemob%
Returns the faction name the activemob is in: set {_faction} to faction of {_am}

### stance of activemob %activemob%
Returns the stance of the activemob, if it has one: set {_stance} to stance of {_am}

### level of activemob %activemob%
Returns the level of the activemob: set {_lvl} to level of {_am}

### playerkills of activemob %activemob%
Returns the playerkills of the activemob: set {_kills} to playerkills of {_am}

### lastsignal of activemob %activemob%
Returns the lastest signal of the activemob, if there is one: set {_signal} to lastsignal of {_am}



#### for MythicSpawner:

### mythicspawner of %activemob%
Returns the MythicSpawner instance of the activemob, if it have one: set {_ms} to mythicspawner of {_am}

### location of mythicspawner %mythicspawner%
Returns the Location of the MythicSpawner.

### world of mythicspawner %mythicspawner%
Returns the world of the MythicSpawner.

### all mythicspawners in world %string% - OR - all mythicspawners
Returns all MythicSpawners in the world %string% or if if used without world condition returns all MythicSpawners on the Server.

### all activemobs of mythicspawner %mythicspawner%
Returns all the ActiveMobs of the MythicSpawner

### mobtype of mythicspawner %mythicspawner%
Retruns the MythicMob mobtype name of the MythicSpawner as string.

### moblevel of mythicspawner %mythicspawner%
Returns the Level of the Spawner as Number

### cooldown of mythicspawner %mythicspawner% || remainingcooldown of mythicspawner %mythicspawner%
Returns the cooldown or the cooldown timer of the MythicSpawner. Both values are numbers in seconds.

### warmup of mythicspawner %mythicspawner% || remainingwarmup of mythicspawner %mythicspawner%
Returns the cooldown or the cooldown timer of the MythicSpawner. Both values are numbers in seconds.

### number of activemobs from mythicspawner %mythicspawner% || number of maxmobs from mythicspawner %mythicspawner%
Returns the number of spawned mobs or the number of MaxMobs that can be spawned.



#### for MobDrops & MobItems:

### all items of mobdrop %mobdrop%
Gets you the MythicMob Drops from the MythicMobDeathEvent. Only works in the mythicmob deathevent!



## Effects:

#### for Player:

### make player %entity% cast skill %string% with trigger %entity% at entity %entity% with delay %number% and repeat %number%
### make player %entity% cast skill %string% with trigger %entity% at location %location% with delay %number% and repeat %number%
### make player %entity% cast skill %string% with trigger %entity% at self with delay %number% and repeat %number%

Example:
```
#Skriptpart:

on left click:
	if player's tool is a sword:
		target is set
		cancel event
		make player player cast skill "Damage" with trigger player at entity target with delay 0 and repeat 0
	else if player's tool is dirt:
		cancel event
		make player player cast skill "Heal" with trigger player at self with delay 10 and repeat 20

#MythicMob skill part:
Heal:
  Skills:
  - effect:particles{particle=heart;amount=8;vSpread=0.5;hSpread=0.5;Spped=0.01;yoffset=1} @self
  - heal{a=2;overheal=false} @self

Damage:
  TargetConditions:
  - lineofsight true
  - distance{d=<10} true
  - distance{d=>3} true
  Skills:
  - effect:particleline{particle=lava;amount=20;vSpread=0.10;hSpread=0.10;Speed=0.2;yoffset=1;ystartoffset=0;distancebetween=1} @target
  - damage @target
```


#### for ActiveMob:


### remove mythic from activemob %activemob%
- make every mythicmobs into a common bukkit entity. including players.

### spawn mythicmob %string% at location %location% in world %string%
Spawns a MythicMob with mobtype %string% at the given location in the given world. Returns the ActiveMob instance of the spawned MythicMob. Mobtype string is case sensitive!

Example:
```
on right click:
	event-block is dirt
	set {_loc} to location of event-block
	set {_world} to "%world of event-block%"
	set {_mobtype} to "disguisedummy"
	set {_am} to spawn mythicmob {_mobtype} at location {_loc} in world {_world}
	if "%{_am}%" is "<none>":
		send "Something went wrong! Maybe the Mobtype does not exist?" to player
	else:
		send "You just spawned a MythicMob named %displayname of activemob {_am}%!" to player
```

### set health of activemob %activemob% to %number%
Set the health of the ActiveMob. Amount of 1 = 1/2 heart

### set maxhealth of activemob %activemob% to %number%
Set the max possible health the ActiveMob can have. 1 = 1/2 heart

### set lastaggro of activemob %activemob% to %entity%
Set the LastAggroEntity to a new entity: set lastaggro of {_am} to event-entity

### dropcombat for activemob %activemob%
Clears the target, the threattable & calls the dropcombat trigger (if threattable is enabled) for the activemob: dropcombat for {_am}

### set %entity% to new target of activemob %activemob%
Gives the ActiveMob a new valid target. Must be an entity: set attacker to new target of {_am}

### set faction of activemob %activemob% to %string%
Set the MythicMobs Faction Tag for the ActiveMob: set faction of event-activemob to "Orcs"

### set stance of activemob %activemob% to %string%
Change the stance of the ActiveMob: set stance of event-activemob to "fight"

### set level of activemob %activemob% to %number%
Change the level of the ActiveMob: set level of {_am} to 10

### set owner of activemob %activemob% to %entity%
### set owner of activemob %activemob% to %string% by uuid
Change the owner of the ActiveMob eighter by %entity% or by UUID in String format.

### set kills of activemob %activemob% to %number%
Set the playerkills of the activemob to a new amount:

Example:
```
if event-entity instanceof activemob:
	set {_am} to activemob of event-entity
	set {_pks} to playerkills of activemob {_am}
	add 1 to {_pks}
	set kills of activemob {_am} to {_pks}
```

### send signal %string% to activemob %activemob% with trigger %entity%
Use this effect to send the signal %string% to the %activemob% with the trigger %entity%

### remove activemob %activemob%
Use this effect to remove the ActiveMob.

### make activemob %activemob% cast skill %string% with trigger %entity% at target %entity%
### make activemob %activemob% cast skill %string% with trigger %entity% at location %location%
Use this effect to make an activemob cast a skill.

### get threattable of activemob %activemob%
Returns a list with all entities of the activemobs threattable.

### get threatvalue of %entity% from activemob %activemob%
Returns the value as number of threat from entity out of activemobs threattable NOTE: entity have to be a threat of activemob

### inc threat of %entity% by %number% from activemob %activemob%
### dec threat of %entity by %number% from activemob %activemob%
Increases (inc) or decreases (dec) the threat by amount of the entity from activemobs threattable.

### remove threat of %entity% from activemob %activemob%
Remove the entity from activemobs threattable.

### clear threattable of activemob %activemob%
This clear the complete threattable of the activemob.

### set damage of activemob %activemob% to %number%
Change the attackdamage

### set knockbackresist of activemob %activemob% to %number%
Change Knockback resist

### set armor of activemob %activemob% to %number%
Change armor stats (only >1.9)

### set attackspeed of activemob %activemob% to %number%
Change attackspeed (only >1.9)

### set followrange of activemob %activemob% to %number%
Change follow range

### set speed of activemob %activemob% to %number%
Change movement speed

### trigger %string% for activemob %activemob%
### trigger %string% for activemob %activemob% with triggerentity %entity%
Trigger activemob for trigger defined in %string%
Valid triggers are: DEFAULT, ATTACK, BOW_HIT, BLOCK, COMBAT, CROUCH, UNCROUCH, DAMAGED, DROPCOMBAT, DEATH, ENTERCOMBAT, EXPLODE, INTERACT, KILL, KILLPLAYER, PLAYERDEATH, SHOOT, SIGNAL, SPAWN, SPLASH_POTION, SWING, TARGETCHANGE, TELEPORT, TIMER, USE, READY
```
skript part:

on damage of entity:
	victim instanceof activemob
	event-entity is arrow
	set {_am} to activemob of victim
	trigger "bow_hit" for activemob {_am} with triggerentity attacker
	
	
Mob.yml:

Monkey:
  Type: zombie
  Display: "MythicMobs Monkey"
  Health: 40
  Armor: 10
  Skills:
  - message{msg="Ouch! Stop shooting arrows at me!"} @trigger ~onBowHit 1
```

#### for MythicSpawner:

### activate mythicspawner %mythicspawner% || deactivate mythicspawner %mythicspawner%
Activates or deaktivates a MythicSpawner.

### set cooldown of mythicspawner %mythicspawner% to %number% || set remainingcooldown of mythicspawner %mythicspawner% to %number%
Set the cooldown or the remaining cooldown of the MythicMobSpawner in seconds.

### set warmup of mythicspawner %mythicspawner% to %number% || set remainingwarmup of mythicspawner %mythicspawner% to %number%
Set the warumup or the remaining warmup of the MythicMobspawner in seconds.

### set mobtype of mythicspawner %mythicspawner% to %string%
Change the Mobtype of the MythicSpawner. Where %string% is a valid MythicMob.

### set moblevel of mythicspawner %mythicspawner% to %number%
Change to moblevel of the MythicSpawner.

### make mythicspawner %mythicspawner% spawn
Activates the MythicSpawner. 

### attach activemob %activemob% to mythicspawner %mythicspawner%
Register the given ActiveMob to the MythicSpawner.


#### for MobDrops & MobItems:

### remove mobitem %mobitem% from mobdrop %mobdrop% || clear mobdrop %mobdrop%
Remove the given mobitem from the mobdrop or clears the mobdrop completely.

### set material of mobitem %mobitem% to %string%
Change the materialtype of the given mobitem into a new Materialtype. %string% have to be a valid Bukkit Material.

Example:
```
on mythicmob deathevent:
	loop all items of mobdrop event-mobdrop:
		if "%loop-mobitem%" contains "STONE":
			set material of mobitem loop-mobitem to "GRASS"
		if "%loop-mobitem%" contains "DIRT":
			remove mobitem loop-mobitem from mobdrop event-mobdrop
```

### add item %itemstack% to mobdrop %mobdrop%
Add a new item to the mobdrop of the mythicmob deathevent.

Example:
```
on mythicmob deathevent:
	clear mobdrop event-mobdrop
	set {_item} to diamond sword named "Godsword"
	add item {_item} to mobdrop event-mobdrop
```