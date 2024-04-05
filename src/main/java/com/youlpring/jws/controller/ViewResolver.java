package com.youlpring.jws.controller;

import com.youlpring.tomcat.apache.coyote.http11.enums.ContentType;
import com.youlpring.tomcat.apache.coyote.http11.request.HttpRequest;
import com.youlpring.tomcat.apache.coyote.http11.response.HttpResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.nio.charset.StandardCharsets;

public class ViewResolver {

    private final static TemplateEngine templateEngine = initializeTemplateEngine();
    private static final String TEMPLATE_PREFIX = "/templates/";
    private static final String TEMPLATE_SUFFIX = ".html";

    private ViewResolver() {}

    private static TemplateEngine initializeTemplateEngine() {
        var resolver = new ClassLoaderTemplateResolver();
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resolver.setPrefix(TEMPLATE_PREFIX);
        resolver.setSuffix(TEMPLATE_SUFFIX);
        TemplateEngine engine = new TemplateEngine();
        engine.setTemplateResolver(resolver);
        return engine;
    }

    public static void execute(HttpRequest request, HttpResponse response) {
        if (response.getViewName() == null || response.getViewName().isBlank()) {
            return;
        }
        String html = viewThymeleafHtml(response.getModelAndView(), request);
        response.createResponseBody(
            html,
            ContentType.TEXT_HTML
        );
    }

    private static String viewThymeleafHtml(ModelAndView modelAndView, HttpRequest request) {
        Context context = new Context();
        for (String key : modelAndView.getModelKeys()) {
            context.setVariable(key, modelAndView.getModelValue(key));
        }
        context.setVariable("isLogin", request.isLogin());
        return templateEngine.process(modelAndView.getViewName(), context);
    }
}
