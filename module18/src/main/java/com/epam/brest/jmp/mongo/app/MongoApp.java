package com.epam.brest.jmp.mongo.app;

import com.epam.brest.jmp.mongo.dao.FileShareMongoDAO;
import com.epam.brest.jmp.mongo.model.File;
import com.epam.brest.jmp.mongo.model.User;
import com.mongodb.MongoClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Scanner;

/**
 * Simple CLI for demonstration of CRUD and Aggregations with MongoDB
 * Created by alexander_borohov on 25.6.17.
 */
public class MongoApp {
    private static final Logger logger = LogManager.getLogger(MongoApp.class);
    private static final User ADMIN = new User("ADMIN");
    private static String input;
    private static User currentUser;
    private static final Scanner SCANNER = new Scanner(System.in);
    private final FileShareMongoDAO dao;

    public MongoApp(FileShareMongoDAO dao) {
        this.dao = dao;
    }

    public static void main(String[] args) {
        MongoApp app = new MongoApp(new FileShareMongoDAO(new MongoClient()));
        app.initAdmin();
        do {
            app.mainMenu();
            input = SCANNER.nextLine();
            try {
                int choice = Integer.parseInt(input);
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

    private void initAdmin() {
        User admin = dao.readUserByName(ADMIN.getName());
        if (admin == null) {
            String id = dao.createUser(ADMIN);
            ADMIN.setId(id);
        }
        currentUser = admin;
    }

    private void mainMenu() {
        logger.info("Welcome to application!");
        logger.info("1. Create user");
        logger.info("2. Switch to user");
        logger.info("3. File operations");
        logger.info("4. Statistics operations");
        logger.info("Type 'exit' to quit");
    }


    private void operate(int choice) {
        switch (choice) {
            case 1:
                createUser();
                break;
            case 2:
                switchToUser();
                break;
            case 3:
                fileOperationsSubMenu();
                break;
            case 4:
                statisticsSubMenu();
                break;
        }
    }

    private void statisticsSubMenu() {
        logger.info("Choose statistics to list:");
        logger.info("1. Total files count");
        logger.info("2. Total users count");
        logger.info("3. Total downloads");
        logger.info("4. Total files being downloaded");
        logger.info("5. Back to Main Menu");

        input = SCANNER.nextLine();

        try {
            Integer choice = Integer.parseInt(input);
            statOps(choice);
        } catch (NumberFormatException e) {
            if (!"exit".equals(input)) {
                logger.info("Incorrect input {}, try again", input);
            }
        }


    }

    private void statOps(Integer choice) {
        switch (choice) {
            case 1:
                totalFilesCount();
                break;
            case 2:
                totalUsersCount();
                break;
            case 3:
                totalDownloads();
                break;
            case 4:
                totalDownloadedFiles();
                break;
            default:
                break;

        }
    }

    private void totalFilesCount() {
        logger.info("Total files count in FileShare: {}", dao.countAllFiles());
    }

    private void totalUsersCount() {
        logger.info("Total active users of fileShare: {}", dao.countActiveUsers());
    }

    private void totalDownloads() {
        logger.info("Total downloads from FileShare: {}", dao.countTotalDownloads());
    }

    private void totalDownloadedFiles() {
        logger.info("Total downloadable files from FileShare: {}", dao.countDownloadableFiles());
    }

    private void createUser() {
        logger.info("Existing users: {}", dao.readAllUsers());
        logger.info("Enter username:");
        String userName = SCANNER.nextLine();
        if (dao.readUserByName(userName) == null) {
            User newUser = new User(userName);
            String id = dao.createUser(newUser);
            if (id != null) {
                newUser.setId(id);
                currentUser = newUser;
                logger.info("User {} successfully created!", userName);
            } else {
                logger.info("Error occurred. Could not create a user.");
            }
        } else {
            logger.info("User {} already exists, try again");
        }
    }

    private void switchToUser() {
        logger.info("Existing users: {}", dao.readAllUsers());
        logger.info("Choose one of the users");

        String userName = SCANNER.nextLine();

        User swithedUser = dao.readUserByName(userName);

        if (swithedUser != null) {
            logger.info("Successfully switched to {}", userName);
            currentUser = swithedUser;
        } else {
            logger.info("Wrong input: {}", userName);
        }
    }


    private void fileOperationsSubMenu() {
        logger.info("Available file operations for user {}", currentUser.getName());
        logger.info("1. Upload file");
        logger.info("2. List all files owned by you");
        logger.info("3. List all files ");
        logger.info("4. List all expired files ");
        logger.info("5. List all files ordered by downloaded times desc");
        logger.info("6. Download file");
        logger.info("7. Go back");
        input = SCANNER.nextLine();
        try {
            int choice = Integer.parseInt(input);
            fileOps(choice);
        } catch (NumberFormatException e) {
            if (!"exit".equals(input)) {
                logger.info("Incorrect input {}, try again", input);
                fileOperationsSubMenu();
            }
        }
    }

    private void fileOps(int choice) {
        switch (choice) {
            case 1:
                uploadFile();
                break;
            case 2:
                listAllFilesOwned();
                break;
            case 3:
                listAllFiles();
                break;
            case 4:
                listAllExpiredFiles();
                break;
            case 5:
                listOrderedByDownloadFiles();
                break;
            case 6:
                downloadFile();
                break;
            default:
                break;
        }

    }

    private void listAllFilesOwned() {
        logger.info("List of files of a user {}:", currentUser.getName());
        dao.readAllFilesOfAUser(currentUser.getId()).forEach(file -> logger.info("{}", file));
    }
    private void listAllFiles() {
        logger.info("List of all available files: ");
        dao.readAllFiles().forEach(file -> {
            User owner = dao.getFileOwner(file.getId());
            logger.info("File: {}, Owner: {}", file, owner.getName());
        });
    }

    private void listAllExpiredFiles() {
        logger.info("List of expired files only: ");
        dao.readExpiredFiles().forEach(file -> {
            User owner = dao.getFileOwner(file.getId());
            logger.info("File: {}, Owner: {}", file, owner.getName());
        });
    }

    private void listOrderedByDownloadFiles() {
        logger.info("List of files ordered by downloaded times desc:");
        dao.listFilesDownloadTimesDescending().forEach(file -> {
            User owner = dao.getFileOwner(file.getId());
            logger.info("File: {}, Owner: {}", file, owner.getName());
        });
    }

    private void downloadFile() {
        logger.info("Enter a fileName to download:");
        String fileName = SCANNER.nextLine();
        File downloaded = dao.readFileByName(fileName);
        if (downloaded == null) {
            logger.info("Sorry, incorrect input");
        } else {
            downloaded = dao.downloadFile(downloaded.getId());
            logger.info("Successfully downloaded {}. This is {} download of this file"
                    , downloaded.getFileName()
                    , downloaded.getTimes()
            );
        }
        fileOperationsSubMenu();
    }


    private void uploadFile() {
        logger.info("Specify filename:");
        String fileName = SCANNER.nextLine();

        List<File> existingFiles = dao.readAllFilesByName(fileName);
        if (existingFiles != null) {
            final boolean[] foundOwner = new boolean[1];
            existingFiles.forEach(file -> {
                User owner = dao.getFileOwner(file.getId());
                if (currentUser.equals(owner)) {
                    logger.info("Sorry, this file already exists in your collection!");
                    foundOwner[0] = true;
                }
            });
             if (!foundOwner[0]) {
                newFile(fileName);
            }

        } else {
            newFile(fileName);
        }

        fileOperationsSubMenu();
    }

    private void newFile(String fileName) {
        File newFile = new File(fileName);
        currentUser.addFiles(newFile);

        dao.createFile(newFile);
        dao.updateUser(currentUser);

        logger.info("Successfully uploaded a file");
    }

}
