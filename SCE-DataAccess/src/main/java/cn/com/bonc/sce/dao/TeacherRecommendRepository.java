package cn.com.bonc.sce.dao;

import cn.com.bonc.sce.entity.TeacherRecommend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author yuehaibo
 * @version 0.1
 * @since 2018/12/14 14:26
 */
@Repository
public interface TeacherRecommendRepository extends JpaRepository< TeacherRecommend, Long > {

}