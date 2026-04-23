package com.osamuharu.app.infrastructure.pesistence.adapter;

import com.osamuharu.app.infrastructure.redis.RedisBlacklistProperties;
import com.osamuharu.shared.port.MemoryPost;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisAdapter implements MemoryPost {

  private final RedisBlacklistProperties blacklistProperties;
  private final StringRedisTemplate stringRedisTemplate;

  @Override
  public void saveTokenInBlacklist(String idToken, long expiresIn) {
    if (expiresIn <= 0) {
      return;
    }

    String prefix = blacklistProperties.getTokenPrefix();

    stringRedisTemplate.opsForValue().set(prefix + idToken, "revoked", expiresIn, TimeUnit.SECONDS);
  }

  @Override
  public boolean isTokenInBlackList(String idToken) {
    String prefix = blacklistProperties.getTokenPrefix();
    String redisKey = prefix + idToken;

    return Boolean.TRUE.equals(stringRedisTemplate.hasKey(redisKey));
  }
}
