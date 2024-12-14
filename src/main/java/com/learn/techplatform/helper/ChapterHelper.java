package com.learn.techplatform.helper;

import com.learn.techplatform.common.utils.ListHelper.ListHelperFactory;
import com.learn.techplatform.dto_modals.ChapterDTO;
import com.learn.techplatform.dto_modals.LessonDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Component
public class ChapterHelper {

    public List<ChapterDTO> mappingChapterLessonDTO(String courseId, List<ChapterDTO> chapterList, List<LessonDTO> lessonDTOList, boolean showDetail) {

        return chapterList.stream()
                .filter(item -> item.getCourseId().equals(courseId))
                .peek(item -> {
                    var a = this.filterByChapterId(lessonDTOList, item.getId(), showDetail);
                    item.setLessons(a);
                })
                .toList();
    }

    private List<LessonDTO> filterByChapterId(List<LessonDTO> lessonDTOList, String chapterId, boolean showDetail) {
        return lessonDTOList.stream()
                .filter(item -> item.getChapterId() != null && item.getChapterId().equals(chapterId))
                .peek(item -> {
                    if(!showDetail) {
                        item.ignoreMainData();
                    }
                })
                .toList();
    }

    private void recursiveInsert(List<ChapterDTO> root, List<ChapterDTO> result) {
        var restChapter = new ArrayList<ChapterDTO>();
        root.forEach(item -> {
            boolean isAdded = insertChapter(result, item);
            if(!isAdded) {
                restChapter.add(item);
            }
        });
        if(!root.isEmpty()) {
            recursiveInsert(restChapter, result);
        }
    }

    private boolean insertChapter(List<ChapterDTO> result, ChapterDTO item) {
          try {
              if(item != null && item.getPreviousChapterId() == null) {
                  item.setPosition(1);
                  result.add(0, item);
                  return true;
              }
              int index = ListHelperFactory.findIndex(result, child -> {
                  assert item != null;
                  boolean isEqual = child.getId().equals(item.getPreviousChapterId());
                  if(isEqual) {
                      item.setPosition(child.getPosition() + 1);
                  }
                  return isEqual ;
              });
              if(index > -1) {
                  result.add(index + 1, item);
                  return true;
              }
              return false;
          } catch ( Exception e) {
              return false;
          }
    }

}
