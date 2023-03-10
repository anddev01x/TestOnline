package com.techja.testonline.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Question {
    @SerializedName("Question")
    @Expose
    private String question;
    @SerializedName("AnswerA")
    @Expose
    private String answerA;
    @SerializedName("AnswerB")
    @Expose
    private String answerB;
    @SerializedName("AnswerC")
    @Expose
    private String answerC;
    @SerializedName("AnswerD")
    @Expose
    private String answerD;
    @SerializedName("Result")
    @Expose
    private String result;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
