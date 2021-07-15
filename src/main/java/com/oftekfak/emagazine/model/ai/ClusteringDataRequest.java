package com.oftekfak.emagazine.model.ai;

import java.util.ArrayList;
import java.util.List;

public class ClusteringDataRequest {
    private List<ClusteringData> clusteringData;

    public List<ClusteringData> getClusteringData() {
        return clusteringData;
    }

    public void setClusteringData(List<ClusteringData> clusteringData) {
        this.clusteringData = clusteringData;
    }

    public static class ClusteringData {
        private Long userId;
        private ArrayList<Double> userChars;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public ArrayList<Double> getUserChars() {
            return userChars;
        }

        public void setUserChars(ArrayList<Double> userChars) {
            this.userChars = userChars;
        }
    }
}
