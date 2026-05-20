package com.osamuharu.security.adapters;

import com.osamuharu.security.ports.BlackListPort;
import com.osamuharu.security.properties.BlacklistProperties;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlackListAdapter implements BlackListPort {

  private final BlacklistProperties blacklistProperties;
  private final StringRedisTemplate stringRedisTemplate;

  @Override
  public void saveToken(String idToken, long expiresIn) {
    if (expiresIn <= 0) {
      return;
    }

    String prefix = blacklistProperties.getRedisTokenPrefix();

    stringRedisTemplate.opsForValue().set(prefix + idToken, "revoked", expiresIn, TimeUnit.SECONDS);
  }

  @Override
  public boolean hasToken(String idToken) {
    String prefix = blacklistProperties.getRedisTokenPrefix();
    String redisKey = prefix + idToken;

    return Boolean.TRUE.equals(stringRedisTemplate.hasKey(redisKey));
  }
}
