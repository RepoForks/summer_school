package com.luseen.yandexsummerschool.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.luseen.yandexsummerschool.R;
import com.luseen.yandexsummerschool.model.History;
import com.luseen.yandexsummerschool.ui.adapter.view_holder.HistoryAndFavouriteViewHolder;

import java.util.List;

import io.realm.Realm;

/**
 * Created by Chatikyan on 25.03.2017.
 */

public class HistoryAndFavouriteRecyclerAdapter extends RecyclerView.Adapter {

    public interface AdapterItemClickListener {

        void onAdapterItemClick(History history);

        void onFavouriteClicked(boolean isFavourite, String identifier);
    }

    private AdapterItemClickListener adapterItemClickListener;
    private List<History> historyList;

    public HistoryAndFavouriteRecyclerAdapter(List<History> historyList) {
        this.historyList = historyList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.history_and_favourite_item, parent, false);
        HistoryAndFavouriteViewHolder holder = new HistoryAndFavouriteViewHolder(inflatedView);

        holder.itemView.setOnClickListener(__ -> {
            int position = holder.getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                History history = historyList.get(position);
                if (adapterItemClickListener != null) {
                    adapterItemClickListener.onAdapterItemClick(history);
                }
            }
        });

        holder.getFavouriteIcon().setOnClickListener(v -> {
            int position = holder.getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                //Saving favourite state
                History history = historyList.get(position);
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                history.setFavourite(!history.isFavourite());
                realm.commitTransaction();
                realm.close();

                if (history.isFavourite()) {
                    holder.getFavouriteIcon().setImageResource(R.drawable.bookmark_check);
                } else {
                    holder.getFavouriteIcon().setImageResource(R.drawable.bookmark_outline);
                }

                if (adapterItemClickListener != null) {
                    adapterItemClickListener.onFavouriteClicked(history.isFavourite(), history.getIdentifier());
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        History history = historyList.get(holder.getAdapterPosition());
        HistoryAndFavouriteViewHolder viewHolder = ((HistoryAndFavouriteViewHolder) holder);
        viewHolder.bind(history);
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public void updateAdapterList(List<History> historyList) {
        this.historyList = historyList;
        notifyDataSetChanged();

        //It is not works as i expected, so comment it ...
//        DiffCallback diffCallback = new DiffCallback(this.historyList, historyList);
//        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
//        diffResult.dispatchUpdatesTo(this);
    }

    public void setAdapterItemClickListener(AdapterItemClickListener adapterItemClickListener) {
        this.adapterItemClickListener = adapterItemClickListener;
    }
}
