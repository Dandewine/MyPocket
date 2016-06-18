package com.denis.mypocket.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denis.mypocket.databinding.FragmentCycleOperationBinding;

public class CycleOperationFragment extends BaseFragment {

    public static CycleOperationFragment newInstance() {
        Bundle args = new Bundle();
        CycleOperationFragment fragment = new CycleOperationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private FragmentCycleOperationBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

  /*  private void startTransition(){
        Intent intent = new Intent(getActivity(), AddCycleOperationActivity.class);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(getActivity(), binding.fabAddCO, binding.fabAddCO.getTransitionName());
        ActivityCompat.startActivity(getActivity(),intent,optionsCompat.toBundle());
    }*/

    @Override
    protected void initDI() {
       /* DaggerGetCycleOpComponent.builder()
                .applicationComponent(getBaseActivity().getApplicationComponent())
                .build().inject(this);*/
    }

    @Override
    public void onDestroy() {
//        viewModel.destroy();
        super.onDestroy();
    }
}
