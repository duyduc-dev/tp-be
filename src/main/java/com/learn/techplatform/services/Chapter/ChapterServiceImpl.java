package com.learn.techplatform.services.Chapter;

import com.learn.techplatform.common.enums.SystemStatus;
import com.learn.techplatform.common.restfullApi.RestAPIStatus;
import com.learn.techplatform.common.restfullApi.RestStatusMessage;
import com.learn.techplatform.common.utils.UniqueID;
import com.learn.techplatform.common.validations.Validator;
import com.learn.techplatform.controllers.models.response.PagingResponse;
import com.learn.techplatform.dto_modals.ChapterDTO;
import com.learn.techplatform.entities.Chapter;
import com.learn.techplatform.entities.Course;
import com.learn.techplatform.repositories.ChapterRepository;
import com.learn.techplatform.services.AbstractBaseService;
import com.learn.techplatform.services.Course.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ChapterServiceImpl extends AbstractBaseService<Chapter, String> implements ChapterService {
    @Autowired
    ChapterRepository chapterRepository;

    public ChapterServiceImpl(JpaRepository<Chapter, String> genericRepository) {
        super(genericRepository);
    }

    @Override
    public PagingResponse getPage(int pageNumber, int pageSize, Sort.Direction sortType, String searchKey, String courseId, String courseSlug) {
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(sortType, "createdDate"));
        Sort sort = Sort.by(orders);
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, sort);
        PagingResponse pagingResponse = new PagingResponse(chapterRepository.getPage("%"+ searchKey + "%",courseId, courseSlug,  pageable));
        return pagingResponse;
    }

    @Override
    public ChapterDTO createChapter(ChapterDTO newChapter) {
        Chapter chapterSaved = Chapter.builder()
                .previousChapterId(newChapter.getPreviousChapterId())
                .id(UniqueID.getUUID())
                .title(newChapter.getTitle())
                .courseId(newChapter.getCourseId())
                .systemStatus(SystemStatus.ACTIVE)
                .build();

        Chapter prevChapter = chapterRepository.findFirstByPreviousChapterIdAndCourseIdAndSystemStatus(chapterSaved.getPreviousChapterId(), chapterSaved.getCourseId(), SystemStatus.ACTIVE);
        if(prevChapter != null) {
            prevChapter.setPreviousChapterId(chapterSaved.getId());
            this.save(prevChapter);
        }
        this.save(chapterSaved);
        return new ChapterDTO(chapterSaved);
    }

    @Override
    public List<ChapterDTO> getChapterList(String courseId, SystemStatus status) {
        return chapterRepository.findByCourseIdAndSystemStatus(courseId, status).stream().map(ChapterDTO::new).toList();
    }
}
