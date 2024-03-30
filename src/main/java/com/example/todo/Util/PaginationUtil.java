package com.example.todo.Util;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.Map;

public class PaginationUtil {
    public static Map<String, Integer> Pagination(Page<?> page){
        int currentPage = page.getNumber()+1;
        int totalPages = page.getTotalPages();
        int blockLimit = 5;

        Map<String, Integer> pageInfo = new HashMap<>();
        int startPage = Math.max(1, currentPage - blockLimit/2);
        int endPage = Math.min(startPage + blockLimit-1, totalPages);
        int prevPage = Math.max(1, currentPage-1);
        int nextPage = Math.min(totalPages, currentPage+1);
        int lastPage = totalPages;

        pageInfo.put("startPage", startPage);
        pageInfo.put("endPage", endPage);
        pageInfo.put("prevPage", prevPage);
        pageInfo.put("currentPage", currentPage);
        pageInfo.put("nextPage", nextPage);
        pageInfo.put("lastPage", lastPage);

        return pageInfo;
    }
}
