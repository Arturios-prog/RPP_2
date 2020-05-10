package com.example.RPP_Lab_2_Fedodeev;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;

import com.example.laba_2_ver3.R;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onWindowFocusChanged(boolean hasFocus){
        super.onWindowFocusChanged(hasFocus);
        if(hasFocus){
            String jsonString = ""; //строка в которой будет храниться весь json файл

            // Создаем связь со вторым активити
            Intent intent = new Intent(this, Activity_2.class);

            // Считывание всего файла
            try {
                jsonString = readUsingScanner();

                // Передаем строку второму активити
                intent.putExtra("jsonString", jsonString);

            } catch (IOException e) {
                e.printStackTrace();
                // Передаем ошибку во второе активити
                intent.putExtra("jsonString", e.toString());
            }

            //установка флага
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            // Переход в новое активити
            startActivity(intent);
        }
    }


    // Функция для считывания содержимого из Json файла (Перевод в String)
    private String readUsingScanner()throws ClassCastException, IOException {
        // Создаем читатель файла
        Resources res = this.getResources();
        AssetManager.AssetInputStream buffer = (AssetManager.AssetInputStream) res.openRawResource(R.raw.myjsonfile);

        // Записываем содержимое файла в буфер
        int c;
        StringBuilder jsonString = new StringBuilder("");
        while((c=buffer.read())!=-1){
            jsonString.append((char)c);
        }

        // Возвращаем содержимое
        return jsonString.toString();
    }
}
