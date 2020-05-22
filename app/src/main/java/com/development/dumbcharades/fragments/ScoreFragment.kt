package com.development.dumbcharades.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.development.dumbcharades.R
import com.development.dumbcharades.databinding.FragmentScoreBinding
import com.development.dumbcharades.viewmodels.ScoreViewModel
import com.development.dumbcharades.viewmodels.ScoreViewModelFactory

class ScoreFragment : Fragment() {

    private lateinit var viewModel: ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentScoreBinding>(inflater,
            R.layout.fragment_score,
            container, false)

        val args = ScoreFragmentArgs.fromBundle(arguments!!)

        viewModelFactory = ScoreViewModelFactory(args.score)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ScoreViewModel::class.java)
        binding.scoreViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.restartGame.observe(viewLifecycleOwner, Observer { restartGame -> if(restartGame) navigateToGame()})

        return binding.root
    }

    private fun navigateToGame() {
        findNavController().navigate(ScoreFragmentDirections.actionScoreFragmentToGameFragment())
    }

}
