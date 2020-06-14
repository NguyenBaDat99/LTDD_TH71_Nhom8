package com.example.dictionary.DictionaryRequest;

public class ListItem_CauHoi {

    String content;
    String question;
    String answer;

    public ListItem_CauHoi (String content, String question, String answer){
        this.content = content;
        this.question = question;
        this.answer = answer;
    }


    public void setContent(String content) {
        this.content = content;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getContent() {
        return content;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}
