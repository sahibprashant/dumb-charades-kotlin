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
import com.development.dumbcharades.databinding.FragmentMainBinding
import com.development.dumbcharades.db.WordsRoomDatabase
import com.development.dumbcharades.viewmodels.MainViewModel
import com.development.dumbcharades.viewmodels.MainViewModelFactory
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment(), View.OnClickListener{

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentMainBinding>(inflater,
            R.layout.fragment_main,
            container,
            false)

        setHasOptionsMenu(true)

        val wordsDAO = WordsRoomDatabase.getInstance(this.requireContext()).wordsDAO
        val viewModelFactory = MainViewModelFactory(wordsDAO)
        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

        binding.mainViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.readyToPlay.observe(viewLifecycleOwner, Observer { isReady ->
            if(isReady){
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToGameFragment())
                viewModel.checkReadyToPlay()
            }
        })

        viewModel.showSnackBar.observe(viewLifecycleOwner, Observer { showSnackBar ->
            if(showSnackBar){
                Snackbar.make(this.requireView(), "Please add words first to play.", Snackbar.LENGTH_SHORT).show()
                viewModel.hideSnackBar()
            }}
        )
        return binding.root
    }

    override fun onClick(v: View?) {
        findNavController().navigate(MainFragmentDirections.actionMainFragmentToGameFragment())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main_fragment, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.wordDatabase -> NavigationUI.onNavDestinationSelected(item, view!!.findNavController())
            else -> super.onOptionsItemSelected(item)
        }
    }

}