import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.demo.mapper.BlogMapper;
import org.demo.model.Blog;
import org.demo.model.Comment;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
            BlogMapper mapper = session.getMapper(BlogMapper.class);
//            Blog blog = mapper.get(1);
            Blog blog = mapper.getBlogWithComments(1);

            System.out.println(blog);

            List<Comment> comments = blog.getComments();
            if(comments!=null) {
                for (Comment comment : comments) {
                    System.out.println(comment);
                }
            }
            // do work
        } finally {
            session.close();
        }
    }
}
