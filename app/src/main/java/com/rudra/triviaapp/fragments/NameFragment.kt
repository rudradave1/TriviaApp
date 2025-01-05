package com.rudra.triviaapp.fragments

import android.content.*
import android.os.*
import android.view.*
import android.widget.*
import androidx.fragment.app.*
import com.google.android.material.textfield.*
import com.rudra.triviaapp.*
import com.rudra.triviaapp.activities.*

class NameFragment : Fragment() {

    private lateinit var mView: View
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_name, container, false)
        val nextButton: Button = mView.findViewById(R.id.nextButton)
        val historyButton: Button = mView.findViewById(R.id.historyButton)
        val nameEditText: TextInputEditText = mView.findViewById(R.id.nameEditText)

        nextButton.setOnClickListener { _ ->
            if (nameEditText.text?.isEmpty() != true) {
                loadFragment(SingleChoiceFragment(), nameEditText.text.toString())
            } else {
                nameEditText.error = "Please enter name"
            }
        }

        historyButton.setOnClickListener { _ ->
            val intent = Intent (activity, TriviaListActivity::class.java)
            activity?.startActivity(intent)
        }

        return mView
    }

    private fun loadFragment(fragment: Fragment, name: String){
        val bundle = Bundle()
        bundle.putString("name", name)
        fragment.arguments = bundle

        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.root_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

}