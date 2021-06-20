package com.rsschool.quiz

interface OpenFragment {
    fun openFirstFragment()
    fun openSecondFragment(
        resultArray: ArrayList<String>,
        resultInt: Int,
        resultQuestion: ArrayList<String>
    )
}