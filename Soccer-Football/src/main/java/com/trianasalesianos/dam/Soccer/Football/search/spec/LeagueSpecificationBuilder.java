package com.trianasalesianos.dam.Soccer.Football.search.spec;

import com.trianasalesianos.dam.Soccer.Football.league.model.League;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteria;
import com.trianasalesianos.dam.Soccer.Football.team.model.Team;

import java.util.List;

public class LeagueSpecificationBuilder extends GenericSpecificationBuilder<League>{

    public LeagueSpecificationBuilder(List<SearchCriteria> params) {
        super(params, Team.class);
    }


}
