package com.denis.mypocket.view.adapter;

import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denis.mypocket.BR;
import com.denis.mypocket.R;
import com.denis.mypocket.databinding.MaterialDrawerActivityBinding;
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

    public DrawerAdapter() {
        setData();
    }

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
    public int getItemViewType(int position) {
        if(position==0)
            return TYPE_HEADER;
        else return TYPE_ITEM;
    }

    private void setData(){
        DrawerItem item1 = new DrawerItem("1",null,"23",true);
        DrawerItem item2 = new DrawerItem("341",null,"23",false);
        DrawerItem item3 = new DrawerItem("1",null,"23",false);

        addAll(Arrays.asList(item1,item2,item3));
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
