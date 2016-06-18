package com.denis.mypocket.model.mapper;

import com.denis.domain.models.Transaction;
import com.denis.domain.models.categories.Category;
import com.denis.mypocket.model.TransactionModel;
import com.denis.mypocket.model.categories.CategoryModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class TransactionModelDataMapper implements ModelMapper<Transaction, TransactionModel> {

    private CategoryMapper categoryMapper;

    @Inject
    public TransactionModelDataMapper() {
        categoryMapper = new CategoryMapper();
    }

    public TransactionModel toModel(Transaction transaction) {
        TransactionModel model = null;
        if (transaction != null) {
            model = new TransactionModel(transaction.getId());
            model.setAmount(transaction.getAmount());
            model.setType(transaction.getType());
            model.setUnixDateTime(transaction.getUnixDateTime());

            Category category = transaction.getCategory();
            CategoryModel categoryModel = categoryMapper.toModel(category);

            model.setCategory(categoryModel);
            model.setWalletId(transaction.getWalletId());
        }
        return model;
    }

    @Override
    public List<TransactionModel> toModel(List<Transaction> transactionList) {
        List<TransactionModel> modelList = null;
        if (transactionList != null && !transactionList.isEmpty()) {
            modelList = new ArrayList<>();
            for (Transaction t : transactionList) {
                TransactionModel model = toModel(t);
                modelList.add(model);
            }
        }
        return modelList;
    }

    @Override
    public List<Transaction> fromModel(List<TransactionModel> transactionModels) {
        return null;
    }

    @Override
    public Transaction fromModel(TransactionModel transactionModel) {
        return null;
    }
}
