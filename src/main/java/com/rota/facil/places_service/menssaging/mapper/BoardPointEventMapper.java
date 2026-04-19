package com.rota.facil.places_service.menssaging.mapper;

import com.rota.facil.places_service.domain.enums.ActionType;
import com.rota.facil.places_service.http.dto.request.CurrentUser;
import com.rota.facil.places_service.menssaging.dto.send.BoardPointEvent;
import com.rota.facil.places_service.persistence.entities.BoardPointEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface BoardPointEventMapper {
    @Mapping(target = "userId", source = "currentUser.userId")
    @Mapping(target = "role", source = "currentUser.role")
    @Mapping(target = "actionTitle", expression = "java(currentUser.email() + actionType.getTitle() + \"ponto de embarque\" + entity.getName())")
    @Mapping(target = "resourceName", expression = "java(ResourceName.BOARD_POINT)")
    @Mapping(target = "resourceId", source = "entity.id")
    @Mapping(target = "boardId", source = "entity.id")
    BoardPointEvent map (BoardPointEntity entity, CurrentUser currentUser, ActionType actionType);
}
