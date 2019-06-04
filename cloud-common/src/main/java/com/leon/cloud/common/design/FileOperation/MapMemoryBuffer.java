package com.leon.cloud.common.design.FileOperation;

import sun.misc.Cleaner;

import java.io.*;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MapMemoryBuffer {

    public static void main(String[] args) throws IOException {
//        produceData();
        subFile();
    }


    public static void NioCopyFile(String sourcePath, String targetPath) throws Exception {
        long before = System.currentTimeMillis();

        File files = new File(sourcePath);  //源文件
        File filet = new File(targetPath);  //目标文件

        long size = files.length();         // 文件总大小
        long copyCount = size * 2 / Integer.MAX_VALUE; //获取读、写之和所占用虚拟内存 倍数
        int copyNum = copyCount >= 1 ? (int) copyCount + 2 : (int) copyCount + 1; // 根据倍数确认分割份数

        long countSize = Integer.MAX_VALUE / copyNum;  //每块分割大小<每次读写的大小>
        long lontemp = countSize;       //初始读、写大小
        FileChannel channels = new RandomAccessFile(files, "r").getChannel();       //得到映射读文件的通道
        FileChannel channelt = new RandomAccessFile(filet, "rw").getChannel();      //得到映射写文件的通道

        long j = 0; // 每次循环累加字节的起始点
        MappedByteBuffer mbbs = null; // 声明读源文件对象
        MappedByteBuffer mbbt = null; // 声明写目标文件对象
        while (j < size) {
            mbbs = channels.map(FileChannel.MapMode.READ_ONLY, j, lontemp);     //每次读源文件都重新构造对象
            mbbt = channelt.map(FileChannel.MapMode.READ_WRITE, j, lontemp);    //每次写目标文件都重新构造对象
            for (int i = 0; i < lontemp; i++) {
                byte b = mbbs.get(i);           //从源文件读取字节
                mbbt.put(i, b);                 //把字节写到目标文件中
            }
//            System.gc();                //手动调用 GC       <必须的，否则出现异常>
//            System.runFinalization();   //运行处于挂起终止状态的所有对象的终止方法。<必须的，否则出现异常>
            clean(mbbs);
            clean(mbbt);
            j += lontemp;               //累加每次读写的字节
            lontemp = size - j;         //获取剩余字节
            lontemp = lontemp > countSize ? countSize : lontemp; //如果剩余字节 大于 每次分割字节 则 读取 每次分割字节 ，否则 读取剩余字节
        }
        System.out.println("MillTime : "
                + (double) (System.currentTimeMillis() - before) / 1000 + "s");
    }


    public static void clean(final MappedByteBuffer buffer) throws Exception {
        if (buffer == null) {
            return;
        }
        buffer.force();
        //Privileged特权
        AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
            try {
                System.out.println(buffer.getClass().getName());
                Method getCleanerMethod = buffer.getClass().getMethod("cleaner", new Class[0]);
                getCleanerMethod.setAccessible(true);
                Cleaner cleaner = (Cleaner) getCleanerMethod.invoke(buffer, new Object[0]);
                cleaner.clean();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        });
    }


    //生成数据
    public static void produceData() throws IOException {
        long startTime = System.currentTimeMillis();
        File file = new File("e://data//data.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fos);
        String separator = System.getProperty("line.separator");
        Random random = new Random();
        StringBuilder context = new StringBuilder();
        context.append("10.19.152.").append(random.nextInt(256)).append("  ").append(random.nextInt(10)).append(separator);
        byte[] bytes = new byte[1024 * 1024];
        int count = 0;
        for (int i = 0; i < 200000000; i++) {
            if (count == 1024) {
                count = 0;
                bufferedOutputStream.write(bytes);
                bufferedOutputStream.flush();
            }
            context.append("10.19.152.").append(random.nextInt(256)).append("  ").append(random.nextInt(10)).append(separator);
            bytes = context.toString().getBytes();
            count++;
        }
        fos.close();
        bufferedOutputStream.close();
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) / 1000);
    }

    //多线程切割文件
    public static void subFile(){
        File file=new File("e://data//data.txt");
        long fileSize=file.length();
        //开启四个线程切割文件
        int files=10;
        int threadSize=4;
        ExecutorService executorService= Executors.newFixedThreadPool(threadSize);
        long index=fileSize/files;

        for(int i=0;i<files;i++){
            long indexBegin=index*i;
            long indexEnd=index*(i+1);
            if(i+1==threadSize){
                indexEnd=fileSize;
            }
            executorService.submit(new FileThread(indexBegin,indexEnd,file));
        }
    }



}
