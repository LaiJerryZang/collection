package tw.com.collection.mvp.second;

import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.TextView;
import java.io.FileNotFoundException;
import tw.com.collection.R;
import tw.com.collection.basic.base.BaseActivity;
import tw.com.collection.basic.utils.CommonUtil;
import tw.com.collection.databinding.ActivitySecondBinding;

public class SecondActivity extends BaseActivity<ActivitySecondBinding> implements SecondViewContract{
    private SecondPresenter secondPresenter = new SecondPresenter(this);

    @Override
    protected int setLayout() {
        return R.layout.activity_second;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        dataBinding.cToolbarLy.setTitle(intent.getStringExtra("title"));
        try {
            secondPresenter.getImage(openFileInput(intent.getStringExtra("image")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        dataBinding.tv.setText(intent.getStringExtra("description"));
    }

    @Override
    protected void initData() {

    }

    @Override
    public void setImageBackground(Bitmap img) {
        dataBinding.iv.setImageBitmap(img);
    }

    @Override
    public void Error(String exception) {
        CommonUtil.showToast(this,exception);
    }

    @Override
    protected void onDestroy() {
        secondPresenter.Destroy();
        super.onDestroy();
    }

}
