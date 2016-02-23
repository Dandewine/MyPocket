package com.denis.mypocket.viewmodel.getting;

import com.denis.domain.interactor.DefaultSubscriber;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.Wallet;
import com.denis.mypocket.model.WalletModel;
import com.denis.mypocket.model.mapper.WalletModelDataMapper;
import com.denis.mypocket.view.adapter.WalletAdapter;
import com.denis.mypocket.viewmodel.ViewModel;

import java.util.List;

import javax.inject.Inject;

public class GetAllWalletViewModel implements ViewModel {
    private UseCase<Wallet> useCase;
    private WalletAdapter walletAdapter;
    private WalletModelDataMapper walletMapper;

    @Inject
    public GetAllWalletViewModel(UseCase<Wallet> useCase, WalletModelDataMapper walletMapper) {
        this.useCase = useCase;
        this.walletMapper = walletMapper;

        walletAdapter = new WalletAdapter();
        useCase.executeSync(new GetAllWalletSubscriber());
    }

    @Override
    public void destroy() {
        useCase.unSubscribe();
    }

    private class GetAllWalletSubscriber extends DefaultSubscriber<List<Wallet>>{
        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<Wallet> wallets) {
            if(wallets != null && !wallets.isEmpty()){
                List<WalletModel> walletModels = walletMapper.transform(wallets);
                walletAdapter.addAll(walletModels);
            }
        }
    }

    public WalletAdapter getWalletAdapter() {
        return walletAdapter;
    }
}
