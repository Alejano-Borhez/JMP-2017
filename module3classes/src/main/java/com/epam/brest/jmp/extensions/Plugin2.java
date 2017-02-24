package com.epam.brest.jmp.extensions;

import com.epam.brest.jmp.interfaces.Plugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by alexander_borohov on 24.2.17.
 */
public class Plugin2 implements Plugin {
    private String name = "Plugin2";
    private final Logger logger = LogManager.getLogger(Plugin2.class);

    @Override
    public void sayHello() {
        logger.info("Hello, I am {}", this.name);
    }

}
