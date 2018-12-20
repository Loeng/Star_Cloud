package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.TeacherRecommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRecommendRepository extends JpaRepository< TeacherRecommend, Long > {

}