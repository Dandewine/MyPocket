package com.denis.mypocket.view.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denis.mypocket.R;
import com.denis.mypocket.databinding.FragmentCycleOperationBinding;
import com.denis.mypocket.internal.di.components.DaggerGetCycleOpComponent;
import com.denis.mypocket.viewmodel.getting.GetCycleOperationViewModel;

import javax.inject.Inject;

public class CycleOperationFragment extends BaseFragment {

    public static CycleOperationFragment newInstance() {
        Bundle args = new Bundle();
        CycleOperationFragment fragment = new CycleOperationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private FragmentCycleOperationBinding binding;
    @Inject public GetCycleOperationViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cycle_operation, container, false);
       // binding.fabAddCO.setOnClickListener(v -> startTransition());
      //  binding.recyclerTransactions.setAdapter(viewModel.operationAdapter);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

  /*  private void startTransition(){
        Intent intent = new Intent(getActivity(), AddCycleOperationActivity.class);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(getActivity(), binding.fabAddCO, binding.fabAddCO.getTransitionName());
        ActivityCompat.startActivity(getActivity(),intent,optionsCompat.toBundle());
    }*/

    @Override
    protected void initDI() {
        DaggerGetCycleOpComponent.builder()
                .applicationComponent(getBaseActivity().getApplicationComponent())
                .build().inject(this);
    }

    @Override
    public void onDestroy() {
        viewModel.destroy();
        super.onDestroy();
    }
}
