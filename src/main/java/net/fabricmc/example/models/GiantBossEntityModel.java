package net.fabricmc.example.models;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.GiantEntityModel;

public class GiantBossEntityModel extends GiantEntityModel {
    public GiantBossEntityModel(ModelPart modelPart) {
        super(modelPart);
    }

    public static TexturedModelData getTexturedModelData(){
        return TexturedModelData.of(getModelData(Dilation.NONE, 0F), 64, 64);
    }
}
