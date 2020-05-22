package com.development.dumbcharades.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.development.dumbcharades.R
import com.development.dumbcharades.databinding.FragmentHandleWordsBinding
import com.development.dumbcharades.db.WordsRoomDatabase
import com.development.dumbcharades.viewmodels.HandleWordsViewModel
import com.development.dumbcharades.viewmodels.HandleWordsViewModelFactory


class HandleWordsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHandleWordsBinding>(inflater,
            R.layout.fragment_handle_words, container, false)

        val application = this.requireContext()

        val wordsDAO = WordsRoomDatabase.getInstance(application).wordsDAO
        val viewModelFactory = HandleWordsViewModelFactory(wordsDAO,application)

        val viewModel = ViewModelProviders.of(this, viewModelFactory).get(HandleWordsViewModel::class.java)

        binding.wordsModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

}
