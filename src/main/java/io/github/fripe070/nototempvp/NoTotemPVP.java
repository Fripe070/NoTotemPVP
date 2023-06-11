package io.github.fripe070.nototempvp;

import io.github.fripe070.nototempvp.config.NoTotemPVPConfig;
import net.fabricmc.api.ModInitializer;

import java.util.logging.Logger;

public class NoTotemPVP implements ModInitializer {
    public static final NoTotemPVPConfig CONFIG = NoTotemPVPConfig.createAndLoad();
    public static final Logger logger = Logger.getLogger("NoTotemPVP");

    @Override
    public void onInitialize() {
        logger.info("NoTotemPVP initialized");
    }
}
