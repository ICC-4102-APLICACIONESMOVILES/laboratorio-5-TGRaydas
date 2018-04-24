package com.example.tgraydas.lab_6_pedro_grand;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Question {
    @NonNull
    @PrimaryKey
    public String Question;
    public Question (String Question) {
        this.Question = Question;
    }
}
