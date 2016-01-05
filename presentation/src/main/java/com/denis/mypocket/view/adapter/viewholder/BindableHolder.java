package com.denis.mypocket.view.adapter.viewholder;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class BindableHolder<VH extends ViewDataBinding> extends RecyclerView.ViewHolder {
    private VH binding;

    public static <VH extends ViewDataBinding>BindableHolder<VH> newInstance(@LayoutRes int laoutId,
                                                                             LayoutInflater inflater,
                                                                             @Nullable ViewGroup parent,
                                                                             boolean attachToRoot){

        VH vh = DataBindingUtil.inflate(inflater,laoutId,parent,attachToRoot);
        return new BindableHolder<>(vh);
    }

    public BindableHolder(VH binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void setOnClickListener(int position, Object item, ActionListener listener){
        itemView.setClickable(true);
        itemView.setOnClickListener(e -> listener.onItemClickListener(position,item));
    }

    public BindableHolder(View itemView) {
        super(itemView);
        this.binding = DataBindingUtil.bind(itemView);
    }

    public VH getBinding() {
        return binding;
    }

    public interface ActionListener{
        void onItemClickListener(int position, Object item);
    }
}
