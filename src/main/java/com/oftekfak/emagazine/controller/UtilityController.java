package com.oftekfak.emagazine.controller;


import com.oftekfak.emagazine.model.post.TagModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
public class UtilityController {
    /*
    @Autowired
    private IUtilityService utilityService;
    */

    @GetMapping(path = "/inquireAllTags")
    public ResponseEntity<List<TagModel>> inquireAllTags() {
        try {
            return ResponseEntity.ok(Arrays.asList(
                    new TagModel(1, "sbahar", "Sonbahar", 1, "mevsim"),
                    new TagModel(2, "ibahar", "İlkbahar", 1, "mevsim"),
                    new TagModel(3, "yaz", "Yaz", 1, "mevsim"),
                    new TagModel(4, "kis", "Kış", 1, "mevsim"),
                    new TagModel(5, "etek", "Etek", 2, "tur"),
                    new TagModel(6, "elbise", "Elbise", 2, "tur"),
                    new TagModel(7, "cadilarbayrami", "Cadılar Bayramı", 3, "konsept"),
                    new TagModel(8, "yilbasi", "Yılbaşı", 3, "konsept")
            ));
        } catch (Exception e) {
            return ResponseEntity.ok(new ArrayList<>());
        }
    }
}
