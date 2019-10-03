package xiaokun.pack.client.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class ContainerDemo extends Container {
    private ItemStackHandler items = new ItemStackHandler(4);

    protected Slot goldSlot;
    protected Slot diamondSlot;
    protected Slot emeraldSlot;
    protected Slot ironSlot;

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    public ContainerDemo(EntityPlayer player) {
        super();
        this.addSlotToContainer(this.goldSlot = new SlotItemHandler(items, 0, 38 + 0 * 32, 20) {
            // TODO
        });

        this.addSlotToContainer(this.diamondSlot = new SlotItemHandler(items, 1, 38 + 1 * 32, 20) {
            // TODO
        });

        this.addSlotToContainer(this.emeraldSlot = new SlotItemHandler(items, 2, 38 + 2 * 32, 20) {
            // TODO
        });

        this.addSlotToContainer(this.ironSlot = new SlotItemHandler(items, 3, 38 + 3 * 32, 20) {
            // TODO
        });

    }

    public Slot getIronSlot()
    {
        return this.ironSlot;
    }
}
