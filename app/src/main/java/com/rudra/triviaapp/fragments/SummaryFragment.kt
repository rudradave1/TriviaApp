package com.rudra.triviaapp.fragments

import android.content.*
import android.os.*
import android.view.*
import android.widget.*
import androidx.fragment.app.*
import com.rudra.triviaapp.*
import com.rudra.triviaapp.activities.MainActivity
import com.rudra.triviaapp.db.*
import java.text.*
import java.util.*

class SummaryFragment : Fragment() {
    
    private lateinit var mView: View
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_summary, container, false)
        val finishButton: Button = mView.findViewById(R.id.finishButton)
        val nameTextView: TextView = mView.findViewById(R.id.nameTextView)
        val timeTextView: TextView = mView.findViewById(R.id.timeTextView)
        val a1TextView: TextView = mView.findViewById(R.id.a1TextView)
        val a2TextView: TextView = mView.findViewById(R.id.a2TextView)
        
        val databaseHandler = DatabaseHandler(context)
        val args = arguments
        val name = args?.getString("name")
        val answer1 = args?.getString("answer1")
        val answer2 = args?.getString("answer2")
        
        val currentDate = DateFormat.getDateInstance(DateFormat.MEDIUM).format(Date())
        
        a1TextView.text = answer1
        a2TextView.text = answer2
        nameTextView.text = resources.getString(R.string.hello, name)
        timeTextView.text = resources.getString(R.string.submission_time, currentDate)
        
        finishButton.setOnClickListener {
            val status = databaseHandler.addTst(answer1.toString(), answer2.toString(), name.toString(), currentDate)
            if (status > -1) {
                Toast.makeText(context, getString(R.string.answers_saved), Toast.LENGTH_SHORT).show()
                val intent = Intent(activity, MainActivity::class.java)
                activity?.startActivity(intent)
                activity?.finish()
            } else {
                Toast.makeText(context, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show()
            }
            
        }
        return mView
    }
}