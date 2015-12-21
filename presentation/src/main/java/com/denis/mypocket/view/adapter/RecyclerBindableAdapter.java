package com.denis.mypocket.view.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.denis.mypocket.view.adapter.viewholder.BindableHolder;

import java.util.ArrayList;
import java.util.List;


public abstract class RecyclerBindableAdapter<T,VH extends BindableHolder> extends RecyclerView.Adapter<VH> {

    public static final int TYPE_HEADER = 10;
    public static final int TYPE_FOOTER = 20;
    public static final int TYPE_ITEM = 30;
    public Context context;
    public LayoutInflater inflater;


    private List<T> items = new ArrayList<>();
    private List<ViewDataBinding> headers = new ArrayList<>();

    //region add section
    public void add(int position, T item){
        items.add(position,item);
        notifyItemInserted(position);
        int positionStart = position + getHeadersCount();
        int itemCount = items.size() - position;
        notifyItemRangeChanged(positionStart,itemCount);
    }

    public void add(T item){
        items.add(item);
        notifyItemInserted(items.size()-1+getHeadersCount());
    }

    public void addAll(int position, List<? extends T> items){
        final int size = this.items.size();
        this.items.addAll(items);
        notifyItemRangeInserted(position+size+getHeadersCount(),items.size() - position);
    }
    public void addAll(List<? extends T> items) {
        final int size = this.items.size();
        this.items.addAll(items);
        notifyItemRangeInserted(size + getHeadersCount(), items.size());
    }

    public void set(int position, T item){
        items.set(position, item);
        notifyItemChanged(position + getHeadersCount());
    }
    //endregion

    //region remove section
    public void removeChild(int position){
        items.remove(position);
        notifyItemRemoved(position+getHeadersCount());
        int positionStart = position + getHeadersCount();
        int itemCount = items.size() - position;
        notifyItemRangeInserted(positionStart,itemCount);
    }
    public void clear() {
        final int size = items.size();
        items.clear();
        notifyItemRangeRemoved(getHeadersCount(), size);
    }
    //endregion

    //region getters
    public List<T> getItems() {
        return items;
    }

    public int getHeadersCount() {
        return headers.size();
    }

    @Override
    public int getItemCount() {
        return items.size()+getHeadersCount();
    }

    public T getItem(int position){
        return items.get(position);
    }
    //endregion


    //add a header to the adapter
    public void addHeader(ViewDataBinding header) {
        if (!headers.contains(header)) {
            headers.add(header);
        }
    }

    public void removeHeader(int position){
        headers.remove(position);
        notifyItemRemoved(position); //need to check
    }


    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==TYPE_HEADER){
            return getHeaderHolder(inflater, parent);
        }else if(viewType==TYPE_FOOTER){
            return getFooterHolder(inflater,parent);
        }else return getItemHolder(inflater,parent);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        this.context = recyclerView.getContext();
        this.inflater = LayoutInflater.from(context);
    }

    /**
     * Get ViewHolder to pass into the onCreateViewHolder
     * @return
     */
    protected abstract VH getItemHolder(LayoutInflater inflater, ViewGroup viewGroup);
    protected abstract VH getHeaderHolder(LayoutInflater inflater, ViewGroup viewGroup);
    protected abstract VH getFooterHolder(LayoutInflater inflater, ViewGroup viewGroup);



}
