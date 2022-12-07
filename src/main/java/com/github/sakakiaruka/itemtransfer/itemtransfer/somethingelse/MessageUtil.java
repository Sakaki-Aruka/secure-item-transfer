package com.github.sakakiaruka.itemtransfer.itemtransfer.somethingelse;

public class MessageUtil {
    public String warn(String cause){
        String result = "§c[Item Transfer Warn] "+cause;
        return result;
    }

    public String info(String content){
        String result = "§a[Item Transfer Info] "+content;
        return result;
    }
}
