package com.rsschool.quiz

class Question(
    private var text_Question: String?,
    private var answers: ArrayList<Answers>,
    private var colorGeneral: Int,
    private var colorDLC: Int
) {

    fun Question() {}

    fun Question(
        text_Question: String?,
        answers: ArrayList<Answers>,
        colorGeneral: Int,
        colorDLC: Int
    ) {
        this.text_Question = text_Question
        this.answers = answers
        this.colorGeneral = colorGeneral
        this.colorDLC = colorDLC
    }

    fun getQuestion(): String? {
        return text_Question
    }

    fun setQuestion(text_Question: String?) {
        this.text_Question = text_Question
    }

    fun getAnswers(): ArrayList<Answers> {
        return answers
    }

    fun setAnswers(answers: ArrayList<Answers>) {
        this.answers = answers
    }

    fun getGeneralColor(): Int {
        return colorGeneral
    }

    fun setGeneralColor(colorGeneral: Int) {
        this.colorGeneral = colorGeneral
    }

    fun getDLCColor(): Int {
        return colorDLC
    }

    fun setDLCColor(colorDLC: Int) {
        this.colorDLC = colorDLC
    }
}