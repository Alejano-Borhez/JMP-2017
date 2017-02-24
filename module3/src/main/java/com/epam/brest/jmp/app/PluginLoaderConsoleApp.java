package com.epam.brest.jmp.app;

import com.epam.brest.jmp.classloader.CustomClassLoader;
import com.epam.brest.jmp.interfaces.Extension;
import com.epam.brest.jmp.interfaces.Plugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by alexander_borohov on 24.2.17.
 */
public class PluginLoaderConsoleApp {
    private final static Logger logger = LogManager.getLogger(PluginLoaderConsoleApp.class);
    private final static Scanner scanner = new Scanner(System.in);
    Map<String, Plugin> pluginsMap;
    Map<String, Extension> extensionsMap;
    private final ClassLoader classLoader;

    private PluginLoaderConsoleApp() {
        this.classLoader = new CustomClassLoader();
        this.pluginsMap = new HashMap<>();
        this.extensionsMap = new HashMap<>();
    }

    public static void main(String[] args) {
        PluginLoaderConsoleApp app = new PluginLoaderConsoleApp();
        String input;
        // Loop to imitate console app work
        do {
            app.mainMenu();
            input = scanner.nextLine();
            try {
                int choice = Integer.parseInt(input);
//                Main logic performs here
                app.operate(choice);
            } catch (NumberFormatException e) {
                if (!"exit".equals(input)) {
                    logger.info("Incorrect input {}, try again", input);
                    app.mainMenu();
                }
            }
        } while (!"exit".equals(input));
        logger.info("Bye, Bye!");
    }

    private void mainMenu() {
        logger.info("Welcome to application!");
        logger.info("1. Load plugin");
        logger.info("2. Load extension");
        logger.info("Type 'exit' to quit");
    }

    private void operate(int choice) {
        switch (choice) {
            case 1:
                loadPlugin();
                break;
            case 2:
                loadExtension();
                break;
            default:
                logger.info("Try another choice");
                break;
        }
    }

    private void loadPlugin() {
        if (!pluginsMap.isEmpty()) {
            logger.info("Plugins already loaded:");
            int i = 0;
            for (Map.Entry<String, Plugin> plugin : pluginsMap.entrySet()) {
                logger.info("{}. {}", ++i, plugin.getKey());
            }
        }
        logger.info("Enter plugin name");
        String className = scanner.nextLine();
        try {
            Plugin plugin = loadPlugin(className);
            plugin.sayHello();
        } catch (ClassNotFoundException e) {
            logger.info("Plugin {} not found!", className);
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private Plugin loadPlugin(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Plugin plugin = pluginsMap.get(name);
        if (plugin == null) {
            // Magic happens here
            Class<Plugin> pluginClass = (Class<Plugin>) classLoader.loadClass(name);
            plugin = pluginClass.newInstance();
            pluginsMap.put(name, plugin);
            return plugin;
        } else {
            logger.info("Returning plugin from cache: {}", name);
            return pluginsMap.get(name);
        }
    }

    private void loadExtension() {
        if (!extensionsMap.isEmpty()) {
            logger.info("Extensions already loaded:");
            int i = 0;
            for (Map.Entry<String, Extension> extension : extensionsMap.entrySet()) {
                logger.info("{}. {}", ++i, extension.getKey());
            }
        }
        logger.info("Enter extension name");
        String className = scanner.nextLine();
        try {
            Extension loadExtension = loadExtension(className);
            logger.info("Type what to extend:");
            String extension = scanner.nextLine();
            loadExtension.extend(extension);
        } catch (ClassNotFoundException e) {
            logger.info("Extension {} not found!", className);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private Extension loadExtension(String name) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Extension extension = extensionsMap.get(name);
        if (extension == null) {
            // Magic happens here
            Class<Extension> extensionClass = (Class<Extension>) classLoader.loadClass(name);
            extension = extensionClass.newInstance();
            extensionsMap.put(name, extension);
            return extension;
        } else {
            logger.info("Returning plugin from cache: {}", name);
            return extensionsMap.get(name);
        }
    }
}
