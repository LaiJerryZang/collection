package tw.com.collection.mvp.main.item;

import tw.com.collection.R;
import tw.com.collection.basic.base.MultiTypeAdapter;
import tw.com.collection.basic.utils.CommonUtil;

public class TextItem extends BaseItem {
    private String content;

    private int position;

    //傳入layout 在adapter判斷viewType
    @Override
    public int getLayout() {
        return R.layout.item_text;
    }

    @Override
    public void setPosition(int position) {
        this.position = position;
    }

    public TextItem(String content, MultiTypeAdapter adapter) {
        this.content = content;
        setOnClickListener(v -> CommonUtil.showToast(v.getContext(),position+""));
    }

    //傳值給layout
    public String getContent(){return content;}
}
