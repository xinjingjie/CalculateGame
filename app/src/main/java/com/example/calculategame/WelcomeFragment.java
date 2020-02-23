package com.example.calculategame;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calculategame.databinding.FragmentWelcomeBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class WelcomeFragment extends Fragment {


    public WelcomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        CalculateViewModel calculateViewModel;
        calculateViewModel= new ViewModelProvider(getActivity(),
                new SavedStateViewModelFactory(requireActivity().getApplication(),requireActivity()))
                .get(CalculateViewModel.class);

        FragmentWelcomeBinding binding;
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_welcome,container,false);
        binding.setData(calculateViewModel);
        binding.setLifecycleOwner(requireActivity());

        binding.beginButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_welcomeFragment_to_questionFragment));
        return binding.getRoot();
    }

}
