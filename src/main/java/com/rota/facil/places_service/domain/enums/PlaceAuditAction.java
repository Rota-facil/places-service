package com.rota.facil.places_service.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlaceAuditAction {
    INSTITUTION_CREATED("CREATE", "%s criou a instituicao %s"),
    INSTITUTION_UPDATED("UPDATE", "%s atualizou a instituicao %s"),
    INSTITUTION_DELETED("DELETE", "%s deletou a instituicao %s"),
    BOARD_POINT_CREATED("CREATE", "%s criou o ponto de embarque %s"),
    BOARD_POINT_UPDATED("UPDATE", "%s atualizou o ponto de embarque %s"),
    BOARD_POINT_DELETED("DELETE", "%s deletou o ponto de embarque %s");

    private final String actionType;
    private final String titleTemplate;

    public String title(String actorEmail, String resourceName) {
        return this.titleTemplate.formatted(actorEmail, resourceName);
    }
}
