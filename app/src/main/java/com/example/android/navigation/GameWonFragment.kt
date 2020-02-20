package com.example.android.navigation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding


class GameWonFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)

        binding.nextMatchButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_gameWonFragment_to_gameFragment2)
        }

        val args = GameWonFragmentArgs.fromBundle(arguments!!)
        Toast.makeText(
                context,
                "NumCorrect: ${args.numCorrect}, NumQuestions: ${args.numCorrect}",
                Toast.LENGTH_LONG)
                .show()

        setHasOptionsMenu(true)

        return binding.root
    }

    private fun getShareIntent(): Intent {
        val args = GameWonFragmentArgs.fromBundle(arguments!!)
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
                .putExtra(Intent.EXTRA_TEXT,
                        getString(R.string.share_success_text,
                                args.numCorrect, args.numQuestions)
                )

        return shareIntent
    }

    private fun shareSucessResult() {
        startActivity(getShareIntent())
    }
}
