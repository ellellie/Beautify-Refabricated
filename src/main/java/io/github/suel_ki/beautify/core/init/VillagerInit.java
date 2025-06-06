package io.github.suel_ki.beautify.core.init;

import io.github.suel_ki.beautify.Beautify;
import com.google.common.collect.ImmutableSet;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PointOfInterestHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.state.BlockState;


public class VillagerInit
{

	public static final PoiType BOTANIST_WORKBENCH_POI = registerPoiType("botanist_workbench_poi",
					ImmutableSet.copyOf(BlockInit.BOTANIST_WORKBENCH.getStateDefinition().getPossibleStates()));

    public static final ResourceKey<VillagerProfession> BOTANIST = ResourceKey.create(Registries.VILLAGER_PROFESSION, Beautify.id("botanist"));

    public static void registerProfessions() {
        registerVillagerProfession(BOTANIST, BOTANIST_WORKBENCH_POI, SoundEvents.CAVE_VINES_PLACE);
    }

	private static PoiType registerPoiType(String name, Iterable<BlockState> blocks) {
		return PointOfInterestHelper.register(Beautify.id(name), 1, 1, blocks);
		//return Registry.register(Registry.POINT_OF_INTEREST_TYPE, Beautify.id(name), poiType);
	}

	private static VillagerProfession registerVillagerProfession(ResourceKey<VillagerProfession> resourceKey, PoiType poi, SoundEvent soundEvent) {
		return Registry.register(
            BuiltInRegistries.VILLAGER_PROFESSION,
            resourceKey,
            new VillagerProfession(
                Component.translatable("entity.minecraft.villager." + resourceKey.location().getPath()),
                x -> x.value() == poi,
                x -> x.value() == poi, ImmutableSet.of(), ImmutableSet.of(),
                soundEvent
            )
        );
	}
}
