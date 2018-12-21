package com.example.rache.triptipper;

import android.os.Environment;

import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class ListItems {
    public List<Item> items;
    String data;

    public ListItems() {
        items = new ArrayList<>();

    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public void writeData(String data) {
        File file = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS);

        if (!file.exists()) file.mkdirs();

        if (!isExternalStorageWritable() && isExternalStorageReadable()) return;
        FileOutputStream outputStream;
        file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "destinations.txt");
        try {
            if (file.exists()) {
                System.out.println("Filename has already existed");
                if (Integer.parseInt(String.valueOf(file.length())) == data.length()) {
                    System.out.println("no new file");
                    return;
                }
                System.out.println(" file size " + file.length() + " data size" + data.length());
            }
            outputStream = new FileOutputStream(file);
            outputStream.write(data.getBytes());
            outputStream.close();
            System.out.println("Override Download/data.txt !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readData() {
        if (!isExternalStorageReadable()) return null;
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS), "destinations.txt");
        if (!file.exists()) {
            System.out.println("File doesn't exist");
            return null;
        }

        FileInputStream inputStream;
        String tmp = "";
        try {
            inputStream = new FileInputStream(file);
            byte[] buff = new byte[Integer.parseInt(String.valueOf(file.length()))];
            inputStream.read(buff);
            System.out.println("reading file");
            tmp = new String(buff);
            System.out.println("read file " + tmp);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmp;
    }

}