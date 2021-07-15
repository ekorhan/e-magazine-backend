package com.oftekfak.emagazine.service.impl;

import com.oftekfak.emagazine.model.post.TagModel;
import com.oftekfak.emagazine.repository.TagRepository;
import com.oftekfak.emagazine.service.IUtilityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilityServiceImpl implements IUtilityService {
    TagRepository tagRepository;

    @Override
    public List<TagModel> inquireAllTags() {
        return tagRepository.getAllTags();
    }
}
