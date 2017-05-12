package com.github.atomicblom.inspiration.capability;

import com.github.atomicblom.inspiration.model.AcquiredInspiration;
import com.github.atomicblom.inspiration.model.inspiration.EntityInspiration;
import com.github.atomicblom.inspiration.model.inspiration.Inspiration;
import com.github.atomicblom.inspiration.model.inspiration.ItemInspiration;
import com.github.atomicblom.inspiration.util.Logger;
import com.github.atomicblom.inspiration.util.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

import java.util.List;

public class InspirationCapabilityStorage implements IStorage<IInspirationCapability>
{
    public static final IStorage<IInspirationCapability> instance = new InspirationCapabilityStorage();

    @Override
    public NBTBase writeNBT(Capability<IInspirationCapability> capability, IInspirationCapability instance, EnumFacing side)
    {
        final NBTTagCompound compound = new NBTTagCompound();

        if (!(instance instanceof InspirationCapability)) {
            Logger.severe("Attempting to serialize an unknown implementation of IInspirationCapability");
            return compound;
        }

        try {

            InspirationCapability inspirationCapability = (InspirationCapability) instance;

            NBTTagCompound poemText = new NBTTagCompound();

            String[] poemLines = inspirationCapability.getPoemLines();
            poemText.setInteger(Reference.Capability.NBT.PoemLineCount, poemLines.length);
            for (int i = 0; i < poemLines.length; i++) {
                if (poemLines[i] != null) {
                    poemText.setString("" + (i + 1), poemLines[i]);
                }
            }
            compound.setTag(Reference.Capability.NBT.PoemText, poemText);

            List<AcquiredInspiration> acquiredInspirations = inspirationCapability.getAcquiredInspirationsList();
            NBTTagList serializedInspirations = new NBTTagList();
            for (AcquiredInspiration acquiredInspiration : acquiredInspirations) {
                NBTTagCompound serializedInspiration = new NBTTagCompound();
                acquiredInspiration.getInspiration().writeToNBT(serializedInspiration);
                serializedInspiration.setString(Reference.Capability.NBT.InspirationTranslation, acquiredInspiration.getTranslation());
                serializedInspiration.setDouble(Reference.Capability.NBT.InspirationAmount, acquiredInspiration.getAmount());
                serializedInspirations.appendTag(serializedInspiration);
            }
            compound.setTag(Reference.Capability.NBT.InspirationList, serializedInspirations);

            return compound;
        } catch (Exception e) {
            Logger.severe("Error saving inspiration capability: %s", e);
            throw e;
        }
    }

    @Override
    public void readNBT(Capability<IInspirationCapability> capability, IInspirationCapability instance, EnumFacing side, NBTBase nbt)
    {
        if (!(instance instanceof InspirationCapability)) {
            Logger.severe("Attempting to deserialize an unknown implementation of IInspirationCapability");
            return;
        }

        try {

            InspirationCapability inspirationCapability = (InspirationCapability) instance;

            final NBTTagCompound compound = (NBTTagCompound) nbt;
            NBTTagCompound poemText = compound.getCompoundTag(Reference.Capability.NBT.PoemText);
            int lineCount = poemText.getInteger(Reference.Capability.NBT.PoemLineCount);
            for (int i = 0; i < lineCount; i++) {
                if (poemText.hasKey("" + (i + 1))) {
                    String poemLine = poemText.getString("" + (i + 1));
                    inspirationCapability.addPoemLine(poemLine);
                }
            }

            NBTBase rawInspirationList = compound.getTag(Reference.Capability.NBT.InspirationList);
            List<AcquiredInspiration> acquiredInspirationsList = inspirationCapability.getAcquiredInspirationsList();
            if (rawInspirationList instanceof NBTTagList) {
                NBTTagList inspirationList = (NBTTagList) rawInspirationList;
                for (int i = 0; i < inspirationList.tagCount(); i++) {
                    NBTTagCompound serializedInspiration = inspirationList.getCompoundTagAt(i);

                    Inspiration constructedInspiration = createInspiration(serializedInspiration);
                    if (constructedInspiration == null) {
                        Logger.warning("Could not reconstruct inspiration from %s", serializedInspiration.toString());
                        continue;
                    }

                    AcquiredInspiration acquiredInspiration = new AcquiredInspiration(constructedInspiration);

                    acquiredInspiration.increment(serializedInspiration.getDouble(Reference.Capability.NBT.InspirationAmount));
                    acquiredInspiration.setTranslation(serializedInspiration.getString(Reference.Capability.NBT.InspirationTranslation));
                    acquiredInspirationsList.add(acquiredInspiration);
                }
            }
        } catch (Exception e) {
            Logger.severe("Error loading inspiration capability: %s", e);
            throw e;
        }

    }

    private Inspiration createInspiration(NBTTagCompound nbt) {
        String type = nbt.getString(Reference.Capability.NBT.InspirationType);
        Inspiration inspiration = null;
        switch (type) {
            case Reference.Capability.NBT.ItemInspirationType:
                inspiration = new ItemInspiration(new ItemStack(nbt));
                break;
            case Reference.Capability.NBT.EntityInspirationType:
                Entity entity = EntityList.createEntityFromNBT(nbt, null);
                inspiration = new EntityInspiration(entity);
        }
        return inspiration;
    }
}
