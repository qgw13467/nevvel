package com.ssafy.novvel.transactionhistory.controller;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import com.ssafy.novvel.transactionhistory.dto.PointChargeDto;
import com.ssafy.novvel.transactionhistory.service.TransactionHistoryService;
import com.ssafy.novvel.util.token.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class TransactionHistoryController {

    //생성자로 rest api key와 secret을 입력해서 토큰 바로생성.
    @Value("${portone.imp_key}")
    private String imp_key;

    @Value("${portone.imp_secret}")
    private String imp_secret;

    private final TransactionHistoryService historyService;

    //포트원 결제 내역에서 해당 uid를 가진 결제 내역 가지고 오기
    //환불이나 결제 취소나 결제 에러는 진짜 결제 할 때 구현한다는 생각으로 db 구조 바꾸진 않음...(실제라면 uid도 db 저장 해야할듯)
    public IamportResponse<Payment> paymentLookup(String impUid) throws IamportResponseException, IOException{
        IamportClient iamportClientApi = new IamportClient(imp_key, imp_secret);
        return iamportClientApi.paymentByImpUid(impUid);
    }

    @PostMapping("/point-charge")
    public ResponseEntity<?> chargePoint(@RequestBody PointChargeDto pointChargeDto,
                                         @AuthenticationPrincipal CustomUserDetails customUserDetails)
                                         throws IamportResponseException, IOException {

        IamportResponse<Payment> irsp = paymentLookup(pointChargeDto.getImpUid());
        historyService.verifyIamportService(customUserDetails.getMember(), irsp, pointChargeDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
