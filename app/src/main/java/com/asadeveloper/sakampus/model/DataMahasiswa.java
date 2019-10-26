package com.asadeveloper.sakampus.model;

public class DataMahasiswa {
    String nama, alamat, gender, hobi, nim;

    public DataMahasiswa(){}

    public DataMahasiswa(String nama, String alamat, String gender, String hobi) {
        this.nama = nama;
        this.alamat = alamat;
        this.gender = gender;
        this.hobi = hobi;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHobi() {
        return hobi;
    }

    public void setHobi(String hobi) {
        this.hobi = hobi;
    }
}
