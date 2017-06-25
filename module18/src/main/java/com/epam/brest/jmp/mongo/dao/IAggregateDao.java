package com.epam.brest.jmp.mongo.dao;

/**
 * Additional methods for aggregations
 * Created by alexander_borohov on 25.6.17.
 */
public interface IAggregateDao {
    long countAllFiles();
    long countActiveUsers();
    long countDownloadableFiles();
    long countTotalDownloads();
}
