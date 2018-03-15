package gregtech.api.util;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import gregtech.api.metatileentity.BaseMetaTileEntity;
import gregtech.common.tileentities.machines.basic.GT_MetaTileEntity_MonsterRepellent;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingSpawnEvent.CheckSpawn;

import java.util.ArrayList;
import java.util.List;

public class GT_SpawnEventHandler {

    public static volatile List<int[]> mobReps = new ArrayList();

    public GT_SpawnEventHandler() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void denyMobSpawn(CheckSpawn event) {
        if (event.getResult() == Event.Result.ALLOW) {
            return;
        }
        if (event.getEntityLiving().isCreatureType(EnumCreatureType.MONSTER, false)) {
            for (int[] rep : mobReps) {
                if (rep[3] == event.getEntity().field_70170_p.field_73011_w.getDimension()) {
                    TileEntity tTile = event.getEntity().field_70170_p.func_175625_s(new BlockPos(rep[0], rep[1], rep[2]));
                    if (tTile instanceof BaseMetaTileEntity && ((BaseMetaTileEntity) tTile).getMetaTileEntity() instanceof GT_MetaTileEntity_MonsterRepellent) {
                        int r = ((GT_MetaTileEntity_MonsterRepellent) ((BaseMetaTileEntity) tTile).getMetaTileEntity()).mRange;
                        double dx = rep[0] + 0.5F - event.getEntity().field_70165_t;
                        double dy = rep[1] + 0.5F - event.getEntity().field_70163_u;
                        double dz = rep[2] + 0.5F - event.getEntity().field_70161_v;
                        if ((dx * dx + dz * dz + dy * dy) <= Math.pow(r, 2)) {
                            event.setResult(Event.Result.DENY);
                        }
                    }
                }
            }
        }
    }
}
