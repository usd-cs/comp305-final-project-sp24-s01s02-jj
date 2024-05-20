package edu.sandiego.comp305.sp24.schoolSim.controller;

import edu.sandiego.comp305.sp24.schoolSim.model.DatabaseTable;
import edu.sandiego.comp305.sp24.schoolSim.model.PersonTest;
import edu.sandiego.comp305.sp24.schoolSim.service.EmployeeTable;
import edu.sandiego.comp305.sp24.schoolSim.service.PersonTable;
import edu.sandiego.comp305.sp24.schoolSim.view.WebForm;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindingResult;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonFormControllerTest {
    @Test
    void getTableFromKeyBadkey() {
        PersonFormController controller = new PersonFormController();
        DatabaseTable table = controller.getTableFromKey(Optional.of("fakeValue"));
        assertInstanceOf(PersonTable.class, table);
        table = controller.getTableFromKey(Optional.empty());
        assertInstanceOf(PersonTable.class, table);
    }

    @Test
    void getTableFromKeyValid() {
        PersonFormController controller = new PersonFormController();
        DatabaseTable table = controller.getTableFromKey(Optional.of("employee"));
        assertInstanceOf(EmployeeTable.class, table);
    }

    @Test
    void handleWebFormValidationError() {
        PersonFormController controller = new PersonFormController();
        WebForm form = mock(WebForm.class);
        when(form.getFormPath()).thenReturn("Expected");

        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        String actual = controller.handleWebForm(form, result);
        assertEquals("Expected", actual);
    }

    @Test
    void handleWebBuildError() {
        PersonFormController controller = new PersonFormController();
        WebForm form = mock(WebForm.class);
        when(form.getUnsuccessfulRedirect()).thenReturn("Bad Return");
        doThrow(IllegalArgumentException.class).when(form).build();

        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);

        String actual = controller.handleWebForm(form, result);
        assertEquals("Bad Return", actual);
    }

    @Test
    void successfulWebForm() {
        PersonFormController controller = new PersonFormController();
        WebForm form = mock(WebForm.class);
        when(form.getSuccessRedirect()).thenReturn("Nice");

        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);

        String actual = controller.handleWebForm(form, result);
        assertEquals("Nice", actual);
    }
}