public class Patient {
    private String imie, nazwisko, pesel, ubezpieczenie;
    private char plec;
    private int dzien_badania;
    private int miesiac_badania;
    private int rok_badania;
    private int HDL;
    private int LDL;
    private int trojglic;
    private boolean czy_badany;

    public Patient(String imie, String nazwisko, String pesel, String ubezpieczenie, char plec){
        this.imie = imie;
        this.nazwisko=nazwisko;
        this.pesel=pesel;
        this.ubezpieczenie=ubezpieczenie;
        this.plec=plec;
        this.czy_badany=false;
    }





    //GETTERY

    public String[] getPatientAsArray(){
        String[] patientArray = new String[5];
        patientArray[0]=imie+" "+nazwisko;
        patientArray[1]=Character.toString(plec);
        patientArray[2]=pesel;
        patientArray[3]=ubezpieczenie;
        patientArray[4]=Boolean.toString(czy_badany);
        return patientArray;
    }




    public boolean isCzy_badany() {
        return czy_badany;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public String getUbezpieczenie() {
        return ubezpieczenie;
    }

    public char getPlec() {
        return plec;
    }

    public int getDzien_badania() {
        return dzien_badania;
    }

    public int getMiesiac_badania() {
        return miesiac_badania;
    }

    public int getRok_badania() {
        return rok_badania;
    }

    public int getHDL() {
        return HDL;
    }

    public int getLDL() {
        return LDL;
    }

    public int getTrojglic() {
        return trojglic;
    }

    //SETTERY

    public void setCzy_badany(boolean czy_badany) {
        this.czy_badany = czy_badany;
    }

    public void setImie(String imie){
        this.imie=imie;
    }

    public void setNazwisko(String nazwisko){
        this.nazwisko=nazwisko;
    }

    public void setPesel(String pesel){
        this.pesel=pesel;
    }

    public void setUbezpieczenie(String ubezpieczenie){
        this.ubezpieczenie=ubezpieczenie;
    }

    public void setPlec(char plec){
        this.plec=plec;
    }

    public void setDzien_badania(int dzien_badania) {
        this.dzien_badania = dzien_badania;
    }

    public void setMiesiac_badania(int miesiac_badania){
        this.miesiac_badania=miesiac_badania;
    }

    public void setRok_badania(int rok_badania){
        this.rok_badania=rok_badania;
    }

    public void setHDL(int HDL){
        this.HDL=HDL;
    }

    public void setLDL(int LDL){
        this.LDL=LDL;
    }

    public void setTrojglic(int trojglic) {
        this.trojglic = trojglic;
    }
}
