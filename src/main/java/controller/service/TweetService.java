package controller.service;

import model.Entity.Tweet;
import model.repository.TweetRepository;

public class TweetService extends BaseService<Tweet,TweetRepository> {
    public TweetService() {
        super(new TweetRepository());
    }
}
