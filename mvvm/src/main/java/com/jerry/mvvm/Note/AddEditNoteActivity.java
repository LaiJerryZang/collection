package com.jerry.mvvm.Note;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jerry.mvvm.R;
import com.jerry.mvvm.databinding.AddNoteActivityBinding;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_ID =
            "com.jerry.mvvm.Note.EXTRA_ID";
    public static final String EXTRA_TITLE =
            "com.jerry.mvvm.Note.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION =
            "com.jerry.mvvm.Note.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY =
            "com.jerry.mvvm.Note.EXTRA_PRIORITY";


    private AddNoteActivityBinding dataBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataBinding = DataBindingUtil.setContentView(this, R.layout.add_note_activity);

        dataBinding.numberPickerPriority.setMinValue(1);
        dataBinding.numberPickerPriority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        Intent intent = getIntent();

        if(intent.hasExtra(EXTRA_ID)){
            setTitle("Edit Note");
            dataBinding.editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            dataBinding.editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            dataBinding.numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        }else {
            setTitle("Add Note");
        }
    }

    private void saveNote() {
        String title = dataBinding.editTextTitle.getText().toString();
        String description = dataBinding.editTextDescription.getText().toString();
        int priority = dataBinding.numberPickerPriority.getValue();

        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this,"請新增 title 和 描述",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if(id != -1){
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK,data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
