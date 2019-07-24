package com.epam;


import com.epam.calc.core.CalcImpl;
import com.epam.calc.interfaces.Calc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


public class App {

    private static final String PROGRAM_WILL_BE_CLOSED_IMMEDIATELY_WITH_AN_ERROR
            = "Program will be closed immediately with an error.";
    public static final String CANT_RESOLVE_GIVEN_OPERATION = "Can't resolve given operation.";
    public static final String INFINITY_MESSAGE = "When you divide by zero you will have infinity as an answer in our application. Nice try!";
    public static final String SHUT_DOWN_MSG = "Program is shutting down in usual way.";
    private static Logger logger = LoggerFactory.getLogger(App.class.getName());
    private static Calc calc = new CalcImpl();

    private static final String ANSWER_TEMPLATE =
            "number1={} number2={} operator={} result={}";

    public static void main(String[] args) {
        logger.info("The main program has been started.");
        logger.warn("There should be only 3 parameters number number and operation(+ - * /). Instead of * you can use english small letter x.");
        if (nonNull(args) && args.length >= 3) {

            double first = Double.parseDouble(args[0]);
            double second = Double.parseDouble(args[1]);
            double result;
            String operation = args[2];
            switch (operation) {
                case (Operations.ADDITION): {
                    showInfoAboutCurrentOperation("addition");
                    result = calc.addition(first, second);
                    break;
                }
                case (Operations.SUBTRACTION): {
                    showInfoAboutCurrentOperation("subtraction");
                    result = calc.subtraction(first, second);
                    break;
                }
                case (Operations.MULTIPLICATION):
                case (Operations.ALTERNATE_MULTIPLICATION): {
                    showInfoAboutCurrentOperation("multiplication");
                    result = calc.multiplication(first, second);
                    break;
                }
                case (Operations.DIVISION): {
                    showInfoAboutCurrentOperation("division");
                    result = calc.division(first, second);
                    if (Double.isInfinite(result)){
                        logger.info(INFINITY_MESSAGE);
                        logger.warn(INFINITY_MESSAGE);
                    }
                    break;
                }
                default: {
                    showMessageToAllLoggers(CANT_RESOLVE_GIVEN_OPERATION);
                    exitWithError();
                    return;
                }
            }
            logger.info(ANSWER_TEMPLATE, first, second, operation, result);
        } else if (isNull(args)) {
            logger.error("Run again but pass 3 parameters - number number and operation(+ - * /). Instead of * you can use english small letter x.");
            exitWithError();
        } else {
            logger.error("Run again but with more parameters.");
            exitWithError();
        }
        logger.info(SHUT_DOWN_MSG);
        logger.warn(SHUT_DOWN_MSG);
    }

    private static void showMessageToAllLoggers(String message) {
        logger.error(message);
        logger.info(message);
        logger.warn(message);
    }

    private static void showInfoAboutCurrentOperation(String addition) {
        logger.info("The operation is {}.", addition);
    }

    private static void exitWithError() {
        showMessageToAllLoggers(PROGRAM_WILL_BE_CLOSED_IMMEDIATELY_WITH_AN_ERROR);
    }
}
