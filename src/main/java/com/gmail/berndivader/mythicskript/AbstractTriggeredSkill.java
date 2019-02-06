package com.gmail.berndivader.mythicskript;

import io.lumine.xikage.mythicmobs.adapters.AbstractEntity;
import io.lumine.xikage.mythicmobs.adapters.AbstractLocation;
import io.lumine.xikage.mythicmobs.mobs.ActiveMob;
import io.lumine.xikage.mythicmobs.skills.IParentSkill;
import io.lumine.xikage.mythicmobs.skills.SkillMetadata;
import io.lumine.xikage.mythicmobs.skills.SkillTrigger;

public class AbstractTriggeredSkill
implements IParentSkill {
    private SkillMetadata data;
    private boolean cancel = false;

    
    public AbstractTriggeredSkill(SkillTrigger cause, ActiveMob am, AbstractEntity trigger) {
        this(cause, am, trigger, null, false);
    }
    
    public AbstractTriggeredSkill(SkillTrigger cause, ActiveMob am, AbstractEntity trigger, AbstractLocation origin, boolean sync) {
        this.data = new SkillMetadata(cause, am, trigger);
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

