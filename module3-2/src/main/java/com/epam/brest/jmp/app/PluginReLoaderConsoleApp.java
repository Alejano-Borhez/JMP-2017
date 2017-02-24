package com.epam.brest.jmp.app;

import com.epam.brest.jmp.classloader.CustomClassLoader;
import com.epam.brest.jmp.classloader.ReloadClassLoader;
import com.epam.brest.jmp.exception.ClassNotReloadedException;
import com.epam.brest.jmp.interfaces.Plugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Added ability to explicitly reload classes from different jar resources
 * Created by alexander_borohov on 24.2.17.
 */
public class PluginReLoaderConsoleApp {
    private final static Logger logger = LogManager.getLogger(PluginReLoaderConsoleApp.class);
    private final static Scanner scanner = new Scanner(System.in);
    Map<String, Plugin> pluginsMap;
    private final ClassLoader classLoader;

    private PluginReLoaderConsoleApp() {
        this.classLoader = new CustomClassLoader();
        this.pluginsMap = new HashMap<>();
    }

    public static void main(String[] args) {
        PluginReLoaderConsoleApp app = new PluginReLoaderConsoleApp();
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
        logger.info("2. Re-Load plugin");
        logger.info("Type 'exit' to quit");
    }

    private void operate(int choice) {
        switch (choice) {
            case 1:
                loadPlugin();
                break;
            case 2:
                reLoadPlugin();
                break;
            default:
                logger.info("Try another choice");
                break;
        }
    }

    private void reLoadPlugin() {
        if (pluginsMap.isEmpty()) {
            logger.info("Nothing to reload!");
        } else {
            logger.info("Specify plugin to reload:");
            int i = 0;
            for (Map.Entry<String, Plugin> plugin : pluginsMap.entrySet()) {
                logger.info("{}. {}", ++i, plugin.getKey());
            }
            String className = scanner.nextLine();
            Class<?> clazz = reloadPlugin(className);
            if (clazz != null) {
                logger.info("Reloaded plugin {}", className);
                for (Map.Entry<String, Plugin> entry: pluginsMap.entrySet()) {
                    Plugin plugin = entry.getValue();
                    if (plugin.getClass().getName().contains(className)) {
                        try {
                            plugin = (Plugin) clazz.newInstance();
                            pluginsMap.replace(entry.getKey(), plugin);
                        } catch (InstantiationException | IllegalAccessException e) {
                            logger.info("Class {} reloaded but plugin was not refreshed", className);
                            e.printStackTrace();
                        }
                    }
                }
                pluginsMap.get(className).sayHello();
            }
        }
    }

    private Class<?> reloadPlugin(String name) {
        Plugin plugin = pluginsMap.get(name);
        if (plugin == null) {
            logger.info("You specified wrong plugin name");
            return null;
        } else {
            logger.info("Reloading class for plugin {}", name);
            // Magic happens here
            try {
                ReloadClassLoader reloadClassLoader = new ReloadClassLoader();
                logger.info("Specify package to reload from:");
                String packageToReload = scanner.nextLine();
                reloadClassLoader.setPathToJars(packageToReload);
                return reloadClassLoader.reloadClass(name);
            } catch (ClassNotReloadedException e) {
                logger.info("Class was not reloaded!");
                return null;
            }
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

}
