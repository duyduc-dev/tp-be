package com.learn.techplatform.services.Lesson;

import com.learn.techplatform.common.enums.LessonStatus;
import com.learn.techplatform.common.enums.LessonType;
import com.learn.techplatform.common.enums.SystemStatus;
import com.learn.techplatform.common.exceptions.ApplicationException;
import com.learn.techplatform.common.restfullApi.RestAPIStatus;
import com.learn.techplatform.common.restfullApi.RestStatusMessage;
import com.learn.techplatform.common.utils.StringUtils;
import com.learn.techplatform.common.utils.UniqueID;
import com.learn.techplatform.common.validations.Validator;
import com.learn.techplatform.controllers.models.response.PagingResponse;
import com.learn.techplatform.dto_modals.LessonDTO;
import com.learn.techplatform.entities.Lesson;
import com.learn.techplatform.event.publisher.NextLessonEventPublisher;
import com.learn.techplatform.repositories.LessonRepository;
import com.learn.techplatform.services.AbstractBaseService;
import com.learn.techplatform.services.LessonQuestion.LessonQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LessonServiceImpl extends AbstractBaseService<Lesson, String> implements LessonService {
    private final LessonRepository lessonRepository;
    private final LessonQuestionService lessonQuestionService;

    private final NextLessonEventPublisher nextLessonEventPublisher;

    public LessonServiceImpl(JpaRepository<Lesson, String> genericRepository, LessonRepository lessonRepository, LessonQuestionService lessonQuestionService, NextLessonEventPublisher nextLessonEventPublisher) {
        super(genericRepository);
        this.lessonRepository = lessonRepository;
        this.lessonQuestionService = lessonQuestionService;
        this.nextLessonEventPublisher = nextLessonEventPublisher;
    }

    @Override
    public PagingResponse getPageLesson(
            int pageNumber,
            int pageSize,
            String sortField,
            boolean isAscSort,
            String searchKey,
            String courseId,
            String chapterId
    ) {

        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<LessonDTO> lessonDTOList = lessonRepository.getLessonDtoPaging(searchKey, courseId, chapterId, sortField, isAscSort, pageable);
        return new PagingResponse(lessonDTOList);
    }

    @Override
    public LessonDTO getLessonById(String id) {
        LessonDTO lessonDTO = lessonRepository.getDTOById(id);
//        if(lessonDTO.getLessonType() == LessonType.QUESTION) {
//            List<LessonQuestion> answers = this.lessonQuestionService.getByLessonId(lessonDTO.getId());
//            lessonDTO.setAnswers(answers);
//        }
        return lessonDTO;
    }

    @Override
    public LessonDTO createNewLesson(LessonDTO lessonDTO) {

        return switch (lessonDTO.getLessonType()) {
            case VIDEO -> this.newVideoLesson(lessonDTO);
            case DOCUMENT -> this.newDocumentLesson(lessonDTO);
            case QUESTION -> this.newQuestionLesson(lessonDTO);
        };
    }

    @Override
    public List<LessonDTO> getAllLessonsByCourseId(String courseId) {
        return lessonRepository.findByCourseId(courseId).stream().map(LessonDTO::new).toList();
    }

    @Override
    public String unlockNextLesson(String userId, String currentLessonId) {
        Lesson lesson = this.lessonRepository.findById(currentLessonId).orElse(null);
        Validator.notNull(lesson, RestAPIStatus.NOT_FOUND, RestStatusMessage.NEXT_LESSON_NOT_FOUND);
        lesson.setLessonStatus(LessonStatus.DONE);
        Lesson nextLesson = this.lessonRepository.findByPreviousLessonId(lesson.getId());
        if(nextLesson.isUnlocked() || nextLesson.isDone()) {
            throw new ApplicationException(RestAPIStatus.FORBIDDEN, RestStatusMessage.FORBIDDEN);
        }
        nextLesson.setLessonStatus(LessonStatus.UNLOCKED);
        this.save(lesson);
        this.save(nextLesson);
        this.nextLessonEventPublisher.publishNextLessonEvent(userId, nextLesson.getId());
        return nextLesson.getId();
    }

    private LessonDTO newVideoLesson(LessonDTO lessonDTO) {
        Lesson lesson = new Lesson();
        lesson.setId(UniqueID.getUUID());
        lesson.setTitle(lessonDTO.getTitle());
        lesson.setSlug(StringUtils.slugify(lessonDTO.getTitle()));
        lesson.setLessonType(LessonType.VIDEO);
        lesson.setChapterId(lessonDTO.getChapterId());
        lesson.setPreviousLessonId(lessonDTO.getPreviousLessonId());
        lesson.setContent(lessonDTO.getContent());
        lesson.setDocument(lessonDTO.getDocument());
        lesson.setDuration(lessonDTO.getDuration());
        lesson.setThumbnailUrl(lessonDTO.getThumbnailUrl());
        lesson.setSystemStatus(SystemStatus.ACTIVE);
        lesson.setVideoId(lessonDTO.getVideoId());
        lesson.setLessonStatus(LessonStatus.LOCKED);

        this.save(lesson);
        return new LessonDTO(lesson);
    }

    private LessonDTO newDocumentLesson(LessonDTO lessonDTO) {
        Lesson lesson = new Lesson();
        lesson.setId(UniqueID.getUUID());
        lesson.setTitle(lessonDTO.getTitle());
        lesson.setSlug(StringUtils.slugify(lessonDTO.getTitle()));
        lesson.setLessonType(LessonType.DOCUMENT);
        lesson.setChapterId(lessonDTO.getChapterId());
        lesson.setPreviousLessonId(lessonDTO.getPreviousLessonId());
        lesson.setContent(lessonDTO.getContent());
        lesson.setDocument(lessonDTO.getDocument());
        lesson.setDuration(lessonDTO.getDuration());
        lesson.setThumbnailUrl(lessonDTO.getThumbnailUrl());
        lesson.setSystemStatus(SystemStatus.ACTIVE);
        lesson.setLessonStatus(lessonDTO.getPreviousLessonId() == null ? LessonStatus.UNLOCKED : LessonStatus.LOCKED);
        this.save(lesson);
        return new LessonDTO(lesson);
    }

    private LessonDTO newQuestionLesson(LessonDTO lessonDTO) {

        return lessonDTO;
    }
}
