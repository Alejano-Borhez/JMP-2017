package com.epam.brest.jmp.dao;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.TRUNCATE_EXISTING;
import static java.nio.file.StandardOpenOption.WRITE;

import com.epam.brest.jmp.model.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * Created by alexander_borohov on 9.2.17.
 */
public class TaskDaoCSVImpl implements TaskDao {
    private static final Logger LOGGER = LogManager.getLogger(TaskDaoCSVImpl.class);
    private static final String PROPS_NAME = "app";
    private final Path path;
    private Properties properties;

    public TaskDaoCSVImpl() {
        setProperties();
        this.path = getSavePath();
    }

    @Override
    public Task getTask(Integer id) {
        LOGGER.debug("Getting a task: {}", id);
        Task task = null;
        List<Task> tasks = loadFromFile();

        if (tasks != null && !tasks.isEmpty()) {
            for (Task listTask : tasks) {
                if (id.equals(listTask.getId())) task = listTask;
            }
        }
        return task;
    }

    @Override
    public List<Task> getAllTasks() {
        LOGGER.debug("Getting all tasks");
        return loadFromFile();
    }

    @Override
    public Integer addTask(Task task) {
        LOGGER.debug("Adding new task: {}", task.getDescription());
        List<Task> tasks = loadFromFile();
        Integer generatedId = null;
        if (tasks != null) {
            if (tasks.isEmpty()) {
                generatedId = 1;
            } else {
                generatedId = tasks.get(tasks.size() - 1).getId() + 1;
            }
            task.setId(generatedId);
            addToFile(task);
        }
        return generatedId;
    }

    @Override
    public Boolean removeTask(Integer id) {
        LOGGER.debug("Removing a task: {}", id);
        List<Task> tasks = loadFromFile();
        if (tasks != null && !tasks.isEmpty()) {
            for (Task task : tasks) {
                if (id.equals(task.getId())) {
                    tasks.remove(task);
                    saveToFile(tasks);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Boolean removeAllTasks() {
        LOGGER.debug("Removing all tasks");
        List<Task> tasks = Collections.emptyList();
        return saveToFile(tasks);
    }


    private List<Task> loadFromFile() {
        List<Task> tasks = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(path, Charset.forName("UTF-8"));
            for (String line : lines) {
                String csvColumns[] = line.split(",", 2);
                Integer id = Integer.parseInt(csvColumns[0]);
                String description = csvColumns[1];
                Task task = new Task(id, description);
                tasks.add(task);
            }
        } catch (IOException e) {
            LOGGER.info(e.getMessage());
            return null;
        }
        return tasks;
    }

    private Boolean saveToFile(List<Task> tasks) {
        try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"), WRITE, TRUNCATE_EXISTING)) {
            for (Task task : tasks) {
                writer.write(task.getId() + "," + task.getDescription());
                writer.newLine();
            }
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private Boolean addToFile(Task task) {
        try (BufferedWriter writer = Files.newBufferedWriter(path, Charset.forName("UTF-8"), WRITE, APPEND)) {
            writer.write(task.getId() + "," + task.getDescription());
            writer.newLine();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private Path getSavePath() {
        assert properties != null;
        String path = properties.getProperty("save.path");
        File file = new File(System.getProperty("user.home"), path);
        Path path1 = Paths.get(file.toURI());
        if (!file.exists()) {
            try {
                Files.createDirectories(path1.getParent());
                Files.createFile(path1);
            } catch (IOException e) {
                LOGGER.info(e.getMessage());
            }
        }
        return path1;
    }

    private void setProperties() {
        Properties properties = new Properties();
        Boolean isTestEnv = (System.getProperty("env") != null) && (System.getProperty("env").equals("test"));
        try {
            String propertiesFileName = (isTestEnv) ? "test" : PROPS_NAME;
            properties.load(this.getClass().getClassLoader().getResourceAsStream(propertiesFileName + ".properties"));
            LOGGER.info("Properties loaded from {}.properties.", PROPS_NAME);
        } catch (IOException e) {
            LOGGER.error("Could not load properties: {}.", e.getLocalizedMessage());
        }
        this.properties = properties;
    }

}
