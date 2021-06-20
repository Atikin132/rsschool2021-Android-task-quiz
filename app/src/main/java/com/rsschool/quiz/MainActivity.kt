package com.rsschool.quiz


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.rsschool.quiz.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), OpenFragment {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openFirstFragment()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun openFirstFragment() {
        val questionsFragment: Fragment = QuestionsFragment.newInstance()
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, questionsFragment).commit()
    }

    override fun openSecondFragment(
        resultArray: ArrayList<String>,
        resultInt: Int,
        resultQuestion: ArrayList<String>
    ) {
        val resultFragment: Fragment =
            ResultFragment.newInstance(resultArray, resultInt, resultQuestion)
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, resultFragment).commit()
    }


}