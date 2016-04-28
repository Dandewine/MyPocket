package com.denis.domain.repository;

import com.denis.domain.models.Transaction;

import java.util.List;

import rx.Observable;

public interface TransactionRepository {
    /**
     * Get an {@link rx.Observable} which will emit a List of {@link Transaction}.
     */
    Observable<List<Transaction>> getTransactionList();

    /**
     * Get an {@link rx.Observable} which will emit a {@link Transaction}.
     *
     * @param transactionId The getTransaction id used to retrieve getTransaction data.
     */
    Observable<Transaction> getTransaction(final String transactionId);

    /**
     * Add transaction into our local storage
     * @param Transaction
     * @return
     */

    Observable<Transaction> addTransaction(Transaction Transaction);

    /**
     * Add transaction's list to our local storage
     * @param Transactions
     * @return
     */
    Observable<List<Transaction>> addTransaction(List<Transaction> Transactions);
}
