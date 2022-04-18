package com.example.recipefoodapp.Listeners;

import android.os.Message;

import com.example.recipefoodapp.Models.InstructionsResponse;

import java.util.List;

public interface InstructionsListener {
    void didFetch(List<InstructionsResponse> response, String message);
    void didError(String message);
}
