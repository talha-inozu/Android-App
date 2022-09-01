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

public class PDFReader extends AppCompatActivity  {
    Button btnOpenFile,btnNext,btnPrevious;
    PdfRenderer renderer,rendy;
    Integer total_pages = 0;
    Integer display_page = 0;
    ImageView pdfPage;
    Database db;
    Intent getterIntent;
    String userName;
    Uri uri;
    public static final int PICK_FILE = 99;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_reader);
        btnOpenFile = findViewById(R.id.openFile);
        btnNext = findViewById(R.id.nextPage);
        btnPrevious = findViewById(R.id.previousPage);
        pdfPage = findViewById(R.id.imageView);

        db = new Database(getApplicationContext());
        getterIntent = getIntent();
        userName = getterIntent.getStringExtra("ka");
        if(!db.findUser(userName).getLastPDF().isEmpty()){
            uri=Uri.parse(db.findUser(userName).getLastPDF());
            display_page = db.findUser(userName).getLastPage();
            this.onActivityResult(11,Activity.RESULT_OK,null);
        }






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

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(display_page>0){
                    display_page -=1;
                    displayPage(display_page );
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

            if (data != null) {
                uri = data.getData();

            }

            try {
                ParcelFileDescriptor parcelFileDescriptor = getContentResolver()
                        .openFileDescriptor(uri, "r");
             /*   File file = new File(uri.getPath());*/
                renderer = new PdfRenderer(parcelFileDescriptor);
                total_pages = renderer.getPageCount();
                db.updatePDF(userName,uri.toString());
                displayPage(display_page);
            } catch (Throwable th){
                System.out.println(th.getMessage());

            }

        }
        if (requestcode == 11 && resultCode == Activity.RESULT_OK) {



            try {
                ParcelFileDescriptor parcelFileDescriptor = getContentResolver()
                        .openFileDescriptor(uri, "r");
                renderer = new PdfRenderer(parcelFileDescriptor);
                total_pages = renderer.getPageCount();
                db.updatePDF(userName,uri.toString());
                displayPage(display_page);
            } catch (Throwable th){
                System.out.println(th.getMessage());

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
            db.updatePage(userName,pageNumber);

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
