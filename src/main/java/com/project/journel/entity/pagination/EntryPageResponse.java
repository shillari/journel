package com.project.journel.entity.pagination;

import java.util.List;

import com.project.journel.entity.EntryJson;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EntryPageResponse {
    private List<EntryJson> entries;
    private int totalPages;
    private long totalElements;
}
