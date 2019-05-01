package com.mygdx.game.map;

import com.badlogic.gdx.math.MathUtils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MapGenerator {
    float[][] noise;
    HashMap<Integer,Integer> materialsize = new HashMap<Integer, Integer>();

    private final int[][] SAND = {
            {1377, 1378, 1379},
            {1409, 1410, 1411},
            {1441, 1442, 1443}
    };
    private final int[][] ROCK = {
            {1299, 1300, 1301},
            {1331, 1332, 1333},
            {1363, 1364, 1365}
    };
    private final int[][] DIRT = {
            {80, 81, 82},
            {112, 113, 114},
            {144, 145, 146}
    };
    private final int[][] GRASS = {
            {86, 87, 88},
            {118, 1207, 120},
            {150, 151, 152}
    };
    private final int[][] TREE = {
            {1950, 1951, 1952},
            {1982, 1983, 1984},
            {2014, 2015, 2016},
            {2046, 2047, 2048}
    };
    private ArrayList<int[][]> propsList;

    public MapGenerator() {
        propsList = new ArrayList();
        propsList.add(DIRT);
        propsList.add(ROCK);
        propsList.add(SAND);
        propsList.add(GRASS);
        materialsize.put(0,200);
        materialsize.put(1,200);
        materialsize.put(2,200);
        materialsize.put(3,200);
        try {
            generateFile();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    private void drawFill(PrintWriter zapis) {
        zapis.println("<layer id=\"2\" name=\"InBorder\" width=\"50\" height=\"50\">");
        zapis.println("<data encoding=\"csv\">");
        for (int i = 0; i < 49; i++) {
            zapis.println("0,118,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,120,");
        }
        zapis.println("0,118,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,119,120");
        zapis.println("</data>");
        zapis.println("</layer>");
    }

    private void drawTerrain(PrintWriter zapis, int[][] tab) {
        zapis.println("<layer id=\"3\" name=\"Terrain\" width=\"50\" height=\"50\">");
        zapis.println("<data encoding=\"csv\">");
        zapis.println("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,");
        for (int x = 0; x < 48; x++) {
            for (int y = 0; y < 50; y++) {
                int[][] temp = propsList.get(tab[x][y]);
                zapis.print(temp[1][1] + ",");
            }
            zapis.println();
        }
        zapis.println("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        zapis.println("</data>");
        zapis.println("</layer>");
    }

    private void drawItems2(PrintWriter zapis) {

    }

    private void drawItems1(PrintWriter zapis) {
        zapis.println("<layer id=\"4\" name=\"Item-1\" width=\"50\" height=\"50\">");
        zapis.println("<data encoding=\"csv\">");
        zapis.println("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,");
        for (int x = 0; x < 24; x++) {
            zapis.println("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,");
            zapis.println();
        }
        for (int i = 0; i < TREE.length; i++) {
            zapis.println("0,0,0,0,0,0,0,0,0,0,0,0,0," + TREE[i][0] + "," + TREE[i][1] + "," + TREE[i][2] + "," + "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,");
        }
        for (int x = 0; x < 20; x++) {
            zapis.println("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,");
            zapis.println();
        }
        zapis.println("0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0");
        zapis.println("</data>");
        zapis.println("</layer>");
    }

    private void drawBorder(PrintWriter zapis) {
        zapis.println("<layer id=\"1\" name=\"Border\" width=\"50\" height=\"50\">");
        zapis.println("<data encoding=\"csv\">");
        zapis.println("471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,");
        zapis.println("471,375,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,503,376,471,");
        for (int i = 0; i < 46; i++) {
            zapis.println("471,472,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,470,471,");
        }
        zapis.println("471,407,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,439,408,471,");
        zapis.println("471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471,471");
        zapis.println("</data>");
        zapis.println("</layer>");
    }

    private float[][] generateNoise(int width, int height) {
        new SimplexNoise(MathUtils.random(10000));
        float[][] noise = new float[width][height];
        //Frequency = features. Higher = more features
        float layerF = 0.003f;
        //Weight = smoothness. Higher frequency = more smoothness
        float weight = 1;

        for(int i = 0; i < 4; i++) {
            for(int x = 0; x < width; x++) {
                for(int y = 0; y < height; y++) {
                    noise[x][y] += (float) SimplexNoise.noise(x * layerF, y * layerF) * weight;
                    noise[x][y] = MathUtils.clamp(noise[x][y], -0.25f, 0.25f);
                }
            }
            layerF *= 3.5f;
            weight *= 0.5f;
        }

        return noise;
    }
    private int getNumber(float val) {
//        if(val >= -1.0f && val <= -0.1f) {
//            return 0; //navy
//        }
//        else if(val > -0.1f && val <= 0.1f) {
//            return 1; //blue
//        }
//        else if(val > 0.1f && val <= 0.6f) {
//            return 2; //green
//        }
//        else if(val > 0.6f && val <= 1.0f) {
//            return 3; //gray
//        if (materialsize.get(1) != 0) {
//            materialsize.put(1,materialsize.get(1)-1);
//            return 1; //navy
//        }else{
//            int idMin = 0;
//            int min = 600;
//            for(Map.Entry<Integer, Integer> entry : materialsize.entrySet()) {
//                Integer key = entry.getKey();
//                Integer value = entry.getValue();
//                if(min > value && value != 0){
//                    min = value;
//                    idMin = key;
//                }
//                // do what you have to do here
//                // In your case, another loop.
//            }
//            materialsize.put(idMin,materialsize.get(idMin)-1);
//            return idMin;
//        }
//        }
        if(val >= -0.25f && val <= -0.125f) {
            return 1;
        }
        else if(val > -0.125f && val <= 0.0f) {
            return 2;
        }
        else if(val > 0.0f && val <= 0.125f) {
            return 3;
        }
        else if(val > 0.125f && val <= 0.25f) {
           return 0;
        }
        return 0;
    }
    public void generateFile() throws FileNotFoundException {
        Random randGener = new Random();
        int[][] randomeTab = new int[48][50];
        noise = generateNoise(48, 50);
        for (int x = 0; x < 48; x++) {
            for (int y = 0; y < 50; y++) {
//                randomeTab[x][y] = randGener.nextInt(4);
                randomeTab[x][y] = getNumber(noise[x][y]);
            }
        }

        PrintWriter zapis = new PrintWriter("generTest.tmx");
        zapis.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        zapis.println("<map version=\"1.2\" tiledversion=\"1.2.3\" orientation=\"orthogonal\" renderorder=\"right-down\" width=\"50\" height=\"50\" tilewidth=\"32\" tileheight=\"32\" infinite=\"0\" nextlayerid=\"4\" nextobjectid=\"1\">\n");
        zapis.println("<tileset firstgid=\"1\" source=\"base_out_atlas.tsx\"/>");
        zapis.println("<tileset firstgid=\"1025\" source=\"terrain_atlas.tsx\"/>");
        //layer2 start
        drawFill(zapis);
        //layer2 end
        //layer3 start
        drawTerrain(zapis, randomeTab);
        //layer3 end
        //layer4 start
        drawItems1(zapis);
        //layer4 end
        //layer1 start
        drawBorder(zapis);
        //layer1 end
        zapis.println("</map>");
        zapis.close();
    }
}
