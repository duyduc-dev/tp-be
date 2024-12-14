package com.learn.techplatform.services.LessonAnswer;

import com.learn.techplatform.entities.LessonAnswer;
import com.learn.techplatform.services.AbstractBaseService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class LessonAnswerServiceImpl extends AbstractBaseService<LessonAnswer, String> implements LessonAnswerService {
    public LessonAnswerServiceImpl(JpaRepository<LessonAnswer, String> genericRepository) {
        super(genericRepository);
    }
}
