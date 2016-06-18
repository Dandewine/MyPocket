package com.denis.mypocket.screens;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.denis.mypocket.databinding.ItemWalletBinding;
import com.denis.mypocket.model.WalletModel;

public class WalletAdapter extends RecyclerBindableAdapter<WalletModel,BindableHolder> {
    @Override
    protected BindableHolder getItemHolder(LayoutInflater inflater, ViewGroup viewGroup) {
        return new BindableHolder(ItemWalletBinding.inflate(inflater));
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

    }
}
