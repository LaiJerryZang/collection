package tw.com.collection.basic.base;

import android.view.View;

import tw.com.collection.basic.utils.CommonUtil;

public abstract class OnMultiClickListener implements View.OnClickListener {

    public abstract void onMultiClick(View v);

    @Override
    public void onClick(View view) {
        if(CommonUtil.isFastToClick()){
            onMultiClick(view);
        }
    }
}
