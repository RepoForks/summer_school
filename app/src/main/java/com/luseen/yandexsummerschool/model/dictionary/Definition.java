package com.luseen.yandexsummerschool.model.dictionary;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Chatikyan on 25.03.2017.
 */

public class Definition extends RealmObject {

    @PrimaryKey
    private int id;

    @SerializedName("text")
    private String word;

    @SerializedName("pos")
    private String partOfSpeech;

    @SerializedName("ts")
    private String transcription;

    @SerializedName("tr")
    private RealmList<DictionaryTranslation> translations;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getPartOfSpeech() {
        return partOfSpeech;
    }

    public void setPartOfSpeech(String partOfSpeech) {
        this.partOfSpeech = partOfSpeech;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public RealmList<DictionaryTranslation> getTranslations() {
        return translations;
    }

    public void setTranslations(RealmList<DictionaryTranslation> translations) {
        this.translations = translations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}