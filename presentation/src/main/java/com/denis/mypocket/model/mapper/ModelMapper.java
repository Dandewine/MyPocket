package com.denis.mypocket.model.mapper;


import java.util.List;


/**
 * Created by denis on 2/21/16.
 */
public interface ModelMapper<DomainModel, Model> {
    Model transform(DomainModel domainModel);
    List<Model> transform(List<DomainModel> domainModels);
}
