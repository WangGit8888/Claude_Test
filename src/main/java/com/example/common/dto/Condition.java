package com.example.common.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Condition {
   private String keyword;
   private String eventDest;
   private List<String> idList;
   private String name;
   private LocalDateTime startTime;
   private LocalDateTime endTime;
   private String workType;
   private String sectionId;
}
