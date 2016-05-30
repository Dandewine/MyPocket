package com.denis.mypocket.model.mapper;


import java.util.List;


/**
 * Created by denis on 2/21/16.
 */
public interface ModelMapper<DomainModel, Model> {
    Model toModel(DomainModel domainModel);
    List<Model> toModel(List<DomainModel> domainModels);

    List<DomainModel> fromModel(List<Model> modelList);
    DomainModel fromModel(Model model);

}
