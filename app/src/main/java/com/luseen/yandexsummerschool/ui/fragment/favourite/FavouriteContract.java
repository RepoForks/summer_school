package com.luseen.yandexsummerschool.ui.fragment.favourite;

import com.luseen.yandexsummerschool.base_mvp.api.ApiContract;
import com.luseen.yandexsummerschool.model.History;

import java.util.List;

import io.realm.RealmResults;

/**
 * Created by Chatikyan on 04.04.2017.
 */

public interface FavouriteContract {

    interface View extends ApiContract.View {

        void onFavouriteResult(List<History> favouriteList);

        void onEmptyResult();

        void onEmptySearchResult();
    }

    interface Presenter extends ApiContract.Presenter<View> {

        void fetchFavourite();

        void doSearch(String input);

        void resetFavourite();

        void decideFavouriteFetching(String searchText);
    }
}
