package com.denis.mypocket.view.adapter;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.denis.mypocket.BR;
import com.denis.mypocket.R;
import com.denis.mypocket.databinding.MaterialDrawerHeaderBinding;
import com.denis.mypocket.databinding.MaterialDrawerItemPrimaryBinding;
import com.denis.mypocket.model.DrawerItem;
import com.denis.mypocket.model.UserModel;
import com.denis.mypocket.view.activity.WalletsActivity;
import com.denis.mypocket.view.adapter.viewholder.BindableHolder;

import java.util.Arrays;


/**
 * Created by denis on 12/20/15.
 */
public class DrawerAdapter extends RecyclerBindableAdapter<DrawerItem,BindableHolder> {


    @Override
    public void onBindViewHolder(BindableHolder holder, int position) {
        if(getItemViewType(position)==TYPE_HEADER)
            holder.getBinding().setVariable(BR.user,new UserModel("Denis Zinkovskiy","denis_zink@gmail.com"));
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
        DrawerItem item1 = new DrawerItem(context.getString(R.string.drawer_categories),getDrawable(R.drawable.vector_categories),"23",true);
        DrawerItem item2 = new DrawerItem(context.getString(R.string.drawer_saves),getDrawable(R.drawable.vector_saves),"23",false);
        DrawerItem item3 = new DrawerItem(context.getString(R.string.drawer_travel_mode),getDrawable(R.drawable.vector_travel_mode),"23",false);
        DrawerItem item4 = new DrawerItem(context.getString(R.string.drawer_cycle_operations),getDrawable(R.drawable.vector_cycle),"23",false);
        DrawerItem item5 = new DrawerItem(context.getString(R.string.drawer_tutorials),getDrawable(R.drawable.vector_tutorials),"23",false);
        DrawerItem item6 = new DrawerItem(context.getString(R.string.drawer_wallets),getDrawable(R.drawable.vector_wallet),"23",false);
        DrawerItem item7 = new DrawerItem(context.getString(R.string.drawer_settings),getDrawable(R.drawable.vector_settings),"23",false);
        DrawerItem item8 = new DrawerItem(context.getString(R.string.drawer_debts),getDrawable(R.drawable.vector_debts),"23",false);

        addAll(Arrays.asList(item1,item2,item3,item4,item5,item6,item7,item8));
    }


    private Drawable getDrawable(@DrawableRes int res){
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.KITKAT)
            return context.getResources().getDrawable(res);
        else return context.getResources().getDrawable(res,context.getTheme());
    }

    //Important thins, if we pass into this method only inflater,
    // items in our recyclerview will not be match_parent
    @Override
    protected BindableHolder getItemHolder(LayoutInflater inflater, ViewGroup viewGroup) {
        return  new DrawerItemHolder(MaterialDrawerItemPrimaryBinding.inflate(inflater,viewGroup,false));
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
        switch (position){
            case 6: context.startActivity(new Intent(context, WalletsActivity.class));
        }
    };

}
