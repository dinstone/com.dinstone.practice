
package com.dinstone.practice.redblue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.dinstone.practice.redblue.BallStats.Comp;

public class WinningSearch {

    public static void main(String[] args) {
        String inFile = "redblueball.txt";
        List<WinningInfo> wiList = loadWinningInfos(inFile);

        File df = new File("recommend.txt");
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(df)));
            int step = 10;
            for (int i = step; i < wiList.size(); i = i + step) {
                String temp = recommend(wiList, i);
                out.write(temp);
                out.newLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Escape character is 'quit'.");
        while (true) {
            System.out.println("====================================");
            System.out.println("please input ball number:");
            try {
                String cmd = reader.readLine();
                if ("quit".equals(cmd)) {
                    break;
                }

                String[] splits = cmd.split(" ");
                if (splits.length < 6) {
                    // System.out.println("please input ball number:");
                    continue;
                }
                int[] balls = new int[splits.length];
                for (int i = 0; i < splits.length; i++) {
                    balls[i] = Integer.parseInt(splits[i]);
                }
                Arrays.sort(balls);

                foundWinning(wiList, balls);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        for (WinningInfo winningInfo : wiList) {
            System.out.println(winningInfo);
        }
    }

    private static String recommend(List<WinningInfo> wiList, int range) {
        int startIndex = 0;
        if (range <= 0) {
            System.out.println("Nearly " + wiList.size() + " frequent sequential:");
        } else {
            System.out.println("Nearly " + range + " frequent sequential:");
            startIndex = Math.max(wiList.size() - range, 0);
        }

        BallStats[] rss = redHitStat(wiList, startIndex);
        BallStats[] bss = blueHitStat(wiList, startIndex);

        BallStats[] ruh = redUnhitStat(wiList, startIndex);
        BallStats[] buh = blueUnhitStat(wiList, startIndex);

        Set<BallStats> redSet = new TreeSet<BallStats>(new Comp());
        for (int i = 0; i < 33; i++) {
            redSet.add(rss[i]);
            redSet.add(ruh[i]);
            if (redSet.size() >= 6) {
                break;
            }
        }
        // System.out.println("red = " + redSet);

        Set<BallStats> blueSet = new TreeSet<BallStats>(new Comp());
        for (int i = 0; i < 33; i++) {
            blueSet.add(bss[i]);
            blueSet.add(buh[i]);
            if (blueSet.size() >= 2) {
                break;
            }
        }

        // System.out.println("blue = " + blueSet);

        StringBuilder x = new StringBuilder();
        x.append("Recommend [");
        for (BallStats rs : redSet) {
            x.append(" ");
            x.append(rs.getBall());
        }
        x.append(" |");
        for (BallStats bs : blueSet) {
            x.append(" ");
            x.append(bs.getBall());
        }
        x.append(" ]");

        x.append(", Nearly " + range);
        System.out.println(x);

        System.out.print("*****************************************************");
        System.out.println("*****************************************************");

        return x.toString();
    }

    private static BallStats[] blueUnhitStat(List<WinningInfo> wiList, int startIndex) {
        int[] unhits = new int[16];
        for (int index = startIndex; index < wiList.size(); index++) {
            for (int i = 0; i < unhits.length; i++) {
                unhits[i]++;
            }

            Ball bb = wiList.get(index).getBlueBall();
            unhits[bb.getNumber() - 1] = 0;
        }

        BallStats[] blueStats = new BallStats[16];
        for (int i = 0; i < 16; i++) {
            blueStats[i] = new BallStats(BlueBall.valueOf(i + 1), unhits[i]);
        }
        Arrays.sort(blueStats);

        System.out.print("Blue Unhit\t");
        for (BallStats blueStat : blueStats) {
            System.out.print(" ");
            System.out.print(blueStat);
        }

        System.out.println();

        return blueStats;
    }

    private static BallStats[] redUnhitStat(List<WinningInfo> wiList, int startIndex) {
        int[] unhits = new int[33];
        for (int index = startIndex; index < wiList.size(); index++) {
            for (int i = 0; i < unhits.length; i++) {
                unhits[i]++;
            }

            Ball[] rbs = wiList.get(index).getRedBalls();
            for (int i = 0; i < rbs.length; i++) {
                unhits[rbs[i].getNumber() - 1] = 0;
            }
        }

        BallStats[] redStats = new BallStats[33];
        for (int i = 0; i < unhits.length; i++) {
            redStats[i] = new BallStats(RedBall.valueOf(i + 1), unhits[i]);
        }

        Arrays.sort(redStats);

        System.out.print("Red Unhit\t");
        for (BallStats redStat : redStats) {
            System.out.print(" ");
            System.out.print(redStat);
        }

        System.out.println();

        return redStats;
    }

    private static BallStats[] blueHitStat(List<WinningInfo> wiList, int startIndex) {
        int[] hits = new int[16];
        for (int index = startIndex; index < wiList.size(); index++) {
            Ball bb = wiList.get(index).getBlueBall();
            hits[bb.getNumber() - 1]++;
        }

        BallStats[] blueStats = new BallStats[16];
        for (int i = 0; i < hits.length; i++) {
            blueStats[i] = new BallStats(BlueBall.valueOf(i + 1), hits[i]);
        }

        Arrays.sort(blueStats);

        System.out.print("Blue Hit\t");
        for (BallStats blueStat : blueStats) {
            System.out.print(" ");
            System.out.print(blueStat);
        }

        System.out.println();

        return blueStats;
    }

    private static BallStats[] redHitStat(List<WinningInfo> wiList, int startIndex) {
        int[] hits = new int[33];
        for (int index = startIndex; index < wiList.size(); index++) {
            Ball[] rbs = wiList.get(index).getRedBalls();
            for (int i = 0; i < rbs.length; i++) {
                hits[rbs[i].getNumber() - 1]++;
            }
        }

        BallStats[] redStats = new BallStats[33];
        for (int i = 0; i < hits.length; i++) {
            redStats[i] = new BallStats(RedBall.valueOf(i + 1), hits[i]);
        }

        Arrays.sort(redStats);

        System.out.print("Red  Hit\t");
        for (BallStats redStat : redStats) {
            System.out.print(" ");
            System.out.print(redStat);
        }

        System.out.println();

        return redStats;
    }

    private static void foundWinning(List<WinningInfo> wiList, int[] balls) {
        WinningInfo found = null;
        for (WinningInfo wi : wiList) {
            Ball[] rbs = wi.getRedBalls();
            if (contain(balls, rbs)) {
                found = wi;
                break;
            }
        }

        if (found != null) {
            System.out.println("your balls is winning," + found);
        } else {
            System.out.println("your balls is not winning");
        }
    }

    private static boolean contain(int[] balls, Ball[] rbs) {
        boolean flag = true;
        for (int i = 0; i < rbs.length; i++) {
            Ball redBall = rbs[i];
            if (redBall.getNumber() == balls[i]) {
                continue;
            }

            flag = false;
            break;
        }

        return flag;
    }

    private static List<WinningInfo> loadWinningInfos(String inFile) {
        List<WinningInfo> wiList = new LinkedList<WinningInfo>();
        BufferedReader reader = null;
        int line = 0;
        String temp = null;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(inFile)));
            while ((temp = reader.readLine()) != null) {
                line++;
                // split by empty
                String[] splits = temp.split(" ", 8);
                if (splits.length != 8) {
                    continue;
                }

                RedBall[] rbs = new RedBall[6];
                for (int i = 1; i < 7; i++) {
                    int number = Integer.parseInt(splits[i]);
                    rbs[i - 1] = RedBall.valueOf(number);
                }

                int number = Integer.parseInt(splits[7].substring(1));
                Ball bb = BlueBall.valueOf(number);

                // create winning info
                wiList.add(new WinningInfo(splits[0].trim(), rbs, bb));
            }
        } catch (Exception e) {
            System.out.println("can't handle this line " + line + " [" + temp + "]");
            throw new RuntimeException("load winning information error", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }

        return wiList;
    }

}
