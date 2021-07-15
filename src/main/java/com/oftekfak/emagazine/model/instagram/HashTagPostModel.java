package com.oftekfak.emagazine.model.instagram;

import java.io.Serializable;

public class HashTagPostModel implements Serializable {
    private Graphql graphql;

    public Graphql getGraphql() {
        return graphql;
    }

    public void setGraphql(Graphql graphql) {
        this.graphql = graphql;
    }
}
