package com.epam.brest.jmp.mongo.dao;

import com.epam.brest.jmp.mongo.model.File;

import java.util.List;
import java.util.Set;

/**
 * Dao interface
 * Created by alexander_borohov on 25.6.17.
 */
public interface IFilesDao {
    String createFile(File file);
    File downloadFile(String id);
    File readFileByName(String fileName);
    List<File> readAllFiles();
    File updateFile(File newFile);
    Boolean deleteFile(String id);
    List<File> readExpiredFiles();
    Set<File> readAllFilesOfAUser(String userId);

    List<File> readAllFilesByName(String fileName);
}
