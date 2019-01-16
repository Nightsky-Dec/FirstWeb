package com.example.demo.domain;

import java.util.ArrayList;

public class FileSaver {

    private ArrayList<Object> porvince;
    private Object city;
    private Object district;

    public FileSaver() {}

    public FileSaver(ArrayList<Object> porvince) {
        this.porvince = porvince;
    }

    public FileSaver(ArrayList<Object> porvince, Object city) {
        this.porvince = porvince;
        this.city = city;
    }

    public FileSaver(ArrayList<Object> porvince, Object city, Object district) {
        this.porvince = porvince;
        this.city = city;
        this.district = district;
    }

    public ArrayList<Object> getPorvince() { return porvince; }
    public void setPorvince(ArrayList<Object> porvince) {
        this.porvince = porvince;
    }

    public Object getCity() { return city; }
    public void setCity(Object city) {
        this.city = city;
    }

    public Object getDistrict() { return district; }
    public void setDistrict(Object district) {
        this.district = district;
    }

    @Override
    public String toString() {
        return "{" +
                "porvince='" + porvince + "\'" +
                ", city='" + city + "\'" +
                ", district='" + district + "\'" +
                "}";
    }


//    public Object getMapFile() {
////      读取本地json文件
////      1.创建文件对象
//        File fromFile = new File("file/json/map.json");
////      2.创建字符输入流
//        Reader reader = null;
//        try {
//            reader = new FileReader(fromFile);
//            // 3.循环读取（打印）
//            int content = reader.read();
//            while (content != -1) {
//                content = reader.read();
////                System.out.print((char) content);
//                this.file = content;
//                System.out.print(this.file);
//            }
//            System.out.print((char) content);
//            return content;
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            // 4.关闭流
//            try {
//                reader.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.print(this.file);
//        return this.file;
//    }
}
