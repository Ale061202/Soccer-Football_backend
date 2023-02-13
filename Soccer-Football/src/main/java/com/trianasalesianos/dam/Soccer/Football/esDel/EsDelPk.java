package com.trianasalesianos.dam.Soccer.Football.esDel;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Embeddable
public class EsDelPk {
    private final long serialVersionUID = 8682909319466153524L;

    private long team_id;
    private long user_id;

}
