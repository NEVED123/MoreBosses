package net.fabricmc.example.models;

import net.fabricmc.example.entities.GiantPillagerBossEntity;
import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.AbstractZombieModel;
import net.minecraft.client.render.entity.model.GiantEntityModel;
import net.minecraft.client.render.entity.model.IllagerEntityModel;

public class GiantPillagerBossEntityModel extends AbstractZombieModel<GiantPillagerBossEntity> {

    public GiantPillagerBossEntityModel(ModelPart modelPart) {
        super(modelPart);
    }

    @Override
    public boolean isAttacking(GiantPillagerBossEntity entity) {
        return false;
    }

    public static TexturedModelData getTexturedModelData(){
        return TexturedModelData.of(getModelData(Dilation.NONE, 0F), 64, 64);
    }

}
