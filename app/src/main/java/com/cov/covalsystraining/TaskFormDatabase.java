package com.cov.covalsystraining;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {TaskForm.class},version = 1)
public abstract class TaskFormDatabase extends RoomDatabase {
    public abstract TaskFormDAO getTaskFormDAO();
}
