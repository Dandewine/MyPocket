package com.denis.mypocket.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denis.mypocket.BR;
import com.denis.mypocket.R;
import com.denis.mypocket.databinding.MaterialDrawerHeaderBinding;
import com.denis.mypocket.databinding.MaterialDrawerItemPrimaryBinding;
import com.denis.mypocket.model.DrawerItem;
import com.denis.mypocket.model.User;
import com.denis.mypocket.view.adapter.viewholder.BindableHolder;

import java.util.Arrays;


/**
 * Created by denis on 12/20/15.
 */
public class DrawerAdapter extends RecyclerBindableAdapter<DrawerItem,BindableHolder> {


    @Override
    public void onBindViewHolder(BindableHolder holder, int position) {
        if(getItemViewType(position)==TYPE_HEADER)
            holder.getBinding().setVariable(BR.user,new User("Denis Zinkovskiy","denis_zink@gmail.com"));
        else {
            DrawerItem item = getItem(position - getHeadersCount());
            holder.getBinding().setVariable(BR.item, item);
            holder.setOnClickListener(position,item,actionListener);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        setData();
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0)
            return TYPE_HEADER;
        else return TYPE_ITEM;
    }

    private void setData(){
        DrawerItem item1 = new DrawerItem(context.getString(R.string.drawer_categories),null,"23",true);
        DrawerItem item2 = new DrawerItem(context.getString(R.string.drawer_saves),null,"23",false);
        DrawerItem item3 = new DrawerItem(context.getString(R.string.drawer_travel_mode),null,"23",false);
        DrawerItem item4 = new DrawerItem(context.getString(R.string.drawer_cycle_operations),null,"23",false);
        DrawerItem item5 = new DrawerItem(context.getString(R.string.drawer_tutorials),null,"23",false);
        DrawerItem item6 = new DrawerItem(context.getString(R.string.drawer_wallets),context.getResources().getDrawable(R.drawable.ic_wallet),"23",false);
        DrawerItem item7 = new DrawerItem(context.getString(R.string.drawer_settings),context.getResources().getDrawable(R.drawable.ic_settings),"23",false);
        DrawerItem item8 = new DrawerItem(context.getString(R.string.drawer_debts),null,"23",false);

        addAll(Arrays.asList(item1,item2,item3,item4,item5,item6,item7,item8));
    }

    @Override
    protected BindableHolder getItemHolder(LayoutInflater inflater, ViewGroup viewGroup) {
        return  new DrawerItemHolder(MaterialDrawerItemPrimaryBinding.inflate(inflater));
    }

    @Override
    protected BindableHolder getHeaderHolder(LayoutInflater inflater, ViewGroup viewGroup) {
        MaterialDrawerHeaderBinding header = MaterialDrawerHeaderBinding.inflate(inflater);
        addHeader(header);
        return new HeaderHolder(header);
    }

    @Override
    protected BindableHolder getFooterHolder(LayoutInflater inflater, ViewGroup viewGroup) {
        return null;
    }

    public static class DrawerItemHolder extends BindableHolder<MaterialDrawerItemPrimaryBinding>{
        public DrawerItemHolder(MaterialDrawerItemPrimaryBinding binding) {
            super(binding);
        }

    }
    public static class HeaderHolder extends BindableHolder<MaterialDrawerHeaderBinding>{
        public HeaderHolder(MaterialDrawerHeaderBinding binding) {
            super(binding);
        }
    }

    BindableHolder.ActionListener actionListener = (position, item) -> {
        Toast.makeText(context,((DrawerItem)item).itemText.get(),Toast.LENGTH_SHORT).show();
    };
}
