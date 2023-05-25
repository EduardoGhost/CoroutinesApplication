package com.example.coroutinesapplication.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.coroutinesapplication.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val myTextView = view?.findViewById<TextView>(R.id.view_games)

        viewModel = ViewModelProvider(
            this,
            MainViewModel.MainViewModelFactory(MainRepository())
        )[MainViewModel::class.java]

        viewModel.gamesLifeData.observe(viewLifecycleOwner, Observer { games ->

            viewLifecycleOwner.lifecycleScope.launch {
                for (game in games) {
                    delay(1000)
                    myTextView?.text = game.title
                }
            }
        })
        viewModel.getGamesCoroutines()

    }
}
