package com.dodok.honeypot.domain.stamp.service;

import com.dodok.honeypot.domain.stamp.dto.res.AllStampResDto;
import com.dodok.honeypot.domain.stamp.dto.res.StampDto;
import com.dodok.honeypot.domain.stamp.helper.StampHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StampService {
    private final StampHelper stampHelper;

    public AllStampResDto getAllStamp(){
        List<StampDto> allStamp = stampHelper.getAllStamp();
        return AllStampResDto.of(allStamp);
    }

}
