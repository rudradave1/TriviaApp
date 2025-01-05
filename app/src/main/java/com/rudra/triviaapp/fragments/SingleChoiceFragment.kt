package com.rudra.triviaapp.fragments

import android.os.*
import android.view.*
import android.widget.*
import androidx.fragment.app.*
import com.rudra.triviaapp.*

class SingleChoiceFragment : Fragment() {
    private lateinit var mView: View
    private lateinit var radioButton: RadioButton
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_single_choice, container, false)
        val nextButton: Button = mView.findViewById(R.id.nextButton)
        val radioGroup: RadioGroup = mView.findViewById(R.id.radioGroup)

        val args = arguments
        val name = args?.getString("name")

        nextButton.setOnClickListener {_ ->
            if (radioGroup.checkedRadioButtonId == -1)
            {
                // no radio buttons are checked
                Toast.makeText(context, "Please select a option", Toast.LENGTH_SHORT).show()
            }
            else
            {
                // one of the radio buttons is checked
                val selectedOption: Int = radioGroup.checkedRadioButtonId
                radioButton = mView.findViewById(selectedOption)
                loadFragment(MultipleChoiceFragment(), radioButton.text.toString(), name.toString())
            }

        }
        return mView
    }

    private fun loadFragment(fragment: Fragment, answer1: String, name: String){
        val bundle = Bundle()
        bundle.putString("name", name)
        bundle.putString("answer1", answer1)
        fragment.arguments = bundle

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.root_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}