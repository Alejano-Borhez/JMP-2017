package com.epam.brest.jmp.model;

/**
 * Simple interface to interact with generic entities
 * Created by alexander_borohov on 17.3.17.
 */
public interface Entity<I> {
    I getId();
    void setId(I id);
}
