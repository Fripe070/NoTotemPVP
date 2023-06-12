package io.github.fripe070.nototempvp.config;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.*;
import net.minecraft.registry.Registries;

import java.util.ArrayList;
import java.util.List;

import static io.github.fripe070.nototempvp.NoTotemPVP.MOD_ID;
import static net.minecraft.entity.EntityType.PLAYER;

@SuppressWarnings("unused")
@Modmenu(modId = MOD_ID)
@Config(name = MOD_ID + "-config", wrapperName = "NoTotemPVPConfig")
public class NoTotemPVPConfigModel {
    @SectionHeader("totemIgnore")
    @PredicateConstraint("containsNamespacedIds")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public List<String> totemIgnoredEntities = new ArrayList<>(List.of(Registries.ENTITY_TYPE.getId(PLAYER).toString()));

    @SectionHeader("elytraDisable")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public DamageSources disableElytraOn = DamageSources.PLAYER;

    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public int elytraCooldownTicks = 100;

    public static boolean containsNamespacedIds(List<String> list) {
        for (String string : list) {
            // Regex based on https://minecraft.fandom.com/wiki/Resource_location#Java_Edition
            if (!string.matches("[a-z0-9_.\\-]+:[a-z0-9_.\\-/]+|\\*")) {
                return false;
            }
        }
        return true;
    }

    public enum DamageSources {
        PLAYER, ENTITY, ANYTHING
    }
}
