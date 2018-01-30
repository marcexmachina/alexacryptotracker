package main.java;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.*;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.amazon.speech.ui.SimpleCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

/**
 * Created by marconeill on 30/01/2018.
 */
public class PriceFinderSpeechlet implements Speechlet {

    static final Logger logger = LogManager.getLogger(PriceFinderSpeechlet.class);
    private MarketClient client = new MarketClient();

    public void onSessionStarted(SessionStartedRequest sessionStartedRequest, Session session) throws SpeechletException {

    }

    public SpeechletResponse onLaunch(LaunchRequest launchRequest, Session session) throws SpeechletException {
        return null;
    }

    public SpeechletResponse onIntent(IntentRequest intentRequest, Session session) throws SpeechletException {
        Intent intent = intentRequest.getIntent();
        String intentName = (intent != null) ? intent.getName() : null;

        if("GetPrice".equals(intentName)) {
            logger.debug("INTENT:: " + intentName);
            Slot instrumentSlot = intentRequest.getIntent().getSlot("Instrument");
            if(instrumentSlot != null) {
                logger.debug("SLOT NOT NULL");
                logger.debug("SLOT VALUE::" + instrumentSlot.getValue());
            } else {
                logger.debug("SLOT:: NULL");
            }
            String instrument = instrumentSlot.getValue();
            return getPriceResponse(instrument);
        } else {
            throw new SpeechletException("Invalid Intent");
        }
    }

    public void onSessionEnded(SessionEndedRequest sessionEndedRequest, Session session) throws SpeechletException {

    }

    private SpeechletResponse getPriceResponse(String instrument) {

        String price = "";
        try {
            price = client.getPrice(instrument);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String speechText = "The price of " + instrument + " is " + price;

        SimpleCard card = new SimpleCard();
        card.setTitle("Crypto Tracker");
        card.setContent(speechText);

        PlainTextOutputSpeech speech = new PlainTextOutputSpeech();
        speech.setText(speechText);

        return SpeechletResponse.newTellResponse(speech, card);
    }
}

