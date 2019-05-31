package tw.com.collection.mvp.presenter;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.badoo.mobile.util.WeakHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import tw.com.collection.basic.base.MultiTypeAdapter;
import tw.com.collection.basic.http.IDataCallListener;
import tw.com.collection.mvp.activity.MainViewContract;
import tw.com.collection.mvp.item.ImageItem;
import tw.com.collection.mvp.item.TextItem;
import tw.com.collection.mvp.model.MainModel;

public class MainPresenter {
    private MainViewContract mainViewContract;
    private MultiTypeAdapter adapter = new MultiTypeAdapter();
    private MainModel mainModel = new MainModel();

    public MainPresenter(MainViewContract mainViewContract) {
        this.mainViewContract = mainViewContract;
    }

    public MultiTypeAdapter getAdapter() {
        return adapter;
    }

    public void reLoadData(){
        addItem();
    }

    private void addItem(){
        mainModel.getOpenData(new IDataCallListener() {
            @Override
            public void onSuccess(String response) {
                adapter.clearItems();
                new Thread(() -> {
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String title = jsonObject.optString("title","not title");
                            String url = jsonObject.optString("imageUrl", "not img");
//                                adapter.addItem(new TextItem(title,adapter));
//                                adapter.addItem(new ImageItem(url,adapter));
                            weakHandler.sendMessage(setMessage(0,title));
                            weakHandler.sendMessage(setMessage(1,url));
                        }
                        weakHandler.sendMessage(setMessage(2,""));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }).start();
            }

            @Override
            public void onFailure(String response) {
                mainViewContract.Error(response);
            }

            @Override
            public void start() {
                mainViewContract.startProgress();
            }

            @Override
            public void end() {
                mainViewContract.endProgress();
            }
        });
    }

    private WeakHandler weakHandler = new WeakHandler(msg -> {
        switch (msg.what){
            case 0:
                adapter.addItem(new TextItem(msg.obj.toString(),adapter));
                break;
            case 1:
                adapter.addItem(new ImageItem(msg.obj.toString(),adapter));
                break;
            case 2:
                adapter.notifyDataSetChanged();
                mainViewContract.setRefreshing(false);
                break;
        }
        return true;
    });

    private Message setMessage(int what, Object obj){
        Message message = Message.obtain();
        message.what = what;
        message.obj = obj;
        return message;
    }
}
