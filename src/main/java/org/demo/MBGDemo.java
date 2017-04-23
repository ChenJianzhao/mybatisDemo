package org.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.demo.mapper.mbg.BlogMapper;
import org.demo.model.mbg.Blog;
import org.demo.model.mbg.BlogExample;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by jzchen on 2017/4/23.
 */
public class MBGDemo {

    public static void main(String[] args) {

        String resource = "./mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            testInsert(session);

        } finally {
            session.close();
        }
    }

    public static void testInsert(SqlSession session) {

        BlogMapper mapper = session.getMapper(BlogMapper.class);
        BlogExample example = new BlogExample();
        example.createCriteria()
//                .andTitleLike("title")
                .andDateLessThan(new Date(System.currentTimeMillis()));
        example.or()
                .andContentLike("content");

        List<Blog> list = mapper.selectByExample(example);
        for(Blog blog : list) {
            System.out.println( blog.getTitle() + " | " + blog.getContent() + " | " + blog.getDate());
        }
    }
}
