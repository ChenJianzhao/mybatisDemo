package org.demo.mapper;

import org.demo.model.Blog;

/**
 * Created by pc on 2017/4/13.
 */
public interface BlogMapper extends MapperBase<Blog>{

    Blog getBlogWithComments(Integer id);
}
