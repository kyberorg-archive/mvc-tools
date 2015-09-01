package net.virtalab.mvctools.parser;

import org.springframework.web.servlet.ModelAndView;

/**
 * Edit this.
 */
class ParserException extends Exception {
    private final ModelAndView mav;

    public ParserException(ModelAndView mv) {
        super();
        this.mav = mv;
    }

    public ModelAndView getResult() {
        return this.mav;
    }
}
