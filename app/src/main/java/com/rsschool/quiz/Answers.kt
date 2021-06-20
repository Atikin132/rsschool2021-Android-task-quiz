package com.rsschool.quiz

class Answers(private var answer: String?, private var isTrue: Boolean?) {

    fun Answers() {}

    fun Answers(answer: String?, isTrue: Boolean?) {
        this.answer = answer
        this.isTrue = isTrue
    }

    fun getAnswer(): String? {
        return answer
    }

    fun setAnswer(answer: String?) {
        this.answer = answer
    }

    fun isTrue(): Boolean? {
        return isTrue
    }

    fun setTrue(isTrue: Boolean?) {
        this.isTrue = isTrue
    }


}