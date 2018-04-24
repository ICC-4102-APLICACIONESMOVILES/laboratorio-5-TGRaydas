package com.example.tgraydas.lab_6_pedro_grand;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import static android.arch.persistence.room.ForeignKey.CASCADE;

@Entity(foreignKeys = @ForeignKey(entity = Question.class, parentColumns = "Question", childColumns = "questionId", onDelete = CASCADE))
public class Answer {
    @PrimaryKey
    public Integer id;
    public String questionId;
    public String Answer;
    public Answer(Integer id, String Answer, String questionId)
    {
        this.id = id;
        this.questionId = questionId;
        this.Answer = Answer;
    }
}
