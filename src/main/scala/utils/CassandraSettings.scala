package utils

import com.datastax.spark.connector.cql.CassandraConnector
import org.apache.spark.SparkConf

object CassandraSettings {

  /**
    * Set up the keyspace and tables used for storing tweets
    * @param conf
    */
  def setUp(conf: SparkConf): Unit = {
    CassandraConnector(conf).withSessionDo { session =>
      session.execute("CREATE KEYSPACE IF NOT EXISTS twitter_streaming WITH REPLICATION = {'class': 'SimpleStrategy', 'replication_factor': 1}")
      session.execute("""
        CREATE TABLE IF NOT EXISTS twitter_streaming.tweets (
          body text,
          user_id bigint,
          user_screen_name text,
          lang text,
          created_at timestamp,
          favorite_count int,
          retweet_count int,
          tweet_id bigint,
          user_mentions list<text>,
          reply_id bigint,
          response_time text,
          hashtags list<text>,
          urls list<text>,
          sentiment text,
          PRIMARY KEY (body, user_id, tweet_id, user_screen_name, sentiment)
        )"""
      )
      session.execute("""
        CREATE TABLE IF NOT EXISTS twitter_streaming.freq (
          date timestamp,
          count counter,
          PRIMARY KEY (date)
        )"""
      )
    }
  }
}
