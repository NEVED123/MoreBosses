package net.fabricmc.more_bosses.models.bosses;

import net.fabricmc.more_bosses.entities.bosses.GiantZombieBossEntity;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.AbstractZombieModel;

public class GiantZombieBossEntityModel extends AbstractZombieModel<GiantZombieBossEntity> {
    public GiantZombieBossEntityModel(ModelPart modelPart) {
        super(modelPart);
    }

    @Override
    public boolean isAttacking(GiantZombieBossEntity entity) {
        return false;
    }

    public static TexturedModelData getTexturedModelData(){
        return TexturedModelData.of(getModelData(Dilation.NONE, 0F), 64, 64);
    }
}
