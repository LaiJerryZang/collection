package tw.com.collection.mvp.main.item;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;

import java.io.FileOutputStream;
import java.util.Map;

import tw.com.collection.R;
import tw.com.collection.basic.base.MultiTypeAdapter;
import tw.com.collection.basic.utils.CommonUtil;
import tw.com.collection.basic.utils.ConversionUtil;
import tw.com.collection.mvp.second.SecondActivity;

public class ImageItem extends BaseItem {
    private Map<String,String> itemData;
    private int position = 0;

    //傳入layout 在adapter判斷viewType
    @Override
    public int getLayout() {
        return R.layout.item_image;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    public ImageItem(Map<String,String> itemData, MultiTypeAdapter adapter) {
        this.itemData = itemData;
        setOnClickListener(v -> {
            CommonUtil.showToast(v.getContext(), position + "");
            try {
                openActivity(v);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    //傳值給layout
    public String getUrl() {
        return itemData.get("imageUrl");
    }

    //在layout加入自訂屬性  並將layout傳入的值做處理
    @BindingAdapter({"imageUrl", "error", "placeholder"})
    public static void loadImage(ImageView imgView,
                                 String url,
                                 Drawable error,
                                 Drawable placeholder) {
        Glide.with(imgView.getContext())
                .load(url)
                .error(error)
                .placeholder(placeholder)
                .into(imgView);
    }

    private void openActivity(View view) throws Exception{
        Intent intent = new Intent(view.getContext(), SecondActivity.class);
        String filename = "bitmap.png";
        FileOutputStream stream = view.getContext().openFileOutput(filename, Context.MODE_PRIVATE);
        Drawable drawable = ((ImageView)view).getDrawable();
        Bitmap bmp = ConversionUtil.drawableToBitmap(drawable);
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        //Cleanup
        stream.close();
//        if (!bmp.isRecycled()) {
//            bmp.recycle();
//        }
        intent.putExtra("title",itemData.get("title"));
        intent.putExtra("image",filename);
        intent.putExtra("description",itemData.get("description"));
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) view.getContext(), view,view.getTransitionName());
        ActivityCompat.startActivity(view.getContext(), intent, compat.toBundle());
    }
}


