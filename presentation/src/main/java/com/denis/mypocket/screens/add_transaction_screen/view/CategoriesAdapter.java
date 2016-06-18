package com.denis.mypocket.screens.add_transaction_screen.view;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ItemCategoryBinding;
import com.denis.mypocket.model.categories.CategoryModel;
import com.denis.mypocket.model.categories.ExpenseCategoryModel;
import com.denis.mypocket.model.categories.IncomeCategoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis_Zinkovskiy at 6/12/16.
 */

public class CategoriesAdapter extends BaseAdapter {

    private List<IncomeCategoryModel> incomeCategoryModels;
    private List<ExpenseCategoryModel> expenseCategoryModels;
    private List<CategoryModel> categoryList = new ArrayList<>();

    public CategoriesAdapter() {
    }

    @Override
    public int getCount() {
       // return expenseCategoryModels == null ? incomeCategoryModels.size() : expenseCategoryModels.size();
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
     /*   return expenseCategoryModels == null ? incomeCategoryModels.get(position) :
                expenseCategoryModels.get(position);*/
        return categoryList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override @SuppressLint("ViewHolder")
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemCategoryBinding binding = DataBindingUtil.inflate(inflater,R.layout.item_category,parent,false);
       // ExpenseCategoryModel expenseCategory = expenseCategoryModels != null ? expenseCategoryModels.get(position) : null;
        //IncomeCategoryModel incomeCategory = incomeCategoryModels != null ? incomeCategoryModels.get(position) : null;
        CategoryModel category = categoryList.get(position);

        binding.setTitle(category.getName());
        binding.setD(parent.getContext().getDrawable(category.getPath()));
       // binding.setD(expenseCategory == null ? parent.getContext().getDrawable(incomeCategory.getPath()) : parent.getContext().getDrawable(expenseCategory.getPath()));

        return binding.getRoot();
    }

    public void addData(List<CategoryModel> data) {
        categoryList.clear();
        categoryList.addAll(data);


       /* if (incomeCategoryModels != null)
            incomeCategoryModels.clear();
        if (expenseCategoryModels != null)
            expenseCategoryModels.clear();

        if (data == null)
            throw new NullPointerException("Categories are null!");
        if (data.get(0) instanceof IncomeCategoryModel)

            incomeCategoryModels = data;
        else
            expenseCategoryModels = data;*/
    }

}
