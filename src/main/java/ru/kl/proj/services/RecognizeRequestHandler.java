package ru.kl.proj.services;

import com.google.api.gax.longrunning.OperationFuture;
import com.google.api.gax.longrunning.OperationTimedPollAlgorithm;
import com.google.api.gax.retrying.RetrySettings;
import com.google.api.gax.retrying.TimedRetryAlgorithm;
import com.google.api.gax.rpc.ApiStreamObserver;
import com.google.api.gax.rpc.BidiStreamingCallable;
import com.google.api.gax.rpc.ClientStream;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.StreamController;
import com.google.cloud.speech.v1.LongRunningRecognizeMetadata;
import com.google.cloud.speech.v1.LongRunningRecognizeResponse;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognitionConfig.AudioEncoding;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.cloud.speech.v1.SpeechSettings;
import com.google.cloud.speech.v1.StreamingRecognitionConfig;
import com.google.cloud.speech.v1.StreamingRecognitionResult;
import com.google.cloud.speech.v1.StreamingRecognizeRequest;
import com.google.cloud.speech.v1.StreamingRecognizeResponse;
import com.google.cloud.speech.v1.WordInfo;
import com.google.common.util.concurrent.SettableFuture;
import com.google.protobuf.ByteString;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.TargetDataLine;
import org.threeten.bp.Duration;

public class RecognizeRequestHandler {

    /**
     * Performs non-blocking speech recognition on remote FLAC file and prints the transcription.
     *
     * @param gcsUri the path to the remote LINEAR16 audio file to transcribe.
     */
    public static void asyncRecognizeGcs(String gcsUri) throws Exception {
        // Configure polling algorithm
        SpeechSettings.Builder speechSettings = SpeechSettings.newBuilder();
        TimedRetryAlgorithm timedRetryAlgorithm =
                OperationTimedPollAlgorithm.create(
                        RetrySettings.newBuilder()
                                .setInitialRetryDelay(Duration.ofMillis(500L))
                                .setRetryDelayMultiplier(1.5)
                                .setMaxRetryDelay(Duration.ofMillis(5000L))
                                .setInitialRpcTimeout(Duration.ZERO) // ignored
                                .setRpcTimeoutMultiplier(1.0) // ignored
                                .setMaxRpcTimeout(Duration.ZERO) // ignored
                                .setTotalTimeout(Duration.ofHours(24L)) // set polling timeout to 24 hours
                                .build());
        speechSettings.longRunningRecognizeOperationSettings().setPollingAlgorithm(timedRetryAlgorithm);

        // Instantiates a client with GOOGLE_APPLICATION_CREDENTIALS
        try (SpeechClient speech = SpeechClient.create(speechSettings.build())) {

            // Configure remote file request for FLAC
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setEncoding(AudioEncoding.AMR_WB)
                            .setLanguageCode("ru-RU")
                            .setSampleRateHertz(16000)
                            .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder().setUri(gcsUri).build();

            // Use non-blocking call for getting file transcription
            OperationFuture<LongRunningRecognizeResponse, LongRunningRecognizeMetadata> response =
                    speech.longRunningRecognizeAsync(config, audio);
            while (!response.isDone()) {
                System.out.println("Waiting for response...");
                Thread.sleep(10000);
            }

            List<SpeechRecognitionResult> results = response.get().getResultsList();

            for (SpeechRecognitionResult result : results) {
                // There can be several alternative transcripts for a given chunk of speech. Just use the
                // first (most likely) one here.
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                System.out.printf("Transcription: %s\n", alternative.getTranscript());
            }
        }
    }
}
