package org.demo;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.demo.mapper.BlogMapper;
import org.demo.model.Blog;
import org.demo.model.Comment;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pc on 2017/4/13.
 */
public class MyBatisDemo {

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

            testSelectOne(session);

        } finally {
            session.close();
        }
    }

    // ******************************************************** //
    /**
     * Test CRUD
     * @param session session
     */
    private static void testGet( SqlSession session) {
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Blog blog = mapper.get(1);
        System.out.println(blog);
    }

    private static void testAdd(SqlSession session) {
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Blog blog = new Blog();
        blog.setTitle("addTitle");
        blog.setContent("addContents");
        blog.setDate(new Timestamp(System.currentTimeMillis()));
        Integer result = mapper.add(blog);
        session.commit();
        System.out.println(result);
    }
    private static void testUpadate( SqlSession session) {
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Blog blog = mapper.get(1);
        blog.setTitle("updateTitle");
        mapper.update(blog);
        session.commit();
        blog = mapper.get(1);
        System.out.println(blog);
    }

    private static void testDelete( SqlSession session) {
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Integer result = mapper.delete(2);
        System.out.println(result);
        session.commit();
    }


    // ******************************************************** //
    /**
     * 测试 association 和 collection ResultMap
     * @param session session
     */
    private static void testAssociation( SqlSession session) {
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Blog blog = mapper.getBlogWithComments(1);
        System.out.println(blog);

        List<Comment> comments = blog.getComments();
        if(comments!=null) {
            for (Comment comment : comments) {
                System.out.println(comment);
            }
        }
    }


    // ******************************************************** //
    /**
     * 测试 SQL 构建器
     * @param session session
     */
    private static void testSelectOne( SqlSession session) {

        String sql = new SQL(){{
            SELECT("blog.FID id, blog.FTitle title, blog.FContent content, blog.FDate date, " +
                    "blog.FAuthorID author_id, " +
                    "u.FUsername author_username, u.FTruename author_truename");
            FROM(" T_Blog blog join T_User u on u.FID = blog.FAuthorid ");
            WHERE(" blog.FID = #{id} ");
        }}.toString();

        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Map<String,Object> param =  new HashMap<String,Object>();
        param.put("id", 1);

        Blog blog = mapper.selectOneBySQL(sql,param);
        System.out.println(blog);

    }

    private static void testSelectAll(SqlSession session) {
        String sql = new SQL() {{
            SELECT("select blog.FID id, blog.FTitle title, blog.FContent content, blog.FDate date, blog.FAuthorID author_id,\n" +
                    "                u.FUsername author_username, u.FTruename author_truename");
            FROM("  T_Blog blog join T_User u on u.FID = blog.FAuthorid ");
            WHERE(" blog.FID in (#{param.idList.item})");
        }}.toString();

        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("idList", Arrays.asList(new Integer[]{1}));
        List<Blog> blogList =  mapper.selectAllBySQL(sql,param);
        if(blogList!=null){
            for(Blog blog:blogList) {
                System.out.println(blog);
            }
        }
    }
}
