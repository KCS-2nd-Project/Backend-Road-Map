package com.make.backendroadmap.domain.service;

import com.make.backendroadmap.domain.entity.MainCategory;
import com.make.backendroadmap.domain.exception.ResourceNotFoundException;
import com.make.backendroadmap.domain.repository.MainCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class MainCategoryService {
    private final MainCategoryRepository mainCategoryRepository;

    public MainCategory findMainCategoryById(Long id) {
        return mainCategoryRepository.findMainCategoriesByMainDocsId(id)
                .orElseThrow(() -> new ResourceNotFoundException());
    }

    public MainCategory findMainCategoryByTitle(String title) {
        return mainCategoryRepository.findMainCategoryByMainDocsTitle(title)
                .orElseThrow(() -> new ResourceNotFoundException());
    }
}
