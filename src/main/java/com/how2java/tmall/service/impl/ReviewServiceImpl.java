package com.how2java.tmall.service.impl;

import com.how2java.tmall.mapper.ReviewMapper;
import com.how2java.tmall.pojo.Review;
import com.how2java.tmall.pojo.ReviewExample;
import com.how2java.tmall.pojo.User;
import com.how2java.tmall.service.ReviewService;
import com.how2java.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewMapper reviewMapper;

    @Autowired
    UserService userService;

    @Override
    public void add(Review c) {
        reviewMapper.insert(c);
    }

    @Override
    public void delete(int id) {
        reviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Review c) {
        reviewMapper.updateByPrimaryKeySelective(c);
    }

    @Override
    public Review get(int id) {
        return reviewMapper.selectByPrimaryKey(id);
    }

    @Override
    public List list(int pid) {
        ReviewExample example = new ReviewExample();
        example.createCriteria().andPidEqualTo(pid);
        example.setOrderByClause("id desc");
        List<Review> reviews = reviewMapper.selectByExample(example);
        setUSer(reviews);

        return reviews;
    }

    @Override
    public int getCount(int pid) {
        return list(pid).size();
    }

    public void setUSer(Review review){
        User user = userService.get(review.getUid());
        review.setUser(user);
    }

    public void setUSer(List<Review> reviews){
        for ( Review re:reviews
             ) {
            setUSer(re);
        }
    }
}
