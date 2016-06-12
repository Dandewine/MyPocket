package com.denis.mypocket.screens.add_transaction_screen.view;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.denis.mypocket.BR;
import com.denis.mypocket.R;
import com.denis.mypocket.databinding.ItemCategoryBinding;
import com.denis.mypocket.model.ExpenseCategoryModel;
import com.denis.mypocket.model.IncomeCategoryModel;

import java.util.List;

/**
 * @author Denis_Zinkovskiy at 6/12/16.
 */

public class CategoriesAdapter extends BaseAdapter {

    private List<IncomeCategoryModel> incomeCategoryModels;
    private List<ExpenseCategoryModel> expenseCategoryModels;

    public CategoriesAdapter(List data) {
        if (data == null)
            throw new NullPointerException("Categories are null!");
        if (data.get(0) instanceof IncomeCategoryModel)
            incomeCategoryModels = data;
        else
            expenseCategoryModels = data;
    }

    public CategoriesAdapter() {
    }

    @Override
    public int getCount() {
        return expenseCategoryModels == null ? incomeCategoryModels.size() : expenseCategoryModels.size();
    }

    @Override
    public Object getItem(int position) {
        return expenseCategoryModels == null ? incomeCategoryModels.get(position) :
                expenseCategoryModels.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
            holder = new ViewHolder(DataBindingUtil.bind(view));

            int drawableId = incomeCategoryModels == null ? expenseCategoryModels.get(position).getPath() : incomeCategoryModels.get(position).getPath();
            Drawable drawable = parent.getContext().getDrawable(drawableId);

            holder.binding.setVariable(BR.d,drawable);
            holder.binding.setVariable(BR.incomeCategory, incomeCategoryModels != null ? incomeCategoryModels.get(position) : null);
            holder.binding.setVariable(BR.expenseCategory, expenseCategoryModels != null ? expenseCategoryModels.get(position) : null);
            view.setTag(holder);
        }else{
            holder = ((ViewHolder) view.getTag());
        }
        return holder.binding.getRoot();
    }

    public void addData(List data) {
        if (incomeCategoryModels != null)
            incomeCategoryModels.clear();
        if (expenseCategoryModels != null)
            expenseCategoryModels.clear();

        if (data == null)
            throw new NullPointerException("Categories are null!");
        if (data.get(0) instanceof IncomeCategoryModel)

            incomeCategoryModels = data;
        else
            expenseCategoryModels = data;
    }

    private static class ViewHolder{
        ItemCategoryBinding binding;

        public ViewHolder(ItemCategoryBinding binding) {
            this.binding = binding;
        }
    }

}
