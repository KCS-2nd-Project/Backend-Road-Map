package ncnk.make.backendroadmap.domain.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/practice")
public class PracticeController {

    @GetMapping()
    public String webCompiler() {
        return "ide";
    }


}
