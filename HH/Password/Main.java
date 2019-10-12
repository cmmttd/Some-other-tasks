:wqpackage Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/*
    Привет, твой коллега забыл пин-код от двери в офис.

Клавиатура выглядит следующим образом:


            ┌───┬───┬───┐
            │ 1 │ 2 │ 3 │
            ├───┼───┼───┤
            │ 4 │ 5 │ 6 │
            ├───┼───┼───┤
            │ 7 │ 8 │ 9 │
            └───┼───┼───┘
                │ 0 │
                └───┘

Коллега вспоминает, что, кажется, пин код был 1357, но возможно каждая цифра должна быть сдвинута по горизонтали или вертикали, но не по диагонали.

Например, вместо 1 может быть 2 или 4, а вместо 5 может быть 2, 4, 6 или 8.

Помоги коллеге попасть в офис.


Входные данные
Строка с пин-кодом. Длина строки от 1 до 8 цифр, например, 1357.


Выходные данные
Исходный пин-код и возможные варианты пин-кодов с учетом сдвигов, отсортированные и разделенные запятой.

Не забудь, что пин-коды должны быть строками, так как могут начинаться с 0, и должны быть отсортированы, как строки.


Пример
Входные данные: 11

Выходные данные: 11,12,14,21,22,24,41,42,44


Входные данные: 8

Выходные данные: 0,5,7,8,9


Входные данные: 46

Выходные данные: 13,15,16,19,43,45,46,49,53,55,56,59,73,75,76,79
 */

public class Main {
    public static void main(String[] args) {
        String in = new String();
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            in = br.readLine();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (in != null) {
            Brute comb = new Brute(in);
            System.out.println(comb.getPWSeq());
        }
    }

    public static class Brute {
        private ArrayList<ArrayList<String>> pws;

        public Brute(String s) {
            force(s);
        }

        private void force(String s) {
            pws = new ArrayList<>();
            for (Character ch : s.toCharArray()) {
                switch (ch) {
                    case ('0'):
                        pws.add(new ArrayList<String>() {{
                            add("0");
                            add("8");
                        }});
                        break;
                    case ('1'):
                        pws.add(new ArrayList<String>() {{
                            add("1");
                            add("2");
                            add("4");
                        }});
                        break;
                    case ('2'):
                        pws.add(new ArrayList<String>() {{
                            add("2");
                            add("1");
                            add("3");
                            add("5");
                        }});
                        break;
                    case ('3'):
                        pws.add(new ArrayList<String>() {{
                            add("3");
                            add("2");
                            add("6");
                        }});
                        break;
                    case ('4'):
                        pws.add(new ArrayList<String>() {{
                            add("4");
                            add("1");
                            add("5");
                            add("7");
                        }});
                        break;
                    case ('5'):
                        pws.add(new ArrayList<String>() {{
                            add("5");
                            add("2");
                            add("4");
                            add("6");
                            add("8");
                        }});
                        break;
                    case ('6'):
                        pws.add(new ArrayList<String>() {{
                            add("6");
                            add("3");
                            add("5");
                            add("9");
                        }});
                        break;
                    case ('7'):
                        pws.add(new ArrayList<String>() {{
                            add("7");
                            add("4");
                            add("8");
                        }});
                        break;
                    case ('8'):
                        pws.add(new ArrayList<String>() {{
                            add("8");
                            add("5");
                            add("7");
                            add("0");
                            add("9");
                        }});
                        break;
                    case ('9'):
                        pws.add(new ArrayList<String>() {{
                            add("9");
                            add("8");
                            add("6");
                        }});
                        break;
                }
            }
        }

        public String getPWSeq() {

            ArrayList<String> result = new ArrayList<>();

            int num = pws.size();
            if (num == 0) return null;
            for (String fs : pws.get(0)) {
                if (num >= 2) {
                    for (String s : pws.get(1)) {
                        if (num >= 3) {
                            for (String ss : pws.get(2)) {
                                if (num >= 4) {
                                    for (String fours : pws.get(3)) {
                                        if (num >= 5) {
                                            for (String fths : pws.get(4)){
                                                if (num >= 6){
                                                    for (String sixs : pws.get(5)){
                                                        if (num >= 7){
                                                            for (String sevs : pws.get(6)){
                                                                if (num >= 8){
                                                                    for (String es : pws.get(7)){
                                                                        result.add(fs + s + ss + fours + fths + sixs + sevs + es);
                                                                    }
                                                                }else {
                                                                    result.add(fs + s + ss + fours + fths + sixs + sevs);
                                                                }
                                                            }
                                                        } else {
                                                            result.add(fs + s + ss + fours + fths + sixs);
                                                        }
                                                    }
                                                } else {
                                                    result.add(fs + s + ss + fours + fths);
                                                }
                                            }
                                        } else {
                                            result.add(fs + s + ss + fours);
                                        }
                                    }
                                } else {
                                    result.add(fs + s + ss);
                                }
                            }
                        } else {
                            result.add(fs + s);
                        }
                    }
                } else {
                    result.add(fs);
                }
            }
            Collections.sort(result);

            return String.join(",", result);
        }
    }
}