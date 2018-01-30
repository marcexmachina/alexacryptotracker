# alexacryptotracker

The Jar file needed to be uploaded to Lambda can be created using the Maven command: 
```
mvn assembly:assembly -DdescriptorId=jar-with-dependencies package
```

The application ID also needs to be replaced in:
```
PriceFinderSpeechletRequestStreamHandler.java
```
