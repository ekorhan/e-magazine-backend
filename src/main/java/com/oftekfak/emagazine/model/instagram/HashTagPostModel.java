package com.oftekfak.emagazine.model.instagram;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class HashTagPostModel implements Serializable {
    private Graphql graphql;

    public class Graphql {
        private Hashtag hashtag;

        public class Hashtag {
            private long id;
            private String name;

            @SerializedName("profile_pic_url")
            private String profilePicUrl;

            @SerializedName("edge_hashtag_to_media")
            private EdgeHashTagToMedia edgeHashTagToMedia;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProfilePicUrl() {
                return profilePicUrl;
            }

            public void setProfilePicUrl(String profilePicUrl) {
                this.profilePicUrl = profilePicUrl;
            }

            public class EdgeHashTagToMedia {
                ArrayList<Edges> edges;

                public class Edges {
                    Node node;

                    public class Node {
                        @SerializedName("display_url")
                        String displayUrl;
                        @SerializedName("accessibility_caption")
                        String accessibilityCaption;
                        Owner owner;

                        public String getDisplayUrl() {
                            return displayUrl;
                        }

                        public String getAccessibilityCaption() {
                            return accessibilityCaption;
                        }

                        public class Owner {
                            Long id;

                            public Long getId() {
                                return id;
                            }
                        }

                        public Owner getOwner() {
                            return owner;
                        }
                    }

                    public Node getNode() {
                        return node;
                    }
                }

                public ArrayList<Edges> getEdges() {
                    return edges;
                }
            }

            public EdgeHashTagToMedia getEdgeHashTagToMedia() {
                return edgeHashTagToMedia;
            }
        }

        public Hashtag getHashtag() {
            return hashtag;
        }

        public void setHashtag(Hashtag hashtag) {
            this.hashtag = hashtag;
        }

    }

    public Graphql getGraphql() {
        return graphql;
    }

    public void setGraphql(Graphql graphql) {
        this.graphql = graphql;
    }
}
