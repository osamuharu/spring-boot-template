package com.osamuharu.core.redis;

import com.osamuharu.shared.provider.MemoryProvider;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisProvider implements MemoryProvider {

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
