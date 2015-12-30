# Spark Streaming Twitter App [![Build Status](https://travis-ci.org/t3g7/spark-streaming-twitter.svg)](https://travis-ci.org/t3g7/spark-streaming-twitter) [![Codacy Badge](https://api.codacy.com/project/badge/grade/cdc422e76c8a4a5698642a2fc421e1d1)](https://www.codacy.com/app/b-fovet/spark-streaming-twitter)

### Configuration
Import Twitter API credentials into `src/main/resources/twitter_credentials.txt` or pass them as arguments when launching the jar.

	Usage: TwitterStreamingApp --consumerKey <consumer key> --consumerSecret <consumer secret> --accessToken <access token> --accessTokenSecret <access token secret>
       	   Or set credentials in text file

### Building
Create the JAR with:

	sbt -J-Xms2048m -J-Xmx4096m assembly
	
or to skip tests:

	sbt 'set test in assembly := {}' -J-Xms2048m -J-Xmx4096m clean assembly 
	
The jar file is now located in `target/scala-2.10/spark-streaming-twitter-assembly-$VERSION.jar`.

### Running the app
Note: a Cassandra instance must be running.
From the ```$SPARK_HOME``` folder, run the following:

    ./bin/spark-submit --class TwitterStreamingApp $PATH_TO_JAR/spark-streaming-twitter-assembly-$VERSION.jar
    
or add arguments `<consumer key> <consumer secret> <access token> <access token secret>` at runtime.