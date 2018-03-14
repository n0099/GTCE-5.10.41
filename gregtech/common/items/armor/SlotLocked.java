package gregtech.common.items.armor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


public class SlotLocked extends Slot {

    public SlotLocked(IInventory par1iInventory, int par2, int par3, int par4) {
        super(par1iInventory, par2, par3, par4);
    }

    @Override
    public void func_82870_a(EntityPlayer player, ItemStack itemStack) {
    }

    @Override
    public boolean func_75214_a(ItemStack par1ItemStack) {
        return false;
    }

    @Override
    public boolean func_75216_d() {
        return false;
    }

    @Override
    public ItemStack func_75209_a(int i) {
        return null;
    }

    @Override
    public boolean func_82869_a(EntityPlayer stack) {
        return false;
    }

}
