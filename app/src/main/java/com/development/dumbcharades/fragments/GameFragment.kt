package com.development.dumbcharades.fragments

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.development.dumbcharades.R
import com.development.dumbcharades.databinding.FragmentGameBinding
import com.development.dumbcharades.viewmodels.GameViewModel
import com.development.dumbcharades.viewmodels.GameViewModelFactory

class GameFragment : Fragment(){

    private lateinit var viewModel : GameViewModel
    private lateinit var viewModelFactory: GameViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater
            , R.layout.fragment_game
            , container
            , false)



        viewModelFactory = GameViewModelFactory()
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(GameViewModel::class.java)

        binding.gameViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.gameFinished.observe(viewLifecycleOwner, Observer { isGameFinished -> if(isGameFinished) gameFinished() })

        return binding.root
    }

    private fun gameFinished() {
        val score = viewModel.score.value ?: 0
        findNavController().navigate(GameFragmentDirections.actionGameFragmentToScoreFragment(score))
    }


}

