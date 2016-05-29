package com.denis.mypocket.model.mapper;

import com.denis.domain.models.Transaction;
import com.denis.mypocket.model.TransactionModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TransactionModelDataMapper implements ModelMapper<Transaction, TransactionModel> {


    @Inject
    public TransactionModelDataMapper() {
    }

    @Override
    public TransactionModel transform(Transaction transaction) {
        TransactionModel model = null;
        if (transaction != null) {
            model = new TransactionModel(transaction.getId());
            model.setAmount(transaction.getAmount());
            model.setType(transaction.getType());
            model.setUnixDateTime(transaction.getUnixDateTime());
            model.setCategoryId(transaction.getCategoryId());
        }
        return model;
    }

    @Override
    public List<TransactionModel> transform(List<Transaction> transactionList) {
        List<TransactionModel> modelList = null;
        if (transactionList != null && !transactionList.isEmpty()) {
            modelList = new ArrayList<>();
            for (Transaction t : transactionList) {
                TransactionModel model = transform(t);
                modelList.add(model);
            }
        }
        return modelList;
    }

}
