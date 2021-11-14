package ru.kl.proj.mobileControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kl.proj.dao.AuthTokenDaoImpl;
import ru.kl.proj.dao.CallsInfoDaoImpl;
import ru.kl.proj.dao.RecordDaoImpl;
import ru.kl.proj.entity.AuthToken;
import ru.kl.proj.entity.CallsInfo;
import ru.kl.proj.entity.Record;

import java.util.List;

@RestController
public class RecordController {

    @Autowired
    AuthToken authToken;

    @Autowired
    AuthTokenDaoImpl authTokenDao;

    @Autowired
    Record record;

    @Autowired
    RecordDaoImpl recordDao;

    private String RESULT = "NO MATCHING";

    @PostMapping(value = "/newAudioRecord", consumes = "application/json",
            produces = "text/plain;charset=UTF-8")
    public String postNewRecord(@RequestParam(value = "token", required = true) String token,
                                @RequestBody Record record) throws JsonProcessingException {
        System.out.println(record.getOid() + " " + record.getRecordFileName());
        authToken = authTokenDao.readByToken(token);
        if (authToken != null) {
            record.setOid(authToken.getOid());
            recordDao.create(record);
            Record resultRecord = recordDao.readByName(authToken.getOid(),
                    record.getRecordFileName());
            ObjectMapper mapper = new ObjectMapper();
            RESULT = mapper.writeValueAsString(resultRecord);
            //тут вызвать запрос в гугл для распознавания
        }
        return RESULT;
    }
}
