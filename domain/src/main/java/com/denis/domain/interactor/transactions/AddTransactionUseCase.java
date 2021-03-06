package com.denis.domain.interactor.transactions;

import com.denis.domain.executor.PostExecutionThread;
import com.denis.domain.executor.ThreadExecutor;
import com.denis.domain.interactor.UseCase;
import com.denis.domain.models.Transaction;
import com.denis.domain.repository.TransactionRepository;

import javax.inject.Inject;

import rx.Observable;

public class AddTransactionUseCase extends UseCase<Transaction> {

    private TransactionRepository transactionRepository;

    @Inject
    public AddTransactionUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread,
                                 TransactionRepository repository) {
        super(threadExecutor, postExecutionThread);
        this.transactionRepository = repository;
    }

    @Override
    protected Observable buildUseCaseObservable(Transaction... arg) {
       /* Transaction transaction = arg[0];
        if (transaction != null) {
            Wallet wallet = transaction.getWallet();
            if (transaction.getType() == 0) // if transaction was expense
                wallet.setBalance(wallet.getBalance() - transaction.getAmount());
            else if (transaction.getType() == 1) //if transaction was income
                wallet.setBalance(wallet.getBalance() + transaction.getAmount());
            Log.d("myTag", "walletBalance = "+wallet.getBalance());
            walletsRepository.update(wallet);
            return transactionRepository.addTransaction(transaction);
        } else throw new RuntimeException("Transaction was null, can't proceed operation!");*/
        return Observable.empty();
    }
}
