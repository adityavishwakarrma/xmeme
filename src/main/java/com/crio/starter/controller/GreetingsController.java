package com.crio.starter.controller;

import com.crio.starter.data.GreetingsEntity;
import com.crio.starter.exchange.ResponseDto;
import com.crio.starter.service.GreetingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GreetingsController {

  private final GreetingsService greetingsService;

  @GetMapping("/say-hello")
  public ResponseDto sayHello(@RequestParam long id) {
    return greetingsService.getMessage(id);
  }


  @PostMapping("/memes/")
  public ResponseEntity<ResponseDto> postMeme(@RequestBody GreetingsEntity greetingsEntity) {

    String name = greetingsEntity.getName();
    String url =  greetingsEntity.getUrl();
    String caption = greetingsEntity.getCaption();
    if(name==null && url==null && caption==null){
      return ResponseEntity.status(400).body(null);
    }

    if (greetingsService.findByName(name) != null || greetingsService.findByUrl(url) != null || greetingsService.findByCaption(caption) != null) {
      return ResponseEntity.status(409).body(null);     //duplicate data sends 409 status
    } else {
    ResponseDto response = greetingsService.postMeme(greetingsEntity);
    return ResponseEntity.ok().body(response);          //post a meme
    }
  }


}
