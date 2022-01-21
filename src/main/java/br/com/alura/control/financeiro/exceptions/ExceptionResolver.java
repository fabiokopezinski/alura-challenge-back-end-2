package br.com.alura.control.financeiro.exceptions;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class ExceptionResolver {

    public static String getRootException(Throwable ex) {
        return String.format("%s in class: %s Line: %s", ExceptionUtils.getRootCauseMessage(ex),
                ExceptionUtils.getRootCause(ex).getStackTrace()[0].getClassName(),
                ExceptionUtils.getRootCause(ex).getStackTrace()[0].getLineNumber());
    }
}
