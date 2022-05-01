package com.example.demo.services.interfaces;


import com.example.demo.entities.TechnologyEntity;

import java.util.List;
import java.util.Set;

public interface TechnologyService {
    List<TechnologyEntity> findByIds(Set<Long> ids);
}
