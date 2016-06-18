package com.denis.data.entity.mapper;

import com.denis.data.entity.ExpenseCategoryEntity;
import com.denis.data.entity.IncomeCategoryEntity;
import com.denis.data.entity.LoginResponseEntity;
import com.denis.domain.models.categories.ExpenseCategory;
import com.denis.domain.models.categories.IncomeCategory;
import com.denis.domain.models.LoginResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by denis on 4/28/16.
 */
public class LoginResponseMapper implements EntityMapper<LoginResponseEntity, LoginResponse> {

    private ExpenseCategoryDataMapper expenseCategoryDataMapper;
    private IncomeCategoryDataMapper incomeCategoryDataMapper;

    @Inject
    public LoginResponseMapper() {
        expenseCategoryDataMapper = new ExpenseCategoryDataMapper();
        incomeCategoryDataMapper = new IncomeCategoryDataMapper();
    }

    @Override
    public LoginResponseEntity toEntity(LoginResponse loginResponse) {
        LoginResponseEntity loginResponseEntity = null;
        if (loginResponse != null) {
            loginResponseEntity = new LoginResponseEntity();
            loginResponseEntity.setToken(loginResponse.getToken());
            loginResponseEntity.setUser(loginResponse.getUser());

            List<ExpenseCategory> expenseCategories = loginResponse.getExpenseCategories();
            loginResponseEntity.setExpenseCategoryEntities(expenseCategoryDataMapper.toEntity(expenseCategories));

            List<IncomeCategory> incomeCategories = loginResponse.getIncomeCategories();
            loginResponseEntity.setIncomeCategoryEntities(incomeCategoryDataMapper.toEntity(incomeCategories));
        }
        return loginResponseEntity;
    }

    @Override
    public List<LoginResponseEntity> toEntity(List<LoginResponse> loginResponses) {
        List<LoginResponseEntity> entityList = null;
        if (loginResponses != null) {
            entityList = new ArrayList<>();
            for (LoginResponse lr : loginResponses) {
                LoginResponseEntity lrl = toEntity(lr);
                entityList.add(lrl);
            }
        }
        return entityList;
    }

    @Override
    public LoginResponse fromEntity(LoginResponseEntity model) {
        LoginResponse domainModel = null;
        if (model != null) {
            domainModel = new LoginResponse();
            domainModel.setToken(model.getToken());
            domainModel.setUser(model.getUser());

            List<ExpenseCategoryEntity> expenseCategoryEntities = model.getExpenseCategoryEntities();
            domainModel.setExpenseCategories(expenseCategoryDataMapper.fromEntity(expenseCategoryEntities));

            List<IncomeCategoryEntity> incomeCategoryEntities = model.getIncomeCategoryEntities();
            domainModel.setIncomeCategories(incomeCategoryDataMapper.fromEntity(incomeCategoryEntities));
        }
        return domainModel;
    }

    @Override
    public List<LoginResponse> fromEntity(List<LoginResponseEntity> models) {
        List<LoginResponse> domainList = null;
        if (models != null) {
            domainList = new ArrayList<>();
            for (LoginResponseEntity lre : models) {
                LoginResponse lr = fromEntity(lre);
                domainList.add(lr);
            }
        }
        return domainList;
    }
}
