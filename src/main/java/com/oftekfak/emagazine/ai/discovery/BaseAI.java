package com.oftekfak.emagazine.ai.discovery;

import com.oftekfak.emagazine.model.post.PostModel;

import java.util.List;

public abstract class BaseAI {
    public abstract List<PostModel> getPosts(Long userId);
}
