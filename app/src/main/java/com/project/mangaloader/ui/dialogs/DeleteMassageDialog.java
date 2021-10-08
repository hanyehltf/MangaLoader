package com.project.mangaloader.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.project.mangaloader.R;
import com.project.mangaloader.data.database.CreateDB;
import com.project.mangaloader.data.model.Manga;
import com.project.mangaloader.ui.adaptor.list.LibraryRecyclerViewAdapter;

import java.util.List;

public class DeleteMassageDialog {


    private Context context;
    private CreateDB createDB;
    private Manga manga;

    public DeleteMassageDialog(Context context, List<Manga> mangaList, CreateDB createDB, Manga manga, LibraryRecyclerViewAdapter adapter) {

        this.context = context;
        this.createDB = createDB;
        this.manga = manga;
        Dialog dialog = new Dialog(context);

        dialog.setContentView(R.layout.delete_massage_dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.TOP);
        Button yes = (Button) dialog.getWindow().findViewById(R.id.yes_del);
        Button no = (Button) dialog.getWindow().findViewById(R.id.no_del);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDB.deleteFromLib(manga);
                mangaList.remove(manga);
                adapter.notifyDataSetChanged();
                adapter.deleteItem(manga);
                dialog.dismiss();

            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
}
