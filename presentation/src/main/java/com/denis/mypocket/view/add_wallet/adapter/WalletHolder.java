package com.denis.mypocket.view.add_wallet.adapter;

import android.view.View;

import com.denis.mypocket.databinding.ItemWalletBinding;
import com.denis.mypocket.model.WalletModel;
import com.denis.mypocket.view.adapter.viewholder.BindableHolder;

/**
 * Created by denis on 5/22/16.
 */

public class WalletHolder extends BindableHolder<ItemWalletBinding> {

    public WalletHolder(View itemView) {
        super(itemView);
    }

    public WalletHolder(ItemWalletBinding binding) {
        super(binding);
    }

    public void bind(WalletModel model){
        getBinding().setWallet(model);
    }
}
