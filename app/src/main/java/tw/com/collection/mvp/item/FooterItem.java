package tw.com.collection.mvp.item;

import android.util.Log;

import tw.com.collection.R;

public class FooterItem extends BaseItem{
    public final static int NO_STATE = -1;
    public final static int LOADING = 0;
    public final static int ERROR = 1;
    public final static int NO_MORE = 2;

    private int state = NO_STATE;
    private int position;

    @Override
    public int getLayout() {
        return R.layout.item_footer;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public FooterItem setState(int state) {
        this.state = state;
        return this;
    }

    //加載狀態
    public boolean isLoading() {
        return state == LOADING;
    }

    //錯誤狀態
    public boolean isError() {
        return state == ERROR;
    }

    //無更多資料狀態
    public boolean isNoMore() {
        return state == NO_MORE;
    }
}
