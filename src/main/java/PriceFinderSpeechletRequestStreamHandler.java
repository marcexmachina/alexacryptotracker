package main.java;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by marconeill on 30/01/2018.
 */
public class PriceFinderSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {

    private static final Set<String> supportedApplicationIds = new HashSet<String>();

    static {
        supportedApplicationIds.add("<APP ID HERE>");
    }

    public PriceFinderSpeechletRequestStreamHandler() {
        super(new PriceFinderSpeechlet(), supportedApplicationIds);
    }
}
