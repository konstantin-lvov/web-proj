package ru.kl.proj.mobileControllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.kl.proj.dao.AuthTokenDaoImpl;
import ru.kl.proj.dao.RecordDaoImpl;
import ru.kl.proj.entity.AuthToken;
import ru.kl.proj.entity.AudioRecord;
import ru.kl.proj.services.RecognizeRequestHandler;

import java.io.IOException;

@RestController
public class RecordController {

    public static Logger logger = LoggerFactory.getLogger(RecordController.class);

    @Autowired
    AuthToken authToken;

    @Autowired
    AuthTokenDaoImpl authTokenDao;

    @Autowired
    AudioRecord audioRecord;

    @Autowired
    RecordDaoImpl recordDao;

    Thread t;

    private String RESULT = "NO MATCHING";
    private String GCS_URL = "gs://summaryapp/";
    private String RECORD_FILE_NAME = "";

    @PostMapping(value = "/newAudioRecord", consumes = "application/json",
            produces = "text/plain;charset=UTF-8")
    public String postNewRecord(@RequestParam(value = "token", required = true) String token,
                                @RequestBody AudioRecord audioRecord) throws JsonProcessingException {
        System.out.println(audioRecord.getOid() + " " + audioRecord.getRecordFileName());
        authToken = authTokenDao.readByToken(token);
        if (authToken != null) {
            RECORD_FILE_NAME = audioRecord.getRecordFileName();
            audioRecord.setOid(authToken.getOid());
            recordDao.create(audioRecord);
            AudioRecord resultAudioRecord = recordDao.readByName(audioRecord.getOid(),
                    audioRecord.getRecordFileName());
            ObjectMapper mapper = new ObjectMapper();
            RESULT = mapper.writeValueAsString(resultAudioRecord);

            t = new Thread() {
                public void run() {
                    try {
                        RecognizeRequestHandler.asyncRecognizeGcs(GCS_URL + RECORD_FILE_NAME, audioRecord.getOid());
                    } catch (Exception e) {
                        logger.info(e.getMessage());
                    }
                    while (t != null) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                        }
                    }
                }

            };
            t.start();
            this.stopThread();
        }
        return RESULT;
    }

    public void stopThread(){
        t = null;
    }
}
