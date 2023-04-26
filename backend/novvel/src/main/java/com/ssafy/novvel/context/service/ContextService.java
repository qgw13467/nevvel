package com.ssafy.novvel.context.service;

import com.ssafy.novvel.context.dto.ContextTouchsDto;

import java.util.List;

public interface ContextService {
    String createContext(List<ContextTouchsDto> contextList);
}
