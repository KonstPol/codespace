package com.alphabank.codespace.src.service;

import com.alphabank.codespace.src.exсeption.EmptyFieldException;
import com.alphabank.codespace.src.exсeption.NumberFieldException;
import javafx.scene.control.TextField;

import java.util.List;

public class ValidationService {
  public boolean isEmpty(List list) {
    return list.isEmpty();
  }

  public boolean isFieldsEmpty(TextField ...args) {
    for (TextField field: args) {
      if (!field.getText().equals("")) {
        return true;
      }
    }

    return false;
  }

  public boolean isFieldEmpty(TextField field) {
    return field.getText().equals("");
  }

  public boolean isNumber(TextField field) {
    try {
      Integer.parseInt(String.valueOf(field.getText()));

      return false;
    } catch (NumberFormatException e) {
      return true;
    }
  }

  public void checkFields(TextField author, TextField title, TextField publisher, TextField year, TextField pages, TextField cost) throws EmptyFieldException {
    if (isFieldEmpty(author)) {
      throw new EmptyFieldException("Вы не заполнили поле \"Author\"");
    }

    if (isFieldEmpty(title)) {
      throw new EmptyFieldException("Вы не заполнили поле \"Title\"");
    }

    if (isFieldEmpty(publisher)) {
      throw new EmptyFieldException("Вы не заполнили поле \"Publisher\"");
    }

    if (isFieldEmpty(year)) {
      throw new EmptyFieldException("Вы не заполнили поле \"Year\"");
    }

    if (isFieldEmpty(pages)) {
      throw new EmptyFieldException("Вы не заполнили поле \"Pages\"");
    }

    if (isFieldEmpty(cost)) {
      throw new EmptyFieldException("Вы не заполнили поле \"Cost\"");
    }
  }

  public void chekFieldsType(TextField author, TextField year, TextField pages, TextField cost) throws NumberFieldException {
    if (!isNumber(author)) {
      throw new NumberFieldException("Вы ввели Число в поле \"Author\"");
    }

    if (isNumber(year)) {
      throw new NumberFieldException("Вы ввели строку в числовое поле \"Year\"");
    }

    if (isNumber(pages)) {
      throw new NumberFieldException("Вы ввели строку в числовое поле \"Year\"");
    }

    if (isNumber(cost)) {
      throw new NumberFieldException("Вы ввели строку в числовое поле \"Year\"");
    }
  }

  public void chekFieldType(TextField field, String fieldName) throws NumberFieldException {
    if (isNumber(field)) {
      throw new NumberFieldException("Вы ввели строку в числовое поле" + "\"" + fieldName + "\"");
    }
  }
}
