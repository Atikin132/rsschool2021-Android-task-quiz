package com.rsschool.quiz

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentResultBinding

class ResultFragment : Fragment(), View.OnClickListener {

    private var binding: FragmentResultBinding? = null
    private var resultArray: ArrayList<String> = ArrayList()
    private var resultInt: Int = 0
    private var resultQuestion: ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentResultBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.shareButton?.setOnClickListener(this)
        binding?.backButton?.setOnClickListener(this)
        binding?.exitButton?.setOnClickListener(this)

        resultArray = arguments?.getStringArrayList(RESULT_ARRAY)!!
        resultInt = arguments?.getInt(RESULT_INT) ?: 0
        resultQuestion = arguments?.getStringArrayList(RESULT_QUESTION)!!
        activity?.window?.statusBarColor = resources.getColor(R.color.black)

        binding?.resultText?.text = "Ваш результат: ${(resultInt * 100 / 5)} %"
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding?.shareButton?.id -> {
                val email = Intent(Intent.ACTION_SEND)
                email.type = "message/rfc822"
                email.putExtra(Intent.EXTRA_SUBJECT, "Quiz results")
                email.putExtra(
                    Intent.EXTRA_TEXT,
                    "Ваш результат: ${(resultInt * 100 / 5)} %\n \n 1) ${resultQuestion[0]} \n" +
                            "Ваш ответ: ${(resultArray[0])} \n \n 2) ${resultQuestion[1]} \n" +
                            "Ваш ответ: ${(resultArray[1])} \n \n 3) ${resultQuestion[2]} \n" +
                            "Ваш ответ: ${(resultArray[2])} \n \n 4) ${resultQuestion[3]} \n" +
                            "Ваш ответ: ${(resultArray[3])} \n \n 5) ${resultQuestion[4]} \n" +
                            "Ваш ответ: ${(resultArray[4])}"
                )
                startActivity(email)
            }
            binding?.backButton?.id -> {
                (activity as OpenFragment).openFirstFragment()
            }
            binding?.exitButton?.id -> {
                activity?.finish()
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(
            resultArray: ArrayList<String>,
            resultInt: Int,
            resultQuestion: ArrayList<String>
        ): ResultFragment {
            val fragment = ResultFragment()
            val args = Bundle()
            args.putStringArrayList(RESULT_ARRAY, resultArray)
            args.putInt(RESULT_INT, resultInt)
            args.putStringArrayList(RESULT_QUESTION, resultQuestion)
            fragment.arguments = args
            return fragment
        }

        private const val RESULT_ARRAY = "RESULT_ARRAY"
        private const val RESULT_INT = "RESULT_INT"
        private const val RESULT_QUESTION = "RESULT_QUESTION"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}