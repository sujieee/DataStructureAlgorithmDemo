package com.jie.recursion;

/**
 * Labyrinth
 * 模拟迷宫
 *
 * @author sujie
 * @version 1.0
 * @since 2021/3/26 下午2:39
 */
public class Labyrinth {
    public static void main(String[] args) {
        //二维数组模拟
        //8行7列
        int[][] map = new int[8][7];
        //1 表示墙
        //上下置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int i1 = 0; i1 < 7; i1++) {
                System.out.printf(map[i][i1] + " ");
            }
            System.out.println();
        }
        //测试小球找路
        setWay2(map, 1, 1);
        System.out.println("小球走过，并标识过地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int i1 = 0; i1 < 7; i1++) {
                System.out.printf(map[i][i1] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 递归回溯
     * 使用递归回溯找到通路
     * i,j表示从哪开始
     * map表示地图
     * 如果小球能到[6][5]的位置，则说明通路找到
     * 约定：当map[i][j]为0时，表示该点还没走过，为1时表示墙，为2时表示通路可以走，3表示该位置已经走过，但是走不通
     * 在走迷宫时，确定一个策略，下->右->上->左，如果该点走不通，再回溯
     *
     * @param map 地图
     * @param i
     * @param j
     * @return 是否找到通路
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (setWay(map, i + 1, j)) {
                    return true;
                } else if (setWay(map, i, j + 1)) {
                    return true;
                } else if (setWay(map, i - 1, j)) {
                    return true;
                } else if (setWay(map, i, j - 1)) {
                    return true;
                } else {
                    //已经走过，走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }

    /**
     * 递归回溯
     * 修改找路策略 上->右->下->左
     *
     * @param map
     * @param i
     * @param j
     * @return
     */
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (setWay2(map, i - 1, j)) {
                    return true;
                } else if (setWay2(map, i, j + 1)) {
                    return true;
                } else if (setWay2(map, i + 1, j)) {
                    return true;
                } else if (setWay2(map, i, j - 1)) {
                    return true;
                } else {
                    //已经走过，走不通
                    map[i][j] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
