package net.deneme.login;

public class Kullanicilar {
    private String kullaniciadi;
    private String emailler;
    private Integer sanslisayi;
    private String sifreler;
    private int oturum = 0;
    private String lastPDF = "";
    private int lastPage = 0;

    public int getLastPage() {
        return lastPage;
    }

    public String getLastPDF() {
        return lastPDF;
    }

    public int getOturum() {
        return oturum;
    }


    public Kullanicilar(String kullaniciadi, String emailler, Integer sanslisayi, String sifreler,Integer oturum,String lastPDFUri,Integer lastPage) {
        this.oturum = oturum;
        this.kullaniciadi = kullaniciadi;
        this.emailler = emailler;
        this.sanslisayi = sanslisayi;
        this.sifreler = sifreler;
        this.lastPDF = lastPDFUri;
        this.lastPage = lastPage;
    }
    public Kullanicilar(String kullaniciadi, String emailler, Integer sanslisayi, String sifreler) {
        this.kullaniciadi = kullaniciadi;
        this.emailler = emailler;
        this.sanslisayi = sanslisayi;
        this.sifreler = sifreler;
    }



    public String getKullaniciadi() {
        return kullaniciadi;
    }

    public String getEmailler() {
        return emailler;
    }

    public Integer getSanslisayi() {
        return sanslisayi;
    }

    public String getSifreler() {
        return sifreler;
    }
}
