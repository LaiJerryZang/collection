package tw.com.collection.basic.base;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MultiTypeAdapter extends RecyclerView.Adapter<MultiTypeAdapter.ItemViewHolder> {

        public interface IItem {
            // get the xml layout this type item used in
            int getLayout();

            // get the variable name in the xml
            int getVariableId();
        }

        private List<IItem> items = new ArrayList<>();

        ////////////////////////////////////////////////////////
        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return ItemViewHolder.create(parent, viewType);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            holder.bindTo(items.get(position));
        }

        @Override
        public int getItemViewType(int position) {
            return items.get(position).getLayout();
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        ////////////////////////////////////////////////////////
        // default items operation
        // you also can inherit MultiTypeAdapter to implement more methods to operate items
        public List<IItem> getItems() {
            return items;
        }

        public int findPos(IItem item) {
            return items.indexOf(item);
        }

        public void setItem(IItem item) {
            clearItems();
            addItem(item);
        }

        public void setItems(List<IItem> items) {
            clearItems();
            addItems(items);
        }

        public void addItem(IItem item) {
            items.add(item);
        }

        public void addItem(IItem item, int index) {
            items.add(index, item);
        }

        public void addItems(List<IItem> items) {
            this.items.addAll(items);
        }

        public int removeItem(IItem item) {
            int pos = findPos(item);
            items.remove(item);
            return pos;
        }

        public void clearItems() {
            items.clear();
        }

        ////////////////////////////////////////////////////////
        static class ItemViewHolder extends RecyclerView.ViewHolder {
            private final ViewDataBinding binding;

            static ItemViewHolder create(ViewGroup parent, int viewType) {
                ViewDataBinding binding =
                        DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                                viewType, parent, false);
                return new ItemViewHolder(binding);
            }

            ItemViewHolder(ViewDataBinding binding) {
                super(binding.getRoot());
                this.binding = binding;
            }

            void bindTo(IItem item) {
                binding.setVariable(item.getVariableId(), item);
                binding.executePendingBindings();
            }
        }
    }

