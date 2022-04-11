package org.intelycare.engine;

import com.intelycare.engine.exceptions.BadCommandException;
import com.intelycare.engine.models.commands.Command;
import com.intelycare.engine.models.commands.IndexCommand;
import com.intelycare.engine.models.commands.QueryCommand;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestUnitQueryCommandRunner {

    @Test
    public void shouldQueryCorrectly() {
        Command queryCommand = new QueryCommand();
        assertDoesNotThrow(() ->queryCommand.executeCommand("query test"));
    }

    @Test
    public void shouldThrowBadCommandException() {
        Command queryCommand = new QueryCommand();
        assertThrows( BadCommandException.class, () ->queryCommand.executeCommand("quey test"));
    }
}
