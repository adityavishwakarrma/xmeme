package com.crio.starter.service;

import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.repository.GreetingsRepository;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GreetingsService {

  private final GreetingsRepository greetingsRepository;

  public ResponseDto getMessage(Long id) {
    return new ResponseDto(id);
  }

  private final AtomicLong counter = new AtomicLong();
  
  public ResponseDto postMeme(GreetingsEntity greetingsEntity) {
    greetingsEntity.setId(counter.incrementAndGet());
    greetingsRepository.save(greetingsEntity);
    return new ResponseDto(greetingsEntity.getId());
  }
}