package gregtech.common.items.armor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public abstract class ContainerModularArmor extends Container {

	public InventoryArmor mInvArmor;

	public ContainerModularArmor(EntityPlayer player, InventoryArmor aInvArmor) {
		this.mInvArmor = aInvArmor;
		addSlots(player.field_71071_by);
	}
	
	public ArmorData getData(EntityPlayer aPlayer){
		
		
		
		return null;
	}

	@Override
	public boolean func_75145_c(EntityPlayer aPlayer) {
		return true;
	}

	public abstract void addSlots(InventoryPlayer aInventoryPlayer);

	public abstract int getSlotCount();

	public abstract int getShiftClickSlotCount();

	public void saveInventory(EntityPlayer entityplayer) {
		mInvArmor.onGuiSaved(entityplayer);
	}

	@Override
	public void func_75134_a(EntityPlayer player) {
		super.func_75134_a(player);
		if (!player.field_70170_p.field_72995_K) {
			saveInventory(player);
		}
	}

	@Override
	public ItemStack func_82846_b(EntityPlayer player, int slotIndex) {
		if (player == null) {
			return null;
		}

		ItemStack originalStack = null;
		Slot slot = (Slot) field_75151_b.get(slotIndex);
		int numSlots = field_75151_b.size();
		if (slot != null && slot.func_75216_d()) {
			ItemStack stackInSlot = slot.func_75211_c();
			originalStack = stackInSlot.func_77946_l();
			if (slotIndex >= numSlots - 9 * 4 && tryShiftItem(stackInSlot, numSlots)) {
			} else if (slotIndex >= numSlots - 9 * 4 && slotIndex < numSlots - 9) {
				if (!shiftItemStack(stackInSlot, numSlots - 9, numSlots)) {
					return null;
				}
			} else if (slotIndex >= numSlots - 9 && slotIndex < numSlots) {
				if (!shiftItemStack(stackInSlot, numSlots - 9 * 4, numSlots - 9)) {
					return null;
				}
			} else if (!shiftItemStack(stackInSlot, numSlots - 9 * 4, numSlots)) {
				return null;
			}
			slot.func_75220_a(stackInSlot, originalStack);
			if (stackInSlot.field_77994_a <= 0) {
				slot.func_75215_d(null);
			} else {
				slot.func_75218_e();
			}
			if (stackInSlot.field_77994_a == originalStack.field_77994_a) {
				return null;
			}
			slot.func_82870_a(player, stackInSlot);
		}
		return originalStack;
	}

	private boolean tryShiftItem(ItemStack stackToShift, int numSlots) {
		for (int machineIndex = 0; machineIndex < numSlots - 9 * 4; machineIndex++) {
			Slot slot = (Slot) field_75151_b.get(machineIndex);
			if (slot.func_75216_d()) {
				continue;
			}
			if(slot instanceof SlotLocked){
				continue;
			}
			if(slot instanceof SlotFluid){
				continue;
			}

			if (!slot.func_75214_a(stackToShift)) {
				continue;
			}
			if (shiftItemStack(stackToShift, machineIndex, machineIndex + 1)) {
				return true;
			}
		}
		return false;
	}

	protected boolean shiftItemStack(ItemStack stackToShift, int start, int end) {
		boolean changed = false;
		if (stackToShift.func_77985_e()) {
			for (int slotIndex = start; stackToShift.field_77994_a > 0 && slotIndex < end; slotIndex++) {
				Slot slot = (Slot) field_75151_b.get(slotIndex);
				ItemStack stackInSlot = slot.func_75211_c();
				if (stackInSlot != null && isIdenticalItem(stackInSlot, stackToShift)) {
					int resultingStackSize = stackInSlot.field_77994_a + stackToShift.field_77994_a;
					int max = Math.min(stackToShift.func_77976_d(), slot.func_75219_a());
					if (resultingStackSize <= max) {
						stackToShift.field_77994_a = 0;
						stackInSlot.field_77994_a = resultingStackSize;
						slot.func_75218_e();
						changed = true;
					} else if (stackInSlot.field_77994_a < max) {
						stackToShift.field_77994_a -= max - stackInSlot.field_77994_a;
						stackInSlot.field_77994_a = max;
						slot.func_75218_e();
						changed = true;
					}
				}
			}
		}
		if (stackToShift.field_77994_a > 0) {
			for (int slotIndex = start; stackToShift.field_77994_a > 0 && slotIndex < end; slotIndex++) {
				Slot slot = (Slot) field_75151_b.get(slotIndex);
				ItemStack stackInSlot = slot.func_75211_c();
				if (stackInSlot == null) {
					int max = Math.min(stackToShift.func_77976_d(), slot.func_75219_a());
					stackInSlot = stackToShift.func_77946_l();
					stackInSlot.field_77994_a = Math.min(stackToShift.field_77994_a, max);
					stackToShift.field_77994_a -= stackInSlot.field_77994_a;
					slot.func_75215_d(stackInSlot);
					slot.func_75218_e();
					changed = true;
				}
			}
		}
		return changed;
	}

	public static boolean isIdenticalItem(ItemStack lhs, ItemStack rhs) {
		if (lhs == null || rhs == null) {
			return false;
		}

		if (lhs.func_77973_b() != rhs.func_77973_b()) {
			return false;
		}

		if (lhs.func_77952_i() != OreDictionary.WILDCARD_VALUE) {
			if (lhs.func_77952_i() != rhs.func_77952_i()) {
				return false;
			}
		}

		return ItemStack.func_77970_a(lhs, rhs);
	}

}
