package com.oftekfak.emagazine.service;

import java.util.List;

public interface IInstagramService {
    List<String> findUsers(String hashTag);
}
