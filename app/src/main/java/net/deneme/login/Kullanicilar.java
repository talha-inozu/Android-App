package net.deneme.login;

public class Kullanicilar {
    private String kullaniciadi;
    private String emailler;
    private Integer sanslisayi;
    private String sifreler;
    private int oturum = 0;

    public int getOturum() {
        return oturum;
    }


    public Kullanicilar(String kullaniciadi, String emailler, Integer sanslisayi, String sifreler,Integer oturum) {
        this.oturum = oturum;
        this.kullaniciadi = kullaniciadi;
        this.emailler = emailler;
        this.sanslisayi = sanslisayi;
        this.sifreler = sifreler;
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
