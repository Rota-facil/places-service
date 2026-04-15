package com.rota.facil.places_service.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ActionType {
    CREATE(" criou "),
    UPDATE(" atualizou "),
    DELETE(" deletou ");

    private final String title;
}
