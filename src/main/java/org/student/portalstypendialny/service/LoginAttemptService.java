package org.student.portalstypendialny.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Service
public class LoginAttemptService {

    private final int MAX_ATTEMPTS = 5;

    private LoadingCache<String, Integer> attemptCache;

    public LoginAttemptService() {
        super();
        attemptCache = CacheBuilder.newBuilder().
                expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>() {
            public Integer load(String key) {
                return 0;
            }
        });
    }

    public void loginFailed(String remoteAddress) {
        int attempts;
        try {
            attempts = attemptCache.get(remoteAddress);

        } catch (ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptCache.put(remoteAddress,attempts);
    }

    public void loginSucceeded(String remoteAddress) {
        attemptCache.invalidate(remoteAddress);
    }


    boolean isBlocked(String remoteAdress) {
        try {
            return attemptCache.get(remoteAdress) >=MAX_ATTEMPTS;
        } catch (ExecutionException e) {
            return false;
        }
    }

}
