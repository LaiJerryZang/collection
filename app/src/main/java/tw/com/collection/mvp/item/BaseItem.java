package tw.com.collection.mvp.item;

import android.view.View;

import tw.com.collection.BR;
import tw.com.collection.basic.base.MultiTypeAdapter;

public abstract class BaseItem implements MultiTypeAdapter.IItem {

    @Override
    public int getVariableId() {
        return BR.item;
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
