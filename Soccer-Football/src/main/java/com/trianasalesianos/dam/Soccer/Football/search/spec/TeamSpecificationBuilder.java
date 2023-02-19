package com.trianasalesianos.dam.Soccer.Football.search.spec;

import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteria;
import com.trianasalesianos.dam.Soccer.Football.team.model.Team;

import java.util.List;

public class TeamSpecificationBuilder extends GenericSpecificationBuilder<Team>{

    public TeamSpecificationBuilder(List<SearchCriteria> params) {
        super(params, Team.class);
    }

}
