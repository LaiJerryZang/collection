package tw.com.collection.mvp.item;

import android.view.View;

import tw.com.collection.basic.base.MultiTypeAdapter;

public class BaseItem implements MultiTypeAdapter.IItem {

    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    public int getVariableId() {
        return 0;
    }

    ////////////////////////////////////////////
    // handle event
    private View.OnClickListener onClickListener;

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

}
