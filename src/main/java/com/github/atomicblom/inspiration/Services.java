package com.github.atomicblom.inspiration;

import com.github.atomicblom.inspiration.model.action.Action;
import com.github.atomicblom.inspiration.network.handling.GatherTranslationsRequestHandler;
import com.github.atomicblom.inspiration.network.handling.GatherTranslationsResponseHandler;
import com.github.atomicblom.inspiration.network.message.GatherTranslationRequest;
import com.github.atomicblom.inspiration.network.message.GatherTranslationsResponse;
import com.github.atomicblom.inspiration.util.Reference;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.common.registry.IForgeRegistry;
import net.minecraftforge.fml.relauncher.Side;

@SuppressWarnings({"UtilityClass", "StaticNonFinalField", "PublicField", "AssignmentToNull"})
public final class Services
{
    public static final SimpleNetworkWrapper NETWORK = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

    public static IForgeRegistry<Action> Actions;

    static {
        Actions = null;
    }

    static void initializeNetworkService() {
        NETWORK.registerMessage(GatherTranslationsRequestHandler.class, GatherTranslationRequest.class, 0, Side.CLIENT);
        NETWORK.registerMessage(GatherTranslationsResponseHandler.class, GatherTranslationsResponse.class, 1, Side.SERVER);
    }

    private Services() {}
}
