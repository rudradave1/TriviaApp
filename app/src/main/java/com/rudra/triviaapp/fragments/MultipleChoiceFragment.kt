package com.rudra.triviaapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.rudra.triviaapp.R

class MultipleChoiceFragment : Fragment() {
    
    private lateinit var mView: View
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_multiple_choice, container, false)
        val nextButton: Button = mView.findViewById(R.id.nextButton)
        val checkbox1: CheckBox = mView.findViewById(R.id.checkbox1)
        val checkbox2: CheckBox = mView.findViewById(R.id.checkbox2)
        val checkbox3: CheckBox = mView.findViewById(R.id.checkbox3)
        val checkbox4: CheckBox = mView.findViewById(R.id.checkbox4)
        
        val args = arguments
        val name = args?.getString("name")
        val answer1 = args?.getString("answer1")
        
        nextButton.setOnClickListener { _ ->
            val checkBoxString =
                mutableListOf<String>()
            val nameString =
                arrayOf(checkbox1, checkbox2, checkbox3, checkbox4)
            for (i in 0..3) {
                //looping to see which of the checkboxes are selected
                if (nameString[i].isChecked) {
                    checkBoxString.add(nameString[i].text.toString())
                }
            }
            checkBoxString.joinToString(
                prefix = " ",
                postfix = " ",
                separator = " , ",
                transform = { joinedString -> joinedString }) // using ',' as separator
            val finalString: String = checkBoxString.toString().removeSurrounding("[", "]") // removing brackets
            if (answer1 != null && name != null) {
                if (finalString.isNotBlank()) {
                    loadFragment(SummaryFragment(), name, answer1, finalString)
                } else {
                    Toast.makeText(context, getString(R.string.please_select_an_item), Toast.LENGTH_SHORT).show()
                }
            }
        }
        return mView
    }
    
    private fun loadFragment(
        fragment: Fragment,
        name: String?,
        answer1: String?,
        checkBoxString: String
    ) {
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putString("answer1", answer1)
        bundle.putString("answer2", checkBoxString)
        fragment.arguments = bundle
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.root_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}