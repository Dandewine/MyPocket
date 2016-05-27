package com.denis.mypocket.screens.wallets_screen.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denis.mypocket.R;
import com.denis.mypocket.model.WalletModel;
import com.denis.mypocket.view.adapter.RecyclerBindableAdapter;

/**
 * Created by denis on 5/22/16.
 */

public class WalletAdapter extends RecyclerBindableAdapter<WalletModel, WalletHolder> {
    @Override
    protected WalletHolder getItemHolder(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.item_wallet, viewGroup, false);
        return new WalletHolder(view);
    }

    @Override
    protected WalletHolder getHeaderHolder(LayoutInflater inflater, ViewGroup viewGroup) {
        return null;
    }

    @Override
    protected WalletHolder getFooterHolder(LayoutInflater inflater, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public void onBindViewHolder(WalletHolder holder, int position) {
        WalletModel model = getItem(position);
        holder.getBinding().setWallet(model);
    }
}
