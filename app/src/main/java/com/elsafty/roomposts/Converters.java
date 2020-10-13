package com.elsafty.roomposts;

import com.google.gson.Gson;

import androidx.room.TypeConverter;

class Converters {

    @TypeConverter
    public String FromUserToString(User user) {
        return new Gson().toJson(user);
    }

    @TypeConverter
    public User FromStringToUser(String userString) {
        return new Gson().fromJson(userString, User.class);
    }
}
