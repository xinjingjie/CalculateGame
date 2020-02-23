package com.example.calculategame;


import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.calculategame.databinding.FragmentQuestionBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {


    public QuestionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final CalculateViewModel calculateViewModel;
//        calculateViewModel= ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()).create(CalculateViewModel.class);
        calculateViewModel= new ViewModelProvider(getActivity(),
                new SavedStateViewModelFactory(requireActivity().getApplication(),requireActivity()))
                .get(CalculateViewModel.class);
        final FragmentQuestionBinding binding;
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_question,container,false);
        binding.setData(calculateViewModel);
        binding.setLifecycleOwner(requireActivity());
        calculateViewModel.operator(20);
        final StringBuilder sb=new StringBuilder();

        /*
        数字输入
         */
        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button11:
                        sb.append("0");
                        break;
                    case R.id.button:
                        sb.append("1");
                        break;
                    case R.id.button2:
                        sb.append("2");
                        break;
                    case R.id.button3:
                        sb.append("3");
                        break;
                    case R.id.button4:
                        sb.append("4");
                        break;
                    case R.id.button5:
                        sb.append("5");
                        break;
                    case R.id.button6:
                        sb.append("6");
                        break;
                    case R.id.button7:
                        sb.append("7");
                        break;
                    case R.id.button8:
                        sb.append("8");
                        break;
                    case R.id.button9:
                        sb.append("9");
                        break;

                }
                    if ( sb.length()==0 ){
                        binding.readyAnswer.setText(getResources().getString(R.string.enterAnswerMessage));
                    }else {
                        binding.readyAnswer.setText(sb.toString());
                    }
            }
        };
        binding.button.setOnClickListener(listener);
        binding.button2.setOnClickListener(listener);
        binding.button3.setOnClickListener(listener);
        binding.button4.setOnClickListener(listener);
        binding.button5.setOnClickListener(listener);
        binding.button6.setOnClickListener(listener);
        binding.button7.setOnClickListener(listener);
        binding.button8.setOnClickListener(listener);
        binding.button9.setOnClickListener(listener);
        binding.button11.setOnClickListener(listener);




        /*
        提交按钮判断
         */
        binding.button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//答对了
                if ( sb.toString().equals(calculateViewModel.getCorrectAnswer().getValue().toString()) ){
                    calculateViewModel.answerCorrect(getResources().getInteger((int) R.integer.range));//这里需要改进
                    binding.readyAnswer.setText(getResources().getString(R.string.answerCorrectMessage));
                    sb.setLength(0);
                }//答错了
                else {
                    NavController navController=Navigation.findNavController(v);
                    if ( calculateViewModel.win_flag==true ){
                        navController.navigate(R.id.action_questionFragment_to_winFragment);
                        calculateViewModel.win_flag=false;
                        calculateViewModel.save();
                    }else{
                        navController.navigate(R.id.action_questionFragment_to_loseFragment);
                    }
                }
            }
        });




        binding.button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("AAAA", "onClick: "+sb.length());
                if ( sb.length()>1 ){
                    Log.d("AAAA", "onClick: dididiiiiiiiiiiiiiiiiii");
                    sb.deleteCharAt(sb.length()-1);
                }else if ( sb.length()==1 ){
                    sb.setLength(0);
                    sb.append("0");
                }
                binding.readyAnswer.setText(sb);
                calculateViewModel.getCurrentAnswer().setValue(Integer.parseInt(sb.toString()));
            }
        });


        return binding.getRoot();
    }

}
