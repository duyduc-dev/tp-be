package com.learn.techplatform.repositories;

import com.learn.techplatform.entities.LessonAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonAnswerRepository extends JpaRepository<LessonAnswer, String> {
}
