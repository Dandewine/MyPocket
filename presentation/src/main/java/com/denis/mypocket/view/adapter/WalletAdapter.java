package com.denis.mypocket.view.adapter;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denis.mypocket.BR;
import com.denis.mypocket.R;
import com.denis.mypocket.model.WalletModel;
import com.denis.mypocket.view.adapter.viewholder.BindableHolder;

public class WalletAdapter extends RecyclerBindableAdapter<WalletModel,BindableHolder> {
    @Override
    protected BindableHolder getItemHolder(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.item_wallet, viewGroup, false);
        return new BindableHolder(view);
    }

    @Override
    protected BindableHolder getHeaderHolder(LayoutInflater inflater, ViewGroup viewGroup) {
        return null;
    }

    @Override
    protected BindableHolder getFooterHolder(LayoutInflater inflater, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public void onBindViewHolder(BindableHolder holder, int position) {
        holder.getBinding().setVariable(BR.wallet,getItem(position));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }
}
