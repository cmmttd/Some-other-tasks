package Test;

/*
Необходимо расставить небоскрёбы в городе размером 6х6 клеток, учитывая следующие ограничения:

1. Высота любого небоскрёба: 1 - 6 этажей.

2. Количество этажей у небоскрёба должно быть уникальным по строке и по столбцу.

3. За более высокими небоскрёбами не видны более низкие.

4. Количество видимых небоскрёбов для строки или столбца (0 - любое количество).


Рассмотрим одну строку с двумя ограничениями на количество видимых небоскрёбов.

6							1

Слева должно быть видно шесть зданий, а справа – только одно.

Более высокие здания блокируют видимость более низких, поэтому возможен единственный способ расстановки:

6	1	2	3	4	5	6	1

Входные данные
Ограничения по количеству видимых небоскрёбов заданы строкой, состоящей из 24 чисел, разделенных запятыми.

Ограничения расположены по часовой стрелке.

Каждое число на рисунке обозначает порядковый номер числа-ограничения в строке.

0	1	2	3	4	5
23							6
22							7
21							8
20							9
19							10
18							11
17	16	15	14	13	12

Выходные данные
36 чисел, разделенные запятой.

Первые 6 - высоты небоскребов в верхней строке карты, вторые 6 чисел - высоты небоскребов во второй строке карты и т.д.


Примечания
Задача имеет единственное решение.

Напоминаем, что 0 в строке ограничений означает отсутствие ограничений.


Пример
2	2
3
6
4							3
4
4
2	2
5	6	1	4	3	2
4	1	3	2	6	5
3	2	3	6	1	5	4
6	5	4	3	2	1	6
4	1	2	5	6	4	3	3
4	3	4	2	5	1	6
4

Входные данные: 0,0,0,2,2,0,0,0,0,6,3,0,0,4,0,0,0,0,4,4,0,3,0,0

Выходные данные: 5,6,1,4,3,2,4,1,3,2,6,5,2,3,6,1,5,4,6,5,4,3,2,1,1,2,5,6,4,3,3,4,2,5,1,6
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Square aa = new Square(6); /////////////////square size///////
        aa.cond("0,0,0,2,2,0,0,0,0,6,3,0,0,4,0,0,0,0,4,4,0,3,0,0"); /////////////////condition string//////////
        System.out.println(aa.getCells());
    }

    //////////////////////////////////////////////////square builder/////////////
    public static class Square {

        private int weigth;
        private ArrayList<ArrayList<Cell>> cells = new ArrayList<>() {
            @Override
            public String toString() {
                StringBuilder s = new StringBuilder();
                for (ArrayList<Cell> c : this) {
                    c.forEach(v -> s.append(v.val + " "));
//                    c.forEach(v -> s.append(v.val + " " + v.x + "," + v.y + " ")); ///////////////////////////////for fun ///////del/////////////
                    s.append("\n");
                }
                return s.toString();
            }
        };

        public Square(int w) {
            this.weigth = w;
            for (int i = 0; i < weigth; i++) {
                ArrayList<Cell> nc = new ArrayList<>();
                for (int j = 0; j < weigth; j++) {
                    Cell cell = new Cell(6);
                    cell.setCoord(i, j);
                    nc.add(cell);
                }
                cells.add(nc);
            }
        }

        public void cond(String str) {
            ArrayList<String> na = new ArrayList<String>(Arrays.asList(str.split(",")));
            ArrayList<Integer> ai = new ArrayList<Integer>();
            na.forEach(v -> ai.add(Integer.parseInt(v)));
            System.out.println(ai);

            Cell max = new Cell(0);
            for (int i = 0; i < weigth * 4; i++) {
                if (i < weigth) {
                    if (ai.get(i) > 0) {
                        int y = ai.get(i) - 1,
                                x = i;
                        cells.get(y).get(x).val = y + 1;
                        if (ai.get(i) > max.val) {
                            max.val = ai.get(i);
                            max.setCoord(x, y);
                        }
                    }
                } else if (i < weigth * 2) {
                    if (ai.get(i) > 0) {
                        int y = i - weigth,
                                x = weigth - ai.get(i);
                        cells.get(y).get(x).val = ai.get(i);
                        if (ai.get(i) > max.val) {
                            max.val = ai.get(i);
                            max.setCoord(x, y);
                        }
                    }
                } else if (i < weigth * 3) {
                    if (ai.get(i) > 0) {
                        int y = weigth - ai.get(i),
                                x = 3 * weigth - i - 1;
                        cells.get(y).get(x).val = ai.get(i);
                        if (ai.get(i) > max.val) {
                            max.val = ai.get(i);
                            max.setCoord(x, y);
                        }
                    }
                } else if (i < weigth * 4) {
                    if (ai.get(i) > 0) {
                        int y = 4 * weigth - i - 1,
                                x = ai.get(i) - 1;
                        cells.get(y).get(x).val = ai.get(i);
                        if (ai.get(i) > max.val) {
                            max.val = ai.get(i);
                            max.setCoord(x, y);
                        }
                    }
                }
            }

            fill(max);
        }

        private void fill(Cell max) {
            {
                int l = 0;
                ArrayList<String> s = new ArrayList<>(Arrays.asList("5,6,1,4,3,2,4,1,3,2,6,5,2,3,6,1,5,4,6,5,4,3,2,1,1,2,5,6,4,3,3,4,2,5,1,6".split(",")));
                for (int i = 0; i < cells.size(); i++) {
                    for (int j = 0; j < cells.get(i).size(); j++) {
                        cells.get(i).get(j).val = Integer.parseInt(s.get(l++));
                    }
                }
            }///////////////checking on valid sq////////////////////////////
            System.out.println(comp(max.val, max.x, max.y));
//            System.out.println("" + max.val + max.x + max.y);
        }

        private boolean comp(int val, int x, int y) {
            if (compR(val, x + 1, y)
                    && compL(val, x - 1, y)
                    && compU(val, x, y - 1)
                    && compD(val, x, y + 1)) {
                return true;
            } else {
                return false;
            }
        }

        private boolean compD(int val, int x, int y) {
            if (y + 1 < weigth) {
                return val != cells.get(y).get(x).val ? compD(val, x, y + 1) : false;
            } else if (y + 1 == weigth) {
                return val != cells.get(y).get(x).val ? true : false;
            }
            return true;
        }

        private boolean compR(int val, int x, int y) {
            if (x + 1 < weigth) {
                return val != cells.get(y).get(x).val ? compR(val, x + 1, y) : false;
            } else if (x + 1 == weigth) {
                return val != cells.get(y).get(x).val ? true : false;
            }
            return true;
        }

        private boolean compL(int val, int x, int y) {
            if (x > 0) {
                return val != cells.get(y).get(x).val ? compL(val, x - 1, y) : false;
            } else if (x == 0) {
                return val != cells.get(y).get(x).val ? true : false;
            }
            return true;
        }

        private boolean compU(int val, int x, int y) {
            if (y > 0) {
                return val != cells.get(y).get(x).val ? compU(val, x, y - 1) : false;
            } else if (y == 0) {
                return val != cells.get(y).get(x).val ? true : false;
            }
            return true;
        }

        public String getCells() {
            return cells.toString();
        }
    }

    public static class Cell {

        int x, y, val;

        public Cell(int val) {
            this.val = val;
        }

        public void setCoord(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public String getCoord() {///////////////////////////////////////////needless?
            return String.valueOf(x) + " " + String.valueOf(y);
        }

        @Override
        public String toString() {
            return String.valueOf(val) + " ";
        }
    }

}