package com.trianasalesianos.dam.Soccer.Football.search.spec;

import com.trianasalesianos.dam.Soccer.Football.comment.model.Comment;
import com.trianasalesianos.dam.Soccer.Football.search.util.SearchCriteria;

import java.util.List;

public class CommentSpecificationBuilder extends GenericSpecificationBuilder<Comment>{

    public CommentSpecificationBuilder(List<SearchCriteria> params) {
        super(params, Comment.class);
    }

}
