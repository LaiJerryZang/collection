package tw.com.collection.mvp.second;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

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
        dataBinding.tvTitle.setText(intent.getStringExtra("title"));
//        try {
//            secondPresenter.getImage(openFileInput(intent.getStringExtra("image")));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        dataBinding.tvDescription.setText(intent.getStringExtra("description"));

        setImageBackground(intent.getStringExtra("imageUrl"),dataBinding.iv);
        setImageBackground(intent.getStringExtra("imageUrl"),dataBinding.kbv);
    }

    @Override
    protected void initData() {

    }

//    @Override
//    public void setImageBackground(Bitmap img) {
//        dataBinding.iv.setImageBitmap(img);
//        dataBinding.kbv.setImageBitmap(img);
//    }

    @Override
    public void Error(String exception) {
        CommonUtil.showToast(this,exception);
    }

    public void setImageBackground(String url, ImageView view){
        Glide.with(this)
                .load(url)
                .error(getDrawable(R.drawable.img_placeholder))
                .placeholder(getDrawable(R.drawable.img_placeholder))
                .into(view);
    }
}
