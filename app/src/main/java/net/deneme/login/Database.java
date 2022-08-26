package net.deneme.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final String veritabani_adi = "veritabani_kullanici";
    private static final String kullanici_tablosu= "tbl_Kullanici";
    private static final int veritabani_versiyon = 1 ;


    public Database( Context context) {
        super(context, veritabani_adi, null, veritabani_versiyon);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql_kullaniciTablosuOlusturma = "CREATE TABLE " + kullanici_tablosu + "( Adi TEXT ,Email TEXT,Saki INTEGER,Siffre TEXT)";
        db.execSQL(sql_kullaniciTablosuOlusturma);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + kullanici_tablosu);

    }
    public  long ekleKullanici(Kullanicilar kullanici) {
        SQLiteDatabase db = this.getWritableDatabase();
        if(!this.kek(kullanici.getKullaniciadi())){

            return 0;
        }
        else{ContentValues cv = new ContentValues();
            cv.put("Adi",kullanici.getKullaniciadi());
            cv.put("Email",kullanici.getEmailler());
            cv.put("Saki",kullanici.getSanslisayi());
            cv.put("Siffre",kullanici.getSifreler());
            long id = db.insert(kullanici_tablosu, null, cv);
            return id;}

    }
    public boolean  kek (String Adi) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from tbl_Kullanici where  Adi =?",new String[]{Adi} );
        if (c.getCount()>0) return false ;
        else return true;
    }
    public String searchPass (String Adi){
         SQLiteDatabase db = this.getReadableDatabase() ;
         String query = "select Adi, Siffre from " + kullanici_tablosu ;
         Cursor cursor = db.rawQuery(query, null);
         String a, b ;
         b = "not found";

         if (cursor.moveToFirst()) {

             do{
                a = cursor.getString(0);

                 if (a.equals(Adi)){
                      b = cursor.getString(1);
                     break;
                 }
             }
             while (cursor.moveToNext());
         }
     return b;
    }

    public List<Kullanicilar> users(){
        SQLiteDatabase db = this.getReadableDatabase() ;
        String query = "SELECT * FROM " + kullanici_tablosu ;
        Cursor cursor = db.rawQuery(query, null);
        List<Kullanicilar> usersList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                usersList.add(new Kullanicilar(cursor.getString(0),
                        cursor.getString(1),
                        cursor.getInt(2),
                        cursor.getString(3)));
            } while (cursor.moveToNext());
            // moving our cursor to next.
        }
        return usersList;
    }
    public String searchKa (String Adi){
        SQLiteDatabase db = this.getReadableDatabase() ;
        String query = "select Adi, Siffre from " + kullanici_tablosu ;
        Cursor cursor = db.rawQuery(query, null);
        String a, b ;
        b = "not found";

        if (cursor.moveToFirst()) {

            do{
                a = cursor.getString(0);

                if (a.equals(Adi)){
                    b = cursor.getString(0);
                    break;
                }
            }
            while (cursor.moveToNext());
        }



        return b;
    }

   /* public String check (String Adi) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("Select * from tbl_Kullanici where  Adi=?",new String[]{Adi} );
        Integer adana = c.getColumnIndex("Siffre");
        String baba = c.getString(adana);
        return baba;
    }*/

    }



