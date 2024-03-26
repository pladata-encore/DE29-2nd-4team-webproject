package com.mini.emoti.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mini.emoti.model.entity.EmotionEntity;

public interface EmotionRepository extends JpaRepository<EmotionEntity, Long> {

    // public EmotionEntity findByEmotionId(Long emotionId);

    // 유저 감정 데이터
    @Query(value = "SELECT * FROM emotion WHERE email = :email", nativeQuery = true)
    List<EmotionEntity> findByEmailEmotion(@Param("email") String email);

    // 전체 감정 데이터
    @Query(value = "SELECT * FROM emotion", nativeQuery = true)
    List<EmotionEntity> findAllEmotion();

    // 오늘 우리의 기분
    // CURDATE() 로컬시간보다 1일 늦음
    // Object[] row1 = {"Happy", 10};
    @Query(value = "SELECT emotion_type, COUNT(*) as count FROM emotion WHERE DATE(created_date) = DATE(CONVERT_TZ(NOW(), 'UTC', 'Asia/Seoul')) GROUP BY emotion_type ORDER BY count DESC", nativeQuery = true)
    List<Object[]> getTodayEmotions();

    // 주간 우리의 기분 (7일)
    @Query(value = "SELECT DAYOFWEEK(created_date) as weekday, emotion_type, COUNT(*) as count FROM emotion WHERE created_date BETWEEN (DATE(CONVERT_TZ(NOW(), 'UTC', 'Asia/Seoul')) - INTERVAL 7 DAY) AND (DATE(CONVERT_TZ(NOW(), 'UTC', 'Asia/Seoul')) - INTERVAL 1 DAY) AND email = :email GROUP BY DAYOFWEEK(created_date), emotion_type ORDER BY DAYOFWEEK(created_date), count DESC", nativeQuery = true)
    List<Object[]> getLastWeeklyEmotions();

    // 주별 우리의 기분
    @Query(value = "SELECT DAYOFWEEK(DATE(created_date)) as weekday, emotion_type, COUNT(*) as count FROM emotion WHERE DATE(created_date) BETWEEN (DATE(CONVERT_TZ(NOW(), 'UTC', 'Asia/Seoul')) - INTERVAL 7 DAY) AND (DATE(CONVERT_TZ(NOW(), 'UTC', 'Asia/Seoul')) - INTERVAL 1 DAY) GROUP BY DAYOFWEEK(DATE(created_date)), emotion_type ORDER BY DAYOFWEEK(DATE(created_date))", nativeQuery = true)
    List<Object[]> getWeeklyEmotions();

    // 그룹별 가장 많은 감정데이터 - 전체
    @Query(value = "SELECT emotion_type, COUNT(*) AS emotion_cnt FROM emotion GROUP BY emotion_type HAVING emotion_cnt = (SELECT MAX(cnt) FROM (SELECT COUNT(*) AS cnt FROM emotion GROUP BY emotion_type) AS sub)", nativeQuery = true)
    List<Object[]> getMostEmotionType();

    // 개인별 가장 많은 감정데이터 - 개인
    @Query(value = "SELECT emotion_type, COUNT(*) AS emotion_cnt FROM emotion WHERE email = :email GROUP BY emotion_type HAVING emotion_cnt = (SELECT MAX(cnt) FROM (SELECT COUNT(*) AS cnt FROM emotion WHERE email = :email GROUP BY emotion_type) AS sub)", nativeQuery = true)
    List<Object[]> getMostEmotionTypeByUser(@Param("email") String email);

    // public class TodayEmotion {
    // private String emotionType;
    // private long count;

    // @Override
    // public String toString() {
    // return "EmotionSummary{" +
    // "emotionType='" + emotionType + '\'' +
    // ", count=" + count +
    // '}';
    // }

    // }

}
