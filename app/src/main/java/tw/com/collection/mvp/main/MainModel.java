package tw.com.collection.mvp.main;

import androidx.databinding.BaseObservable;

import tw.com.collection.basic.http.HttpClient;
import tw.com.collection.basic.http.IDataCallListener;

public class MainModel extends BaseObservable {
    public void getOpenData(IDataCallListener listener){
        HttpClient.get("https://cloud.culture.tw/frontsite/trans/SearchShowAction.do?method=doFindTypeJ&category=6", null, listener);
    }
}
