package com.denis.mypocket.screens.transactions_tab_screen.view;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denis.mypocket.BR;
import com.denis.mypocket.R;
import com.denis.mypocket.model.TransactionModel;
import com.denis.mypocket.model.WalletModel;
import com.denis.mypocket.view.adapter.RecyclerBindableAdapter;
import com.denis.mypocket.view.adapter.viewholder.BindableHolder;

import java.util.List;

/**
 * @author Denis_Zinkovskiy at 6/8/16.
 */

public class TransactionAdapter extends RecyclerBindableAdapter<TransactionModel, BindableHolder> {

    private List<WalletModel> walletModelList; //this is not models of this adapter

    public TransactionAdapter(List<WalletModel> walletModelList) {
        this.walletModelList = walletModelList;
    }

    @Override
    protected BindableHolder getItemHolder(LayoutInflater inflater, ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.item_transaction, viewGroup, false);
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
        TransactionModel transaction = getItem(getItemCount() - position - 1);
        WalletModel walletModel = null;
        for (WalletModel model : walletModelList) {
            if (TextUtils.equals(model.getId(), transaction.getWalletId()))
                walletModel = model;
        }

        holder.getBinding().setVariable(BR.wallet, walletModel);
        holder.getBinding().setVariable(BR.transaction, transaction);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);

    }
}
