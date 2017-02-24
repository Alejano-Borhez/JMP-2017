package com.epam.brest.jmp.extensions;

import com.epam.brest.jmp.interfaces.Plugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * Created by alexander_borohov on 24.2.17.
 */
public class Plugin1 implements Plugin {
    private String name = "Reloaded Plugin100";
    private final Logger logger = LogManager.getLogger(Plugin1.class);

    @Override
    public void sayHello() {
        logger.info("Hello, I am {}", this.name);
    }

}
