package com.example.demo;

public class TestToIndex {
    public static void main(String[] args)throws Exception{
        StackSort sign = new StackSort();
        OneThread one = new OneThread(sign);
        TwoThread two=new TwoThread(sign);
        one.start();
        two.start();
    }
}
class StackSort{
    private int index=0;
    private char[] data=new char[20];
    void push(char c){
        data[index]=c;
        index++;
    }
    char pop(){
        char c=data[index];
        index--;
        return c;
    }
    int getIndex(){
        return this.index;
    }
    char[] getData(){
        return this.data;
    }
}
class OneThread extends Thread {
    StackSort sign;
    OneThread (StackSort sign){
        this.sign=sign;
    }
    public void run(){
        sign.push('a');
        sign.push('b');
        sign.push('c');
        sign.push('d');
        sign.push('e');
        System.out.print("OneThread:");
        for(int i=0;i<sign.getIndex();i++){
            System.out.print(sign.getData()[i]+" ");
        }
        System.out.println("index:"+sign.getIndex());
    }
}
class TwoThread extends Thread{
    StackSort sign;
    TwoThread (StackSort sign){
        this.sign=sign;
    }
    public void run(){
        System.out.print("pop:");
        sign.push('f');
        sign.push('g');
        System.out.print(sign.pop()+" ");
        sign.push('h');
        sign.push('i');
        System.out.println(sign.pop());
        sign.push('j');
        if(sign.pop()=='j')System.out.println("true");
        System.out.print("TwoThread:");
        for(int i=0;i<=sign.getIndex();i++){
            System.out.print(sign.getData()[i]+" ");
        }
    }
}
