package tw.com.collection.mvp.item;

import tw.com.collection.R;
import tw.com.collection.basic.base.MultiTypeAdapter;
import tw.com.collection.basic.utils.CommonUtil;

public class TextItem extends BaseItem {
    private String content;

    @Override
    public int getLayout() {
        return R.layout.item_text;
    }

    public TextItem(String content, MultiTypeAdapter adapter) {
        this.content = content;
        setOnClickListener(v -> CommonUtil.showToast(v.getContext(),String.valueOf(adapter.findPos(this))));
    }

    public String getContext(){return content;}
}
