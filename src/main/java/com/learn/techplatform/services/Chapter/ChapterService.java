package com.learn.techplatform.services.Chapter;

import com.learn.techplatform.common.enums.SystemStatus;
import com.learn.techplatform.common.enums.TransactionStatus;
import com.learn.techplatform.common.enums.TransactionType;
import com.learn.techplatform.controllers.models.response.PagingResponse;
import com.learn.techplatform.dto_modals.ChapterDTO;
import com.learn.techplatform.entities.Chapter;
import com.learn.techplatform.security.AuthUser;
import com.learn.techplatform.services.InterfaceBaseService;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ChapterService extends InterfaceBaseService<Chapter, String> {

    PagingResponse getPage(int pageNumber, int pageSize, Sort.Direction sortType, String searchKey, String courseId, String courseSlug);
    ChapterDTO createChapter(ChapterDTO chapter);

    List<ChapterDTO> getChapterList(String courseId, SystemStatus status);
}
