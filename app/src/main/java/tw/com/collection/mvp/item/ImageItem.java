package tw.com.collection.mvp.item;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import tw.com.collection.R;
import tw.com.collection.basic.base.MultiTypeAdapter;
import tw.com.collection.basic.utils.CommonUtil;

public class ImageItem extends BaseItem {
    private String url;
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

    public ImageItem(String url, MultiTypeAdapter adapter) {
        this.url = url;
        setOnClickListener(v -> CommonUtil.showToast(v.getContext(),position+""));
    }

    //傳值給layout
    public String getUrl(){return url;}

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
}
