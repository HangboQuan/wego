package com.jd.wego.service;

import com.jd.wego.entity.Category;

import java.util.List;

/**
 * @author hbquan
 * @date 2021/4/7 20:34
 */
public interface CategoryService {

    void insertCategory(Category category);

    void updateCategory(Category category);

    void deleteCategoryById(int categoryId);

    Category selectCategoryById(int categoryId);

    Category selectCategoryByName(String categoryName);

    List<Category> selectAllCategory();
}
