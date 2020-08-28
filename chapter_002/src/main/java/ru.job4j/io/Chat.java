package ru.job4j.io;


import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Chat {
    public static void main(String[] args) throws IOException {
        String path = "./chapter_002/data/words.txt";
        String str;
        List<String> wordsList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            wordsList = in.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean flag = true;



         try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
             System.out.println("Введите слово: \n 'стоп' - программа замолкает, при этом можно продолжать отправлять сообщения в чат, \n 'продолжить' - программа снова начинает отвечать, \n 'закончить' - программа завершает работу ");
             do {
                 int index = (int) (Math.random() * (wordsList.size() - 1));
                 str = br.readLine();
                 if (!str.equals("стоп") && flag) {
                     System.out.println(wordsList.get(index));

                 }
                 if (str.equals("стоп")) {
                     flag = false;
                 }

                 if (str.equals("продолжить")) {
                     flag = true;
                     System.out.println(wordsList.get(index));
                 }

             } while (!str.equals("закончить"));

         }  catch (Exception e) {
        e.printStackTrace();
    }
         }

}

