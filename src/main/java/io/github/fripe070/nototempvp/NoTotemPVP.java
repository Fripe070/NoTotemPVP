package io.github.fripe070.nototempvp;

import io.github.fripe070.nototempvp.config.NoTotemPVPConfig;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoTotemPVP implements ModInitializer {
    public static final String MOD_ID = "nototempvp";
    public static final NoTotemPVPConfig CONFIG = NoTotemPVPConfig.createAndLoad();
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("NoTotemPVP initialized");
    }
}
