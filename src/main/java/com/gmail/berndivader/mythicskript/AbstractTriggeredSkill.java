package com.gmail.berndivader.mythicskript;

import io.lumine.mythic.api.adapters.AbstractEntity;
import io.lumine.mythic.api.adapters.AbstractLocation;
import io.lumine.mythic.api.skills.IParentSkill;
import io.lumine.mythic.api.skills.SkillTrigger;
import io.lumine.mythic.core.mobs.ActiveMob;
import io.lumine.mythic.core.skills.SkillMetadataImpl;

public class AbstractTriggeredSkill
implements IParentSkill {
    private SkillMetadataImpl data;
    private boolean cancel = false;
    
    public AbstractTriggeredSkill(SkillTrigger<?> cause, ActiveMob am, AbstractEntity trigger) {
        this(cause, am, trigger, null, false);
    }
    
    public AbstractTriggeredSkill(SkillTrigger<?> cause, ActiveMob am, AbstractEntity trigger, AbstractLocation origin, boolean sync) {
        this.data = new SkillMetadataImpl(cause, am, trigger);
        this.data.setCallingEvent(this);
        this.data.setIsAsync(!sync);
        if (this.data.getTrigger() != null) {
            if (am instanceof ActiveMob) {
                am.setLastAggroCause(this.data.getTrigger());
            }
            this.data.setEntityTarget(trigger);
        }
        if (origin != null) {
            this.data.setOrigin(origin);
        }
        this.data.setPower(am.getPower());
        am.getType().executeSkills(this.data.getCause(), this.data);
    }

    @Override
    public void setCancelled() {
        this.cancel = true;
    }

    @Override
    public boolean getCancelled() {
        return this.cancel;
    }
}

