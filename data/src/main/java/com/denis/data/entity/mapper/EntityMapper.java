package com.denis.data.entity.mapper;

import java.util.List;

/**
 * Created by denis on 2/21/16.
 */
public interface EntityMapper<Entity, DomainModel> {
    Entity toEntity(DomainModel model);
    List<Entity> toEntity(List<DomainModel> models);

    DomainModel fromEntity(Entity model);
    List<DomainModel> fromEntity(List<Entity> models);
}
