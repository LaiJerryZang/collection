package tw.com.collection.basic.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import tw.com.collection.basic.view.LoadingDialog;


public abstract class BaseActivity<DB extends ViewDataBinding> extends AppCompatActivity {

    protected DB dataBinding;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = setLayout();
        setContentView(layoutId);
        dataBinding = initDataBinding(layoutId);
        initView();
        initData();
    }

    /**
     * 設定layout
     */
    protected abstract int setLayout();

    /**
     * 初始化DataBinding
     */
    protected DB initDataBinding(@LayoutRes int layoutId) {
        return DataBindingUtil.setContentView(this, layoutId);
    }

    /**
     * 初始化view
     */
    protected abstract void initView();

    /**
     * 加載data
     */
    protected abstract void initData();

    /**
     * 等待dialog
     *
     * @param msg 提示信息
     */
    protected void showDialog(String msg) {
        loadingDialog = new LoadingDialog(this);
        loadingDialog.setLoadingMsg(msg);
        loadingDialog.show();
    }

    /**
     * 隱藏dialog
     */
    protected void dismissDialog() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        if (dataBinding != null) {
            dataBinding.unbind();
        }
        super.onDestroy();
    }
}
