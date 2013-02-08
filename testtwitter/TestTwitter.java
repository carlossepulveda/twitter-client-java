/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testtwitter;

import java.util.ResourceBundle;
import java.util.logging.Logger;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

public class TestTwitter {

    private String consumerKey;
    private String consumerSecret;
    private String accessToken;
    private String accessTokenSecret;

    public TestTwitter () {
        ResourceBundle properties = ResourceBundle.getBundle("properties."+this.getClass().getName());
        consumerKey = properties.getString("consumerKey");
        consumerSecret = properties.getString("consumerSecret");
        accessToken = properties.getString("accessToken");
        accessTokenSecret = properties.getString("accessTokenSecret");
    }
 
    public boolean publish(String message){

       Twitter twitter = null;
       try {
           try {
               ConfigurationBuilder cb = new ConfigurationBuilder();
                cb.setDebugEnabled(true)
                  .setOAuthConsumerKey(this.consumerKey)
                  .setOAuthConsumerSecret(this.consumerSecret)
                  .setOAuthAccessToken(this.accessToken)
                  .setOAuthAccessTokenSecret(this.accessTokenSecret);
                TwitterFactory tf = new TwitterFactory(cb.build());
                twitter = tf.getInstance();


           } catch (IllegalStateException ie) {
               System.err.println("Oauth error : TwitterApplication Class - publish Method : \n"+ie.getMessage());
               return false;
           }
           Status status = twitter.updateStatus(message);
       } catch (TwitterException te) {
           te.printStackTrace();
           return false;
       }
       return true;
       
    }


}

