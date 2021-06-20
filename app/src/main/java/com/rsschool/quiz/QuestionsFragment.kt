package com.rsschool.quiz

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rsschool.quiz.databinding.FragmentQuizBinding

class QuestionsFragment : Fragment(), View.OnClickListener {

    private var binding: FragmentQuizBinding? = null
    private var questions: ArrayList<Question> = ArrayList()
    private var selectAnswers: ArrayList<String> = ArrayList()
    private var i: Int = 0
    private var removeAnswer: Int? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        setQuestions()
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        questions.shuffle()
        for (i in 0 until questions.size) {
            questions[i].getAnswers().shuffle()
        }

        binding?.previousButton?.setOnClickListener(this)
        binding?.nextButton?.setOnClickListener(this)
        binding?.optionOne?.setOnClickListener(this)
        binding?.optionTwo?.setOnClickListener(this)
        binding?.optionThree?.setOnClickListener(this)
        binding?.optionFour?.setOnClickListener(this)
        binding?.optionFive?.setOnClickListener(this)

        binding?.toolbar?.setNavigationOnClickListener {
            if (i > 0) {
                i -= 1
            }
            showQuestions(i)
            setSelectAnswer(selectAnswers)
        }

        notActiveNextButton()
        showQuestions(i)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            binding?.nextButton?.id -> {
                if (binding?.optionOne?.isChecked == false &&
                    binding?.optionTwo?.isChecked == false &&
                    binding?.optionThree?.isChecked == false &&
                    binding?.optionFour?.isChecked == false &&
                    binding?.optionFive?.isChecked == false
                ) {
                    notActiveNextButton()
                    binding?.radioGroup?.clearCheck()
                }
                if (i < 4) {
                    i += 1
                }
                if (binding?.nextButton?.text == "Submit") {
                    var result = 0
                    val questionsInResult: ArrayList<String> = ArrayList()
                    for (i in 0 until questions.size) {
                        questionsInResult.add(questions[i].getQuestion().toString())
                    }
                    for (i in 0 until selectAnswers.size) {
                        for (j in 0 until 5) {
                            if (selectAnswers[i] == questions[i].getAnswers()[j].getAnswer()) {
                                if (questions[i].getAnswers()[j].isTrue() == true) {
                                    result += 1
                                }
                            }
                        }
                    }
                    (activity as OpenFragment).openSecondFragment(
                        selectAnswers,
                        result,
                        questionsInResult
                    )
                }
                showQuestions(i)
                setSelectAnswer(selectAnswers)
            }

            binding?.previousButton?.id -> {

                if (i > 0) {
                    i -= 1
                }
                showQuestions(i)
                setSelectAnswer(selectAnswers)
            }

            binding?.optionOne?.id -> {
                activeNextButton()
                removeSelectAnswerIfUserRemind(binding?.optionOne?.text.toString())
            }
            binding?.optionTwo?.id -> {
                activeNextButton()
                removeSelectAnswerIfUserRemind(binding?.optionTwo?.text.toString())
            }
            binding?.optionThree?.id -> {
                activeNextButton()
                removeSelectAnswerIfUserRemind(binding?.optionThree?.text.toString())
            }
            binding?.optionFour?.id -> {
                activeNextButton()
                removeSelectAnswerIfUserRemind(binding?.optionFour?.text.toString())
            }
            binding?.optionFive?.id -> {
                activeNextButton()
                removeSelectAnswerIfUserRemind(binding?.optionFive?.text.toString())
            }

        }

    }

    private fun removeSelectAnswerIfUserRemind(string: String) {
        if (selectAnswers.contains(binding?.optionOne?.text) ||
            selectAnswers.contains(binding?.optionTwo?.text) ||
            selectAnswers.contains(binding?.optionThree?.text) ||
            selectAnswers.contains(binding?.optionFour?.text) ||
            selectAnswers.contains(binding?.optionFive?.text)
        ) {
            when {
                selectAnswers.contains(binding?.optionOne?.text) -> {
                    removeAnswer = selectAnswers.indexOf(binding?.optionOne?.text)
                    selectAnswers.remove(binding?.optionOne?.text)
                }
                selectAnswers.contains(binding?.optionTwo?.text) -> {
                    removeAnswer = selectAnswers.indexOf(binding?.optionTwo?.text)
                    selectAnswers.remove(binding?.optionTwo?.text)
                }
                selectAnswers.contains(binding?.optionThree?.text) -> {
                    removeAnswer = selectAnswers.indexOf(binding?.optionThree?.text)
                    selectAnswers.remove(binding?.optionThree?.text)
                }
                selectAnswers.contains(binding?.optionFour?.text) -> {
                    removeAnswer = selectAnswers.indexOf(binding?.optionFour?.text)
                    selectAnswers.remove(binding?.optionFour?.text)
                }
                selectAnswers.contains(binding?.optionFive?.text) -> {
                    removeAnswer = selectAnswers.indexOf(binding?.optionFive?.text)
                    selectAnswers.remove(binding?.optionFive?.text)
                }
            }
            selectAnswers.add(removeAnswer!!, string)
        } else {
            selectAnswers.add(string)
        }

    }

    private fun setSelectAnswer(selectAnswers: ArrayList<String>) {
        for (i in 0 until selectAnswers.size) {
            when {
                selectAnswers[i] == binding?.optionOne?.text.toString() -> {
                    binding?.optionOne?.isChecked = true
                    activeNextButton()
                }
                selectAnswers[i] == binding?.optionTwo?.text.toString() -> {
                    binding?.optionTwo?.isChecked = true
                    activeNextButton()
                }
                selectAnswers[i] == binding?.optionThree?.text.toString() -> {
                    binding?.optionThree?.isChecked = true
                    activeNextButton()
                }
                selectAnswers[i] == binding?.optionFour?.text.toString() -> {
                    binding?.optionFour?.isChecked = true
                    activeNextButton()
                }
                selectAnswers[i] == binding?.optionFive?.text.toString() -> {
                    binding?.optionFive?.isChecked = true
                    activeNextButton()
                }
            }
        }
    }

    private fun notActiveNextButton() {
        binding?.nextButton?.setBackgroundColor(resources.getColor(R.color.gray))
        binding?.nextButton?.setTextColor(resources.getColor(R.color.white))
        binding?.nextButton?.isClickable = false
    }

    private fun activeNextButton() {
        binding?.nextButton?.setBackgroundColor(resources.getColor(questions[i].getGeneralColor()))
        binding?.nextButton?.setTextColor(resources.getColor(R.color.black))
        binding?.nextButton?.isClickable = true
    }

    private fun setNextOrPreviousQuestion() {
        setQuestionsToRadioButton(i)
        binding?.toolbar?.title = "Question ${i + 1}"

        binding?.toolbar?.setBackgroundColor(resources.getColor(questions[i].getGeneralColor()))
        binding?.previousButton?.setBackgroundColor(resources.getColor(questions[i].getGeneralColor()))
        activity?.window?.statusBarColor = resources.getColor(questions[i].getDLCColor())

        val colorStateList = ColorStateList(
            arrayOf(
                intArrayOf(-android.R.attr.state_checked),
                intArrayOf(android.R.attr.state_checked)
            ), intArrayOf(
                resources.getColor(R.color.black),
                resources.getColor(questions[i].getGeneralColor())
            )
        )
        binding?.optionOne?.buttonTintList = colorStateList
        binding?.optionTwo?.buttonTintList = colorStateList
        binding?.optionThree?.buttonTintList = colorStateList
        binding?.optionFour?.buttonTintList = colorStateList
        binding?.optionFive?.buttonTintList = colorStateList
    }

    private fun showQuestions(i: Int) {
        when (i) {
            0 -> {
                binding?.toolbar?.navigationIcon = null
                binding?.previousButton?.visibility = View.INVISIBLE
                setNextOrPreviousQuestion()
            }
            1 -> {
                binding?.toolbar?.setNavigationIcon(R.drawable.ic_baseline_chevron_left_24)
                binding?.previousButton?.visibility = View.VISIBLE
                setNextOrPreviousQuestion()
                notActiveNextButton()
                binding?.radioGroup?.clearCheck()
            }
            2 -> {
                setNextOrPreviousQuestion()
                notActiveNextButton()
                binding?.radioGroup?.clearCheck()
            }
            3 -> {
                setNextOrPreviousQuestion()
                binding?.nextButton?.text = "Next"
                notActiveNextButton()
                binding?.radioGroup?.clearCheck()
            }
            4 -> {
                setNextOrPreviousQuestion()
                binding?.nextButton?.text = "Submit"
                notActiveNextButton()
                binding?.radioGroup?.clearCheck()
            }
        }
    }

    private fun setQuestionsToRadioButton(i: Int) {
        binding?.question?.text = questions[i].getQuestion()
        binding?.optionOne?.text = questions[i].getAnswers()[0].getAnswer()
        binding?.optionTwo?.text = questions[i].getAnswers()[1].getAnswer()
        binding?.optionThree?.text = questions[i].getAnswers()[2].getAnswer()
        binding?.optionFour?.text = questions[i].getAnswers()[3].getAnswer()
        binding?.optionFive?.text = questions[i].getAnswers()[4].getAnswer()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance(): QuestionsFragment {
            return QuestionsFragment()
        }
    }

    private fun setQuestions() {
        val answers: ArrayList<Answers> = ArrayList()
        val answers1: ArrayList<Answers> = ArrayList()
        val answers2: ArrayList<Answers> = ArrayList()
        val answers3: ArrayList<Answers> = ArrayList()
        val answers4: ArrayList<Answers> = ArrayList()

        answers.add(Answers("Плёвое дело", true))
        answers.add(Answers("Клёвое дело", false))
        answers.add(Answers("Слёзное дело", false))
        answers.add(Answers("Кровавое дело", false))
        answers.add(Answers("Мокрое дело", false))
        questions.add(
            Question(
                "Как говорят о пустячной задаче?",
                answers,
                R.color.deep_orange_100,
                R.color.deep_orange_100_dark
            )
        )

        answers1.add(Answers("68 градусов", true))
        answers1.add(Answers("128 градусов", false))
        answers1.add(Answers("100 градусов", false))
        answers1.add(Answers("Не кипит", false))
        answers1.add(Answers("78 градусов", false))
        questions.add(
            Question(
                "При какой температуре по Цельсию закипает вода на вершине Эвереста?",
                answers1, R.color.yellow_100, R.color.yellow_100_dark
            )
        )

        answers2.add(Answers("Эстония", true))
        answers2.add(Answers("Литва", false))
        answers2.add(Answers("Швеция", false))
        answers2.add(Answers("Казахстан", false))
        answers2.add(Answers("Украина", false))
        questions.add(
            Question(
                "На флаге какого из этих государств отсутствует жёлтый цвет?",
                answers2, R.color.light_green_100, R.color.light_green_100_dark
            )
        )

        answers3.add(Answers("Ада Байрон", true))
        answers3.add(Answers("Билл Гейтс", false))
        answers3.add(Answers("Чарльз Беббидж", false))
        answers3.add(Answers("Блез Паскаль", false))
        answers3.add(Answers("Илон Маск", false))
        questions.add(
            Question(
                "Кто был первым программистом?",
                answers3,
                R.color.cyan_100,
                R.color.cyan_100_dark
            )
        )

        answers4.add(Answers("Орден Бани", true))
        answers4.add(Answers("Орден Погреба", false))
        answers4.add(Answers("Орден Кухни", false))
        answers4.add(Answers("Орден Кабинета", false))
        answers4.add(Answers("Орден Спальни", false))
        questions.add(
            Question(
                "Какой рыцарский орден существует в Великобритании почти триста лет?",
                answers4, R.color.deep_purple_100, R.color.deep_purple_100_dark
            )
        )
    }
}