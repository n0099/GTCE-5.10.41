package gregtech.api.items;

import ic2.api.item.IBoxable;
import ic2.core.util.StackUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.List;

public class GT_RadioactiveCell_Item extends GT_Generic_Item  implements IBoxable {
    protected int cellCount;
    protected int maxDmg;
    protected int dura;

    public GT_RadioactiveCell_Item(String aUnlocalized, String aEnglish, int aCellcount) {
        super(aUnlocalized, aEnglish, null);
        this.func_77625_d(64);
        this.func_77656_e(100);
        setNoRepair();
        this.cellCount = Math.max(1, aCellcount);
    }

    public static int getDurabilityOfStack(ItemStack aStack) {
        NBTTagCompound tNBT = aStack.func_77978_p();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.func_77982_d(tNBT);
        }
        return tNBT.func_74762_e("advDmg");
    }

    protected static int sumUp(int a) {
        int b = 0;
        for (int c = 1; c <= a; c++) {
            b += c;
        }
        return b;
    }
    
    protected static int triangularNumber(int x)
    {
      return (x * x + x) / 2;
    }

    protected boolean outputPulseForStack(ItemStack aStack) {
        NBTTagCompound tNBT = aStack.func_77978_p();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.func_77982_d(tNBT);
        }
        tNBT.func_74768_a("output", tNBT.func_74762_e("output") + 1);
        return false;//(this.pulserate > 0) || (tNBT.getInteger("output") % -this.pulserate == 0);
    }

    protected boolean incrementPulseForStack(ItemStack aStack) {
        NBTTagCompound tNBT = aStack.func_77978_p();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.func_77982_d(tNBT);
        }
        tNBT.func_74768_a("pulse", tNBT.func_74762_e("pulse") + 1);
        return false;//(this.pulserate > 0) || (tNBT.getInteger("pulse") % -this.pulserate == 0);
    }

    protected void setDurabilityForStack(ItemStack aStack, int aDurability) {
        NBTTagCompound tNBT = aStack.func_77978_p();
        if (tNBT == null) {
            tNBT = new NBTTagCompound();
            aStack.func_77982_d(tNBT);
        }
        tNBT.func_74768_a("durability", aDurability);
//    if (this.maxDelay > 0)
//    {
//      double var4 = (this.maxDelay - aDurability) / this.maxDelay;
//      int var6 = (int)(aStack.getMaxDamage() * var4);
//      if (var6 >= aStack.getMaxDamage()) {
//        var6 = aStack.getMaxDamage() - 1;
//      }
//      aStack.setItemDamage(aStack.getMaxDamage() - var6);
//    }
    }

    public int getMaxNuclearDurability() {
        return 0;//return this.maxDelay;
    }

    public int func_77619_b() {
        return 0;
    }

    public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
        return false;
    }

    public boolean func_82789_a(ItemStack par1ItemStack, ItemStack par2ItemStack) {
        return false;
    }

    public void setDamageForStack(ItemStack stack, int advDmg) {
        NBTTagCompound nbtData = StackUtil.getOrCreateNbtData(stack);
        nbtData.func_74768_a("advDmg", advDmg);
        if (this.maxDmg > 0) {
            double p = (double) advDmg / (double) this.maxDmg;
            int newDmg = (int) (stack.func_77958_k() * p);
            if (newDmg >= stack.func_77958_k()) {
                newDmg = stack.func_77958_k() - 1;
            }
            stack.func_77964_b(newDmg);
            this.dura = newDmg;
        }
    }

    public int getDamageOfStack(ItemStack stack) {
        NBTTagCompound nbtData = StackUtil.getOrCreateNbtData(stack);
        this.dura = nbtData.func_74762_e("advDmg");
        return this.dura;
    }

    public int getControlTagOfStack(ItemStack stack) {
        NBTTagCompound nbtData = StackUtil.getOrCreateNbtData(stack);
        return nbtData.func_74762_e("tag");
    }

    public void setControlTagOfStack(ItemStack stack, int tag) {
        NBTTagCompound nbtData = StackUtil.getOrCreateNbtData(stack);
        nbtData.func_74768_a("tag", tag);
    }

    public int getMaxDamageEx() {
        return this.maxDmg;
    }

    public void damageItemStack(ItemStack stack, int Dmg) {
        setDamageForStack(stack, getDamageOfStack(stack) + Dmg);
    }

    public void addAdditionalToolTips(List aList, ItemStack aStack, EntityPlayer aPlayer) {
        super.addAdditionalToolTips(aList, aStack, aPlayer);
        //aList.add("Time left: " + (this.maxDelay - getDurabilityOfStack(aStack)) + " secs");
        aList.add("Durability: " + (this.maxDmg - getDurabilityOfStack(aStack)) + "/" + this.maxDmg);
    }

	@Override
	public boolean canBeStoredInToolbox(ItemStack itemstack) {
		return true;
	}
}
