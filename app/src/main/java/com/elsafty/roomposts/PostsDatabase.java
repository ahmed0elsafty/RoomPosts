package com.elsafty.roomposts;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = Post.class, version = 1)
@TypeConverters(Converters.class)
abstract class PostsDatabase extends RoomDatabase {
    private static PostsDatabase instance;

    public static synchronized PostsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PostsDatabase.class, "posts_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract PostDao postDao();
}
