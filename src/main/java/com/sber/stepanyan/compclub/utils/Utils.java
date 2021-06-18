package com.sber.stepanyan.compclub.utils;

public class Utils {

    // Возвращает новый номер карты
    public static String generateCardNumber(){

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(generateRandomNumber4digit()).append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // Возвращает новый номер аккаунта
    public static String generateAccountNumber(){

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            sb.append(generateRandomNumber4digit());
        }

        return sb.toString();
    }

    //генерация числа от 1000 до 9999
    static int generateRandomNumber4digit(){
        return 1000 + (int) (Math.random() * 8999);
    }


}
