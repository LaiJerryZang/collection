package tw.com.collection.mvp.main;

import com.badoo.mobile.util.WeakHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import tw.com.collection.basic.base.MultiTypeAdapter;
import tw.com.collection.basic.http.IDataCallListener;
import tw.com.collection.mvp.main.item.FooterItem;
import tw.com.collection.mvp.main.item.ImageItem;
import tw.com.collection.mvp.main.item.TextItem;

public class MainPresenter {
    private MainViewContract mainViewContract;
    private MultiTypeAdapter adapter = new MultiTypeAdapter();
    private MainModel mainModel = new MainModel();
    private List<Map<String, String>> data = new ArrayList<>();
    private WeakHandler weakHandler = new WeakHandler();
    private FooterItem footerItem = new FooterItem();

    private static int LAST_DATA_POS = 0;
    private static final int MORE_DATA_QTY = 8;

    public MainPresenter(MainViewContract mainViewContract) {
        this.mainViewContract = mainViewContract;
        footerItem.setOnClickListener(v -> {
            footerItem.setState(FooterItem.NO_STATE);
            adapter.removeItem(footerItem);
            adapter.notifyDataSetChanged();
            loadMoreData();
        });
    }

    public MultiTypeAdapter getAdapter() {
        return adapter;
    }

    //加載資料
    public void loadData(boolean isRefresh) {
        footerItem.setState(FooterItem.NO_STATE);
        mainModel.getOpenData(new IDataCallListener() {
            @Override
            public void onSuccess(String response) {
                new Thread(() -> {
                    try {
                        adapter.clearItems();
                        data.clear();
                        LAST_DATA_POS = 0;
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Map<String, String> map = new HashMap<>();
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String title = jsonObject.optString("Name", "not title");
                            String url = jsonObject.optString("PicURL", "not img");
                            String description = jsonObject.optString("FoodFeature","no description");
                            map.put("title", title);
                            map.put("imageUrl", url);
                            map.put("description",description);
                            data.add(map);
                        }
                        addDataItem();
                        weakHandler.post(() -> {
                            adapter.notifyDataSetChanged();
                            mainViewContract.setRefreshing(false);
                            if (!isRefresh) mainViewContract.endProgress();
                            mainViewContract.setViewTouch(true);
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }).start();
            }

            @Override
            public void onFailure(String response) {
                mainViewContract.Error(response);
                mainViewContract.setRefreshing(false);
                if (!isRefresh) mainViewContract.endProgress();
            }

            @Override
            public void start() {
                if (!isRefresh) mainViewContract.startProgress();
                mainViewContract.setViewTouch(false);
            }

            @Override
            public void end() {

            }
        });
    }

    //加載更多
    public void loadMoreData() {
        if (footerItem.isNoMore() || footerItem.isLoading() || footerItem.isError() || LAST_DATA_POS > data.size()-1) {
            return;
        }
        setFooterItems();
    }

    private void setFooterItems() {
//         result = 0, network error
//         result = 1, empty
//         result = 2, last page data
//         result = 3 and other, normal result
        int resultType = (new Random()).nextInt(100) % 5;
        if (resultType == 0) {
            adapter.addItem(footerItem.setState(FooterItem.ERROR));
            adapter.notifyDataSetChanged();
        } else if (resultType == 1) {
            footerItem.setState(FooterItem.NO_MORE);
        } else {
            adapter.addItem(footerItem.setState(FooterItem.LOADING));
            adapter.notifyDataSetChanged();
            weakHandler.postDelayed(() -> {
                adapter.removeItem(footerItem);
                addDataItem();
                adapter.notifyDataSetChanged();
                footerItem.setState(FooterItem.NO_STATE);
            }, 500);
        }
    }

    private void addDataItem() {
        for (int i = LAST_DATA_POS; i < LAST_DATA_POS + MORE_DATA_QTY; i++) {
            if(i <= data.size()-1) {
                adapter.addItem(new TextItem(data.get(i).get("title"), adapter));
                adapter.addItem(new ImageItem(data.get(i), adapter));
            }
        }
        LAST_DATA_POS += MORE_DATA_QTY;
    }
}
