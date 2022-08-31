package net.deneme.login;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.IOException;

public class PDFReader extends AppCompatActivity  {
    Button btnOpenFile,btnNext;
    PdfRenderer renderer;
    Integer total_pages = 0;
    Integer display_page = 0;
    ImageView pdfPage;
    public static final int PICK_FILE = 99;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_reader);
        btnOpenFile = findViewById(R.id.openFile);
        btnNext = findViewById(R.id.changePage);
        pdfPage = findViewById(R.id.imageView);


        btnOpenFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("application/pdf");
                startActivityForResult(intent,PICK_FILE);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(total_pages>display_page){
                    display_page +=1;
                    displayPage(display_page);
                }


            }
        });

    }


  /*  public boolean onTouch(View v, MotionEvent event)
    {
            System.out.println(event.getAction());
            return true;
    }*/


    @Override
    public void onActivityResult(int requestcode, int resultCode, Intent data) {

        super.onActivityResult(requestcode, resultCode, data);
        if (requestcode == PICK_FILE && resultCode == Activity.RESULT_OK) {

            if (data == null) {

                return;
            }
            Uri uri = data.getData();
            try {
                ParcelFileDescriptor parcelFileDescriptor = getContentResolver()
                        .openFileDescriptor(uri, "r");
                renderer = new PdfRenderer(parcelFileDescriptor);
                total_pages = renderer.getPageCount();
                displayPage(display_page);
            } catch (FileNotFoundException fnfe){

            } catch (IOException e){

            }

        }

    }

    public void displayPage(int pageNumber){
        if (renderer != null) {
            PdfRenderer.Page page = renderer.openPage(pageNumber);
            Bitmap mBitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(), Bitmap.Config.ARGB_8888);
            page.render(mBitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
            pdfPage.setImageBitmap(mBitmap);
            page.close();

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (renderer != null){
            renderer.close();
        }
    }

}
