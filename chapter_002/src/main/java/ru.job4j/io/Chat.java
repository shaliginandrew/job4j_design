package ru.job4j.io;


import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Chat {
    private String str;
    private boolean flag = true;

    public void dialog() {

        List<String> wordsList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader("./chapter_002/data/words.txt"))) {
            wordsList = in.lines().collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Scanner input = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream("./chapter_002/data/log.txt")))) {
            String text = "Введите слово: \n 'стоп' - программа замолкает, при этом можно продолжать отправлять сообщения в чат, \n 'продолжить' - программа снова начинает отвечать, \n 'закончить' - программа завершает работу ";
            System.out.println(text);
            out.println(text);
            do {
                int index = (int) (Math.random() * (wordsList.size()));
                str = input.nextLine();
                out.println(str);
                if (!str.equals("стоп") && flag && !str.equals("закончить")) {
                    System.out.println(wordsList.get(index));
                    out.println(wordsList.get(index));
                }
                if (str.equals("стоп")) {
                    flag = false;
                }
                if (str.equals("продолжить")) {
                    flag = true;
                    System.out.println(wordsList.get(index));
                    out.println(wordsList.get(index));
                }
            } while (!str.equals("закончить"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
     Chat chat = new Chat();
     chat.dialog();
    }
}



