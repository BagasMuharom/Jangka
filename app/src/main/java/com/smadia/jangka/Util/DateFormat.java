package com.smadia.jangka.Util;


public class DateFormat {

    private int tanggal;

    private int bulan;

    private String bulanString;

    private int tahun;

    @Override
    public String toString() {
        return this.tanggal + ' ' + this.bulanString + ' ' + this.tahun;
    }

}
