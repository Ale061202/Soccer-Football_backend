package com.trianasalesianos.dam.Soccer.Football.search.spec;

import com.trianasalesianos.dam.Soccer.Football.post.model.Post;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteria;

import java.util.List;

public class PostSpecificationBuilder extends GenericSpecificationBuilder<Post>{

    public PostSpecificationBuilder(List<SearchCriteria> params){super(params,Post.class);}
}
