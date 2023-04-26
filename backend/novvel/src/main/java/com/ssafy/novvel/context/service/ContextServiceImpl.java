package com.ssafy.novvel.context.service;

import com.ssafy.novvel.asset.entity.Asset;
import com.ssafy.novvel.context.dto.ContextTouchsDto;
import com.ssafy.novvel.context.entity.Context;
import com.ssafy.novvel.context.repository.ContextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContextServiceImpl implements ContextService{

    private final ContextRepository contextRepository;

    @Override
    public String createContext(List<ContextTouchsDto> contextList) {

        Context context = new Context(contextList);

        context = contextRepository.save(context);

        return context.getId();
    }
}
