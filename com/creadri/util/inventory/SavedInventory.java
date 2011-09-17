package com.creadri.util.inventory;

import java.io.Serializable;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author creadri
 */
public class SavedInventory implements Serializable {

    private int[] typeIds;
    private short[] damageIds;
    private int[] quantities;
    private int size;

    public SavedInventory(int size) {
        this.size = size;
        this.typeIds = new int[size];
        this.damageIds = new short[size];
        this.quantities = new int[size];
    }

    public void setItem(int index, ItemStack is) {
        if (is != null) {
            typeIds[index] = is.getTypeId();
            damageIds[index] = is.getDurability();
            quantities[index] = is.getAmount();
        } else {
            typeIds[index] = -1;
        }
    }

    public ItemStack getNewStackFrom(int index) {
        if (typeIds[index] == -1 || typeIds[index] == 0) {
            return null;
        } else {
            return new ItemStack(typeIds[index], quantities[index], damageIds[index]);
        }
    }

    public int getSize() {
        return size;
    }

    public short[] getDamageIds() {
        return damageIds;
    }

    public void setDamageIds(short[] damageIds) {
        this.damageIds = damageIds;
    }

    public int[] getQuantities() {
        return quantities;
    }

    public void setQuantities(int[] quantities) {
        this.quantities = quantities;
    }

    public int[] getTypeIds() {
        return typeIds;
    }

    public void setTypeIds(int[] typeIds) {
        this.typeIds = typeIds;
    }
}
