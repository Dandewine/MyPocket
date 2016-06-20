package com.denis.mypocket.model.mapper;

import android.os.Parcel;

import com.denis.domain.models.categories.Category;
import com.denis.mypocket.model.categories.CategoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis_Zinkovskiy at 6/14/16.
 */

public class CategoryMapper implements ModelMapper<Category,CategoryModel> {
    @Override
    public CategoryModel toModel(Category category) {
        CategoryModel model = null;
        if(category != null){
            model = new CategoryModelImpl(category.getId(),category.getName(),category.getPath());
        }
        return model;
    }

    @Override
    public List<CategoryModel> toModel(List<Category> categories) {
        List<CategoryModel> categoryModels = null;
        if (categories != null && !categories.isEmpty()){
            categoryModels = new ArrayList<>();
            for (Category model : categories) {
                CategoryModel categoryModel = toModel(model);
                categoryModels.add(categoryModel);
            }
        }

        return categoryModels;
    }

    @Override
    public List<Category> fromModel(List<CategoryModel> categoryModels) {
        List<Category> categories = null;
        if (categoryModels != null && !categoryModels.isEmpty()){
            categories = new ArrayList<>();
            for (CategoryModel model : categoryModels) {
                Category category = fromModel(model);
                categories.add(category);
            }
        }
        return categories;
    }

    @Override
    public Category fromModel(CategoryModel categoryModel) {
        Category category = null;
        if (categoryModel != null){
            category = new CategoryDomain(categoryModel.getId(),categoryModel.getName(),categoryModel.getPath());
        }
        return category;
    }

    private static class CategoryModelImpl implements CategoryModel{
        private String id;
        private String name;
        private int path;

        CategoryModelImpl(String id, String name, int path) {
            this.id = id;
            this.name = name;
            this.path = path;
        }

        public CategoryModelImpl() {
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public int getPath() {
            return path;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeInt(this.path);
        }

        CategoryModelImpl(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.path = in.readInt();
        }

        public static final Creator<CategoryModelImpl> CREATOR = new Creator<CategoryModelImpl>() {
            @Override
            public CategoryModelImpl createFromParcel(Parcel source) {
                return new CategoryModelImpl(source);
            }

            @Override
            public CategoryModelImpl[] newArray(int size) {
                return new CategoryModelImpl[size];
            }
        };
    }

    private static class CategoryDomain implements Category{
        private String id;
        private String name;
        private int path;

        CategoryDomain(String id, String name, int path) {
            this.id = id;
            this.name = name;
            this.path = path;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getId() {
            return id;
        }

        @Override
        public int getPath() {
            return 0;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeInt(this.path);
        }

        CategoryDomain(Parcel in) {
            this.id = in.readString();
            this.name = in.readString();
            this.path = in.readInt();
        }

        public static final Creator<CategoryDomain> CREATOR = new Creator<CategoryDomain>() {
            @Override
            public CategoryDomain createFromParcel(Parcel source) {
                return new CategoryDomain(source);
            }

            @Override
            public CategoryDomain[] newArray(int size) {
                return new CategoryDomain[size];
            }
        };
    }
}
