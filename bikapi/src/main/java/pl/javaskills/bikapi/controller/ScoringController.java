package pl.javaskills.bikapi.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.javaskills.bikapi.dto.ErrorResponse;
import pl.javaskills.bikapi.dto.ScoringRequest;
import pl.javaskills.bikapi.dto.ScoringResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;


import static pl.javaskills.bikapi.utils.ScoringUtils.*;

@RestController
@RequestMapping("scoring")
public class ScoringController {


    @PostMapping
    public ResponseEntity<?> getScoring(HttpServletRequest request, @RequestBody ScoringRequest param) {

        if(StringUtils.isBlank(request.getHeader("x-token")))
            return new ResponseEntity<>(new ErrorResponse("Missing Token"), HttpStatus.UNAUTHORIZED);

        if (Objects.isNull(param))
            return new ResponseEntity<>(new ErrorResponse("Missing Pesel or NIP"), HttpStatus.BAD_REQUEST);

        if (StringUtils.isEmpty(param.getPesel()) && StringUtils.isEmpty(param.getNip()))
            return new ResponseEntity<>(new ErrorResponse("Missing Pesel or NIP"), HttpStatus.BAD_REQUEST);

        String value = !StringUtils.isEmpty(param.getPesel()) ? param.getPesel() : param.getNip();
        if (Objects.isNull(value))
            return new ResponseEntity<>(new ErrorResponse("Missing Pesel or NIP"), HttpStatus.BAD_REQUEST);
        else if (!validate(value))
            return new ResponseEntity<>(new ErrorResponse("Wrong Pesel or NIP"), HttpStatus.BAD_REQUEST);


        return new ResponseEntity<>(new ScoringResponse(getRandomScoring(0, 600)), HttpStatus.OK);
    }
}
