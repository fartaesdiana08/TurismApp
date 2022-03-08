package com.example.turismapp.room;

public interface Callback<R> {
    void runResultOnUiThread(R result);
}
