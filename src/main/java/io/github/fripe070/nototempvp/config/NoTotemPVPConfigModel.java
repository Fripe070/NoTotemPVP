package io.github.fripe070.nototempvp.config;

import io.wispforest.owo.config.Option;
import io.wispforest.owo.config.annotation.*;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

import static net.minecraft.entity.EntityType.PLAYER;

@SuppressWarnings("unused")
@Modmenu(modId = "nototempvp")
@Config(name = "no-totem-pvp-config", wrapperName = "NoTotemPVPConfig")
public class NoTotemPVPConfigModel {

    // Regex based on https://minecraft.fandom.com/wiki/Resource_location#Java_Edition
    @PredicateConstraint("containsNamespacedIds")
    @Sync(Option.SyncMode.OVERRIDE_CLIENT)
    public List<String> totemIgnoredEntities = new ArrayList<>(List.of(Registry.ENTITY_TYPE.getId(PLAYER).toString()));

    public static boolean containsNamespacedIds(List<String> list) {
        for (String string : list) {
            if (!string.matches("[a-z0-9_.\\-]+:[a-z0-9_.\\-/]+")) {
                return false;
            }
        }
        return true;
    }
}
