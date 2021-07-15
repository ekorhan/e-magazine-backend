package com.oftekfak.emagazine.ai;

import com.oftekfak.emagazine.model.ai.ClusteringDataRequest;

import java.util.*;

/**
 * TODO:Veri setini hazırla
 * [{a,[4,5]},{b,[2,6]},{c,[3,4]}]
 * TODO:Tüm verilerin orta noktasını bul bu nokta m olsun
 * m = ((4+2+3)/3,(5+6+4)/3) = (3,5)
 * TODO:Bütün noktaların m noktasına olan uzaklanığı bul
 * [m,a] =
 * [m,a] = ((((4-3)^2)+((5-5)^2)))^(1/2))) = ((1+0)^(1/2)) = 1
 * [m,b] = ((((2-3)^2)+((6-5)^2)))^(1/2))) = ((1+1)^(1/2)) = Kok(2)
 * [m,c] = ((((3-3)^2)+((4-5)^2)))^(1/2))) = ((0+1)^(1/2)) = 1
 * TODO: m noktasına en uzak k tane nokta belirlenir
 * b,a
 * TODO: Belirlenen noktaların her birinin m ile orta noktası merkez olarak kabul edilir
 * m1 = ((2+3)/2),((6+5)/2) = (2.5,5.5)
 * m2 = ((4+3)/2),((5+5)/2) = (3.5,5)
 * TODO: Tüm noktaların m1 ve m2 uzaklıkları hesaplanır
 * <p>
 * TODO: Noktalar en yakın oldukları merkezin kümesine dahil olur
 * <p>
 * TODO: Kümeler şekillendikten sonra her bir kümenin merkezi yeniden belirlenir
 * <p>
 * TODO: Son üç adım kümeleme stabilleşene kadar devam eder
 */
public class ClusteringAlgorithm {
    private final ClusteringDataRequest request;
    private final int k;
    private int sampleCount;
    private final ArrayList<Double> mainCenter = new ArrayList<>();
    private HashMap<Integer, ArrayList<Double>> centers = new HashMap<>();
    HashMap<Long, ArrayList<Double>> userAndCord = new HashMap<>();
    HashMap<Long, Integer> userAndCenter = new HashMap<>();
    HashMap<Long, ArrayList<HashMap<Integer, Double>>> distances = new HashMap<>();

    public ClusteringAlgorithm(ClusteringDataRequest request, int k) {
        if (k < 2)
            throw new IllegalStateException("k value shouldn't less than 2, k: " + k);

        if (Objects.isNull(request))
            throw new IllegalStateException("Request cannot be null");

        if (request.getClusteringData().isEmpty())
            throw new IllegalStateException("Data cannot be empty");

        this.request = request;
        this.k = k;
        prepareData();

        System.out.println("mainCenter: " + mainCenter);
        System.out.println("userAndCord: " + userAndCord);
        System.out.println("centers: " + centers);
        System.out.println("distances: " + distances);

        calculateFirstCenters();
        calculateDistances();
        System.out.println("centers: " + centers);
        System.out.println("distances: " + distances);

        boolean isStable = doClustering();

        System.out.println("userAndCenter: " + userAndCenter);
        System.out.println("isStable: " + isStable);
    }

    private void prepareData() {
        for (ClusteringDataRequest.ClusteringData e : request.getClusteringData()) {
            if (Objects.isNull(e.getUserId()))
                throw new IllegalStateException("Any userId cannot be null");
            if (e.getUserChars().isEmpty())
                throw new IllegalStateException("User chars cannot be empty");

            userAndCord.put(e.getUserId(), e.getUserChars());
        }

        for (int i = 0; i < k; i++) {
            centers.put(i + 1, new ArrayList<>());
        }

        for (int i = 0; i < request.getClusteringData().get(0).getUserChars().size(); i++) {
            mainCenter.add(0.0);
            for (Map.Entry<Integer, ArrayList<Double>> e : centers.entrySet()) {
                e.getValue().add(0.0);
            }
        }

        for (Map.Entry<Long, ArrayList<Double>> e : userAndCord.entrySet()) {
            for (int i = 0; i < e.getValue().size(); i++) {
                mainCenter.set(i, mainCenter.get(i) + e.getValue().get(i));
            }
            sampleCount++;
        }

        for (int i = 0; i < mainCenter.size(); i++) {
            mainCenter.set(i, mainCenter.get(i) / sampleCount);
        }
    }

    private void calculateFirstCenters() {
        HashMap<Long, Double> pointDistanceMap = new HashMap<>();
        for (Map.Entry<Long, ArrayList<Double>> e : userAndCord.entrySet()) {
            double total = 0.0;
            for (int i = 0; i < e.getValue().size(); i++) {
                Double pointOfCenter = mainCenter.get(i);
                Double pointOfChar = e.getValue().get(i);
                total += Math.pow(pointOfCenter - pointOfChar, 2.0);
            }
            total = Math.sqrt(total);
            pointDistanceMap.put(e.getKey(), total);
        }
        List<Map.Entry<Long, Double>> list = new ArrayList<>(pointDistanceMap.entrySet());
        list.sort(Map.Entry.comparingByValue());

        LinkedList<Long> mostFarPoints = new LinkedList<>();
        for (int i = list.size() - 1; i >= 0; i--) {
            mostFarPoints.add(list.get(i).getKey());
        }

        for (int i = 0; i < k; i++) {
            ArrayList<Double> d = new ArrayList<>();
            List<Double> mostFarCord = userAndCord.get(mostFarPoints.getFirst());
            for (int j = 0; j < mostFarCord.size(); j++) {
                d.add(((mostFarCord.get(j) + mainCenter.get(j)) / 2));
            }
            mostFarPoints.removeFirst();
            centers.put(i + 1, d);
        }
    }

    private void calculateDistances() {
        for (Map.Entry<Long, ArrayList<Double>> e : userAndCord.entrySet()) {
            for (Map.Entry<Integer, ArrayList<Double>> c : centers.entrySet()) {
                double total = 0.0;
                for (int i = 0; i < c.getValue().size(); i++) {
                    Double pointOfCenter = c.getValue().get(i);
                    Double pointOfChar = e.getValue().get(i);
                    total += Math.pow(pointOfCenter - pointOfChar, 2.0);
                }
                total = Math.sqrt(total);
                HashMap<Integer, Double> a = new HashMap<>();
                a.put(c.getKey(), total);
                if (distances.get(e.getKey()) != null)
                    distances.get(e.getKey()).add(a);
                else
                    distances.put(e.getKey(), new ArrayList<>(Collections.singletonList(a)));
            }
        }
    }

    public boolean doClustering() {
        boolean isStable = true;
        for (Map.Entry<Long, ArrayList<HashMap<Integer, Double>>> userDistance : distances.entrySet()) {
            Long userId = userDistance.getKey();
            Double mostClose = Double.MAX_VALUE;
            int center = -1;
            for (HashMap<Integer, Double> e : userDistance.getValue()) {
                Double value = e.values().iterator().next();
                if (value < mostClose) {
                    mostClose = value;
                    center = e.keySet().iterator().next();
                }
            }
            if (isStable) {
                if (userAndCenter.get(userId) != null && userAndCenter.get(userId) != center) {
                    isStable = false;
                }
            }
            userAndCenter.put(userId, center);

        }
        return isStable;
    }
}
