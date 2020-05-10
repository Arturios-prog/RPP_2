package com.example.RPP_Lab_2_Fedodeev;

import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.laba_2_ver3.R;
import com.example.RPP_Lab_2_Fedodeev.Technologies.Technology;

import com.google.gson.Gson;

public class MyViewModel extends ViewModel {
    private MutableLiveData<Technologies> technologiesMLD;

    public LiveData<Technologies> getTechnologiesMLD() {
        Log.d("MyTag", "Я в ViewModel, вернул данные");
        return technologiesMLD;

    }

    public int getSizeTechnologies(){
        return technologiesMLD.getValue().getTechnologies().size();
    }

    public Technology getTechnologyIndex(int index){
        if ((index >= 0)&&(index < getSizeTechnologies())) {
            return technologiesMLD.getValue().getTechnologies().get(index);
        }
        else{
            return null;
        }
    }

    public void loadTechnologies(String jsonString, Resources res){
        if(technologiesMLD == null) {
            Log.d("MyTag", "Я в ViewModel, загружаю данные");
            technologiesMLD = new MutableLiveData<Technologies>();
            Gson g = new Gson();
            Technologies tempTechno = g.fromJson(jsonString.toString(), Technologies.class);
            technologiesMLD.setValue(tempTechno);

            // Загрузка картинок
            int sizeTecno = technologiesMLD.getValue().getTechnologies().size();
            for(int i=0; i<sizeTecno; i++){

                // Получаем название картинки
                String nameImage = technologiesMLD.getValue().getTechnologies().get(i).getGraphic();

                // Избавляемся от расширения
                int lengthName = nameImage.length();
                nameImage = nameImage.substring(0, lengthName-4);

                // Получаем код изображения
                int codeImage;
                try {
                    codeImage = R.drawable.class.getDeclaredField(nameImage).getInt(res);
                    Log.d("MyTag", "Картинка загружена из ресурсв:"+nameImage);
                }catch (Exception e){
                    codeImage = R.drawable.advanced_flight;
                    Log.d("MyTag", "Картинка не найдена:"+nameImage);
                }

                // Сохраняем изображение
                technologiesMLD.getValue().getTechnologies().get(i).setImage(
                            BitmapFactory.decodeResource(res, codeImage
                        )
                );
            }
            Log.d("MyTag", "Я в ViewModel, загрул какие-то данные:"+technologiesMLD.getValue().getTechnologies().get(0).getName());
        }
    }
}
