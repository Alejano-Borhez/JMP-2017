package com.epam.brest.jmp.extensions;

import com.epam.brest.jmp.interfaces.Extension;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by alexander_borohov on 24.2.17.
 */
public class Extension1 implements Extension {
    private final Logger logger = LogManager.getLogger(Extension.class);
    @Override
    public void extend(String arg) {
        logger.info("Hello from extension1: {}", arg);
    }
}
