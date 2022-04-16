package com.example.recipefoodapp.Listeners;

import android.os.Message;

import com.example.recipefoodapp.Models.InstructionsResponse;

public interface InstructionsListener {
    void didFetch(InstructionsResponse response, String message);
    void didError(String message);
}
