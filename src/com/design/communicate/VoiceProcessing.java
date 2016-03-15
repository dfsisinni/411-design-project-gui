package com.design.communicate;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import com.ibm.watson.developer_cloud.http.HttpMediaType;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

public class VoiceProcessing {
	
	public static String getString (File audio) {
		SpeechToText service = new SpeechToText();
		service.setUsernameAndPassword("6ce30912-e2be-4a99-9e7a-4e712c5d0cd5", "6XZo4JOLKy6W");

		SpeechResults transcript = service.recognize(audio, HttpMediaType.AUDIO_WAV);
		System.out.println(transcript);
		return transcript.toString();
	}
	
	public static String processAudio(URL audioURL)
	{
		SpeechToText service = new SpeechToText();
		InputStream audioIn = null;
		try {
			audioIn = audioURL.openStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		File audio = null;
		try {
			audio = File.createTempFile(audioURL.getFile(), ".wav");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		audio.deleteOnExit();
		
		SpeechResults transcript = service.recognize(audio, HttpMediaType.AUDIO_WAV);
		return transcript.toString();

	}
	
//	public static void processAudio(URL audioURL, String streamToURL)
//	{
//		// Set up
//		SpeechToText service = new SpeechToText();
//		service.setUsernameAndPassword("6ce30912-e2be-4a99-9e7a-4e712c5d0cd5", "6XZo4JOLKy6W");
//		service.setEndPoint(streamToURL);
//		
//		// Stream audio
//
//		InputStream audioIn = null;
//		try {
//			audioIn = audioURL.openStream();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		// Transcribe the audio
//		RecognizeOptions options = new RecognizeOptions().continuous(true).interimResults(false	);
//		options.contentType("audio/wav");
//		
//	
//		
//		service.recognizeUsingWebSockets(audioIn, options, new BaseRecognizeDelegate()
//		{
//			@Override
//			public void onMessage(SpeechResults speech) {
//				System.out.println(speech);
//				speech.toString();
//			}
//		});
//	}
	
	public void toVoice () {
		TextToSpeech service = new TextToSpeech();
		service.setUsernameAndPassword("6ce30912-e2be-4a99-9e7a-4e712c5d0cd5", "6XZo4JOLKy6W");

		List <Voice> voices = service.getVoices();
		System.out.println(voices);
	}
	
	
	
}