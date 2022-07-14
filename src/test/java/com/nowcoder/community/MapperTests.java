package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTests {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void testSelectUser(){
        User user = userMapper.selectById(101);
        System.out.println(user);

        user = userMapper.selectByName("liubei");
        System.out.println(user);

        user = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user);
    }

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUsername("test");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("test@qq.com");
        user.setHeaderUrl("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());

        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());   //150/158
    }

    @Test
    public void updateUser(){
        int rows = userMapper.updateStatus(158,1);
        System.out.println(rows);

        rows = userMapper.updateHeader(158,"http://www.nowcoder.com/102.png");
        System.out.println(rows);

        rows= userMapper.updatePassword(158,"hello");
        System.out.println(rows);

    }

    @Test
    public void testSelectPosts(){
        // userId==0,不把其拼接到sql中，如果是其他值，则拼接到方法中，在个人中心中可以使用
        // List<DiscussPost> list = discussPostMapper.selectDiscussPosts(0,0,10);
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(149,0,10);
        for(DiscussPost post : list){
            System.out.println(post);
        }

//      int rows = discussPostMapper.selectDiscussPostRows(0);
        int rows = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(rows);
    }
}
