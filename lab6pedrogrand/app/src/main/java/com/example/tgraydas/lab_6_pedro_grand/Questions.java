package com.example.tgraydas.lab_6_pedro_grand;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Questions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String DATABASE_NAME = "movies_db";
        final Database_PJ the_DB;
        the_DB = Room.databaseBuilder(getApplicationContext(),
                Database_PJ.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
        final ArrayList<String> all_questions = new ArrayList<String>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Question> questions = the_DB.daoAccess().fetchAllQuestions();
                for (int i = 0; i < questions.size(); i++)
                {
                    all_questions.add(questions.get(i).Question);
                }
            }

        }).start();
        if (all_questions.size() == 0)
        {
            CreateQuestions();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<Question> questions = the_DB.daoAccess().fetchAllQuestions();
                    for (int i = 0; i < questions.size(); i++)
                    {
                        all_questions.add(questions.get(i).Question);
                    }
                }

            }).start();
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, all_questions);
            ListView listView = findViewById(R.id.list_view_questions);
            listView.setAdapter(arrayAdapter);
        }
        else
        {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, all_questions);
            ListView listView = findViewById(R.id.list_view_questions);
            listView.setAdapter(arrayAdapter);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }



    public void CreateQuestions()
    {
        String DATABASE_NAME = "movies_db";
        final Database_PJ the_DB;
        the_DB = Room.databaseBuilder(getApplicationContext(),
                Database_PJ.class, DATABASE_NAME).fallbackToDestructiveMigration().build();
        final ArrayList<String> questions = new ArrayList<String>();
        questions.add("¿Quien gano el mundial del 2004?");
        questions.add("¿Nombre del ultimo Lider de la URRS?");
        questions.add("¿Quien fue el primer presidende de Chile?");
        questions.add("¿Quien fue el primer papa?");
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < questions.size(); i++)
                {
                    Question question = new Question(questions.get(i));
                    the_DB.daoAccess().newQuestion(question);
                }

            }

        }).start();

    }

}
