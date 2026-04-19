package com.rota.facil.places_service.menssaging.mapper;

import com.rota.facil.places_service.ResourceName;
import com.rota.facil.places_service.domain.enums.ActionType;
import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.menssaging.dto.send.InstitutionEvent;
import com.rota.facil.places_service.persistence.entities.InstitutionEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface InstitutionEventMapper {
    @Mapping(target = "userId", source = "currentUser.userId")
    @Mapping(target = "actionTitle", expression = "java(currentUser.email() + actionType.getTitle() + \"instituição\" + entity.getName())")
    @Mapping(target = "resourceName", expression = "java(ResourceName.INSTITUTION)")
    @Mapping(target = "resourceId", source = "entity.id")
    @Mapping(target = "institutionId", source = "entity.id")
    InstitutionEvent map (InstitutionEntity entity, CurrentUser currentUser, ActionType actionType);
}
