package com.leon.cloud.common.design.FileOperation;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileThread implements Runnable{
    private File file;
    private long indexBegin;
    private long indexEnd;

    public FileThread(long indexBegin,long indexEnd,File file){
        this.indexBegin=indexBegin;
        this.indexEnd=indexEnd;
        this.file=file;
    }

    //读取对应index位置的下一行数据
    @Override
    public void run() {
        RandomAccessFile raf;
        try {
            raf=new RandomAccessFile(file,"r");


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
