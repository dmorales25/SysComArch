package com.lextersoft.cucumber.stepdefs;

import com.lextersoft.SysComArchApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = SysComArchApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
